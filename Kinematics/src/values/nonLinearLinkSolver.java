package values;

import java.util.ArrayList;

public class nonLinearLinkSolver {
	
	private static int numLinks;
	private static int numVectors;
	private static int numVariables;
	private static int numFormulas;
	static double[][] array;
	static String[] nonLinearArray;
	static String[] functionsArray;
	static rigidBodyForm rigidBody;
	static String[] variablesArray;
	
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
		variablesArraySetup();
		
		jacobianMatrix jMatrix = new jacobianMatrix(functionsArray,variablesArray,rigid);
		hessianMatrix hMatrix = new hessianMatrix(functionsArray,variablesArray,rigid);
		
	}
	
	private static void variablesArraySetup() {
		
		variablesArray = new String[numVariables];
		
		ArrayList<pointForm> pointList = rigidBody.getPointList();
		int count = 0;
		
		for(int i = 0;i<pointList.size();i++) {
			if(pointList.get(i).getFixed()==false) {
				variablesArray[count] = String.valueOf(((pointForm)pointList.get(i)).getPointID()) + "X";
				count++;
			
				variablesArray[count] = String.valueOf(((pointForm)pointList.get(i)).getPointID()) + "Y";
				count++;
			
				variablesArray[count] = String.valueOf(((pointForm)pointList.get(i)).getPointID()) + "Z";
				count++;
			}
		}
		
//		for(int i = 0;i<count;i++) {
//			System.out.println("variables names : " + variablesArray[i]);
//		}
		
		
	}
	
	private static void functionsArraySetup() {
		
		int arrayCounter = 0;
		
		functionsArray = new String[numFormulas];
		
		
		for(int formulas = 0;formulas<numLinks;formulas++) {
			functionsArray[arrayCounter] = ((linkForm)rigidBody.getChild(formulas)).getLinkFormula();
			arrayCounter++;
		}
		
		for(int formulas = 0;formulas<numVectors/3;formulas++) {
			
			
			functionsArray[arrayCounter] = rigidBody.vectorLink.getVector().getVectorXFormula();
			arrayCounter++;
			
			functionsArray[arrayCounter] = rigidBody.vectorLink.getVector().getVectorYFormula();
			arrayCounter++;
			
			functionsArray[arrayCounter] = rigidBody.vectorLink.getVector().getVectorZFormula();
			arrayCounter++;
		}
		
//		for(int i=0;i<numFormulas;i++) {
//			System.out.println(functionsArray[i]);
//		}
	}
	
}
