package values;

import java.util.ArrayList;


public class rigidBodyForm{
	
	int numOfLinks = 0;
	int numOfVectors = 0;
	int numOfUsedVectors = 0;
	int numOfVariablePoints = 0;
	int numOfFixed = 0;
	linkForm vectorLink;
	ArrayList<Object> children = new ArrayList<Object>();
	ArrayList<pointForm> pointList = new ArrayList<pointForm>();
	ArrayList<linkForm> linkList = new ArrayList<linkForm>();
	ArrayList<vectorForm> vectorList = new ArrayList<vectorForm>();
	pointForm finalPoint;
	
	public rigidBodyForm() {
		
		
	}
	
	private void addLinkPoints(linkForm link) {
		
		pointForm point1 = link.getPoint(0);
		
		if(pointList.contains(point1)==false) {
			pointList.add(point1);
			
			if(point1.getFixed()) {
				numOfFixed++;
			}
			else {
				numOfVariablePoints++;
			}
			
		}
		
		pointForm point2 = link.getPoint(1);
		
		if(pointList.contains(point2)==false) {
			pointList.add(point2);
			
			if(point2.getFixed()) {
				numOfFixed++;
			}
			else {
				numOfVariablePoints++;
			}
		}
	}
	
	public void setupVectorPlanes() {
		int count = vectorList.size();
		for(int i = 0;i<count;i++) {
			vectorList.get(i).vectorPlaneSetup();
		}
		
		
	}
	
	public void finalPoint(pointForm point) {
		vectorLink = (linkForm) point.getParents().get(0);
		finalPoint = point;
		numOfUsedVectors = 3;
	}
	
	private void addLinkVector(linkForm link) {
		
		vectorList.add(link.getVector());
		numOfVectors++;
		
	}
	
	public void addLink(linkForm link) {
		
		children.add(link);
		link.addParent(this);
		linkList.add(link);
		addLinkPoints(link);
		addLinkVector(link);
		
		
		numOfLinks++;
		
	}
	
	public void addVector(vectorForm vector) {
		
		vectorList.add(vector);
		
		numOfVectors++;
	}
	
	public Object getChild(int index) {
		return children.get(index);
	}
	
	public ArrayList<pointForm> getPointList() {
		return pointList;
	}
	
	public int getNumOfUsedVectors() {
		return numOfUsedVectors;
	}
	
	public int getNumOfVectors() {
		return numOfVectors;
	}
	
	public int getNumOfLinks() {
		return numOfLinks;
	}
	
	public int getNumOfVariablePoints() {
		return numOfVariablePoints;
	}
	
	public int getNumOfFixed() {
		return numOfFixed;
	}
	
	
	
	
	
}
