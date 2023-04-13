package values;

import java.util.ArrayList;
import java.util.Objects;

public class vectorForm {
	
	private double vector[] = new double[4];
	double vectorX;
	double vectorY;
	double vectorZ;
	double vectorD;
	double magnitude;
	double magnitudeX;
	double magnitudeY;
	double magnitudeZ;
	int vectorID;
	boolean vectorPlane = false;
	pointForm point1;
	pointForm point2;
	pointForm planePoint1;
	pointForm planePoint2;
	pointForm planePoint0;
	pointForm vectorPoint;
	ArrayList<Object> parents = new ArrayList<Object>();
	
	public vectorForm(pointForm p1, pointForm p2,int ID) {
		
		vectorID = ID;
		
		point1 = p1;
		point2 = p2;
		
		magnitude = generalFormulas.magnitudeBetweenPoints(p1, p2);
		magnitudeX = generalFormulas.magnitudeBetweenPointsX(p1, p2);
		magnitudeY = generalFormulas.magnitudeBetweenPointsY(p1, p2);
		magnitudeZ = generalFormulas.magnitudeBetweenPointsZ(p1, p2);
		
	}
	
	public void vectorPlaneSetup() {
		addVectorPlane();
		System.out.println("Vector ID : " + vectorID + "  Has Plane : " + vectorPlane);		
	}
	
	
	private void addVectorPlane() {
		
		int planePointCounter = 0;
		pointForm point;
		pointForm otherPoint;
		pointForm pointA;
		pointForm pointB;
		linkForm linkA;
		linkForm linkB;
		ArrayList<Object> littleParents;
		ArrayList<Object> parents;
		int littleCount;
		
		if (point1.getdOF()==0) {
			vectorPoint = point1;
			point = point1;
			otherPoint = point2;
			planePoint0 = point2;
		}else if (point2.getdOF()==0){
			vectorPoint = point2;
			point = point2;
			otherPoint = point1;
			planePoint0 = point1;
		}else {
			return;
		}
		
		parents = point1.getParents();
		
		int parentCount = parents.size();
		
		for(int i = 0;i<parentCount;i++) {
			linkA = (linkForm)parents.get(i);
			
			if(((pointForm)linkA.getChild(0)).getPointID()==point.getPointID()) {
				pointA = (pointForm)linkA.getChild(0);
			}else if(((pointForm)linkA.getChild(1)).getPointID()==point.getPointID()) {
				pointA = (pointForm)linkA.getChild(1);
			}else {
				continue;
			}
			
			littleParents = pointA.getParents();
			littleCount = littleParents.size();
			
			for(int j = 0;j<littleCount;j++) {
				linkB = (linkForm)parents.get(i);
				
				if(((pointForm)linkB.getChild(0)).getPointID()==otherPoint.getPointID()) {
					pointB = (pointForm)linkB.getChild(0);
				}else if(((pointForm)linkB.getChild(1)).getPointID()==otherPoint.getPointID()) {
					pointB = (pointForm)linkB.getChild(1);
				}else {
					continue;
				}
				
				if(planePointCounter == 0) {
					planePoint1 = pointB;
					planePointCounter++;
				}else if(planePointCounter == 1) {
					planePoint1 = pointB;
					planePointCounter++;
					vectorPlane = true;
					return;
				}else {
					return;
				}
				
				
				
			}
			
			
		}
			
			
			
		
		
		
	}
	
	public void getVectorXFormula() {
		
	}
	
	public void getVectorYFormula() {
		
	}

	public void getVectorZFormula() {
	
	}
	
	public void addParent(Object parent) {
		parents.add(parent);
	}
	
	public ArrayList<Object> getParents() {
		return parents;
	}
	
	public double[] getVector() {
		return vector;
	}
	
	public double getVectorX() {
		return vectorX;
	}
	
	public double getVectorY() {
		return vectorY;
	}
	
	public double getVectorZ() {
		return vectorZ;
	}
	
	public double getVectorD() {
		return vectorD;
	}
	
	public String getType() {
		return "vector";
	}
		
}
