package values;

import java.util.ArrayList;

public class jacobianMatrix {
	
	String[][] matrix;
	int count = 0;
	String[] vArray;
	String[] fArray;
	rigidBodyForm rigid;
	
	public jacobianMatrix(String[] functionsArray, String[] variableArray, rigidBodyForm rForm) {
		
		System.out.println("JACOBIAN MATRIX");
		vArray = variableArray;
		fArray = functionsArray;
		rigid = rForm;
		
		if(functionsArray.length!=variableArray.length) {
			System.out.println("NOT SQUARE;IMPLEMENT LATER?");
		}
		
		int arrayLength = functionsArray.length;
		int arrayWidth = variableArray.length;
		String partialDerivative;
		
		matrix = new String[arrayLength][arrayWidth];
		for(int i = 0; i < arrayWidth;i++) {
			System.out.print(variableArray[i] + " | ");
		}
		System.out.println();
		for(int length = 0;length<arrayLength;length++) {
			
			for(int width = 0;width<arrayWidth;width++) {
				partialDerivative = calculatePartialDerivative(functionsArray[length],variableArray[width]);
				matrix[length][width] = partialDerivative;
				System.out.print(matrix[length][width] + " | ");
			}
			System.out.println();
		}
		
		
	}
	
	private String calculatePartialDerivative(String top, String bottom) {
		
		String partialDerivative;
		
		
		
		if(top.contains(")")) {
			partialDerivative = locateVariableInParenthesis(top,bottom);
			}else {
				partialDerivative = top;
			}
		if(partialDerivative!="0") {
			
			//System.out.println("///////////////////////////////////");
			//System.out.println("top : " + top);
			//System.out.println(" bottom : " + bottom);
			partialDerivative = expandString(partialDerivative);
			//System.out.println("expand string : " + partialDerivative);
			partialDerivative = locateVariable(partialDerivative,bottom);
			//System.out.println("locate Variable : " + partialDerivative);
			partialDerivative = partiallyDeriveModifiedString(partialDerivative,bottom);
			//System.out.println("partially Derive : " + partialDerivative);
			//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			partialDerivative = simplify(partialDerivative);
			partialDerivative = replaceConstants(partialDerivative);
		}
		
		return partialDerivative;
	}
	
	private String replaceConstants(String string) {
		
		String modifiedString = "error";
		boolean isVariable = false;
		String holder = string;
		String pointID = "";
		String variable = "";
		String xyz = "";
		String value = "";
		String operation = "";
		String opValue = "";
		int count = 0;
		int beg = 0;
		ArrayList<pointForm> points = rigid.getPointList();
		
		//System.out.println(holder);
		
		if(holder.equals("-%1i%*%10Z%+%1i%*%4Z%")) {
			isVariable = false;
		}
		
		for(int i = 0;i<holder.length();i++) {
			isVariable = false;
			opValue = "";
			count = 0;
			if(holder.charAt(i)=='%') {
				if(i>0) {
					if(holder.charAt(i-1)=='*') {
						operation = "*";
						count = i-2;
						do {
							if (Character.isDigit(holder.charAt(count))==false) {
								break;
							}
							opValue = holder.charAt(count) + opValue;
							count--;
						}while(count>=0);
					}
				}
				beg = i;
				i++;
				do {
					if(holder.charAt(i)=='%') {
						break;
					}
					variable += holder.charAt(i);
					i++;
				}while(i<holder.length());
				for(int j = 0;j<vArray.length;j++) {
					if(variable.equals(vArray[j])) {
						variable = "";
						isVariable = true;
						i++;
						break;
					}
				}
				if(isVariable==false) {
					pointID = variable.substring(0,variable.length()-1);
					xyz = variable.substring(variable.length()-1,variable.length());
					for(int k = 0;k<points.size();k++) {
						if(points.get(k).getPointID() == Integer.parseInt(pointID)) {
							if(xyz.equals("X")) {
								if(opValue.equals("")) {
									value = "" + points.get(k).pointX;
								}else {
									value = "" + (points.get(k).pointX * Double.parseDouble(opValue));
								}
							}else if(xyz.equals("Y")) {
								if(opValue.equals("")) {
									value = "" + points.get(k).pointY;
								}else {
									value = "" + (points.get(k).pointY * Double.parseDouble(opValue));
								}
							}else if(xyz.equals("Z")) {
								if(opValue.equals("")) {
									value = "" + points.get(k).pointZ;
								}else {
									value = "" + (points.get(k).pointZ * Double.parseDouble(opValue));
								}
							}else {
									value = "%" + variable + "%";
							}
							if(opValue.equals("")) {
								holder = holder.substring(0,beg) + value + holder.substring(i+1,holder.length());
							}else {
								holder = holder.substring(0,count) + value + holder.substring(i+1,holder.length());
							}
							//i+=2;
							i+= Math.abs(value.length() - (variable.length()+2));
							variable = "";
							break;
						}
					}
				}
			}
		}
		modifiedString = holder;
		return modifiedString;
	}
	
	private String simplify(String string) {
		
		String modifiedString = "error";
		int len = string.length();
		String holder = string;
		String variable = "";
		String operation = "";
		String operation2 = "";
		String variable2 = "";
		String subOper = "";
		String operString = "";
		String miniHolder = "";
		boolean skip = false;
		int beg = 0;
		int end = 0;
		int beg1 = 0;
		int end1 = 0;
		int count = 0;
		
		//System.out.println("old String : " + holder);
		
		if(holder.contains("*1")||holder.contains("1*")) {
			for(int i = 0; i<holder.length()-1;i++) {
				//System.out.println(holder.substring(i,i+2));
				if(holder.substring(i,i+2).equals("*1")||holder.substring(i,i+2).equals("1*")) {
						holder = holder.substring(0,i) + holder.substring(i+2,holder.length());	
				}
			}
		}
		
		//System.out.println("semi mod String : " + holder);
		if(holder.contains("+%1i%*%4Z%-%1i%*%2Z%")) {
			skip = false;
		}
		
		for(int i = 0; i<holder.length()-1;i++) {
			if(holder.charAt(i)=='%') {
				if(i==0) {
					operation = "+";
				}else {
					if(holder.charAt(i-1)=='*') {
						i++;
						do {
							if(holder.charAt(i)=='+'||holder.charAt(i)=='-') {
								break;
							}
							i++;
						}while(i<holder.length());
						continue;
					}else {
						operString = "";
						operation = "" + holder.charAt(i-1);
					}
				}
				beg = i;
				i++;				
				do {
					if(holder.charAt(i)=='%') {
						if(holder.charAt(i+1)=='*') {
							i++;
							do {
								if(holder.charAt(i)=='+'||holder.charAt(i)=='-') {
									break;
								}else {
								end = i+1;
								variable = holder.substring(beg,i+1);
								i++;
								}
							}while(i<holder.length());
							break;
						}else {
						end = i+1;
						variable = holder.substring(beg,i+1);
						i++;
						break;
						}
					}
					i++;
				}while (i<holder.length());
				
				skip = false;
				
				for(int j = i; j<holder.length();j++) {
					if(holder.charAt(j)=='%') {
						if(holder.charAt(j-1)=='*') {
							break;
						}
						beg1 = j;
						j++;
						do {
							if(holder.charAt(j)=='%') {
								if(j<holder.length()-1) {
									if(holder.charAt(j+1)=='*') {
										j++;
										do {
											if(holder.charAt(j)=='+'||holder.charAt(j)=='-') {
												break;
											}else {
											end1 = j+1;
											variable2 = holder.substring(beg1,j+1);
											j++;
											}
										}while(j<holder.length());
										end1 = j;
										variable2 = holder.substring(beg1,j);
										if(variable.equals(holder.substring(beg1,j))) {
											if(operString.equals("")) {
												if(operation.equals("+")||operation.equals("-")) {
													operation2 = "" + holder.charAt(beg1-1);
													if(operation.equals("" + holder.charAt(beg1-1))) {
														miniHolder = operation + "2*" + variable;
														i += 1;
													}else {
														miniHolder = "";
													}
												}
												holder = miniHolder + holder.substring(0,beg-1) + holder.substring(end,beg1-1) + holder.substring(end1,holder.length()) ;
												skip = true;
											}else {
												
											}
											break;
										}else {
											break;
										}
									}else {
										end1 = j+1;
										variable2 = holder.substring(beg1,j+1);
										if(variable.equals(holder.substring(beg1,j+1))) {
											if(operString.equals("")) {
												if(operation.equals("+")||operation.equals("-")) {
													operation2 = "" + holder.charAt(beg1-1);
													if(operation.equals("" + holder.charAt(beg1-1))) {
														miniHolder = operation + "2*" + variable;
														i += 1;
													}else {
														miniHolder = "";
													}
												}
												holder = miniHolder + holder.substring(0,beg-1) + holder.substring(end,beg1-1) + holder.substring(end1,holder.length()) ;
												skip = true;
											}else {
												
											}
											break;
										}else {
											break;
										}
									}
								}else {
									end1 = j+1;
									variable2 = holder.substring(beg1,j+1);
									if(variable.equals(holder.substring(beg1,j+1))) {
										if(operString.equals("")) {
											if(operation.equals("+")||operation.equals("-")) {
												operation2 = "" + holder.charAt(beg1-1);
												if(operation.equals("" + holder.charAt(beg1-1))) {
													miniHolder = operation + "2*" + variable;
													i += 1;
												}else {
													miniHolder = "";
												}
											}
											holder = miniHolder + holder.substring(0,beg-1) + holder.substring(end,beg1-1) + holder.substring(end1,holder.length()) ;
											skip = true;
										}else {
											
										}
										break;
									}else {
										break;
									}
								}
							}
							j++;
						}while (j<holder.length());
					}
					if(skip) {
						break;
					}
				}
			}
		}
		
		modifiedString = holder;
		
		//System.out.println("new string simplified : " + modifiedString);
		
		return modifiedString;
	}
	
	private String partiallyDeriveModifiedString(String string, String variable) {
		
		String modifiedString = "error";
		String holder = "";
		String miniHolder;
		String variableHolder;
		String value = "";
		String valueOp = "";
		String operation = "";
		String expoValue = "";
		int count;
		
		if(string.contains("%" + variable + "%") & string.contains(")")==false & string.contains("(")==false) {
			modifiedString = "";
			for(int i = 0;i<string.length();i++) {
				if (string.charAt(i)=='+' || string.charAt(i)=='-' || i==string.length()-1) {
					if (i==string.length()-1){
						holder += string.charAt(i);
					}
					if (holder.contains(variable)) {	
						miniHolder = "";
						operation = "";
						value = "";
						valueOp = "";
						if(holder.contains("^")){
							variableHolder = "";
							for(int j = 0;j<holder.length();j++) {
								if(holder.charAt(j)=='%' & variableHolder.contains("%")==false) {
									variableHolder = "" + holder.charAt(j);
								}else if(holder.charAt(j)=='%' & variableHolder.contains(variable)==false) {
									variableHolder += holder.charAt(j);
									if(miniHolder != "") {
										miniHolder += valueOp + value + variableHolder;
									}else {
										miniHolder += operation + value + variableHolder;
									}
									variableHolder = "";
									value = "";
									valueOp = "";
								}else if(holder.charAt(j)=='+' || holder.charAt(j)=='-') {
									operation = "" + holder.charAt(j);
								}else if(holder.charAt(j)=='%' & variableHolder.contains(variable)) {
									variableHolder += holder.charAt(j);
									if(j==holder.length()-1) {
										if (miniHolder == "") {
											miniHolder += operation + value + "1";
											valueOp = "";
											value = "";
										}else {
											miniHolder += valueOp + value + "1";
											valueOp = "";
											value = "";
										}
									}else {
										if(holder.charAt(j+1)=='^') {
											count = j+2;
											j = j + 2;
											expoValue = "";
											do {
												if(Character.isDigit(holder.charAt(count))) {
												expoValue += holder.charAt(count);
												count++;
												j++;
												}else {
													count = holder.length();
												}
											}while (count<holder.length());
											
											if(Integer.parseInt(expoValue)>2){
												variableHolder = variableHolder + "^" + (Integer.parseInt(expoValue)-1);
											}
										}else {
											variableHolder = "error";
										}
										if (miniHolder == "") {
											miniHolder += operation + Integer.parseInt(expoValue) + "*" +  variableHolder;
											variableHolder = "";
											valueOp = "";
											value = "";
										}else {
											miniHolder += valueOp + value + Integer.parseInt(expoValue) + "*" + variableHolder;
											variableHolder = "";
											valueOp = "";
											value = "";
										}
									}
								}else if (variableHolder.contains("%")==false) {
									if (value=="" & Character.isDigit(holder.charAt(j))==false){
										valueOp = "" + holder.charAt(j);
										if(holder.charAt(j+1)!='%') {
											value = "" + holder.charAt(j+1);
											j++;
										}
									}else {
										value += holder.charAt(j);
									}
								}else {
									variableHolder += holder.charAt(j);
								}
							}
						}else {
							 
							variableHolder = "";
							value = "";
							valueOp = "";
							for(int j = 0;j<holder.length();j++) {
								if(holder.charAt(j)=='+' || holder.charAt(j)=='-') {
									operation = "" + holder.charAt(j);
								}else if(holder.charAt(j)=='%' & variableHolder.contains("%")==false) {
									variableHolder = "" + holder.charAt(j);
								}else if(holder.charAt(j)=='%'){
									variableHolder += holder.charAt(j);
									if(variableHolder.contains(variable)) {
										if(miniHolder!="") {
											miniHolder += valueOp + value + "1";
										}else {
											miniHolder += operation + value + "1";
										}
										variableHolder = "";
										value = "";
										valueOp = "";
									}else {
										if(miniHolder!="") {
											miniHolder +=  valueOp + value + variableHolder;
										}else {
											miniHolder += operation + value + variableHolder;
										}
										variableHolder = "";
										value = "";
										valueOp = "";
									}
								}else if (variableHolder.contains("%")==false) {
									if (value=="" & Character.isDigit(holder.charAt(j))==false){
										valueOp = "" + holder.charAt(j);
										if(holder.charAt(j+1)!='%') {
											value = "" + holder.charAt(j+1);
											j++;
										}
									}else {
										value += holder.charAt(j);
									}
								}else {
									variableHolder += holder.charAt(j);
								}
							}
						}
						modifiedString += miniHolder;
					}
				holder = "" + string.charAt(i);	
				}else {
					
				holder = holder + string.charAt(i);
				if(string.charAt(i)=='*') {
					holder = holder + string.charAt(i+1);
					i++;
				}
				
				}
//				if (holder.contains(variable)) {
//					if(i == string.length()-1) {
//						modifiedString = modifiedString + holder;
//					}else if(string.charAt(i+1)=='+' || string.charAt(i+1)=='-') {
//						modifiedString = modifiedString + holder;
//					}
//				}
			}
		}
		
		return modifiedString;
	}
	
	private String handleVector(String string) {
		//temporary, maybe permanent
		String modifiedString = "error";
		boolean parOpen = false;
		String holder = "";
		String miniHolder = "";
		String magVar = "error";
		int firstPar = 0;
		int lastPar = 0;
		int parBeg;
		int parEnd;
		String var1var = "";
		String var2var = "";
		String var2;
		String var1;
		String operation = "+";
		String operation2 = "";
		int parCount = 0;
		int count = 0;
		
		for(int i = 0;i<string.length();i++) {
			if(string.charAt(i)=='%') {
				firstPar = i;
			}
			if(string.charAt(i)=='i' || string.charAt(i)=='j' || string.charAt(i)=='k') {
				magVar = string.substring(firstPar,i+2);
				count = i+4;
				if(string.charAt(i+3)=='-') {
					operation = "-";
				}
				break;
			}
		}
		
		firstPar = 0;
		
		for(int i = count;i<string.length();i++) {
			if (string.charAt(i)=='%'){
					parBeg = 0;
					parEnd = 0;
					count = i;
					parCount = 0;
					do {
						if(string.charAt(count)=='*') {
							parEnd = count;
							break;
						}
						count++;
						i++;
					}while(count<string.length()) ;
					parBeg = count-1;
					do {
						if (string.charAt(parBeg)==')'){
							parCount++;
						}else if (string.charAt(parBeg)=='('){
							parCount--;
						}else if (string.charAt(parBeg)=='%'){
							parOpen = !parOpen;
						}
						if(parCount==0 & parOpen==false) {
							break;
						}
						parBeg--;
					}while(parBeg>=0);
					if (firstPar == 0) {
						firstPar = parBeg;
					}
					var1 = string.substring(parBeg,parEnd);
					parBeg = count+1;
					parEnd++;
					i++;
					do {
						if (string.charAt(parEnd)==')'){
							parCount++;
						}else if (string.charAt(parEnd)=='('){
							parCount--;
						}else if (string.charAt(parEnd)=='%'){
							parOpen = !parOpen;
						}
						if(parCount==0 & parOpen==false) {
							lastPar = parEnd+1;
							break;
						}
						parEnd++;
						i++;
					}while(parEnd<=string.length());
					var2 = string.substring(parBeg,parEnd+1);
					miniHolder = "";
					operation2 = "+";
					for(int j = 1;j<var1.length();j++) {
						if(var1.charAt(j)=='%' & parOpen == false) {
							parOpen = !parOpen;
							parBeg = j;
						}else if(var1.charAt(j)=='-') {
							if(operation == "+") {
								operation = "-";
							}else {
								operation = "+";
							}
						}else if(var1.charAt(j)=='%' & parOpen==true) {
							parOpen = !parOpen;
							var1var = var1.substring(parBeg,j+1);
							for(int k = 1;k<var2.length();k++) {
								if(var2.charAt(k)=='%' & parOpen == false) {
									parOpen = !parOpen;
									parBeg = k;
								}else if(var2.charAt(k)=='-') {
									if(operation2 == "+") {
										operation2 = "-";
									}else {
										operation2 = "+";
									}
								}else if(var2.charAt(k)=='%' & parOpen==true) {
									parOpen = !parOpen;
									var2var = var2.substring(parBeg,k+1);
									if (operation == "+" & operation2 == "+") {
										if(miniHolder!="") {
											miniHolder += "+";
										}
										miniHolder += magVar + "*" + var1var + "*" + var2var;
									}else if (operation == "-" & operation2 == "-") {
										if(miniHolder!="") {
											miniHolder += "+";
										}
										miniHolder += magVar + "*" + var1var + "*" + var2var;
									}else {
										miniHolder += "-" + magVar + "*" + var1var + "*" + var2var;
									}
									operation2 = "+";
								}
							}
						}
						
					}
					if(i+2<string.length()) {
						if(string.charAt(i+2)=='-') {
							operation = "-";
						}else {
							operation = "+";
						}
					}
					holder += miniHolder;
			}
		}
		//System.out.println("holder : " + holder);
		modifiedString = holder;
		
		return modifiedString;
	}
	
	private String expandString(String string) {
		
		String baseString = string;
		int operatorIndex;
		int parBegin;
		int parEnd;
		int parCount;
		String modifierString;
		double modifierValue;
		int count;
		
		//System.out.println("string : " + string);
		if(string.contains("i") || string.contains("j") || string.contains("k")){
			modifierString = handleVector(string);
		}else if (string.contains(")^")){
			modifierString = stringFunctionFinder(string,'^');
			modifierString = exponentHandler(modifierString);
		}else {
			modifierString = string;
		}
		
		//System.out.println("modifier string : " + modifierString);
		
		return modifierString;
	}
	
	private String locateVariable (String top, String bottom) {
		
		String modifiedString = "error";
		String holder = "";
		
		//System.out.println("locatVariable of : " + bottom);
		
		if(top.contains("%" + bottom + "%")) {
			modifiedString = "";
			for(int i = 0;i<top.length();i++) {
				if (top.charAt(i)=='+' || top.charAt(i)=='-') {
					holder = "" + top.charAt(i);
				}else {
				holder = holder + top.charAt(i);
				
				}
				if (holder.contains(bottom)) {
					if(i == top.length()-1) {
						modifiedString = modifiedString + holder;
					}else if(top.charAt(i+1)=='+' || top.charAt(i+1)=='-') {
						modifiedString = modifiedString + holder;
					}
				}
			}
		}
		
		return modifiedString;
		
	}
	
	private String exponentHandler(String string) {
		
		String baseString = string;
		int operatorIndex = -1;
		int parBegin;
		int parEnd;
		int parCount;
		String modifierValueString;
		boolean found = false;
		double modifierValue;
		int numOfVariables = 0;
		int count = 0;
		String returnString = "error";
		
		for(int i = 0;i<string.length();i++) {
			if(string.charAt(i)=='%') {
				count++;
			}
		}
		numOfVariables = count/2;
		
		if (string.contains("^")){
			
			modifierValueString = "";
			for(int i = string.length()-2;i>=0;i--) {
				parCount = 1;
				if(string.charAt(i)=='^') {
					operatorIndex = i;
					break;
				}else if(string.charAt(i)=='(') {
					System.out.println("Unhappy extra ( error");
					return "error of (";
				}else if(string.charAt(i)==')') {
					System.out.println("Unhappy extra ) error");
					return "error of )";
				}else {
					modifierValueString = string.charAt(i) + modifierValueString;
				}
			}
			parCount = 0;
			if (numOfVariables>1){
				String variablesArray[][] = new String[numOfVariables][2];
				for(int x = 0;x<numOfVariables;x++) {
					variablesArray[x][0] = "";
					variablesArray[x][1] = "";
				}
				count = numOfVariables-1;
				for(int i = operatorIndex-1;i>=0;i--) {
					if(string.charAt(i)==')') {
						parCount--;
					}else if(string.charAt(i)=='%') {
						variablesArray[count][0]= string.charAt(i) + variablesArray[count][0];
						i--;
						do {
							variablesArray[count][0]= string.charAt(i) + variablesArray[count][0];
							i--;
						}while(string.charAt(i)!='%' & i>=0);
						variablesArray[count][0]= string.charAt(i) + variablesArray[count][0];
						//Here is where to add to deal with variables inside variables
						if(string.charAt(i-1)=='-') {
							variablesArray[count][1] = "-";
						}else {
							variablesArray[count][1] = "+";
						}
						count--;
					}else if(string.charAt(i)=='(') {
						parCount++;
						if (parCount == 0) {
							returnString = "";
							for(int var = 0;var<numOfVariables;var++) {
								for(int varInner = 0;varInner<numOfVariables;varInner++) {
									if (variablesArray[var][0] == variablesArray[varInner][0]) {
										returnString = returnString + "+" + variablesArray[var][0] + "^2";
									}else if(variablesArray[var][1]=="-" ^ variablesArray[varInner][1] == "-") {
										returnString = returnString + "-" + variablesArray[var][0] + "*" +  variablesArray[varInner][0];
									}else {
										returnString = returnString + "+" + variablesArray[var][0] + "*" +  variablesArray[varInner][0];
									}
								}
							}
							break;
						}else if(parCount>0) {
							System.out.println("Unhappy parCount error");
							return "error of parCount";
						}
					}					
				}
			}
		}else {
			System.out.println("Weird Exponent Handler Error");
		}
		
		return returnString;
	}
	
	private String stringFunctionFinder(String string,char func) {
		
		
		int parCount;
		String modifierString;
		boolean found = false;
		int count;
		
		if (string.contains(String.valueOf(func))){
			 modifierString = String.valueOf(func);
			for(int i = 0;i<string.length();i++) {
				if(string.charAt(i)==func) {
					count = i;
					parCount = 1;
					do {
						count++;
						if(string.charAt(count)==')') {
							parCount--;
							if (parCount == 0){
								modifierString = modifierString + string.charAt(count);
							}
						}else {
							if(string.charAt(count)=='(') {
								parCount++;
							}
							modifierString = modifierString + string.charAt(count);
						}
					}while(count<string.length() & parCount!=0);
					
					count = i;
					parCount=1;
					do {
						count--;
						if(string.charAt(count)=='(') {
							parCount--;
							if (parCount == 0) {
								modifierString = string.charAt(count) + modifierString;
							}else {
								modifierString = string.charAt(count) + modifierString;
							}
						}else {
							if(string.charAt(count)==')') {
								parCount++;
							}
							modifierString = string.charAt(count) + modifierString;
						}
					}while(count>0 & parCount!=0);
					found = true;
					
				}
				if(found) {
					break;
				}
			}
			
		string = modifierString;
			
		}
		
		return string;
	}
	
	private String locateVariableInParenthesis (String top, String bottom) {
		
		String partialDerivative;
		
		
		@SuppressWarnings("unused")
		int parCount = 0;
		int parBegin = -1;
		int parEnd = 0;
		int subBegin = 0;
		int subEnd = 0;
		boolean partialOpen = false;
		boolean holdingPartial = false;
		String partialString = "error";
		partialDerivative = "derive f() " + top + "/f(" + bottom + ")";
		if(top.contains("%" + bottom + "%")) {
			bottom = bottom + "%";
     		for(int i = 0;i<top.length();i++) {
  				if(top.charAt(i)=='(') {
					if(parBegin==-1) {
						parBegin = i;
						partialOpen = true;
						parEnd = 0;
					}else if(parCount == 0) {
						partialOpen = true;
						parBegin = i;
						parEnd = 0;
					}
					parCount++;
  				}else if(top.charAt(i)==')') {
 					parCount--;
					if(parCount==0) {
						parBegin = -1;
						partialOpen = false;
						subEnd = i;
						parEnd = i;
					}
    			}else if(top.charAt(i)=='%') {
					if(holdingPartial==false) {	
						if (parCount == 0) {
							subBegin = i;
						}else {
							subBegin = parBegin;
						}
						i++;
						for(int j = 0;j<bottom.length();j++) {
							if(top.charAt(i)=='%') {
								if(parCount!=0) {
									subEnd = i + 1;
									holdingPartial = true;
								}else {
									holdingPartial = true;
									subEnd = i;
								}
								continue;
							}else if(top.charAt(i)==bottom.charAt(j)) {
								i++;
							}else {
								do {
									i++;
								} while (top.charAt(i)!='%' & i<top.length());
								subEnd = -1;
								break;
							}
						}
					}
    			}
  				if(partialOpen == false & holdingPartial & subEnd!=-1) {
					if(subBegin!=0) {
						subBegin--;
					}
					if(partialString == "error") {
						partialString = top.substring(subBegin,subEnd+1);
					}else {
						partialString = partialString + top.substring(subBegin,subEnd+1);
					}
					subBegin = -1;
					subEnd = -1;
					holdingPartial = false;
				}
			}
			partialDerivative = partialString;
		}else {
			partialDerivative = "0";
		}
		count++;
		return partialDerivative;
	}
	
	
	
	
	
}
