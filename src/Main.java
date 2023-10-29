import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final double ALPHA = 0.5;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
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
            InteriorPointAlgorithm solver = new InteriorPointAlgorithm(A, B, C, xTrial, ALPHA, epsilon);
            System.out.println("Maximization result: " + solver.maximize());
            System.out.println("Minimization result: " + solver.minimize());
        } else if (mode == 2) {
            InteriorPointAlgorithm solver = new InteriorPointAlgorithm(A, B, C, ALPHA, epsilon);
            System.out.println("Maximization result: " + solver.maximize());
            System.out.println("Minimization result: " + solver.minimize());
        }
    }
}
