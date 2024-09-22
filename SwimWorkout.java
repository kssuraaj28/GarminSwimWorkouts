import java.util.List;
import java.util.function.Function;

// TODO: Calculate yardage
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

        public String poolReadable(Pool pool) {
            int dist = 25;
            if (pool == Pool.LCM) {
                dist = 50;
            }

            var p1 = String.format("%d * %d %s", repeats, lap_count * dist, strokename);
            String sep = " @ ";
            if (rest.is_fixed_rest) {
                sep = " + ";
            }
            return p1 + sep + rest.time + "s";
        }

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

    public int totalLapCount(List<SwimWorkoutStep> inp) {
        int ret = 0;
        for (var step : inp) {
            ret += step.lap_count * step.repeats;
        }
        return ret;
    }

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

    @Override
    public String toString() {
        final var warmup_cnt = totalLapCount(warmup);
        final var main_cnt = totalLapCount(main);
        final var cool_cnt = totalLapCount(cooldown);

        Function<Integer, Integer> toUnits = (n) -> n * (pooltype == Pool.LCM ? 50 : 25);

        return String.format("%s (%s)", name, pooltype) +
                String.format("\n--Warmup(%d)--\n%s", toUnits.apply(warmup_cnt), warmup.toString()) +
                String.format("\n--Main(%d)--\n%s", toUnits.apply(main_cnt), main.toString()) +
                String.format("\n--Cooldown(%d)--\n%s", toUnits.apply(cool_cnt), cooldown.toString());
    }
}
