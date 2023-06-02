package values;

import java.util.ArrayList;

public class pointForm {
	
	double pointX;
	double pointY;
	double pointZ;
	double dOF; //how many axis this point can rotate around
	boolean shared;
	boolean fixed;
	int pointID;
	
	ArrayList<Object> parents = new ArrayList<Object>();
	
	public pointForm(double x, double y, double z, double degreesOfFreedom, boolean shar,boolean fix,int IDNum) {
		
		pointX = x;
		pointY = y;
		pointZ = z;
		dOF = degreesOfFreedom;
		shared = shar;
		fixed = fix;
		pointID = IDNum;
		
		//System.out.println("point id " + pointID + " xyz (" + pointX + ", " + pointY + ", " + pointZ + ")");
	}
	
	public void addParent(Object parent) {
		parents.add(parent);
	}
	
	public ArrayList<Object> getParents() {
		return parents;
	}
	
	public int getPointID() {
		return pointID;
	}
	
	public double getX() {
		return pointX;
	}
	
	public double getY() {
		return pointY;
	}
	
	public double getZ() {
		return pointZ;
	}
	
	public double getdOF() {
		return dOF;
	}
	
	public boolean getShared() {
		return shared;
	}
	
	public boolean getFixed() {
		return fixed;
	}
	
	public String getType() {
		return "point";
	}
	
	
	
	
}
