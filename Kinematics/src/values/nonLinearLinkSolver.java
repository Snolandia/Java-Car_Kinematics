package values;

public class nonLinearLinkSolver {
	
	private static int numLinks;
	private static int numVectors;
	private static int numVariables;
	private static int numFormulas;
	static double[][] array;
	static String[] nonLinearArray;
	static String[] functionsArray;
	static rigidBodyForm rigidBody;
	
	public static void solveIt(rigidBodyForm rigid) {
		
		rigidBody = rigid;
		rigid.setupVectorPlanes();
		
		numLinks = rigidBody.getNumOfLinks();
		numVectors = rigidBody.getNumOfUsedVectors();
		
		numFormulas = numLinks + numVectors;
		
		numVariables = rigidBody.getNumOfVariablePoints()*3; //*3 for dimensions x,y,z
		
		if(numFormulas!=numVariables) {
			//System.out.println("NOT SQUARE;IMPLEMENT LATER?");
		}
		
		array = new double[numFormulas][numVariables];
		
		//System.out.println("nonLinearLinkSolver ::: Number of Links " + numLinks + " / Number Of Vectors : " + numVectors + " / Number Of Variables " + numVariables);
		
		functionsArraySetup();
		
	}
	
	private static void functionsArraySetup() {
		
		int arrayCounter = 0;
		
		functionsArray = new String[numFormulas];
		
		
		for(int formulas = 0;formulas<numLinks;formulas++) {
			functionsArray[arrayCounter] = "link";
			arrayCounter++;
		}
		
		for(int formulas = 0;formulas<numVectors/3;formulas++) {
			
			
			functionsArray[arrayCounter] = "vectorX";
			arrayCounter++;
			
			functionsArray[arrayCounter] = "vectorY";
			arrayCounter++;
			
			functionsArray[arrayCounter] = "vectorZ";
			arrayCounter++;
		}
		
//		for(int i=0;i<numFormulas;i++) {
//			System.out.println(functionsArray[i]);
//		}
	}
	
}
