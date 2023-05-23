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
	String ABratioSign = "+";
	String ABratio2Sign = "+";
	String pointToPointSign = "+";
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
	String aboveBelow;
	
	
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
		ABVector = generalFormulas.vectorFromTwoPoints(planePoint1, planePoint0);
//		if(ABVector[0]<0) {
//			ABVectorSigns[0] = "-";
//		}else {
//			ABVectorSigns[0] = "+";
//		}
//		if(ABVector[1]<0) {
//			ABVectorSigns[1] = "-";
//		}else {
//			ABVectorSigns[1] = "+";
//		}
//		if(ABVector[2]<0) {
//			ABVectorSigns[2] = "-";
//		}else {
//			ABVectorSigns[2] = "+";
//		}
		//System.out.println(ABVector[0]+", "+ABVector[1]+", "+ABVector[2]);
		ACVector = generalFormulas.vectorFromTwoPoints(planePoint2, planePoint0);
//		if(ACVector[0]<0) {
//			ACVectorSigns[0] = "-";
//		}else {
//			ACVectorSigns[0] = "+";
//		}
//		if(ACVector[1]<0) {
//			ACVectorSigns[1] = "-";
//		}else {
//			ACVectorSigns[1] = "+";
//		}
//		if(ACVector[2]<0) {
//			ACVectorSigns[2] = "-";
//		}else {
//			ACVectorSigns[2] = "+";
//		}
	}
	
	//EXPERIMENTAL CODE BLOCK
	private void adjustForMagnitudeCorrections() {
		
		//boolean print = true;
		boolean print = false;
		
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
		
		
		double temp = 0;
		closestPointToPointDistance = generalFormulas.magnitudeBetweenPoints(vectorPoint, closestPoint);
//		if(temp<0) {
//			closestPointToPointDistance = -closestPointToPointDistance;
//		}
		if(print) {
			System.out.println("PLANE : " + plane[0] + "x "+ plane[1] + "y "+ plane[2] + "z "+ plane[3] + "c " );
		}
		double[] pointHolder = generalFormulas.pointOnLineClosestToPoint(planePoint1, planePoint0, vectorPoint);
		
//		if(closestPoint[0]-pointHolder[0]<0) {
//			ABVectorSignsPerp[0] = "-";
//		}else {
//			ABVectorSignsPerp[0] = "+";
//		}
//		if(closestPoint[1]-pointHolder[1]<0) {
//			ABVectorSignsPerp[1] = "-";
//		}else {
//			ABVectorSignsPerp[1] = "+";
//		}
//		if(closestPoint[2]-pointHolder[2]<0) {
//			ABVectorSignsPerp[2] = "-";
//		}else {
//			ABVectorSignsPerp[2] = "+";
//		}
		if(ABmagnitude==0) {
			ABratio = 0;
			ABratio2 = 0;
		}else {
			ABratio = ((generalFormulas.magnitudeBetweenPoints(planePoint0,pointHolder))/(ABmagnitude));
			ABratio2 = (generalFormulas.magnitudeBetweenPoints(closestPoint,pointHolder))/(ABmagnitude);
		}
		double x = vectorPoint.getX();
		double y = vectorPoint.getY();
		double z = vectorPoint.getZ();
		double closestPointX = closestPoint[0];
		double closestPointY = closestPoint[1];
		double closestPointZ = closestPoint[2];
		double currentX = planePoint0.getX();
		double currentY = planePoint0.getY();
		double currentZ = planePoint0.getZ();
		
		double ABxPlaneY = (ABVector[0])*plane[1];
		double ABxPlaneZ = (ABVector[0])*plane[2];
		double AByPlaneX = (ABVector[1])*plane[0];
		double AByPlaneZ = (ABVector[1])*plane[2];
		double ABzPlaneX = (ABVector[2])*plane[0];
		double ABzPlaneY = (ABVector[2])*plane[1];
		
		double planeXDistance = plane[0]*closestPointToPointDistance;
		double planeYDistance = plane[1]*closestPointToPointDistance;
		double planeZDistance = plane[2]*closestPointToPointDistance;
		
		temp = pointHolder[2]-(currentZ+(ABVector[2]*ABratio));
		if(temp == 0) {
			temp = pointHolder[1]-(currentY+(ABVector[1]*ABratio));
		}
		if(temp == 0) {
			temp = pointHolder[0]-(currentX+(ABVector[0]*ABratio));
		}
		temp = Math.round(temp*1000000000.0);
		
		if(print) {
			System.out.println("temp test for ABratio : " + df.format(temp));
		}
		
		if(temp!=0) {
			ABratio = -ABratio;
			ABratioSign = "-";
		}else {
			ABratioSign = "+";
		}
		
		temp = closestPointZ - (currentZ + ((ABVector[2]*ABratio)+((ABratio2*(ABxPlaneY-AByPlaneX)))));
		if( temp ==0) {
			temp = closestPointY - (currentY + ((ABVector[1]*ABratio)+((ABratio2*(ABzPlaneX-ABxPlaneZ)))));
		}
		if( temp ==0) {
			temp = closestPointX - (currentX + ((ABVector[0]*ABratio)+((ABratio2*(AByPlaneZ-ABzPlaneY)))));
		}
		temp = Math.round(temp*1000000000.0);
		
		if(print) {
			System.out.println("temp test for ABratio2 : " + df.format(temp));
		}
		if(temp!=0) {
			ABratio2 = -ABratio2;
			ABratio2Sign = "-";
		}else {
			ABratio2Sign = "+";
		}
		temp =0 ;
		temp = z - (currentZ + ((ABVector[2]*ABratio)+((ABratio2*(ABxPlaneY-AByPlaneX))+(planeZDistance))));
		if(temp==0) {
			temp = y - (currentY + ((ABVector[1]*ABratio)+((ABratio2*(ABzPlaneX-ABxPlaneZ))+(planeYDistance))));
		}
		if(temp==0) {
			temp = x - (currentX + ((ABVector[0]*ABratio)+((ABratio2*(AByPlaneZ-ABzPlaneY))+(planeXDistance))));
		}
		temp = Math.round(temp*1000000000.0);
		if(print) {
			System.out.println("temp test for closestPoint to Point : " + df.format(temp));
		}
		if(temp!=0) {
			closestPointToPointDistance = -closestPointToPointDistance;
			pointToPointSign = "-";
		}else {
			pointToPointSign = "+";
		}

		if(print) {
			System.out.println(" test vector ab : " + ABVector[0] + ", "+ ABVector[1] + ", "+ ABVector[2] );
			System.out.println(" test ac: " + (ACVector[0]) + ", "+ (ACVector[1]) + ", "+ (ACVector[2]) );
			System.out.println(" test 0 and closestpoint: " + ((closestPoint[0]-planePoint0.getX())) + ", "+ ((closestPoint[1]-planePoint0.getY())) + ", "+ (closestPoint[2]-planePoint0.getZ()) );
			System.out.println(" test ab and point 2: " + ABVector[0]*planePoint2.getX() + ", "+ ABVector[1]*planePoint2.getY() + ", "+ ABVector[2]*planePoint2.getZ() );
			System.out.println(" test ab and closest point: " + ABVector[0]*closestPoint[0] + ", "+ ABVector[1]*closestPoint[1] + ", "+ ABVector[2]*closestPoint[2] );
		}
		pointHolder = generalFormulas.pointOnLineClosestToPoint(planePoint2, planePoint0, vectorPoint);
		ACratio = (generalFormulas.magnitudeBetweenPoints(planePoint0,pointHolder))/(ACmagnitude);
		ACratio2 = (generalFormulas.magnitudeBetweenPoints(closestPoint,pointHolder))/(ACmagnitude);
		
		//Setup figuring out if plane perpendicular is positive or negative
		
//		aboveBelow = "";
//		
//		temp = 0;
//		if(temp<0) {
//			planeSignsPerp[0] = "-";
//		}else {
//			planeSignsPerp[0] = "+";
//		}

		if(print) {
			System.out.println("closest Point Distance : " + closestPointDistance);
			System.out.println("AB ratio : " + ABratio + "  AC ratio : " + ACratio);
		}
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
		//ABx = "(%" + planePoint1.getPointID() +"X%-%" + planePoint0.getPointID() + "X%)";//*" + p1RatioX + ")";
		//ACx = (p2x-p0x) * ratio
		ACx = "(%" + planePoint2.getPointID() +"X%-%" + planePoint0.getPointID() + "X%)";//*" + p2RatioX + ")";
		//ACx = "(%" + planePoint2.getPointID() +"X%-%" + planePoint0.getPointID() + "X%)";//*" + p2RatioX + ")";
		
		//ABy = (p1y-p0y) * ratio
		ABy = "(%" + planePoint1.getPointID() +"Y%-%" + planePoint0.getPointID() + "Y%)";//*" + p1RatioY + ")";
		//ABy = "(%" + planePoint1.getPointID() +"Y%-%" + planePoint0.getPointID() + "Y%)";//*" + p1RatioY + ")";
		//BAy = "(%" + planePoint0.getPointID() +"Y%-%" + planePoint1.getPointID() + "Y%)";//*" + p1RatioY + ")";
		
		//ACy = (p2y-p0y) * ratio
		ACy = "(%" + planePoint2.getPointID() +"Y%-%" + planePoint0.getPointID() + "Y%)";//*" + p2RatioY + ")";
		//ACy = "(%" + planePoint2.getPointID() +"Y%-%" + planePoint0.getPointID() + "Y%)";//*" + p2RatioY + ")";
		
		//ABz = (p1z-p0z) * ratio
		ABz = "(%" + planePoint1.getPointID() +"Z%-%" + planePoint0.getPointID() + "Z%)";//*" + p1RatioZ + ")";
		//ABz = "(%" + planePoint1.getPointID() +"Z%-%" + planePoint0.getPointID() + "Z%)";//*" + p1RatioZ + ")";
		
		//ACz = (p2z-p0z) * ratio
		ACz = "(%" + planePoint2.getPointID() +"Z%-%" + planePoint0.getPointID() + "Z%)";//*" + p2RatioZ + ")";
		//ACz = "(%" + planePoint2.getPointID() +"Z%-%" + planePoint0.getPointID() + "Z%)";//*" + p2RatioZ + ")";
		
		String normalX = "((" + ABy + "*" + ACz + ")-(" + ABz + "*" + ACy + "))" ;
		String normalY = "((" + ABz + "*" + ACx + ")-(" + ABx + "*" + ACz + "))" ;
		String normalZ = "((" + ABx + "*" + ACy + ")-(" + ABy + "*" + ACx + "))" ;
		String normalize = "(√(((" + normalX + "*" + normalX + ")+(" + normalY + "*" + normalY + "))+(" + normalZ + "*" + normalZ + ")))";
		String planeX = "(" + normalX + "/" + normalize + ")";
		String planeY = "(" + normalY + "/" + normalize + ")";
		String planeZ = "(" + normalZ + "/" + normalize + ")";
		
		
		//VectorX = i((AByACz)-(ACyABz))
		//vectorXformula = "+(i*(("+ABy+"*"+ACz+")-("+ACy+"*"+ABz+")))";
		
		//vectorPointX = point0x + ((AByACz)-(ACyABz))
		//vectorPointX = "0";
		//vectorPointX = "+(%"+vectorPoint.getPointID()+ "Z%)-(+%" + planePoint0.getPointID() + "Z%+((" +"((" +  ABz+"*"+df.format(ABratio)+")+(((("+df.format(ABratio2)+"*((((0-"+ABy+")*"+plane[0]+"))+((((0-"+ABx+")*"+(plane[1])+")))))))+("+(plane[2])+"*"+df.format(closestPointToPointDistance)+"))))))";
		vectorPointX = "+(%"+vectorPoint.getPointID()+ "X%)-(+%" + planePoint0.getPointID() + "X%+((" +"((" +  ABx+"*"+df.format(ABratio)+")+((((("+ABratioSign+"1*"+df.format(ABratio2)+")*((((0-"+ABz+")*"+planeY+"))+((((0-"+ABy+")*"+(planeZ)+")))))))+((("+(planeX)+")*("+df.format(closestPointToPointDistance)+"))))))))";
		vectorPointX = "+(%"+vectorPoint.getPointID()+ "X%)-(+%" + planePoint0.getPointID() + "X%+((" +"((" +  ABx+"*"+df.format(ABratio)+")+((((("+ABratioSign+"1*"+df.format(ABratio2)+")*(((("+ABz+")*"+plane[1]+"))+((((0-"+ABy+")*"+(plane[2])+")))))))+("+(plane[0])+"*"+df.format(closestPointToPointDistance)+"))))))";
		vectorPointX = ABz + "-" + ABz;
		vectorPointX = "+(%"+vectorPoint.getPointID()+ "X%)-(+%" + planePoint0.getPointID() + "X%+((" +"((" +  ABx+"*"+df.format(ABratio)+")+(((("+df.format(ABratio2)+"*(((("+ABy+")*"+df.format(plane[2])+"))-(((("+ABz+")*"+(df.format(plane[1]))+")))))))+("+(df.format(plane[0]))+"*"+df.format(closestPointToPointDistance)+"))))))";
		vectorPointZ = "+(%"+vectorPoint.getPointID()+ "Z%)-(+%" + planePoint0.getPointID() + "Z%+((" +"((" +  ABz+"*"+df.format(ABratio)+")+((((("+df.format(ABratio2)+")*(((("+ABx+")*"+planeY+"))-(((("+ABy+")*"+(planeX)+")))))))+((("+(planeZ)+")*("+df.format(closestPointToPointDistance)+"))))))))";
		
		//vectorPointX = "+(%"+vectorPoint.getPointID()+ "X%)-(+%" + planePoint0.getPointID() + "X%+(" +"((+"+ABx+"*"+ABratio+")-(+"+ABratio2+"*((+"+ABy+"*"+planeZ+")+(+"+ABz+"*"+planeY+"))))+("+planeX+"*"+closestPointToPointDistance+")))";
		
		//VectorY = j((ACxABz)-(ABxACz))
		//vectorYformula = "+(j*(("+ACx+"*"+ABz+")-("+ABx+"*"+ACz+")))";
		
		//vectorPointY = point0y + j((ACxABz)-(ABxACz))
		//vectorPointY = "0";
		//vectorPointY = "+(%"+vectorPoint.getPointID()+ "Z%)-(+%" + planePoint0.getPointID() + "Z%+((" +"((" +  ABz+"*"+df.format(ABratio)+")+(((("+df.format(ABratio2)+"*((((0-"+ABy+")*"+plane[0]+"))+((((0-"+ABx+")*"+(plane[1])+")))))))+("+(plane[2])+"*"+df.format(closestPointToPointDistance)+"))))))";
		
		//vectorPointY = "+(%"+vectorPoint.getPointID()+ "Y%)-(+%" + planePoint0.getPointID() + "Y%+((" +"((" +  ABy+"*"+df.format(ABratio)+")+((((("+ABratioSign+"1*"+df.format(ABratio2)+")*((((0-"+ABx+")*"+planeZ+"))+((((0-"+ABz+")*"+(planeX)+")))))))+((("+(planeY)+")*("+df.format(closestPointToPointDistance)+"))))))))";

		vectorPointY = "+(%"+vectorPoint.getPointID()+ "Y%)-(+%" + planePoint0.getPointID() + "Y%+((" +"((" +  ABy+"*"+df.format(ABratio)+")+(((("+df.format(ABratio2)+"*(((("+ABz+")*"+df.format(plane[0])+"))-(((("+ABx+")*"+(df.format(plane[2]))+")))))))+("+(df.format(plane[1]))+"*"+df.format(closestPointToPointDistance)+"))))))";
		vectorPointY = "+(%"+vectorPoint.getPointID()+ "Y%)-(+%" + planePoint0.getPointID() + "Y%+((" +"((" +  ABy+"*"+df.format(ABratio)+")+((((("+df.format(ABratio2)+")*(((("+ABz+")*"+planeX+"))-(((("+ABx+")*"+(planeZ)+")))))))+((("+(planeY)+")*("+df.format(closestPointToPointDistance)+"))))))))";
		
		//vectorPointY = ABz + "-" + ABz;
		//vectorPointY ="+(%"+vectorPoint.getPointID()+  "Y%)-(+%" + planePoint0.getPointID() + "Y%+(" +"((+"+ACy+"*"+ACratio+")-(+"+ACratio2+"*((+"+ACx+"*"+planeZ+")+(+"+ACz+"*"+planeX+"))))+("+planeY+"*"+closestPointToPointDistance+")))";
		
		//VectorZ = k((ABxACy)-(AByACx))
		//vectorZformula = "+(k*(("+ABx+"*"+ACy+")-("+ABy+"*"+ACx+")))";
		
		//vectorPointZ = point0z + k((ABxACy)-(AByACx))
		//vectorPointZ = "+(%"+vectorPoint.getPointID()+ "Z%)-(+%" + planePoint0.getPointID() + "Z%+(" +"((+"+ABz+"*"+df.format(ABratio)+")-(+"+ABratio2+"*((+"+ABy+"*"+planeX+")+(+"+ABx+"*"+planeY+"))))+("+planeZ+"*"+closestPointToPointDistance+")))";
		vectorPointZ = "+(%"+vectorPoint.getPointID()+ "Z%)-(+%" + planePoint0.getPointID() + "Z%+((" +"((" +  ABz+"*"+df.format(ABratio)+")+(((("+df.format(ABratio2)+"*(((("+ABx+")*"+df.format(plane[1])+"))-(((("+ABy+")*"+(df.format(plane[0]))+")))))))+("+(df.format(plane[2]))+"*"+df.format(closestPointToPointDistance)+"))))))";
		vectorPointZ = "+(%"+vectorPoint.getPointID()+ "Z%)-(+%" + planePoint0.getPointID() + "Z%+((" +"((" +  ABz+"*"+df.format(ABratio)+")+((((("+df.format(ABratio2)+")*(((("+ABx+")*"+planeY+"))-(((("+ABy+")*"+(planeX)+")))))))+((("+(planeZ)+")*("+df.format(closestPointToPointDistance)+"))))))))";
		//vectorPointZ = ABz + "-" + ABz;
		//Vector = i((AByACz)-(ACyABz))+j((ACxABz)-(ABxACz))+k((ABxACy)-(AByACx))
		//vectorFormula = "+(i*((AByACz)-(ACyABz))+j((ACxABz)-(ABxACz))+k((ABxACy)-(AByACx)))";
		
		//VectorPlane = i((AByACz)-(ACyABz))+j((ACxABz)-(ABxACz))+k((ABxACy)-(AByACx))
		//vectorPlaneFormula = "+(i((AByACz)-(ACyABz))+j((ACxABz)-(ABxACz))+k((ABxACy)-(AByACx)))";
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
