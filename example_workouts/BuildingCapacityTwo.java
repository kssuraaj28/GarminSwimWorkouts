import java.util.Arrays;

public class BuildingCapacityTwo {
    public static SwimWorkout workout() {
        var warmup = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 35), 12, true,
                        "free",
                        "Work Bilateral"),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 35), 8, false,
                        "free pull",
                        ""),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 35), 4, false,
                        "kick",
                        ""),
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 35), 4, true,
                        "free",
                        "Strong kick!"));

        var main = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(6, new SwimWorkout.SwimWorkoutStep.Rest(true, 50), 8, true, "free",
                        "Work UW. Aerobic"),
                new SwimWorkout.SwimWorkoutStep(4, new SwimWorkout.SwimWorkoutStep.Rest(false, 70), 2, true, "free",
                        "Sprint!"));

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
