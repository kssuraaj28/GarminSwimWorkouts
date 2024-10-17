import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {
    private static SwimWorkout freeEasy() {
        var warmup = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(1, new SwimWorkout.SwimWorkoutStep.Rest(true, 35), 12, true,
                        "free",
                        "5 kicks underwater"));

        var main = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(8, new SwimWorkout.SwimWorkoutStep.Rest(true, 30), 4, true, "free",
                        "Snorkel"));

        var cooldown = Arrays.asList(
                new SwimWorkout.SwimWorkoutStep(10, new SwimWorkout.SwimWorkoutStep.Rest(true, 10), 2, true, "free",
                        "HR zone down!"));

        var workout = new SwimWorkout(
                "Freestyle Easy",
                warmup,
                main,
                cooldown,
                SwimWorkout.Pool.SCY);

        return workout;
    }

    private static SwimWorkout buildingCapacity2() {
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
                "Aerobic Free 2",
                warmup,
                main,
                cooldown,
                SwimWorkout.Pool.SCY);
        return workout;
    }

    private static SwimWorkout buildingCapacity() {
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
                "Aerobic Free",
                warmup,
                main,
                cooldown,
                SwimWorkout.Pool.SCY);
        return workout;
    }

    public static void main(String[] args) {

        // var main2 = Arrays.asList(
        // new SwimWorkout.SwimWorkoutStep(1, new
        // SwimWorkout.SwimWorkoutStep.Rest(false, 30), 1, true, "free",
        // "Ez"),
        // new SwimWorkout.SwimWorkoutStep(1, new
        // SwimWorkout.SwimWorkoutStep.Rest(false, 30), 1, true, "fly",
        // "Hard UW Kick"),
        // new SwimWorkout.SwimWorkoutStep(2, new
        // SwimWorkout.SwimWorkoutStep.Rest(false, 30), 1, true, "free",
        // "Ez"),
        // new SwimWorkout.SwimWorkoutStep(2, new
        // SwimWorkout.SwimWorkoutStep.Rest(false, 30), 1, true, "fly",
        // "Hard UW Kick"),
        // new SwimWorkout.SwimWorkoutStep(3, new
        // SwimWorkout.SwimWorkoutStep.Rest(false, 30), 1, true, "free",
        // "Ez"),
        // new SwimWorkout.SwimWorkoutStep(3, new
        // SwimWorkout.SwimWorkoutStep.Rest(false, 30), 1, true, "fly",
        // "Hard UW Kick"),
        // new SwimWorkout.SwimWorkoutStep(4, new
        // SwimWorkout.SwimWorkoutStep.Rest(false, 30), 1, true, "free",
        // "Ez"),
        // new SwimWorkout.SwimWorkoutStep(4, new
        // SwimWorkout.SwimWorkoutStep.Rest(false, 30), 1, true, "fly",
        // "Hard UW Kick"));
        // ;

        var workout = freeEasy();
        System.out.println(workout);
        EncodeSwimWorkout.EncodeToFit(workout);

        // var workout = buildingCapacity();
        // System.out.println(workout);
        // EncodeSwimWorkout.EncodeToFit(workout);
        // workout = buildingCapacity2();
        // System.out.println(workout);
        // EncodeSwimWorkout.EncodeToFit(workout);
    }
}
