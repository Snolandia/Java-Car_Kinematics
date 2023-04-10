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
	
	public static double[] planeFromPoints(double[][] points,int linkCount) {
		
		double[] plane = new double[4];
		double[][] vectors = new double[linkCount-1][3];
		double[] normalVector = new double[3];
		
		for(int i = 0;i<linkCount-1;i++) {
			for(int j = 0;j<3;j++) {
				vectors[i][j] = points[i][j]-points[i+1][j];
			}
		}
		
		normalVector[0] = (vectors[0][1]*vectors[1][2])-(vectors[0][2]*vectors[1][1]);
		normalVector[1] = (vectors[0][2]*vectors[1][0])-(vectors[0][0]*vectors[1][2]);
		normalVector[2] = (vectors[0][0]*vectors[1][1])-(vectors[0][1]*vectors[1][0]);
		
		plane[0] = normalVector[0];
		plane[1] = normalVector[1];
		plane[2] = normalVector[2];
		plane[3] = 0-(normalVector[0]*points[0][0])-(normalVector[1]*points[0][1])-(normalVector[2]*points[0][2]);
		
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
	
	public static void testTest() {
		
		
			
	}
		
}
