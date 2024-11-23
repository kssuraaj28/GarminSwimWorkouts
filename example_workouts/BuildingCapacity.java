import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Arrays;

public class BuildingCapacity {
    public static SwimWorkout workout() {
        // TODO: Does the watch detect mixed stroke?
        var warmup = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 30), 4, true, "free",
                        "Work UW!"),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 30), 4, false,
                        "free kick",
                        "Use board"),
                new SwimWorkout.SwimWorkoutStep(4, new SwimWorkout.SwimWorkoutStep.Rest(true, 15), 2, true, "mixed",
                        "Fr Br Bck Fly"));

        // 2x main set
        var main1 = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 20), 2, true, "free",
                        "Aerobic HR Zone"),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 20), 4, true, "free",
                        "Aerobic HR Zone"),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 20), 6, true, "free",
                        "Aerobic HR Zone"),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 20), 8, true, "free",
                        "Aerobic HR Zone"),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 20), 6, true, "free",
                        "Aerobic HR Zone"),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 20), 4, true, "free",
                        "Aerobic HR Zone"),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 20), 2, true, "free",
                        "Aerobic HR Zone"));
        var main = Stream.concat(main1.stream(), main1.stream()).collect(Collectors.toList());

        var cooldown = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(10, new SwimWorkout.SwimWorkoutStep.Rest(true, 10), 2, true, "free",
                        "HR zone down!"));

        var workout = new SwimWorkout(
                warmup,
                main,
                cooldown,
                "Aerobic Freestyle Workout",
                SwimWorkout.Pool.SCY);
        return workout;
    }
}
