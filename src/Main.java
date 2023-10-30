import java.util.Arrays;
import java.util.Scanner;

/**
 * This class contains the main method to interact with the InteriorPointAlgorithm class.
 * It allows the user to input the problem parameters and execute the Interior-Point algorithm.
 */

public class Main {
    private static final double ALPHA05 = 0.5;
    private static final double ALPHA09 = 0.9;

    /**
     * The main method for running the Interior-Point algorithm for linear programming problems.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        // Input and algorithm execution code

        System.out.println("Enter the number of variables: ");
        int n = Integer.parseInt(in.nextLine());
        System.out.println("Enter the number of constraints: ");
        int m = Integer.parseInt(in.nextLine());
        System.out.println("Enter the vector c: ");
        double[] C = Arrays.stream(in.nextLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        double[][] A = new double[m][n];
        System.out.println("Enter the constraints: ");
        for (int i = 0; i < m; ++i) {
            A[i] = Arrays.stream(in.nextLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        }
        System.out.println("Enter the vector b: ");
        double[] B = Arrays.stream(in.nextLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        System.out.println("Enter the approximation accuracy: ");
        double epsilon = Double.parseDouble(in.nextLine());

        System.out.println("Input trial solution? 1(yes) 2(no): ");
        int mode = Integer.parseInt(in.nextLine());
        if (mode == 1) {
            System.out.println("Enter the trial solution: ");
            double[] xTrial = Arrays.stream(in.nextLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            InteriorPointAlgorithm solver05 = new InteriorPointAlgorithm(A, B, C, xTrial, ALPHA05, epsilon);
            InteriorPointAlgorithm solver09 = new InteriorPointAlgorithm(A, B, C, xTrial, ALPHA09, epsilon);
            System.out.println("Maximization result with alpha=0.5: " + solver05.maximize());
            System.out.println("Minimization result with alpha=0.5: " + solver05.minimize());
            System.out.println("Maximization result with alpha=0.9: " + solver09.maximize());
            System.out.println("Minimization result with alpha=0.9: " + solver09.minimize());
        } else if (mode == 2) {
            InteriorPointAlgorithm solver05 = new InteriorPointAlgorithm(A, B, C, ALPHA05, epsilon);
            InteriorPointAlgorithm solver09 = new InteriorPointAlgorithm(A, B, C, ALPHA09, epsilon);
            System.out.println("Maximization result with alpha=0.5: " + solver05.maximize());
            System.out.println("Minimization result with alpha=0.5: " + solver05.minimize());
            System.out.println("Maximization result with alpha=0.9: " + solver09.maximize());
            System.out.println("Minimization result with alpha=0.9: " + solver09.minimize());
        }
    }
}
