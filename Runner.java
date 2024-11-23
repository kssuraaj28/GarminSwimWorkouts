import java.lang.reflect.Method;

// God bless chatgpt

public class Runner {
    public static void main(String[] args) {
        try {
            var wrkclassname = args[0];
            var fitname = args[1];
            Class<?> clazz = Class.forName(wrkclassname);
            Method createMethod = clazz.getMethod("workout");
            SwimWorkout workout = (SwimWorkout) createMethod.invoke(null);
            System.out.println(workout);
            EncodeSwimWorkout.EncodeToFit(fitname,workout);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
