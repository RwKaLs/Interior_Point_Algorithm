import org.apache.commons.math3.linear.*;

import java.util.Arrays;

class InteriorPointAlgorithm {
    private final double[][] A;
    private final double[] B;
    private final double[] C;
    private final double[] xTrial;
    private final double alpha;
    private final double epsilon;

    public InteriorPointAlgorithm(double[][] A, double[] B, double[] C, double[] xTrial, double alpha, double epsilon) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.xTrial = xTrial;
        this.alpha = alpha;
        this.epsilon = epsilon;
    }

    public double maximize() {
        return solve(C);
    }

    public double minimize() {
        double[] negC = Arrays.stream(C).map(c -> -c).toArray();
        return -solve(negC);
    }

    private double solve(double[] c) {
        int n = c.length;
        RealMatrix AMatrix = MatrixUtils.createRealMatrix(A);
        RealVector cVector = MatrixUtils.createRealVector(c);
        double[] x = xTrial;

//        // crutch -\_/-
//        double[] x = new double[n];
//        Arrays.fill(x, 2*epsilon);
//        double difference = Arrays.stream(c).sum() - c[n-1] + 2*epsilon;
//        x[n-1] = difference / c[n-1];
        RealVector v;
        RealMatrix D;
        RealMatrix A_tilda;
        RealVector c_tilda;
        RealVector cp;
        RealMatrix P;
        RealMatrix F;
        RealMatrix FI;
        RealMatrix H;
        RealMatrix HAt;
        RealMatrix I;

        int iteration = 1;
        while (true) {
            v = MatrixUtils.createRealVector(x);
            D = MatrixUtils.createRealDiagonalMatrix(x);

            A_tilda = AMatrix.multiply(D);
            c_tilda = D.operate(cVector);

            I = MatrixUtils.createRealIdentityMatrix(n);
            F = A_tilda.multiply(A_tilda.transpose());
            FI = new LUDecomposition(F).getSolver().getInverse();
            H = A_tilda.transpose().multiply(FI);
            HAt = H.multiply(A_tilda);

            P = I.subtract(HAt);

            cp = P.operate(c_tilda);
            double nu = Math.abs(cp.getMinValue());
            if (false) {
                throw new RuntimeException("The method is not applicable!");
            }
            RealVector ones = new ArrayRealVector(n, 1.0);
            RealVector x_tilda = ones.add(cp.mapMultiply(alpha / nu));
            RealVector x_new = D.operate(x_tilda);
            if (x_new.getDistance(v) < epsilon) {
                Arrays.stream(x).forEach(i -> System.out.println(i + " "));
                return cVector.dotProduct(x_new);
            }
            if (iteration > 1000) {
                throw new RuntimeException("The problem does not have solution!");
            }
            x = x_new.toArray();
            iteration++;
        }
    }
}
