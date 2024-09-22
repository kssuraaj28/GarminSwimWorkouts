import java.util.List;
import java.util.Arrays;

/*
 * Objects of this class are your swim workouts
 */
public class SwimWorkout {

    /*
     * These are the steps of your swimworkout
     */
    public static class SwimWorkoutStep {
        public static class Rest {
            public final boolean is_fixed_rest; // On repeat? or Fixed repeat
            public final int time;

            public Rest(boolean fixed, int time) {
                this.is_fixed_rest = fixed;
                this.time = time;
            }
        };

        public final int repeats; // How many times you have to do it
        public final Rest rest;
        public final int lap_count;
        public final boolean trackable;
        public final String strokename;
        public final String notes;

        public String toString() {
            var p1 = String.format("%d * %dlp %s", repeats, lap_count, strokename);
            String sep = " @ ";
            if (rest.is_fixed_rest) {
                sep = " + ";
            }

            return p1 + sep + rest.time + "s";
        };

        public SwimWorkoutStep(int repeats,
                Rest rest,
                int lap_count,
                boolean trackable, String strokename, String notes) {
            this.repeats = repeats;
            this.rest = rest;
            this.lap_count = lap_count;
            this.trackable = trackable;
            this.strokename = strokename;
            this.notes = notes;
        }
    }

    public static enum Pool {
        SCY,
        SCM,
        LCM
    };

    // Why can't I have aliases in Java...
    public final String name;
    public final List<SwimWorkoutStep> warmup;
    public final List<SwimWorkoutStep> main;
    public final List<SwimWorkoutStep> cooldown;
    public final Pool pooltype;

    public SwimWorkout(
            String name,
            List<SwimWorkoutStep> warmup,
            List<SwimWorkoutStep> main,
            List<SwimWorkoutStep> cooldown,
            Pool pooltype) {
        this.name = name;
        this.warmup = warmup;
        this.main = main;
        this.cooldown = cooldown;
        this.pooltype = pooltype;
    }

    public static void main(String[] args) {

        var workout = new SwimWorkout(
                "Test",
                Arrays.asList(
                        new SwimWorkoutStep(4, new SwimWorkoutStep.Rest(true, 20), 4, true, "free", ""),
                        new SwimWorkoutStep(4, new SwimWorkoutStep.Rest(false, 20), 4, true, "free", "")),
                Arrays.asList(
                        new SwimWorkoutStep(4, new SwimWorkoutStep.Rest(false, 20), 4, true, "free", "")),
                Arrays.asList(
                        new SwimWorkoutStep(4, new SwimWorkoutStep.Rest(false, 20), 4, true, "free", "")),
                SwimWorkout.Pool.SCY);

        System.out.println(workout);

    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, pooltype) +
                "\n--Warmup--\n" +
                warmup.toString() +
                "\n--Main Set--\n" +
                main.toString() +
                "\n--Cooldown--\n" +
                cooldown.toString();
    }
}
