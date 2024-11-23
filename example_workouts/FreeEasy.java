import java.util.Arrays;

public class FreeEasy {
    public static SwimWorkout workout() {
        var warmup = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 35), 12, true,
                        "free",
                        "5 kicks underwater"));

        var main = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(8, new SwimWorkout.SwimWorkoutStep.Rest(true, 30), 4, true, "free",
                        "Snorkel + Paddle + Pull"));

        var cooldown = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(10, new SwimWorkout.SwimWorkoutStep.Rest(true, 10), 2, true, "free",
                        "HR zone down!"));

        var workout = new SwimWorkout(
                warmup,
                main,
                cooldown,
                "Free pull workout with snorkel",
                SwimWorkout.Pool.SCY);

        return workout;
    }
}
