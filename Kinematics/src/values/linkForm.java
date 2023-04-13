package values;

import java.util.ArrayList;

import javafx.scene.Group;

public class linkForm {
	
	boolean fixedLength;
	boolean sharedArray[] = new boolean[2];
	boolean fixedArray[] = new boolean[2];
	int numOfShared = 0;
	int numOfFixed = 0;
	int numOfPoints = 0;
	double length;
	int linkID;
	int pointIDs[] = new int[2];
	ArrayList<Object> parents = new ArrayList<Object>();
	ArrayList<pointForm> points = new ArrayList<pointForm>();
	ArrayList<Object> children = new ArrayList<Object>();
	vectorForm vector;
	
	public linkForm(boolean fixed,int IDnum){
		
		linkID = IDnum;
		fixedLength = fixed;
		
	}
	
	public void addPoint(int num, pointForm point, boolean shared,boolean fixed) {
		
		if(shared) {numOfShared++;}
		if(fixed) {numOfFixed++;}
		numOfPoints++;
		sharedArray[num] = shared;
		fixedArray[num] = fixed;
		pointIDs[num]=point.getPointID();
		
		children.add(point);
		point.addParent(this);
		points.add(point);
		
		if (numOfPoints==2) {
			setLength();
			vector = new vectorForm((pointForm)points.get(0),(pointForm)points.get(1),linkID);
			vector.addParent(this);
		}
		
	}
	
	public void addParent(Object parent) {
		parents.add(parent);
	}
	
	public ArrayList<Object> getParents() {
		return parents;
	}
	
	public vectorForm getVector() {
		return vector;
	}
	
	public pointForm getPoint(int index) {
		return points.get(index);
	}
	
	public Object getChild(int index) {
		return children.get(index);
	}
	
	public int getLinkID() {
		return linkID;
	}
	
	public int getnumFixed() {
		return numOfFixed;
	}
	
	public int getnumShared() {
		return numOfShared;
	}
	
	public boolean getFixed(int pointNum) {
		return fixedArray[pointNum];
	}
	
	public boolean getShared(int pointNum) {
		return sharedArray[pointNum];
	}
	
	public double getLength() {
		return length;
	}
	
	public void setLength() {
		double x1 = points.get(0).getX();
		double x2 = points.get(0).getX();
		double y1 = points.get(0).getX();
		double y2 = points.get(0).getX();
		double z1 = points.get(0).getX();
		double z2 = points.get(0).getX();
		
		length = Math.sqrt((((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))+((z1-z2)*(z1-z2))));
		
	}
	
	public void getLinkFormula() {
		// 0 = X^2 + Y^2 + Z^2 - length^2
		// 0 = (x1-x2)^2 + (y1-y2)^2+(z1-z2)^2 - length^2
	}
	
	public String getType() {
		return "link";
	}
	
}













