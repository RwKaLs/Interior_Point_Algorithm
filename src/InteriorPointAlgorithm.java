import org.apache.commons.math3.linear.*;

import java.util.Arrays;

class InteriorPointAlgorithm {
    private final double[][] A;
    private final double[] B;
    private final double[] C;
    private final double[] xTrial;
    private final boolean trialInput;
    private final double alpha;
    private final double epsilon;

    public InteriorPointAlgorithm(double[][] A, double[] B, double[] C, double alpha, double epsilon) {
        this.A = A;
        this.B = B;
        this.C = C;
        xTrial = null;
        this.alpha = alpha;
        this.epsilon = epsilon;
        trialInput = false;
    }

    public InteriorPointAlgorithm(double[][] A, double[] B, double[] C, double[] xTrial, double alpha, double epsilon) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.xTrial = xTrial;
        this.alpha = alpha;
        this.epsilon = epsilon;
        trialInput = true;
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
        double[] x = new double[n];
        if (trialInput) {
            assert xTrial != null;
            x = xTrial.clone();
        } else {
            double xNow = 100000.0;
            double curSum;
            for (int i = 0; i < A.length; ++i) {
                curSum = 0.0;
                for (int j = 0; j < A[i].length; ++j) {
                    curSum += A[i][j];
                }
                if (B[i] / curSum < xNow && B[i] / curSum > 0) {
                    xNow = B[i] / curSum;
                }
            }
            double nowSum;
            for (int i = 0; i < A.length; ++i) {
                nowSum = 0.0;
                for (int j = 0; j < A.length; ++j) {
                    nowSum += A[i][j] * xNow;
                }
                x[i] = xNow;
                x[A.length + i] = B[i] - nowSum;
            }
        }

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
            if (nu < 0) {
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
