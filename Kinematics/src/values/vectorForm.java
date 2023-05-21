package values;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class vectorForm {
	
	private double vector[] = new double[4];
	double ABVector[];
	double ACVector[];
	double[] plane;
	double vectorX;
	double vectorY;
	double vectorZ;
	double vectorD;
	double magnitude;
	double magnitudeX;
	double magnitudeY;
	double magnitudeZ;
	double ABratio;
	double ABmagnitude;
	double ACmagnitude;
	double ACratio;
	double ABratio2;
	double ACratio2;
	String[] ABVectorSigns = new String[3];
	String[] ACVectorSigns = new String[3];
	String[] ABVectorSignsPerp = new String[3];
	String[] ACVectorSignsPerp = new String[3];
	String[] planeSignsPerp = new String[3];
	double p1RatioX = 1;
	double p1RatioY = 1;
	double p1RatioZ = 1;
	double p2RatioX = 1;
	double p2RatioY = 1;
	double p2RatioZ = 1;
	int vectorID;
	boolean vectorPlane = false;
	double closestPointDistance;
	double closestPointToPointDistance;
	double[] closestPoint;
	pointForm point1;
	pointForm point2;
	pointForm planePoint1;
	pointForm planePoint2;
	pointForm planePoint0;
	pointForm vectorPoint;
	ArrayList<Object> parents = new ArrayList<Object>();
	
	
	
	String ABx;
	String ABy;
	String BAy;
	String CAy;
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
	DecimalFormat df = new DecimalFormat("#");
	
	public vectorForm(pointForm p1, pointForm p2,int ID) {
		
        df.setMaximumFractionDigits(30);
		
		vectorID = ID;
		
		point1 = p1;
		point2 = p2;
		
		magnitude = generalFormulas.magnitudeBetweenPoints(p2, p1);
		magnitudeX = generalFormulas.magnitudeBetweenPointsX(p2, p1);
		magnitudeY = generalFormulas.magnitudeBetweenPointsY(p2, p1);
		magnitudeZ = generalFormulas.magnitudeBetweenPointsZ(p2, p1);
		//System.out.println("vector : " + vectorID + " / " + magnitude);
	}
	
	public void vectorPlaneSetup() {
		//System.out.println("Vector ID : " + vectorID + " point ID's : " + point1.getPointID() + " & " + point2.getPointID());
		addVectorPlane();
		//System.out.println("Vector ID : " + vectorID + "  Has Plane : " + vectorPlane);
		
		//test code
		if(vectorPlane) {
			//Dunno about this one yet?
			addVectors();
			adjustForMagnitudeCorrections();
			createVectorFormulas();
		}
		
	}
	
	private void addVectors() {
		ABVector = generalFormulas.vectorFromTwoPoints(planePoint0, planePoint1);
		if(ABVector[0]<0) {
			ABVectorSigns[0] = "-";
		}else {
			ABVectorSigns[0] = "+";
		}
		if(ABVector[1]<0) {
			ABVectorSigns[1] = "-";
		}else {
			ABVectorSigns[1] = "+";
		}
		if(ABVector[2]<0) {
			ABVectorSigns[2] = "-";
		}else {
			ABVectorSigns[2] = "+";
		}
		//System.out.println(ABVector[0]+", "+ABVector[1]+", "+ABVector[2]);
		ACVector = generalFormulas.vectorFromTwoPoints(planePoint0, planePoint2);
		if(ACVector[0]<0) {
			ACVectorSigns[0] = "-";
		}else {
			ACVectorSigns[0] = "+";
		}
		if(ACVector[1]<0) {
			ACVectorSigns[1] = "-";
		}else {
			ACVectorSigns[1] = "+";
		}
		if(ACVector[2]<0) {
			ACVectorSigns[2] = "-";
		}else {
			ACVectorSigns[2] = "+";
		}
	}
	
	//EXPERIMENTAL CODE BLOCK
	private void adjustForMagnitudeCorrections() {
		
		double[][] points = new double[][] {
			{planePoint0.getX(),planePoint0.getY(),planePoint0.getZ()},
			{planePoint1.getX(),planePoint1.getY(),planePoint1.getZ()},
			{planePoint2.getX(),planePoint2.getY(),planePoint2.getZ()},
		};
		
		ABmagnitude = generalFormulas.magnitudeBetweenPoints(planePoint1, planePoint0);
		ACmagnitude = generalFormulas.magnitudeBetweenPoints(planePoint2, planePoint0);
		
		plane = generalFormulas.planeFromPoints(points, 3);
		closestPoint = generalFormulas.pointOnPlaneClosestToPoint(plane, vectorPoint);
		closestPointDistance = generalFormulas.magnitudeBetweenPoints(planePoint0, closestPoint);
		if(vectorPoint.getX()-closestPoint[0]<0) {
			ABVectorSignsPerp[0] = "-";
		}else {
			ABVectorSignsPerp[0] = "+";
		}
		if(vectorPoint.getY()-closestPoint[0]<0) {
			ABVectorSignsPerp[1] = "-";
		}else {
			ABVectorSignsPerp[1] = "+";
		}
		if(vectorPoint.getZ()-closestPoint[0]<0) {
			ABVectorSignsPerp[2] = "-";
		}else {
			ABVectorSignsPerp[2] = "+";
		}
		double temp = 0;
		closestPointToPointDistance = generalFormulas.magnitudeBetweenPoints(vectorPoint, closestPoint);
		if(temp<0) {
			closestPointToPointDistance = -closestPointToPointDistance;
		}
		//System.out.println("PLANE : " + plane[0] + "x "+ plane[1] + "y "+ plane[2] + "z "+ plane[3] + "c " );
		
		double[] pointHolder = generalFormulas.pointOnLineCloestToPoint(planePoint1, planePoint0, vectorPoint);
		ABratio = ((generalFormulas.magnitudeBetweenPoints(planePoint0,pointHolder))/(ABmagnitude));
		ABratio2 = (generalFormulas.magnitudeBetweenPoints(closestPoint,pointHolder))/(ABmagnitude);
		pointHolder = generalFormulas.pointOnLineCloestToPoint(planePoint2, planePoint0, vectorPoint);
		ACratio = (generalFormulas.magnitudeBetweenPoints(planePoint0,pointHolder))/(ACmagnitude);
		ACratio2 = (generalFormulas.magnitudeBetweenPoints(closestPoint,pointHolder))/(ACmagnitude);
		
		//Setup figuring out if plane perpendicular is positive or negative
		if(true) {
			planeSignsPerp[0] = "-";
		}else {
			planeSignsPerp[0] = "+";
		}
		
		System.out.println("closest Point Distance : " + closestPointDistance);
		System.out.println("AB ratio : " + ABratio + "  AC ratio : " + ACratio);
		//System.out.println("ignore ; " + generalFormulas.pointOnLineCloestToPoint(planePoint1, planePoint0, vectorPoint));
		//System.out.println("ignore ; " + generalFormulas.pointOnLineCloestToPoint(planePoint2, planePoint0, vectorPoint));
		
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
		//BAy = "(%" + planePoint0.getPointID() +"Y%-%" + planePoint1.getPointID() + "Y%)";//*" + p1RatioY + ")";
		
		//ACy = (p2y-p0y) * ratio
		ACy = "(%" + planePoint2.getPointID() +"Y%-%" + planePoint0.getPointID() + "Y%)";//*" + p2RatioY + ")";
		//CAy = "(%" + planePoint0.getPointID() +"Y%-%" + planePoint1.getPointID() + "Y%)";//*" + p2RatioY + ")";
		//ABz = (p1z-p0z) * ratio
		ABz = "(%" + planePoint1.getPointID() +"Z%-%" + planePoint0.getPointID() + "Z%)";//*" + p1RatioZ + ")";
		
		//ACz = (p2z-p0z) * ratio
		ACz = "(%" + planePoint2.getPointID() +"Z%-%" + planePoint0.getPointID() + "Z%)";//*" + p2RatioZ + ")";
		
		String normalX = "((" + ABy + "*" + ACz + ")-(" + ABz + "*" + ACy + "))" ;
		String normalY = "((" + ABx + "*" + ACz + ")-(" + ABz + "*" + ACx + "))" ;
		String normalZ = "((" + ABy + "*" + ACx + ")-(" + ABx + "*" + ACy + "))" ;
		String normalize = "(√(((" + normalX + "*" + normalX + ")+(" + normalY + "*" + normalY + "))+(" + normalZ + "*" + normalZ + ")))";
		String planeX = "(" + normalX + "/" + normalize + ")";
		String planeY = "(" + normalY + "/" + normalize + ")";
		String planeZ = "(" + normalZ + "/" + normalize + ")";
		
		
		//VectorX = i((AByACz)-(ACyABz))
		vectorXformula = "+(i*(("+ABy+"*"+ACz+")-("+ACy+"*"+ABz+")))";
		
		//vectorPointX = point0x + ((AByACz)-(ACyABz))
		vectorPointX = "+(%"+vectorPoint.getPointID()+ "X%)-(+%" + planePoint0.getPointID() + "X%+(" +"((+"+ABx+"*"+ABratio+")-(+"+ABratio2+"*((+"+ABy+"*"+planeZ+")+(+"+ABz+"*"+planeY+"))))+("+planeX+"*"+closestPointToPointDistance+")))";
		
		//VectorY = j((ACxABz)-(ABxACz))
		vectorYformula = "+(j*(("+ACx+"*"+ABz+")-("+ABx+"*"+ACz+")))";
		
		//vectorPointY = point0y + j((ACxABz)-(ABxACz))
		vectorPointY ="+(%"+vectorPoint.getPointID()+  "Y%)-(+%" + planePoint0.getPointID() + "Y%+(" +"((+"+ACy+"*"+ACratio+")-(+"+ACratio2+"*((+"+ACx+"*"+planeZ+")+(+"+ACz+"*"+planeX+"))))+("+planeY+"*"+closestPointToPointDistance+")))";
		
		//VectorZ = k((ABxACy)-(AByACx))
		vectorZformula = "+(k*(("+ABx+"*"+ACy+")-("+ABy+"*"+ACx+")))";
		
		//vectorPointZ = point0z + k((ABxACy)-(AByACx))
		//vectorPointZ = "+(%"+vectorPoint.getPointID()+ "Z%)-(+%" + planePoint0.getPointID() + "Z%+(" +"((+"+ABz+"*"+df.format(ABratio)+")-(+"+ABratio2+"*((+"+ABy+"*"+planeX+")+(+"+ABx+"*"+planeY+"))))+("+planeZ+"*"+closestPointToPointDistance+")))";
		vectorPointZ = "+(%"+vectorPoint.getPointID()+ "Z%)-(+%" + planePoint0.getPointID() + "Z%+((" +"((" + ABVectorSigns[2] + ABz+"*"+df.format(ABratio)+")+(((("+ABVectorSignsPerp[2]+df.format(ABratio2)+"*((("+ABy+"*"+plane[0]+"))+(((("+ABx+")*"+(plane[1])+")))))))+("+(-plane[2])+"*"+df.format(closestPointToPointDistance)+"))))))";
		
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
