import java.util.Arrays;

public class Example {
    public static void main(String[] args) {
        var workout = new SwimWorkout(
                "Test",
                Arrays.asList(
                        new SwimWorkout.SwimWorkoutStep(4, new SwimWorkout.SwimWorkoutStep.Rest(true, 20), 4, true, "free", ""),
                        new SwimWorkout.SwimWorkoutStep(4, new SwimWorkout.SwimWorkoutStep.Rest(false, 20), 4, true, "free", "")),
                Arrays.asList(
                        new SwimWorkout.SwimWorkoutStep(4, new SwimWorkout.SwimWorkoutStep.Rest(false, 20), 4, true, "free", "")),
                Arrays.asList(
                        new SwimWorkout.SwimWorkoutStep(4, new SwimWorkout.SwimWorkoutStep.Rest(false, 20), 4, true, "free", "")),
                SwimWorkout.Pool.SCY);
        System.out.println(workout);
        EncodeSwimWorkout.EncodeToFit(workout);
    }
}
