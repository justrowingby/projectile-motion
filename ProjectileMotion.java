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

        String input1 = in.next();
        String input2 = in.next();
        String input3 = in.next();

        BigDecimal horizDis = new BigDecimal(input1);
        BigDecimal time = new BigDecimal(input2);
        BigDecimal gravity = new BigDecimal(input3);

        double dx = horizDis.doubleValue();
        double dt = time.doubleValue();
        double g = -1 * gravity.doubleValue();

        if(input1.indexOf('.') == -1){ //if a decimal is not present in the string
            horizDis = horizDis.stripTrailingZeros();
        }
        if(input2.indexOf('.') == -1){ //if a decimal is not present in the string
            time = time.stripTrailingZeros();
        }
        if(input3.indexOf('.') == -1){ //if a decimal is not present in the string
            gravity = gravity.stripTrailingZeros();
        }

        int[] precision = {horizDis.precision(), time.precision(), gravity.precision()};
        int truePrecision = Integer.MAX_VALUE;
        for(int p : precision){
            if(p < truePrecision){
                truePrecision = p;
            }
        }

        double[] results = calculations(dx, dt, g);
        System.out.println("Initial velocity: " + new BigDecimal(results[0], new MathContext(truePrecision, RoundingMode.HALF_UP)));
        System.out.println("Angle of launch in radians: " + new BigDecimal(results[1], new MathContext(truePrecision, RoundingMode.HALF_UP)));
        System.out.println("Maximum height: " + new BigDecimal(results[2], new MathContext(truePrecision, RoundingMode.HALF_UP)));
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
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
