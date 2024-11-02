import java.util.Arrays;

public class BasicFree {
    public static SwimWorkout workout() {
        var warmup = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(4, new SwimWorkout.SwimWorkoutStep.Rest(true, 30), 4, true,
                        "free",
                        "5 kicks underwater"));

        var free_laps = 2;
        var back_laps = 4;

        var main = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(3, new SwimWorkout.SwimWorkoutStep.Rest(false, 60), free_laps, true,
                        "free",
                        "paddle + buoy"),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 30), back_laps, true,
                        "back",
                        "Ez"),
                new SwimWorkout.SwimWorkoutStep(3, new SwimWorkout.SwimWorkoutStep.Rest(false, 55), free_laps, true,
                        "free",
                        "paddle + buoy"),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 30), back_laps, true,
                        "back",
                        "Ez"),
                new SwimWorkout.SwimWorkoutStep(3, new SwimWorkout.SwimWorkoutStep.Rest(false, 50), free_laps, true,
                        "free",
                        "paddle + buoy"),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 30), back_laps, true,
                        "back",
                        "Ez"),
                new SwimWorkout.SwimWorkoutStep(5, new SwimWorkout.SwimWorkoutStep.Rest(true, 60), 2, false,
                        "free kick",
                        "fins + snorkel"));

        var cooldown = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(10, new SwimWorkout.SwimWorkoutStep.Rest(true, 10), 2, true, "free",
                        "HR zone down!"));

        var workout = new SwimWorkout(
                warmup,
                main,
                cooldown,
                SwimWorkout.Pool.SCY);
        return workout;
    }
}
