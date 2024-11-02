import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// God bless chatgpt

public class Example {
    public static void main(String[] args) {
        List<String> workoutClasses = getWorkoutClassNames("example_workouts");
        
        for (String className : workoutClasses) {
            try {
                // Load the class
                className = className.replace("example_workouts.","");
                System.out.println(className);
                Class<?> clazz = Class.forName(className);
                
                // Get the create method
                Method createMethod = clazz.getMethod("workout");
                
                // Invoke the create method and get the workout
                SwimWorkout workout = (SwimWorkout) createMethod.invoke(null);
                
                
                // Print the workout name and workout details
                System.out.println("Workout Name: " + className);
                System.out.println(workout);
                EncodeSwimWorkout.EncodeToFit(className,workout);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> getWorkoutClassNames(String packageName) {
        List<String> classNames = new ArrayList<>();
        String path = packageName.replace('.', '/');
        File directory = new File(path);

        if (!directory.exists() || !directory.isDirectory()) {
            return classNames;
        }

        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".java")) {
                String className = packageName + "." + file.getName().replace(".java", "");
                classNames.add(className);
            }
        }

        return classNames;
    }
}

