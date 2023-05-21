package values;

public class generalFormulas {
		
	public static double[] xRotation(double[] arrayA,double radians) {
		
		double[][]  xRot = {
				{1,0,0},
				{0,Math.cos(radians),0-(Math.sin(radians))},
				{0,Math.sin(radians),Math.cos(radians)}
		};
		
		double[] matrix = multiplyRotationMatrix(arrayA,xRot);
		
		return matrix;
	}
	
	public static double[] yRotation(double[] arrayA,double radians) {
		
		double[][]  yRot = {
				{Math.cos(radians),0,Math.sin(radians)},
				{0,1,0},
				{-Math.sin(radians),0,Math.cos(radians)}
		};
		
		double[] matrix = multiplyRotationMatrix(arrayA,yRot);
		
		return matrix;
	}

	public static double[] zRotation(double[] arrayA,double radians) {
		
		double[][]  zRot = {
				{Math.cos(radians),-Math.sin(radians),0},
				{Math.sin(radians),Math.cos(radians),0},
				{0,0,1}
		};
		
		double[] matrix = multiplyRotationMatrix(arrayA,zRot);
		
		return matrix;
	}
	
	public static double[][] muliplyArray(double[][] arrayA,double[][] arrayB) {
		
		int length = arrayA.length;
		int width = arrayA[0].length;
		double[][] matrix = new double[3][3];
		
		for(int i = 0; i < length; i++){
			for(int j = 0;j < width;j++){
		         for(int k = 0;k < width;k++){
		            matrix[i][j] += arrayA[i][k] * arrayB[k][j];
		         }
		    }
		}
		
		return matrix;
	}
	
	public static double[] multiplyRotationMatrix(double[] xyzCoords,double[][] rotationMatrix) {
		
		int decimalplaces = decimalMaker(6);
		int coordsLength = xyzCoords.length;
		double[] newcoords = new double[3];
		
		for(int i = 0; i < coordsLength; i++){
			for(int k = 0;k < coordsLength;k++){
				newcoords[i] +=  (rotationMatrix[i][k] * xyzCoords[k]);
		    }
		}
		for(int i = 0; i < coordsLength; i++){
			newcoords[i] = (double)Math.round(newcoords[i]*decimalplaces);
			newcoords[i] = newcoords[i]/decimalplaces;
		}
		return newcoords;
	}		
	
	private static int decimalMaker(int digits) {
		
		int newDigit = 1;
		for(int i =0;i<digits;i++) {
			newDigit = newDigit *10;
		}
		
		return newDigit;
	}
	
public static double magnitudeBetweenPointsX(pointForm point1, pointForm point2) {
		
		double x1 = point1.getX();
		double x2 = point2.getX();
		
		double x = x1-x2;
		
		return x;
	}

	public static double magnitudeBetweenPointsY(pointForm point1, pointForm point2) {
	
		double y1 = point1.getY();
		double y2 = point2.getY();
		double y = y1-y2;
	
		return y;
	}
	
	public static double planeToPointDistance(double[] plane, pointForm point) {
		// Plane Ax+Bx+Cx+D=0
		// Point a,b,c
		// |a*A+b*B+c*C+D|/sqrt(A^2+B^2+C^2) = distance
		double top = (Math.abs((point.getX()*plane[0] + point.getY()*plane[1] + point.getZ()*plane[2] + plane[3])));
		double bottom = Math.sqrt((plane[0]*plane[0])+(plane[1]*plane[1])+(plane[2]*plane[2]));
		double result = top/bottom;
		//System.out.println("Distance : " + result);
		return result;
	}
	
	public static double[] dotProduct(double[] vectorA, double[] vectorB) {
		double[] result = new double[] {
				vectorA[0]*vectorB[0],
				vectorA[1]*vectorB[1],
				vectorA[2]*vectorB[2]
		};
		return result;
	}
	
	public static double[] crossProduct(double[] vectorA, double[] vectorB) {
		double[] result = new double[] {
				vectorA[1]*vectorB[2]-vectorA[2]*vectorB[1],
				vectorA[2]*vectorB[0]-vectorA[0]*vectorB[2],
				vectorA[0]*vectorB[1]-vectorA[1]*vectorB[0]
		};
		return result;
	}
	
	public static double[] pointOnLineCloestToPoint(pointForm A, pointForm B, pointForm point) {
		// t = ((x₀ - x₁) * a + (y₀ - y₁) * b + (z₀ - z₁) * c) / (a^2 + b^2 + c^2)
		// point closest = (x, y, z) = (x₁ + at, y₁ + bt, z₁ + ct)
		double top = ((point.getX()-A.getX())*(B.getX()-A.getX()))
					+((point.getY()-A.getY())*(B.getY()-A.getY()))
					+((point.getZ()-A.getZ())*(B.getZ()-A.getZ()));
		double bottom = ((B.getX()-A.getX())*(B.getX()-A.getX()))
					+((B.getY()-A.getY())*(B.getY()-A.getY()))
					+((B.getZ()-A.getZ())*(B.getZ()-A.getZ()));
		double t = top/bottom;
		double[] result = new double[] {
				A.getX()+((B.getX()-A.getX()))*t,
				A.getY()+((B.getY()-A.getY()))*t,
				A.getZ()+((B.getZ()-A.getZ()))*t
		};
		System.out.println("point on line closest to point : (" + result[0] + ", " + result[1] + ", " + result[2] + ")");
		return result;
	}
	
	public static double[] pointOnPlaneClosestToPoint(double[] plane,pointForm point) {
		
		// Plane Ax+Bx+Cx+D=0
		// Point a,b,c
		// t = -(a*A+b*B+c*C+D)/(A^2+B^2+C^2) 
		// x = t * A + a
		// y = t * B + b
		// z = t * C + c
		
		double[] result = new double[4];
		double[] pointXYZ = new double[] {point.getX(),point.getY(),point.getZ()};
		
		double top = (-((point.getX()*plane[0] + point.getY()*plane[1] + point.getZ()*plane[2] + plane[3])));
		double bottom = ((plane[0]*plane[0])+(plane[1]*plane[1])+(plane[2]*plane[2]));
		double t = top/bottom;
		
		for(int i = 0;i<3;i++) {
			result[i]=t*plane[i] + pointXYZ[i];
			//System.out.println(t + " * " + plane[i] + " + " + pointXYZ[i] + " = " + result[i]);
		}
		System.out.println("Closest Point : " + result[0] + ", "+ result[1] + ", "+ result[2]);
		return result;
	}
	
	public static double magnitudeBetweenPointsZ(pointForm point1, pointForm point2) {
	
		double z1 = point1.getZ();
		double z2 = point2.getZ();
	
		double z = z1-z2;
	
		return z;
	}

	public static double magnitudeBetweenPoints(pointForm point1, pointForm point2) {
		
		double x1 = point1.getX();
		double x2 = point2.getX();
		double y1 = point1.getY();
		double y2 = point2.getY();
		double z1 = point1.getZ();
		double z2 = point2.getZ();
		
		double x = x1-x2;
		double y = y1-y2;
		double z = z1-z2;
		
		double length = Math.sqrt((x*x)+(y*y)+(z*z));
		
		
		return length;
	}
	
public static double magnitudeBetweenPoints(double[] point1, double[] point2) {
		
		double x1 = point1[0];
		double x2 = point2[0];
		double y1 = point1[1];
		double y2 = point2[1];
		double z1 = point1[2];
		double z2 = point2[2];
		
		double x = x1-x2;
		double y = y1-y2;
		double z = z1-z2;
		
		double length = Math.sqrt((x*x)+(y*y)+(z*z));
		
		
		return length;
	}
	
public static double magnitudeBetweenPoints(pointForm point1, double[] point2) {
		
		double x1 = point1.getX();
		double x2 = point2[0];
		double y1 = point1.getY();
		double y2 = point2[1];
		double z1 = point1.getZ();
		double z2 = point2[2];
		
		double x = x1-x2;
		double y = y1-y2;
		double z = z1-z2;
		
		double length = Math.sqrt((x*x)+(y*y)+(z*z));
		
		
		return length;
	}
	
	public static double[] normalVectorFromPoints(double[][] points,int linkCount) {
		
		// Returns Normal Vector in form : [X,Y,Z,D]
		
		double[] plane = new double[4];
		double[][] vectors = new double[linkCount-1][3];
		double[] normalVector = new double[3];
		
		//creates vectors from points given (only 2 actually needed)
		for(int i = 0;i<linkCount-1;i++) {
			for(int j = 0;j<3;j++) {
				vectors[i][j] = points[i][j]-points[i+1][j];
			}
		}
		
		//uses first 2 vectors to calculate normal vectors of x,y,z
		normalVector[0] = (vectors[0][1]*vectors[1][2])-(vectors[0][2]*vectors[1][1]);
		normalVector[1] = (vectors[0][2]*vectors[1][0])-(vectors[0][0]*vectors[1][2]);
		normalVector[2] = (vectors[0][0]*vectors[1][1])-(vectors[0][1]*vectors[1][0]);
		
		//converts normal vectors into planes
		plane[0] = normalVector[0];
		plane[1] = normalVector[1];
		plane[2] = normalVector[2];
		plane[3] = 0-(normalVector[0]*points[0][0])-(normalVector[1]*points[0][1])-(normalVector[2]*points[0][2]);
		
		return plane;
	}
	
	public static double[] planeFromPoints(double[][] points,int linkCount) {
		
		// Returns plane in form : [X,Y,Z,D]
		
		double[] plane = new double[4];
		double[][] vectors = new double[linkCount-1][3];
		double[] normalVector = new double[3];
		
		//creates vectors from points given (only 2 actually needed)
//		for(int i = 0;i<linkCount-1;i++) {
//			for(int j = 0;j<3;j++) {
//				vectors[i][j] = points[i][j]-points[i+1][j];
//			}
//		}
		vectors[0][0] = points[1][0] - points[0][0];
		vectors[0][1] = points[1][1] - points[0][1];
		vectors[0][2] = points[1][2] - points[0][2];
		vectors[1][0] = points[2][0] - points[0][0];
		vectors[1][1] = points[2][1] - points[0][1];
		vectors[1][2] = points[2][2] - points[0][2];

		//uses first 2 vectors to calculate normal vectors of x,y,z
		normalVector[0] = (vectors[0][1]*vectors[1][2])-(vectors[0][2]*vectors[1][1]);
		normalVector[1] = (vectors[0][2]*vectors[1][0])-(vectors[0][0]*vectors[1][2]);
		normalVector[2] = (vectors[0][0]*vectors[1][1])-(vectors[0][1]*vectors[1][0]);
		double normalize =Math.sqrt(normalVector[0]*normalVector[0]
									+normalVector[1]*normalVector[1]
									+normalVector[2]*normalVector[2]);
		//converts normal vectors into planes
		plane[0] = normalVector[0]/normalize;
		plane[1] = normalVector[1]/normalize;
		plane[2] = normalVector[2]/normalize;
		plane[3] = (0-(normalVector[0]*points[0][0])-(normalVector[1]*points[0][1])-(normalVector[2]*points[0][2]))/normalize;
		
		return plane;
	}
	
	public static double angleFromPlanes(double[] planeA, double[] planeB) {
				
		double top = Math.abs(planeA[0]*planeB[0]+planeA[1]*planeB[1]+planeA[2]*planeB[2]);
		double bottomA = (planeA[0]*planeA[0]+planeA[1]*planeA[1]+planeA[2]*planeA[2]);
		double bottomB = (planeB[0]*planeB[0]+planeB[1]*planeB[1]+planeB[2]*planeB[2]);
		double bottom = Math.sqrt(bottomA*bottomB);
		double angle = Math.acos(top/bottom);
		angle = Math.toDegrees(angle);
		angle = Math.round(angle*100);
		angle = angle/100;

		return angle;
	}
	
	public static double[] vectorFromTwoPoints(pointForm A,pointForm B) {
		
		double[] result = {
			A.getX()-B.getX(),
			A.getY()-B.getY(),
			A.getZ()-B.getZ(),
		};
		
		return result;
	}
	
	public static void testTest() {
		
		
			
	}
		
}
