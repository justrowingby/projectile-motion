import java.util.Scanner;
import java.lang.Math.*;

public class ProjectileMotion{
    //inputs: time in air, horiz distance
    //outputs: initial velocity, angle of launch, max height
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Please print three space separated values:");
        System.out.println("the horizontal distance travelled, the time");
        System.out.println("it spent in the air, and the absolute value");
        System.out.println("of the gravity on your planet in m/s^2.");
        double horizDis = in.nextDouble();
        double time = in. nextDouble();
        double gravity = -1 * in.nextDouble();
        double[] results = calculations(horizDis, time, gravity);
        System.out.println(results[0] + " " + results[1] + " " + results[2]);
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
}
