import java.util.Scanner;
import java.lang.Math;
import java.math.*;

public class ProjectileMotion{
    //inputs: time in air, horiz distance
    //outputs: initial velocity, angle of launch, max height
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Please print three space separated values:");
        System.out.println("the horizontal distance travelled in meters,");
        System.out.println("the time spent in the air in seconds, and ");
        System.out.println("the absolute value of the gravity on your ");
        System.out.println("planet in m/s^2.");

        //Take the three inputs
        String input1 = in.next();
        String input2 = in.next();
        String input3 = in.next();

        BigDecimal horizDis = new BigDecimal(input1);
        BigDecimal time = new BigDecimal(input2);
        BigDecimal gravity = new BigDecimal(input3);

        double dx = horizDis.doubleValue();
        double dt = time.doubleValue();
        double g = -1 * gravity.doubleValue();

        //Strip trailing zeros if no decimal point is present in string
        if(input1.indexOf('.') == -1){ //if a decimal is not present in the string
            horizDis = horizDis.stripTrailingZeros();
        }
        if(input2.indexOf('.') == -1){ //if a decimal is not present in the string
            time = time.stripTrailingZeros();
        }
        if(input3.indexOf('.') == -1){ //if a decimal is not present in the string
            gravity = gravity.stripTrailingZeros();
        }

        int precision = findLowestInt(new int[]{horizDis.precision(), time.precision(), gravity.precision()});

        double[] results = calculations(dx, dt, g);

        System.out.println("Initial velocity: " + doubleToPrecisionString(results[0], precision));
        System.out.println("Angle of launch in radians: " + doubleToPrecisionString(results[1], precision));
        System.out.println("Maximum height: " + doubleToPrecisionString(results[2], precision));
    }
    private static double[] calculations(double dx, double dt, double g){
        double vx = dx / dt;
        double vy = 0 - g * dt/2;
        double dy = vy * dt/2 + 0.5 * g * dt/2 * dt/2;
        double theta = Math.atan(vy/vx);
        double vi = Math.sqrt(vx*vx + vy*vy);
        double[] results = new double[] {vi, theta, dy};
        return results;
    }
    private static int findLowestInt(int[] numbers){
        int fin = Integer.MAX_VALUE;
        for(int p : numbers){
            if(p < fin){
                fin = p;
            }
        }
        return fin;
    }
    private static String doubleToPrecisionString(double input, int precision){
        return new BigDecimal(input, new MathContext(precision, RoundingMode.HALF_UP)).toString();
    }
}
