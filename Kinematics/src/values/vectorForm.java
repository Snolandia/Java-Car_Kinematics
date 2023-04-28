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
	double p1RatioX = 1;
	double p1RatioY = 1;
	double p1RatioZ = 1;
	double p2RatioX = 1;
	double p2RatioY = 1;
	double p2RatioZ = 1;
	int vectorID;
	boolean vectorPlane = false;
	pointForm point1;
	pointForm point2;
	pointForm planePoint1;
	pointForm planePoint2;
	pointForm planePoint0;
	pointForm vectorPoint;
	ArrayList<Object> parents = new ArrayList<Object>();
	
	String ABx;
	String ABy;
	String ABz;
	String ACx;
	String ACy;
	String ACz;
	String vectorXformula;
	String vectorPointX;
	String vectorYformula;
	String vectorPointY;
	String vectorZformula;
	String vectorPointZ;
	String vectorFormula;
	String vectorPlaneFormula;
	
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
		//System.out.println("Vector ID : " + vectorID + " point ID's : " + point1.getPointID() + " & " + point2.getPointID());
		addVectorPlane();
		//System.out.println("Vector ID : " + vectorID + "  Has Plane : " + vectorPlane);
		
		//test code
		if(vectorPlane) {
			//Dunno about this one yet?
			//adjustForMagnitudeCorrections();
			createVectorFormulas();
		}
		
	}
	
	//EXPERIMENTAL CODE BLOCK
	private void adjustForMagnitudeCorrections() {
		
		if (magnitudeX == 0) {
			p1RatioX = 0;
		}else {
			p1RatioX = magnitudeX/generalFormulas.magnitudeBetweenPointsX(planePoint0, planePoint1);
		}
		if (magnitudeY == 0) {
			p1RatioY = 0;
		}else {
			p1RatioY = magnitudeY/generalFormulas.magnitudeBetweenPointsY(planePoint0, planePoint1);
		}
		if (magnitudeZ == 0) {
			p1RatioZ = 0;
		}else {
			p1RatioZ = magnitudeZ/generalFormulas.magnitudeBetweenPointsZ(planePoint0, planePoint1);
		}
		if (magnitudeX == 0) {
			p2RatioX = 0;
		}else {
			p2RatioX = magnitudeX/generalFormulas.magnitudeBetweenPointsX(planePoint0, planePoint2);
		}
		if (magnitudeY == 0) {
			p2RatioY = 0;
		}else {
			p2RatioY = magnitudeY/generalFormulas.magnitudeBetweenPointsY(planePoint0, planePoint2);
		}
		if (magnitudeZ == 0) {
			p2RatioZ = 0;
		}else {
			p2RatioZ = magnitudeZ/generalFormulas.magnitudeBetweenPointsZ(planePoint0, planePoint2);
		}
		
		
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
		
		if (point1.getdOF()!=0) {
			vectorPoint = point2;
			point = point1;
			otherPoint = point2;
			planePoint0 = point1;
		}else if (point2.getdOF()!=0){
			vectorPoint = point1;
			point = point2;
			otherPoint = point1;
			planePoint0 = point2;
		}else {
			return;
		}
		
		parents = point.getParents();
		
		int parentCount = parents.size();
		
		for(int i = 0;i<parentCount;i++) {
			linkA = (linkForm)parents.get(i);
			
			if(linkA.getLinkID()==vectorID) {
				continue;
			}
			
			if(((pointForm)linkA.getChild(0)).getPointID()==point.getPointID()) {
				pointA = (pointForm)linkA.getChild(1);
			}else if(((pointForm)linkA.getChild(1)).getPointID()==point.getPointID()) {
				pointA = (pointForm)linkA.getChild(0);
			}else {
				continue;
			}
			
			littleParents = pointA.getParents();
			littleCount = littleParents.size();
			for(int j = 0;j<littleCount;j++) {
				
				linkB = (linkForm)littleParents.get(j);
				
				if(((pointForm)linkB.getChild(0)).getPointID()==otherPoint.getPointID()) {
					pointB = (pointForm)linkB.getChild(1);
				}else if(((pointForm)linkB.getChild(1)).getPointID()==otherPoint.getPointID()) {
					pointB = (pointForm)linkB.getChild(0);
				}else {
					continue;
				}
				
				if(planePointCounter == 0) {
					planePoint1 = pointA;
					planePointCounter++;
					continue;
				}else if(planePointCounter == 1) {
					planePoint2 = pointA;
					planePointCounter++;
					vectorPlane = true;
					return;
				}else {
					return;
				}
			}
		}
	}
	
	public void createVectorFormulas() {
		
		//ABx = (p1x-) * ratio
		ABx = "(%" + planePoint1.getPointID() +"X%-%" + planePoint0.getPointID() + "X%)";//*" + p1RatioX + ")";
		
		//ACx = (p2x-p0x) * ratio
		ACx = "(%" + planePoint2.getPointID() +"X%-%" + planePoint0.getPointID() + "X%)";//*" + p2RatioX + ")";
		
		//ABy = (p1y-p0y) * ratio
		ABy = "(%" + planePoint1.getPointID() +"Y%-%" + planePoint0.getPointID() + "Y%)";//*" + p1RatioY + ")";
		
		//ACy = (p2y-p0y) * ratio
		ACy = "(%" + planePoint2.getPointID() +"Y%-%" + planePoint0.getPointID() + "Y%)";//*" + p2RatioY + ")";
		
		//ABz = (p1z-p0z) * ratio
		ABz = "(%" + planePoint1.getPointID() +"Z%-%" + planePoint0.getPointID() + "Z%)";//*" + p1RatioZ + ")";
		
		//ACz = (p2z-p0z) * ratio
		ACz = "(%" + planePoint2.getPointID() +"Z%-%" + planePoint0.getPointID() + "Z%)";//*" + p2RatioZ + ")";
		
		//VectorX = i((AByACz)-(ACyABz))
		vectorXformula = "+(i*(("+ABy+"*"+ACz+")-("+ACy+"*"+ABz+")))";
		
		//vectorPointX = point0x + ((AByACz)-(ACyABz))
		vectorPointX = "+%" + planePoint0.getPointID() + "X%+(%" + vectorID + "i%*(("+ABy+"*"+ACz+")-("+ACy+"*"+ABz+")))";
		
		//VectorY = j((ACxABz)-(ABxACz))
		vectorYformula = "+(j*(("+ACx+"*"+ABz+")-("+ABx+"*"+ACz+")))";
		
		//vectorPointY = point0y + j((ACxABz)-(ABxACz))
		vectorPointY = "+%" + planePoint0.getPointID() + "Y%+(%" + vectorID + "j%*(("+ACx+"*"+ABz+")-("+ABx+"*"+ACz+")))";
		
		//VectorZ = k((ABxACy)-(AByACx))
		vectorZformula = "+(k*(("+ABx+"*"+ACy+")-("+ABy+"*"+ACx+")))";
		
		//vectorPointZ = point0z + k((ABxACy)-(AByACx))
		vectorPointZ = "+%" + planePoint0.getPointID() + "Z%+(%" + vectorID + "k%*(("+ABx+"*"+ACy+")-("+ABy+"*"+ACx+")))";
		
		//Vector = i((AByACz)-(ACyABz))+j((ACxABz)-(ABxACz))+k((ABxACy)-(AByACx))
		vectorFormula = "+(i*((AByACz)-(ACyABz))+j((ACxABz)-(ABxACz))+k((ABxACy)-(AByACx)))";
		
		//VectorPlane = i((AByACz)-(ACyABz))+j((ACxABz)-(ABxACz))+k((ABxACy)-(AByACx))
		vectorPlaneFormula = "+(i((AByACz)-(ACyABz))+j((ACxABz)-(ABxACz))+k((ABxACy)-(AByACx)))";
	}
	
	public String getVectorPlaneFormula() {
		//ABx = (p1x-p0x) * ratio
		//ACx = (p2x-p0x) * ratio
		//ABy = (p1y-p0y) * ratio
		//ACy = (p2y-p0y) * ratio
		//ABz = (p1z-p0z) * ratio
		//ACz = (p2z-p0z) * ratio
		//Vector = i((AByACz)-(ACyABz))+j((ACxABz)-(ABxACz))+k((ABxACy)-(AByACx))
		return vectorPlaneFormula;
	}
	
	public String getVectorFormula() {
		//ABx = (p1x-p0x) * ratio
		//ACx = (p2x-p0x) * ratio
		//ABy = (p1y-p0y) * ratio
		//ACy = (p2y-p0y) * ratio
		//ABz = (p1z-p0z) * ratio
		//ACz = (p2z-p0z) * ratio
		//Vector = i((AByACz)-(ACyABz))+j((ACxABz)-(ABxACz))+k((ABxACy)-(AByACx))
		return vectorFormula;
	}
	
	public String getVectorXFormula() {
		//ABy = (p1y-p0y) * ratio
		//ACy = (p2y-p0y) * ratio
		//ABz = (p1z-p0z) * ratio
		//ACz = (p2z-p0z) * ratio
		//VectorX = i((AByACz)-(ACyABz))
		//vectorPointX = point0x + ((AByACz)-(ACyABz))
		return vectorPointX;
	}
	
	public String getVectorYFormula() {
		//ABx = (p1x-p0x) * ratio
		//ACx = (p2x-p0x) * ratio
		//ABz = (p1z-p0z) * ratio
		//ACz = (p2z-p0z) * ratio
		//VectorY = j((ACxABz)-(ABxACz))
		//vectorPointY = point0y + j((ACxABz)-(ABxACz))
		return vectorPointY;
	}

	public String getVectorZFormula() {
		//ABx = (p1x-p0x) * ratio
		//ACx = (p2x-p0x) * ratio
		//ABy = (p1y-p0y) * ratio
		//ACy = (p2y-p0y) * ratio
		//VectorZ = k((ABxACy)-(AByACx))
		//vectorPointZ = point0z + k((ABxACy)-(AByACx))
		
		return vectorPointZ;
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
