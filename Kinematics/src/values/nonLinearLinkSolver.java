package values;

import java.util.ArrayList;

import Jama.LUDecomposition;
import Jama.Matrix;

import java.text.DecimalFormat;

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
	static ArrayList<pointForm> points;
	static double[] fx;
	static double[] vars;
	static int varIndex;
	static String varChanged = "0Y";
	static double[][] solvedArray;
	static int iterations = 100;
	static int stepper = 1;
	static double [][] jSolved;
	public static void solveIt(rigidBodyForm rigid) {
		
		DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(30);
		
		boolean print = false;
		//boolean print = true;
		double evaluation;
		
		rigidBody = rigid;
		rigid.setupVectorPlanes();
		points = rigidBody.getPointList();
		
		numLinks = rigidBody.getNumOfLinks();
		numVectors = rigidBody.getNumOfUsedVectors();
		
		numFormulas = numLinks + numVectors;
		
		numVariables = rigidBody.getNumOfVariablePoints()*3; //*3 for dimensions x,y,z
		
		if(numFormulas!=numVariables) {
			//System.out.println("NOT SQUARE;IMPLEMENT LATER?");
		}
		
		array = new double[numFormulas][numVariables];
		fx = new double[numFormulas];
		vars = new double[numVariables];
		solvedArray = new double[iterations][numVariables];
		
		
		//System.out.println("nonLinearLinkSolver ::: Number of Links " + numLinks + " / Number Of Vectors : " + numVectors + " / Number Of Variables " + numVariables);
		
		functionsArraySetup();
		variablesArraySetup();
		
		jacobianMatrix jMatrix = new jacobianMatrix(functionsArray,variablesArray,rigid);
		//hessianMatrix hMatrix = new hessianMatrix(functionsArray,variablesArray,rigid);
		
		for(int i = 0;i < numVariables;i++) {
			//System.out.println(variablesArray[i]);
			if(variablesArray[i].equals(varChanged)) {
				varIndex = i;
				//System.out.println("Good");
				break;
			}
		}

		for(int i = 0;i < points.size();i++) {
			if(points.get(i).getPointID() == 0) {
				if(print) {
					System.out.println("Point 0 XYZ : (" + points.get(i).getX() +", " +  points.get(i).getY() + ", " + points.get(i).getZ() + ")");
				}
				break;
			}
		}
		
		jSolved = new double[18][18];
		
		initializeVars();
		for(int i = 0;i<numFormulas;i++) {
			if(print) {
				System.out.println("evaluate : " + evaluate(functionsArray[i]));
			}
			evaluation = evaluate(functionsArray[i]);
			evaluation = Math.round(evaluation*100000000.0);
			if(evaluation!=0) {
				for(int j = 0;j < points.size();j++) {
					if(points.get(j).getPointID()==2||points.get(j).getPointID()==4||points.get(j).getPointID()==10) {
						//if(print) {
							System.out.println("Point " + points.get(j).getPointID() + " XYZ : (" + points.get(j).getX() +", " +  points.get(j).getY() + ", " + points.get(j).getZ() + ")");
						//}
					}
				}
				System.out.println("Evaluation Error at formula number : " + i);
				System.out.println("With  : " + functionsArray[i]);
				System.out.println("Evaluation of : " + (evaluation/100000000.0));
				System.exit(0);
			}
		}
		double sum = 0;
		for(int i = 0;i<functionsArray.length;i++) {
			if(print) {
				System.out.print("  ---> Sum : "+ df.format(sum));
				System.out.println();
			}
			sum = 0;
			for(int j = 0;j<variablesArray.length;j++) {
				if(print) {
					System.out.println("With  : " + jMatrix.getJMatrixFormula(j, i));
				}
				evaluation = evaluate(jMatrix.getJMatrixFormula(i, j));
				evaluation = Math.round(evaluation*100000000.0);
				evaluation = evaluation/100000000.0;
				jSolved[i][j] = evaluation;
				sum += evaluation;
				if(print) {
					System.out.print(df.format(evaluation)+" | ");
				}
				if(evaluation!=0) {
//					System.out.println("Evaluation Error at formula number : " + i + "/" + j);
//					System.out.println("With  : " + jMatrix.getJMatrixFormula(j, i));
//					System.out.println("Evaluation of : " + (evaluation/100000000.0));
					
				}
			}
		}
		if(print) {
			System.out.print("  ---> Sum : "+ df.format(sum));
			System.out.println();
		}
		if(print) {
			System.out.println("beg");
		}
		//Solver.solve();
		solver(jMatrix);
		if(print) {
			System.out.println("fin");
		}
	}
	
	private static void solver(jacobianMatrix jMatrix) {
		
		double[] fx0 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		double[] fxN = new double[18];
		
		Matrix jsolvedMatrix = new Matrix(jSolved);
		LUDecomposition luDecomp = new LUDecomposition(jsolvedMatrix);
		Matrix fxMatrix = new Matrix(fx0,fx0.length);
		Matrix solvedMatrix = luDecomp.solve(fxMatrix);
		//holder currently
		String solveFor = "0Y";
		int solveForIndex = -1;
		
		for(int i = 0;i<18;i++) {
			if(variablesArray[i].equals(solveFor)) {
				solveForIndex = i;
				break;
			}
		}
		
		if(solveForIndex<0) {
			System.out.println("solveForIndex Error");
		}
		
		//double[] solved = Solver.solveLinearSystem(jSolved, fx0);
		
		for(int i = 0;i<18;i++) {
			//System.out.println("f of " + i + "solved as : " + solved[i]);
			if(solvedMatrix.get(i, 0)!=0) {
				System.out.println("f of " + i + "solved as : " + solvedMatrix.get(i, 0));
			}
		}
		
		for(int i = 0;i<18;i++) {
			solvedArray[iterations/2][i] = solvedMatrix.get(i, 0); 
		}
		
		for(int i = iterations/2+1;i<iterations;i++) {
			for(int j = 0;j<18;j++) {
				if(j==solveForIndex) {
					fxN[j]=solvedArray[i-1][j]+stepper;
				}else {
					fxN[j]=solvedArray[i-1][j];
				}
			}
			
			newtonRasph(solveForIndex,fxN, jMatrix);
			
		}
		for(int i = (iterations/2)-1;i<=0;i--) {
			for(int j = 0;j<18;j++) {
				if(j==solveForIndex) {
					fxN[j]=solvedArray[i+1][j]-stepper;
				}else {
					fxN[j]=solvedArray[i+1][j];
				}
			}
		}
		
		
		
		
	}
	
	private static double[] newtonRasph(int x,double[] fxN,jacobianMatrix jMatrix) {
		
		boolean print = false;
		
		double evaluation;
		double[] fxI = fxN.clone();
		double[] fSolved = new double[fxN.length];
		double[] f = new double[fxN.length];
		double[][] j = new double[2][2];
        int iteration = 0;
        int maxIterations = 50;
        int sum = 0;
        
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(30);

        while (iteration < maxIterations) {
        	
        	for(int i = 0;i<fx.length;i++) {
        		f[i] = evaluate(functionsArray[i],fxN);
        	}

           // double[][] J = jacobian.apply(x);
        	for(int i = 0;i<functionsArray.length;i++) {
    			sum = 404;
    			for(int j = 0;j<variablesArray.length;j++) {
    				evaluation = evaluate(jMatrix.getJMatrixFormula(i, j));
    				evaluation = Math.round(evaluation*100000000.0);
    				evaluation = evaluation/100000000.0;
    				jSolved[i][j] = evaluation;
    				sum += evaluation;
    				if(print) {
    					System.out.print(df.format(evaluation)+" | ");
    				}
    				if(evaluation!=0) {
//    					System.out.println("Evaluation Error at formula number : " + i + "/" + j);
//    					System.out.println("With  : " + jMatrix.getJMatrixFormula(j, i));
//    					System.out.println("Evaluation of : " + (evaluation/100000000.0));
    					
    				}
    			}
    		}


           // double[] delta = solveLinearSystem(J, negateVector(f));



            //x = addVectors(x, delta);



            //if (norm(delta) < epsilon) {

            //	System.out.println("Iterations : " + iteration);

            //    return x;

           // }



            iteration++;

        }

		
		return 0.0;
	}
	
	private static double evaluate(String formula,double[] fxN) {
		
		//boolean print = true;
		boolean print = false;
		double result = 0;
		char operator = ' ';
		char specOp = '+';
		String variable = "";
		String id = "";
		String xyz = "";
		int beg = 0;
		int end = 0;
		int par = 0;
		String holder;
		String a = "";
		String b = "";
		DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(30);
		
		if(print) {
			System.out.println(formula);
		}
		for(int i = 0;i<formula.length();i++) {
			if(formula.charAt(i)=='%') {
				holder = "";
				beg = i;
				end = 0;
				i++;
				do {
					if(formula.charAt(i)=='%') {
						end = i;
						i++;
						break;
					}else {
						holder += formula.charAt(i);
						i++;
					}
				}while(i<formula.length());
				if(print) {
					//System.out.println(holder);
				}
				for(int j = 0;j<numVariables;j++) {
					if(variablesArray[j].equals(holder)) {
						holder = "" + fxN[j];
						break;
						//following for non variables
					}else if(j==numVariables-1){
						id = holder.substring(0,holder.length()-1);
						xyz = "" + holder.charAt(holder.length()-1);
						if(print) {
							//System.out.println("Id + xyz : " + id + " / " + xyz);
						}
						for(int k = 0;k<points.size();k++) {
							if (points.get(k).getPointID()==Integer.parseInt(id)) {
								switch(xyz) {
								case "X" : 
									holder = "" + df.format(points.get(k).getX());
									break;
								case "Y" : 
									holder = "" + df.format(points.get(k).getY());
									break;
								case "Z" : 
									holder = "" + df.format(points.get(k).getZ());
									break;
								}
							break;
							}else if(k+1 == points.size()) {
								System.out.println("error of evaluator number 1");
								return 404.0;
							}
						}
					}
					
				}
				if(print) {
					//System.out.println(holder);
				}
				formula = formula.substring(0,beg) + holder + formula.substring(end+1,formula.length());
				i = i-2;
			}
		}
		if(print) {
			System.out.println(formula);
		}
		holder = "";
		for(int i = 0;i<formula.length();i++) {
			if(formula.charAt(i)=='(') {
				par = 1;
				specOp = ' ';
				beg = i;
				holder = "";
				a = "";
				b = "";
				operator = ' ';
			}else if(par == 1 & formula.charAt(i)==')') {
				end = i;
				par = 0;
				for( int j = 0;j<holder.length();j++) {
					if(holder.charAt(j)=='.' || Character.isDigit(holder.charAt(j))) {
						if(operator == ' ') {
							a += holder.charAt(j);
						}else {
							b += holder.charAt(j);
						}
					}else if(holder.charAt(j)=='+') {
							if(operator==' ') {
								if(a.equals("")) {
								}else {
									operator = '+';
								}
							}else {
								b += holder.charAt(j);
							}
					}else if(holder.charAt(j)=='-') {
						if(operator==' ') {
							if(a.equals("")) {
								a = "-";
							}else {
								operator = '-';
							}
						}else {
							b += holder.charAt(j);
						}
					}else if(holder.charAt(j)=='^') {
						operator = '^';
					}else if(holder.charAt(j)=='/') {
						operator = '/';
					}else if(holder.charAt(j)=='*') {
						operator = '*';
					}else if(holder.charAt(j)=='√') {
						operator = '√';
					}else if(holder.charAt(j)=='|') {
						operator = '|';
					}else {
						System.out.println("operator error number 1 with a & b & operator of : ");
						System.out.println(a + " : " + b + " : " + operator);
						return 404.0;
					}
				}
				if(print) {
					//System.out.println("A : " + a + "  B : " + b + " Operator : " + operator);
				}
				if(operator == '+') {
					holder = "" +  df.format(Double.parseDouble(a) + Double.parseDouble(b)); 
				}else if(operator == '-') {
					holder = "" +  df.format(Double.parseDouble(a) - Double.parseDouble(b)); 
				}else if(operator == '*') {
					holder = "" +  df.format(Double.parseDouble(a) * Double.parseDouble(b)); 
				}else if(operator == '/') {
					holder = "" +  df.format(Double.parseDouble(a) / Double.parseDouble(b)); 
				}else if(operator == '|') {
					holder = "" +  df.format(Math.abs(Double.parseDouble(b))); 
				}else if(operator == '√') {
					holder = "" +  df.format(Math.sqrt(Double.parseDouble(b)));
//					System.out.println("operator error number XX with a & b & operator of : ");
//					System.out.println(a + " : " + b + " : " + operator);
//					return 404.0;
				}else if(operator == '^') {
					holder = a;
					for(int k = 0; k<Double.parseDouble(b)-1;k++) {
						holder = "" + df.format(Double.parseDouble(holder) * Double.parseDouble(a));
					}
				}
				formula = formula.substring(0,beg) + holder + formula.substring(end+1,formula.length());
				if(print) {
					System.out.println(formula);
				}
				i = -1;
			}else {
				holder = holder + formula.charAt(i);
			}
		}
		if(print) {
			System.out.println("non pars : " + formula);
		}
		beg = -1;
		end = 0;
		holder = "";
		a = "";
		b = "";
		operator = ' ';
		do {
			beg = -1;
			end = 0;
			holder = "";
			a = "";
			b = "";
			operator = ' ';
			for( int j = 0;j<formula.length();j++) {
				if(formula.charAt(j)=='*'||formula.charAt(j)=='/') {
					operator = formula.charAt(j);
					beg = j;
					end = j;
					
					beg--;
					do {
						if(formula.charAt(beg)!='.' & Character.isDigit(formula.charAt(beg))==false ) {
							beg++;
							break;
						}else {
							a = formula.charAt(beg) + a;
						}
						beg--;
					}while(beg>=0);
					
					end++;
					if(formula.charAt(end)=='-') {
						b = "-";
						end++;
					}else if(formula.charAt(end)=='+') {
						end++;
					}
					
					do {
						if(formula.charAt(end)!='.' & Character.isDigit(formula.charAt(end))==false ) {
							break;
						}else {
							b = b + formula.charAt(end);
						}
						end++;
					}while(end<formula.length());
				if(operator == '*') {
					holder = "" +  df.format(Double.parseDouble(a) * Double.parseDouble(b)); 
				}else if(operator == '/') {
					holder = "" +  df.format(Double.parseDouble(a) / Double.parseDouble(b)); 
				}
				if(print) {
					System.out.println("f16 holder: " + holder);
				}
				formula = formula.substring(0,beg) + holder + formula.substring(end,formula.length());
				break;
				}
			}
			
		}while(formula.contains("*")||formula.contains("/"));
		
		beg = -1;
		end = 0;
		holder = "";
		a = "";
		b = "";
		operator = ' ';
		for( int j = 0;j<formula.length();j++) {
			if(operator==' ') {
				if(beg==-1) {
					beg = j;
				}
				if(formula.charAt(j)=='.' || Character.isDigit(formula.charAt(j))) {
					a += formula.charAt(j);
				}else if(formula.charAt(j)=='+') {
					if(a.equals("")) {
					}else {
						if(operator==' ') {
							operator = '+';
						}else {
							b += holder.charAt(j);
						}
					}
				}else if(formula.charAt(j)=='-') {
					if(a.equals("")) {
						a = "-";
					}else {
						if(operator==' ') {
							operator = '-';
						}else {
							b += holder.charAt(j);
						}
					}
				}else if(formula.charAt(j)=='^') {
					operator = '^';
				}else if(formula.charAt(j)=='/') {
					operator = '/';
				}else if(holder.charAt(j)=='√') {
					operator = '√';
				}else if(formula.charAt(j)=='*') {
					operator = '*';
				}else if(holder.charAt(j)=='|') {
					operator = '|';
				}else {
					System.out.println("operator error 3");
					return 404.0;
				}
			}else{
				if(formula.charAt(j)=='.' || Character.isDigit(formula.charAt(j))) {
					b += formula.charAt(j);
				}else if(b.equals("")) {
					if (formula.charAt(j)=='-') {
						b = "-";
						}else {
							System.out.println("operator error number 2 with a & b & operator of : ");
							System.out.println(a + " : " + b + " : " + operator);
							return 404.0;
						}
				}else {
					end = j;
					if(operator == '+') {
						holder = "" +  df.format(Double.parseDouble(a) + Double.parseDouble(b)); 
					}else if(operator == '-') {
						holder = "" +  df.format(Double.parseDouble(a) - Double.parseDouble(b)); 
					}else if(operator == '*') {
						holder = "" +  df.format(Double.parseDouble(a) * Double.parseDouble(b)); 
					}else if(operator == '/') {
						holder = "" +  df.format(Double.parseDouble(a) / Double.parseDouble(b)); 
					}else if(operator == '|') {
						holder = "" +  df.format(Math.abs(Double.parseDouble(b))); 
					}else if(operator == '√') {
						holder = "" +  df.format(Math.sqrt(Double.parseDouble(b)));
//						System.out.println("operator error number XX with a & b & operator of : ");
//						System.out.println(a + " : " + b + " : " + operator);
//						return 404.0;
					}else if(operator == '^') {
						holder = a;
						for(int k = 0; k<Double.parseDouble(b)-1;k++) {
							holder = "" +  df.format(Double.parseDouble(holder) * Double.parseDouble(a));
						}
					}
				if(print) {
					//System.out.println("holder " + holder);
				}
				formula = formula.substring(0,beg) + holder + formula.substring(end,formula.length());
				if(print) {
					System.out.println("f11 : " +formula);
				}
				beg = -1;
				end = 0;
				holder = "";
				a = "";
				b = "";
				operator = ' ';
				j = -1;
				}
				if(j==formula.length()-1) {
					end = j+1;
					if(operator == '+') {
						holder = "" +  df.format(Double.parseDouble(a) + Double.parseDouble(b)); 
					}else if(operator == '-') {
						holder = "" +  df.format(Double.parseDouble(a) - Double.parseDouble(b)); 
					}else if(operator == '*') {
						holder = "" +  df.format(Double.parseDouble(a) * Double.parseDouble(b)); 
					}else if(operator == '|') {
						holder = "" +  df.format(Math.abs(Double.parseDouble(b))); 
					}else if(operator == '√') {
						holder = "" +  df.format(Math.sqrt(Double.parseDouble(b)));
//						System.out.println("operator error number XX with a & b & operator of : ");
//						System.out.println(a + " : " + b + " : " + operator);
//						return 404.0;
					}else if(operator == '/') {
						holder = "" +  df.format(Double.parseDouble(a) / Double.parseDouble(b)); 
					}else if(operator == '^') {
						holder = a;
						for(int k = 0; k<Double.parseDouble(b)-1;k++) {
							holder = "" +  df.format(Double.parseDouble(holder) * Double.parseDouble(a));
						}
					}
				if(print) {
					//System.out.println("holder " + holder);
				}
				formula = formula.substring(0,beg) + holder + formula.substring(end,formula.length());
				operator = ' ';
				beg = -1;
				end = 0;
				holder = "";
				a = "";
				b = "";
				if(print) {
					System.out.println("f12 : " + formula);
				}
				}
			}
		}
		if(print) {
			//System.out.println(formula);
		}
		result = Double.parseDouble(formula);
		return result;
	}
	
	
	private static double evaluate(String formula) {
		
		//boolean print = true;
		boolean print = false;
		double result = 0;
		char operator = ' ';
		char specOp = '+';
		String variable = "";
		String id = "";
		String xyz = "";
		int beg = 0;
		int end = 0;
		int par = 0;
		String holder;
		String a = "";
		String b = "";
		DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(30);
		
		if(print) {
			System.out.println(formula);
		}
		for(int i = 0;i<formula.length();i++) {
			if(formula.charAt(i)=='%') {
				holder = "";
				beg = i;
				end = 0;
				i++;
				do {
					if(formula.charAt(i)=='%') {
						end = i;
						i++;
						break;
					}else {
						holder += formula.charAt(i);
						i++;
					}
				}while(i<formula.length());
				if(print) {
					//System.out.println(holder);
				}
				for(int j = 0;j<numVariables;j++) {
					if(variablesArray[j].equals(holder)) {
						holder = "" + vars[j];
						break;
					}else if(j==numVariables-1){
						id = holder.substring(0,holder.length()-1);
						xyz = "" + holder.charAt(holder.length()-1);
						if(print) {
							//System.out.println("Id + xyz : " + id + " / " + xyz);
						}
						for(int k = 0;k<points.size();k++) {
							if (points.get(k).getPointID()==Integer.parseInt(id)) {
								switch(xyz) {
								case "X" : 
									holder = "" + df.format(points.get(k).getX());
									break;
								case "Y" : 
									holder = "" + df.format(points.get(k).getY());
									break;
								case "Z" : 
									holder = "" + df.format(points.get(k).getZ());
									break;
								}
							break;
							}else if(k+1 == points.size()) {
								System.out.println("error of evaluator number 1");
								return 404.0;
							}
						}
					}
					
				}
				if(print) {
					//System.out.println(holder);
				}
				formula = formula.substring(0,beg) + holder + formula.substring(end+1,formula.length());
				i = i-2;
			}
		}
		if(print) {
			System.out.println(formula);
		}
		holder = "";
		for(int i = 0;i<formula.length();i++) {
			if(formula.charAt(i)=='(') {
				par = 1;
				specOp = ' ';
				beg = i;
				holder = "";
				a = "";
				b = "";
				operator = ' ';
			}else if(par == 1 & formula.charAt(i)==')') {
				end = i;
				par = 0;
				for( int j = 0;j<holder.length();j++) {
					if(holder.charAt(j)=='.' || Character.isDigit(holder.charAt(j))) {
						if(operator == ' ') {
							a += holder.charAt(j);
						}else {
							b += holder.charAt(j);
						}
					}else if(holder.charAt(j)=='+') {
							if(operator==' ') {
								if(a.equals("")) {
								}else {
									operator = '+';
								}
							}else {
								b += holder.charAt(j);
							}
					}else if(holder.charAt(j)=='-') {
						if(operator==' ') {
							if(a.equals("")) {
								a = "-";
							}else {
								operator = '-';
							}
						}else {
							b += holder.charAt(j);
						}
					}else if(holder.charAt(j)=='^') {
						operator = '^';
					}else if(holder.charAt(j)=='/') {
						operator = '/';
					}else if(holder.charAt(j)=='*') {
						operator = '*';
					}else if(holder.charAt(j)=='√') {
						operator = '√';
					}else if(holder.charAt(j)=='|') {
						operator = '|';
					}else {
						System.out.println("operator error number 1 with a & b & operator of : ");
						System.out.println(a + " : " + b + " : " + operator);
						return 404.0;
					}
				}
				if(print) {
					//System.out.println("A : " + a + "  B : " + b + " Operator : " + operator);
				}
				if(operator == '+') {
					holder = "" +  df.format(Double.parseDouble(a) + Double.parseDouble(b)); 
				}else if(operator == '-') {
					holder = "" +  df.format(Double.parseDouble(a) - Double.parseDouble(b)); 
				}else if(operator == '*') {
					holder = "" +  df.format(Double.parseDouble(a) * Double.parseDouble(b)); 
				}else if(operator == '/') {
					holder = "" +  df.format(Double.parseDouble(a) / Double.parseDouble(b)); 
				}else if(operator == '|') {
					holder = "" +  df.format(Math.abs(Double.parseDouble(b))); 
				}else if(operator == '√') {
					holder = "" +  df.format(Math.sqrt(Double.parseDouble(b)));
//					System.out.println("operator error number XX with a & b & operator of : ");
//					System.out.println(a + " : " + b + " : " + operator);
//					return 404.0;
				}else if(operator == '^') {
					holder = a;
					for(int k = 0; k<Double.parseDouble(b)-1;k++) {
						holder = "" + df.format(Double.parseDouble(holder) * Double.parseDouble(a));
					}
				}
				formula = formula.substring(0,beg) + holder + formula.substring(end+1,formula.length());
				if(print) {
					System.out.println(formula);
				}
				i = -1;
			}else {
				holder = holder + formula.charAt(i);
			}
		}
		if(print) {
			System.out.println("non pars : " + formula);
		}
		beg = -1;
		end = 0;
		holder = "";
		a = "";
		b = "";
		operator = ' ';
		do {
			beg = -1;
			end = 0;
			holder = "";
			a = "";
			b = "";
			operator = ' ';
			for( int j = 0;j<formula.length();j++) {
				if(formula.charAt(j)=='*'||formula.charAt(j)=='/') {
					operator = formula.charAt(j);
					beg = j;
					end = j;
					
					beg--;
					do {
						if(formula.charAt(beg)!='.' & Character.isDigit(formula.charAt(beg))==false ) {
							beg++;
							break;
						}else {
							a = formula.charAt(beg) + a;
						}
						beg--;
					}while(beg>=0);
					
					end++;
					if(formula.charAt(end)=='-') {
						b = "-";
						end++;
					}else if(formula.charAt(end)=='+') {
						end++;
					}
					
					do {
						if(formula.charAt(end)!='.' & Character.isDigit(formula.charAt(end))==false ) {
							break;
						}else {
							b = b + formula.charAt(end);
						}
						end++;
					}while(end<formula.length());
				if(operator == '*') {
					holder = "" +  df.format(Double.parseDouble(a) * Double.parseDouble(b)); 
				}else if(operator == '/') {
					holder = "" +  df.format(Double.parseDouble(a) / Double.parseDouble(b)); 
				}
				if(print) {
					System.out.println("f16 holder: " + holder);
				}
				formula = formula.substring(0,beg) + holder + formula.substring(end,formula.length());
				break;
				}
			}
			
		}while(formula.contains("*")||formula.contains("/"));
		
		beg = -1;
		end = 0;
		holder = "";
		a = "";
		b = "";
		operator = ' ';
		for( int j = 0;j<formula.length();j++) {
			if(operator==' ') {
				if(beg==-1) {
					beg = j;
				}
				if(formula.charAt(j)=='.' || Character.isDigit(formula.charAt(j))) {
					a += formula.charAt(j);
				}else if(formula.charAt(j)=='+') {
					if(a.equals("")) {
					}else {
						if(operator==' ') {
							operator = '+';
						}else {
							b += holder.charAt(j);
						}
					}
				}else if(formula.charAt(j)=='-') {
					if(a.equals("")) {
						a = "-";
					}else {
						if(operator==' ') {
							operator = '-';
						}else {
							b += holder.charAt(j);
						}
					}
				}else if(formula.charAt(j)=='^') {
					operator = '^';
				}else if(formula.charAt(j)=='/') {
					operator = '/';
				}else if(holder.charAt(j)=='√') {
					operator = '√';
				}else if(formula.charAt(j)=='*') {
					operator = '*';
				}else if(holder.charAt(j)=='|') {
					operator = '|';
				}else {
					System.out.println("operator error 3");
					return 404.0;
				}
			}else{
				if(formula.charAt(j)=='.' || Character.isDigit(formula.charAt(j))) {
					b += formula.charAt(j);
				}else if(b.equals("")) {
					if (formula.charAt(j)=='-') {
						b = "-";
						}else {
							System.out.println("operator error number 2 with a & b & operator of : ");
							System.out.println(a + " : " + b + " : " + operator);
							return 404.0;
						}
				}else {
					end = j;
					if(operator == '+') {
						holder = "" +  df.format(Double.parseDouble(a) + Double.parseDouble(b)); 
					}else if(operator == '-') {
						holder = "" +  df.format(Double.parseDouble(a) - Double.parseDouble(b)); 
					}else if(operator == '*') {
						holder = "" +  df.format(Double.parseDouble(a) * Double.parseDouble(b)); 
					}else if(operator == '/') {
						holder = "" +  df.format(Double.parseDouble(a) / Double.parseDouble(b)); 
					}else if(operator == '|') {
						holder = "" +  df.format(Math.abs(Double.parseDouble(b))); 
					}else if(operator == '√') {
						holder = "" +  df.format(Math.sqrt(Double.parseDouble(b)));
//						System.out.println("operator error number XX with a & b & operator of : ");
//						System.out.println(a + " : " + b + " : " + operator);
//						return 404.0;
					}else if(operator == '^') {
						holder = a;
						for(int k = 0; k<Double.parseDouble(b)-1;k++) {
							holder = "" +  df.format(Double.parseDouble(holder) * Double.parseDouble(a));
						}
					}
				if(print) {
					//System.out.println("holder " + holder);
				}
				formula = formula.substring(0,beg) + holder + formula.substring(end,formula.length());
				if(print) {
					System.out.println("f11 : " +formula);
				}
				beg = -1;
				end = 0;
				holder = "";
				a = "";
				b = "";
				operator = ' ';
				j = -1;
				}
				if(j==formula.length()-1) {
					end = j+1;
					if(operator == '+') {
						holder = "" +  df.format(Double.parseDouble(a) + Double.parseDouble(b)); 
					}else if(operator == '-') {
						holder = "" +  df.format(Double.parseDouble(a) - Double.parseDouble(b)); 
					}else if(operator == '*') {
						holder = "" +  df.format(Double.parseDouble(a) * Double.parseDouble(b)); 
					}else if(operator == '|') {
						holder = "" +  df.format(Math.abs(Double.parseDouble(b))); 
					}else if(operator == '√') {
						holder = "" +  df.format(Math.sqrt(Double.parseDouble(b)));
//						System.out.println("operator error number XX with a & b & operator of : ");
//						System.out.println(a + " : " + b + " : " + operator);
//						return 404.0;
					}else if(operator == '/') {
						holder = "" +  df.format(Double.parseDouble(a) / Double.parseDouble(b)); 
					}else if(operator == '^') {
						holder = a;
						for(int k = 0; k<Double.parseDouble(b)-1;k++) {
							holder = "" +  df.format(Double.parseDouble(holder) * Double.parseDouble(a));
						}
					}
				if(print) {
					//System.out.println("holder " + holder);
				}
				formula = formula.substring(0,beg) + holder + formula.substring(end,formula.length());
				operator = ' ';
				beg = -1;
				end = 0;
				holder = "";
				a = "";
				b = "";
				if(print) {
					System.out.println("f12 : " + formula);
				}
				}
			}
		}
		if(print) {
			//System.out.println(formula);
		}
		result = Double.parseDouble(formula);
		return result;
	}
	
	private static void initializeVars() {
		
		String id;
		String xyz;
		
		for(int i = 0;i<numVariables;i++) {
			id = variablesArray[i].substring(0, variablesArray[i].length()-1);
			xyz = "";
			xyz += variablesArray[i].charAt(variablesArray[i].length()-1);	
			//System.out.println("Id : " + id + "   xyz : " + xyz);
			for(int j = 0;j<points.size();j++) {
				if(points.get(j).getPointID()==Integer.parseInt(id)) {
					switch(xyz) {
					case "X" : 
						vars[i] = points.get(j).getX();
						break;
					case "Y" : 
						vars[i] = points.get(j).getY();
						break;
					case "Z" : 
						vars[i] = points.get(j).getZ();
						break;
					}
					//System.out.println(id + xyz + " : " + vars[i]);
					break;
				}
				if(j==points.size()) {
					System.out.println("ERROR on : " + variablesArray[i]);
				}
			}
		}
		
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
	}
	
}
