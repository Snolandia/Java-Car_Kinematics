package values;



import java.util.function.Function;



public class Solver{

	



	    public Solver(){ 

	    	

	    }

	    

	    public static void solve() {

	        // Define the system of equations as functions

	        Function<double[], Double> f1 = (x) -> x[0] * x[0] + x[1] * x[1] - 25;

	        Function<double[], Double> f2 = (x) -> x[0] * x[0] - x[1] - 1;



	        // Define the Jacobian matrix as a 2D array of partial derivatives

	        Function<double[], double[][]> jacobian = (x) -> new double[][]{

	                {2 * x[0], 2 * x[1]},

	                {2 * x[0], -1}

	        };



	        // Set the desired convergence criteria and maximum iterations

	        double epsilon = 1e-6;

	        int maxIterations = 100;



	        // Set the initial guess for the variables

	        double[] initialGuess = {1.0, 1.0};



	        // Perform the Newton-Raphson iterations

	        double[] solution = solveSystem(f1, f2, jacobian, epsilon, maxIterations, initialGuess);



	        // Print the solution

	        System.out.println("Solution: x = " + solution[0] + ", y = " + solution[1]);

	    }


	    

	   


	    public static double[] solveSystem(Function<double[], Double> f1, Function<double[], Double> f2,

	                                       Function<double[], double[][]> jacobian, double epsilon, int maxIterations,

	                                       double[] initialGuess) {

	        double[] x = initialGuess.clone();

	        int iteration = 0;



	        while (iteration < maxIterations) {

	            double[] f = {f1.apply(x), f2.apply(x)};

	            double[][] J = jacobian.apply(x);



	            double[] delta = solveLinearSystem(J, negateVector(f));



	            x = addVectors(x, delta);



	            if (norm(delta) < epsilon) {

	            	System.out.println("Iterations : " + iteration);

	                return x;

	            }



	            iteration++;

	        }



	        throw new RuntimeException("Failed to converge to a solution");

	    }



	    public static double[] solveLinearSystem(double[][] A, double[] b) {

	        int n = A.length;



	        // Perform LU decomposition

	        double[][] L = new double[n][n];

	        double[][] U = new double[n][n];

	        for (int i = 0; i < n; i++) {

	            // Compute elements of L

	            for (int j = 0; j <= i; j++) {

	                if (j == i) {

	                    L[i][j] = 1.0;

	                } else {

	                    double sum = 0.0;

	                    for (int k = 0; k < j; k++) {

	                        sum += L[i][k] * U[k][j];

	                    }

	                    L[i][j] = (A[i][j] - sum) / U[j][j];

	                }

	            }



	            // Compute elements of U

	            for (int j = i; j < n; j++) {

	                double sum = 0.0;

	                for (int k = 0; k < i; k++) {

	                    sum += L[i][k] * U[k][j];

	                }

	                U[i][j] = A[i][j] - sum;

	            }

	        }



	        // Solve Ly = b

	        double[] y = new double[n];

	        for (int i = 0; i < n; i++) {

	            double sum = 0.0;

	            for (int j = 0; j < i; j++) {

	                sum += L[i][j] * y[j];

	            }

	            y[i] = (b[i] - sum) / L[i][i];

	        }



	        // Solve Ux = y

	        double[] x = new double[n];

	        for (int i = n - 1; i >= 0; i--) {

	            double sum = 0.0;

	            for (int j = i + 1; j < n; j++) {

	                sum += U[i][j] * x[j];

	            }

	            x[i] = (y[i] - sum) / U[i][i];

	        }



	        return x;

	    }



	    public static double[] negateVector(double[] vector) {

	        int n = vector.length;

	        double[] negated = new double[n];

	        for (int i = 0; i < n; i++) {

	            negated[i] = -vector[i];

	        }

	        return negated;

	    }



	    public static double[] addVectors(double[] a, double[] b) {

	        int n = a.length;

	        double[] sum = new double[n];

	        for (int i = 0; i < n; i++) {

	            sum[i] = a[i] + b[i];

	        }

	        return sum;

	    }



	    public static double norm(double[] vector) {

	        double sumOfSquares = 0.0;

	        for (double value : vector) {

	            sumOfSquares += value * value;

	        }

	        return Math.sqrt(sumOfSquares);

	    }

	}

	