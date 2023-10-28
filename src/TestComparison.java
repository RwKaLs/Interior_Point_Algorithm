import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Assert;
import org.junit.Test;

public class TestComparison {
    @Test
    public void testSolving0() {
        double[][] inC = new double[][]{
                {5, 6, 4, 7, 0, 0, 0, 0}
        };
        double[][] inB = new double[][]{
                {120, 100, 90, 80}
        };
        double[][] inA = new double[][]{
                {3, 0, 2, 0 ,1, 0, 0, 0},
                {0, 2, 0, 4, 0, 1, 0, 0},
                {2, 0, 3, 0, 0, 0, 1, 0},
                {0, 4, 0, 2, 0, 0, 0, 1}
        };
        RealMatrix c = MatrixUtils.createRealMatrix(inC);
        RealMatrix b = MatrixUtils.createRealMatrix(inB);
        RealMatrix a = MatrixUtils.createRealMatrix(inA);
        double eps = 0.01;

        double[] xTrial = {30, 10, 5, 5, 20, 60, 15, 30};
        InteriorPointAlgorithm algorithm = new InteriorPointAlgorithm(inA, inB[0], inC[0], xTrial, 0.5, eps);
        
        Assert.assertEquals(algorithm.maximize(), Simplex.maximize(c, a, b, inC[0], inA, inB[0], eps, true), eps*2);
    }
}
