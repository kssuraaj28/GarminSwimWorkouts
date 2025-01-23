/////////////////////////////////////////////////////////////////////////////////////////////
// Copyright 2024 Garmin International, Inc.
// Licensed under the Flexible and Interoperable Data Transfer (FIT) Protocol License; you
// may not use this file except in compliance with the Flexible and Interoperable Data
// Transfer (FIT) Protocol License.
/////////////////////////////////////////////////////////////////////////////////////////////
// There's some package thing that I can do

import com.garmin.fit.DateTime;
import com.garmin.fit.DisplayMeasure;
import com.garmin.fit.File;
import com.garmin.fit.FileEncoder;
import com.garmin.fit.FileIdMesg;
import com.garmin.fit.Fit;
import com.garmin.fit.FitRuntimeException;
import com.garmin.fit.Intensity;
import com.garmin.fit.Manufacturer;
import com.garmin.fit.Sport;
import com.garmin.fit.SubSport;
import com.garmin.fit.SwimStroke;
import com.garmin.fit.WktStepDuration;
import com.garmin.fit.WktStepTarget;
import com.garmin.fit.WorkoutEquipment;
import com.garmin.fit.WorkoutMesg;
import com.garmin.fit.WorkoutStepMesg;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class EncodeSwimWorkout {
    private static class WorkoutSteps extends ArrayList<WorkoutStepMesg> {
        // I don't know how the enclosing class can access these private methods..
        private WorkoutSteps add(float dist, int repeats, String name, String notes, Intensity i, SwimStroke s,
                WorkoutEquipment e,
                SwimWorkout.SwimWorkoutStep.Rest r) {

            add(CreateWorkoutStepSwim(
                    size(),
                    dist,
                    name,
                    String.format("%s\n\n%s", name, notes),
                    i,
                    s,
                    e));

            add(CreateWorkoutStepSwimRest(
                    size(),
                    r.is_fixed_rest ? WktStepDuration.TIME : WktStepDuration.REPETITION_TIME,
                    (float) r.time));

            if (repeats > 1) {
                add(CreateWorkoutStepRepeat(
                        size(),
                        size() - 2,
                        repeats));
                add(CreateWorkoutStepSwimRest(
                        size(),
                        WktStepDuration.OPEN,
                        null));
            }
            return this;
        }

    }

    public static void EncodeToFit(String wrk_name, SwimWorkout w) {
        float pool_len;
        if (w.pooltype == SwimWorkout.Pool.LCM) {
            pool_len = 50f;
        } else if (w.pooltype == SwimWorkout.Pool.SCM) {
            pool_len = 25f;
        } else {
            assert (w.pooltype == SwimWorkout.Pool.SCY);
            pool_len = 22.86f;
        }

        WorkoutSteps workoutSteps = new WorkoutSteps();

        for (int i = 0; i < 3; i++) {
            var seq = w.warmup;
            var phase = Intensity.WARMUP;
            if (i == 1) {
                seq = w.main;
                phase = Intensity.ACTIVE;
            } else if (i == 2) {
                seq = w.cooldown;
                phase = Intensity.COOLDOWN;
            }
            for (var item : seq) {
                SwimStroke s = SwimStroke.MIXED;

                if (!item.trackable) {
                    s = SwimStroke.DRILL;
                } else if (item.strokename.toLowerCase().contains("free")) {
                    s = SwimStroke.FREESTYLE;
                } else if (item.strokename.toLowerCase().contains("back")) {
                    s = SwimStroke.BACKSTROKE;
                } else if (item.strokename.toLowerCase().contains("breast")) {
                    s = SwimStroke.BREASTSTROKE;
                } else if (item.strokename.toLowerCase().contains("fly")) {
                    s = SwimStroke.BUTTERFLY;
                }

                WorkoutEquipment equip = null;
                String str = (item.strokename + item.notes).toLowerCase();
                // Fake machine learning
                if (str.contains("buoy")) {
                    equip = WorkoutEquipment.SWIM_PULL_BUOY;
                } else if (str.contains("board")) {
                    equip = WorkoutEquipment.SWIM_KICKBOARD;
                } else if (str.contains("paddle")) {
                    equip = WorkoutEquipment.SWIM_PADDLES;
                } else if (str.contains("fins")) {
                    equip = WorkoutEquipment.SWIM_FINS;
                } else if (str.contains("snorkel")) {
                    equip = WorkoutEquipment.SWIM_SNORKEL;
                }

                workoutSteps.add(item.lap_count * pool_len, item.repeats, item.poolReadable(w.pooltype),
                        item.notes,
                        phase, s, equip, item.rest);
            }
        }

        WorkoutMesg workoutMesg = new WorkoutMesg();
        workoutMesg.setWktName(wrk_name);
        workoutMesg.setNotes(
                String.format("%s\n%s",
                        w.lapsDistDesc(
                                w.totalLapCount(w.warmup) + w.totalLapCount(w.main) + w.totalLapCount(w.cooldown)),
                        w.description));
        workoutMesg.setSport(Sport.SWIMMING);
        workoutMesg.setSubSport(SubSport.LAP_SWIMMING);
        workoutMesg.setPoolLength(pool_len);
        if (w.pooltype == SwimWorkout.Pool.SCY) {
            workoutMesg.setPoolLengthUnit(DisplayMeasure.STATUTE);
        } else {
            workoutMesg.setPoolLengthUnit(DisplayMeasure.METRIC);
        }
        workoutMesg.setNumValidSteps(workoutSteps.size());

        CreateWorkout(workoutMesg, workoutSteps);
    }

    private static void CreateWorkout(WorkoutMesg workoutMesg, ArrayList<WorkoutStepMesg> workoutSteps) {
        // The combination of file type, manufacturer id, product id, and serial number
        // should be unique.
        // When available, a non-random serial number should be used.
        File filetype = File.WORKOUT;
        short manufacturerId = Manufacturer.DEVELOPMENT;
        short productId = 0;
        Random random = new Random();
        int serialNumber = random.nextInt();

        // Every FIT file MUST contain a File ID message
        FileIdMesg fileIdMesg = new FileIdMesg();
        fileIdMesg.setType(filetype);
        fileIdMesg.setManufacturer((int) manufacturerId);
        fileIdMesg.setProduct((int) productId);
        fileIdMesg.setTimeCreated(new DateTime(new Date()));
        fileIdMesg.setSerialNumber((long) serialNumber);

        // Create the output stream
        FileEncoder encode;
        String filename = workoutMesg.getWktName().replace(" ", "_") + "_workout.fit";

        try {
            encode = new FileEncoder(new java.io.File(filename), Fit.ProtocolVersion.V1_0);
        } catch (FitRuntimeException e) {
            System.err.println("Error opening file " + filename);
            e.printStackTrace();
            return;
        }

        // Write the messages to the file, in the proper sequence
        encode.write(fileIdMesg);
        encode.write(workoutMesg);

        for (WorkoutStepMesg workoutStep : workoutSteps) {
            encode.write(workoutStep);
        }

        // Close the output stream
        try {
            encode.close();
        } catch (FitRuntimeException e) {
            System.err.println("Error closing encode.");
            e.printStackTrace();
            return;
        }

        System.out.println("Encoded FIT Workout File " + filename);
    }

    private static WorkoutStepMesg CreateWorkoutStepRepeat(int messageIndex, int repeatFrom, int repetitions) {
        WorkoutStepMesg workoutStepMesg = new WorkoutStepMesg();
        workoutStepMesg.setMessageIndex((messageIndex));

        workoutStepMesg.setDurationType(WktStepDuration.REPEAT_UNTIL_STEPS_CMPLT);
        workoutStepMesg.setDurationValue((long) repeatFrom);

        workoutStepMesg.setTargetType(WktStepTarget.OPEN);
        workoutStepMesg.setTargetValue((long) repetitions);

        return workoutStepMesg;
    }

    private static WorkoutStepMesg CreateWorkoutStepSwim(int messageIndex,
            float distance,
            String name,
            String notes,
            Intensity intensity,
            SwimStroke swimStroke,
            WorkoutEquipment equipment) {

        WorkoutStepMesg workoutStepMesg = new WorkoutStepMesg();
        workoutStepMesg.setMessageIndex(messageIndex);

        if (name != null) {
            workoutStepMesg.setWktStepName(name);
        }

        if (notes != null) {
            workoutStepMesg.setNotes(notes);
        }

        workoutStepMesg.setIntensity(intensity);

        workoutStepMesg.setDurationType(WktStepDuration.DISTANCE);
        workoutStepMesg.setDurationDistance(distance);

        workoutStepMesg.setTargetType(WktStepTarget.SWIM_STROKE);

        workoutStepMesg.setTargetStrokeType(swimStroke);

        if (equipment != null) {
            workoutStepMesg.setEquipment(equipment);
        }

        return workoutStepMesg;
    }

    private static WorkoutStepMesg CreateWorkoutStepSwimRest(int messageIndex, WktStepDuration durationType,
            Float durationTime) {
        WorkoutStepMesg workoutStepMesg = new WorkoutStepMesg();
        workoutStepMesg.setMessageIndex(messageIndex);

        workoutStepMesg.setDurationType(durationType);
        workoutStepMesg.setDurationTime(durationTime);

        workoutStepMesg.setTargetType(WktStepTarget.OPEN);

        workoutStepMesg.setIntensity(Intensity.REST);

        return workoutStepMesg;
    }
}
