package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import moreShapes.hollowCylinder;
import values.Statics;

public class wheelSetup extends Group {
	
	public groupForm all = new groupForm();
	public groupForm frontWheel = new groupForm();
	public groupForm rearWheel = new groupForm();
	
	private double hubWidth = 5;
	private double hubDiameter = 20;
	
	final groupForm frontLink1 = new groupForm();
	final groupForm frontLink2 = new groupForm();
	final groupForm frontLink3 = new groupForm();
	final groupForm frontLink4 = new groupForm();
	final groupForm frontLink5 = new groupForm();
	
	final groupForm rearLink1 = new groupForm();
	final groupForm rearLink2 = new groupForm();
	final groupForm rearLink3 = new groupForm();
	final groupForm rearLink4 = new groupForm();
	final groupForm rearLink5 = new groupForm();
	
	//test piece
	final Box fl1 = centeredLink();
	
	private groupForm linkArray[][] = new groupForm[2][5];
	
	private hollowCylinder frontTire = centeredTire();
	private hollowCylinder rearTire = centeredTire();
	
	final Cylinder frontHub = centeredHub();
	final Cylinder rearHub = centeredHub();
	
	public wheelSetup() {
		
		super();
		
		Sphere sphere = new Sphere(50);
		
		final PhongMaterial redMaterial = new PhongMaterial();
    	redMaterial.setDiffuseColor(Color.DARKRED);
    	redMaterial.setSpecularColor(Color.RED);

    	final PhongMaterial greenMaterial = new PhongMaterial();
    	greenMaterial.setDiffuseColor(Color.DARKGREEN);
    	greenMaterial.setSpecularColor(Color.GREEN);

    	final PhongMaterial blueMaterial = new PhongMaterial();
    	blueMaterial.setDiffuseColor(Color.DARKBLUE);
    	blueMaterial.setSpecularColor(Color.BLUE);
	
    	frontWheel.getChildren().add(frontHub);
    	frontWheel.getChildren().add(frontTire);
    	
    	rearWheel.getChildren().add(rearHub);
    	rearWheel.getChildren().add(rearTire);
    	
    	linkArray[0][0] = frontLink1;
    	linkArray[0][0].getChildren().add(centeredLink());
    	linkArray[0][1] = frontLink2;
    	linkArray[0][1].getChildren().add(centeredLink());
    	linkArray[0][2] = frontLink3;
    	linkArray[0][2].getChildren().add(centeredLink());
    	linkArray[0][3] = frontLink4;
    	linkArray[0][3].getChildren().add(centeredLink());
    	linkArray[0][4] = frontLink5;
    	linkArray[0][4].getChildren().add(centeredLink());
    	linkArray[1][0] = rearLink1;
    	linkArray[1][0].getChildren().add(centeredLink());
    	linkArray[1][1] = rearLink2;
    	linkArray[1][1].getChildren().add(centeredLink());
    	linkArray[1][2] = rearLink3;
    	linkArray[1][2].getChildren().add(centeredLink());
    	linkArray[1][3] = rearLink4;
    	linkArray[1][3].getChildren().add(centeredLink());
    	linkArray[1][4] = rearLink5;
    	linkArray[1][4].getChildren().add(centeredLink());
    	
    	all.getChildren().add(frontWheel);
    	all.getChildren().add(rearWheel);
    	
    	all.getChildren().addAll(frontLink1);
    	all.getChildren().addAll(frontLink2);
    	all.getChildren().addAll(frontLink3);
    	all.getChildren().addAll(frontLink4);
    	all.getChildren().addAll(frontLink5);
    	
    	all.getChildren().addAll(rearLink1);
    	all.getChildren().addAll(rearLink2);
    	all.getChildren().addAll(rearLink3);
    	all.getChildren().addAll(rearLink4);
    	all.getChildren().addAll(rearLink5);
    	
    	
	}
	
	private Box centeredLink() {
		final Box link = new Box(1, 1, 1);
		
		return link;
	}
	
	private Cylinder centeredHub() {
		final Cylinder hub = new Cylinder(hubDiameter,hubWidth);
		hub.setRotate(90);
		hub.setRotationAxis(Rotate.Z_AXIS);
		hub.setTranslateY(0);
		hub.setTranslateX(-(hubWidth/2.0));
		
		return hub;
	}
	
	private hollowCylinder centeredTire() {
			final hollowCylinder tire = new hollowCylinder();
			tire.setRotate(90);
			tire.setRotationAxis(Rotate.Z_AXIS);
			tire.setTranslateX(0);
			tire.setTranslateY(1.5-6);
    		tire.setTranslateZ(6);
		return tire;
	}
	
	public void linkAdjustment(double length, int frontRear,int linkNumber) {
		//System.out.println("linkAdjustment");
		
		linkArray[frontRear][linkNumber].getChildren().get(0).setScaleY(length);
		
		if(frontRear == 1 & linkNumber == 0) {
			System.out.println("fr :" + frontRear + "  linknum : " + linkNumber + " length: " + length);
		}
		double inX = Statics.hubLink[frontRear][linkNumber][0][0];
		double inY = Statics.hubLink[frontRear][linkNumber][0][1];
		double inZ = Statics.hubLink[frontRear][linkNumber][0][2];
		double outX = Statics.hubLink[frontRear][linkNumber][1][0];
		double outY = Statics.hubLink[frontRear][linkNumber][1][1];
		double outZ = Statics.hubLink[frontRear][linkNumber][1][2];
		double rX = 90;
		double rY = 0;
		double rZ = 0;
		
		linkArray[frontRear][linkNumber].setTranslateX(((outX-inX)/2.0)+inX);
		linkArray[frontRear][linkNumber].setTranslateY(((outY-inY)/2.0)+inY);
		linkArray[frontRear][linkNumber].setTranslateZ(((outZ-inZ)/2.0)+inZ);
		
		
		if(Math.sqrt(((outY-inY)*(outY-inY))+((outZ-inZ)*(outZ-inZ)))!=0){
				rX = Math.toDegrees(Math.asin(Math.sqrt(((inX-outX)*(inX-outX))+((inZ-outZ)*(inZ-outZ)))/length));
		}
		if(Math.sqrt(((outX-inX)*(outX-inX))+((outZ-inZ)*(outZ-inZ)))!=0) {
			if(outY>inY) {
				rY = 180-Math.toDegrees(Math.acos((inZ-outZ)/Math.sqrt(((outX-inX)*(outX-inX))+((outZ-inZ)*(outZ-inZ)))));
			}
			else {
				rY = -Math.toDegrees(Math.acos((inZ-outZ)/Math.sqrt(((outX-inX)*(outX-inX))+((outZ-inZ)*(outZ-inZ)))));
			}
		}
		
		if(inX>outX) {rY = -rY;}
		if(frontRear == 1 & linkNumber == 0) {
			System.out.println("rotates: rx:" + rX + " / ry: " + rY + " / rz: " + rZ);
			System.out.println("Ins: x : " + inX + " / y " + inY + " / z " + inZ);
			System.out.println("Outs: x :" + outX + " / y " + outY + " / z " + outZ);
		}
		linkArray[frontRear][linkNumber].setRotate(rX,rY,rZ);
	}
	
	public void frontAlignment(double camber, double toe){
		
		frontWheel.setRotateY(toe);
		frontWheel.setRotateZ(camber);
		
	}
	
	public void rearAlignment(double camber, double toe){
		
		rearWheel.setRotateY(toe);
		rearWheel.setRotateZ(camber);
		
	}
	
	
	public void moveFrontWheel(double track, double wheelbase) {
		frontWheel.setTranslateX(track/2.0);
		frontWheel.setTranslateZ(wheelbase/2.0);
	}
	
	public void moveRearWheel(double track, double wheelbase) {
		rearWheel.setTranslateX(track/2.0);
		rearWheel.setTranslateZ(-wheelbase/2.0);
	}
	
	public void resizeFrontWheel(double width, double ratio, double diameter,double offset) {
		double yMulti = frontTire.diameter;
		double xMulti = frontTire.width;
		
		frontTire.setScaleY(width/xMulti);
		
		frontTire.setScaleX((diameter+((width*ratio/100.0)*2.0))/yMulti);
		frontTire.setScaleZ((diameter+((width*ratio/100.0)*2.0))/yMulti);
		
		frontHub.setTranslateX(offset-(hubWidth/2.0));
		
		frontWheel.setTranslateY(((diameter+((width*ratio/100.0)*2.0))/2.0));
		
	}

	public void resizeRearWheel(double width, double ratio, double diameter,double offset) {
		double yMulti = rearTire.diameter;
		double xMulti = rearTire.width;
		
		rearTire.setScaleY(width/xMulti);
		rearTire.setScaleX((diameter+((width*ratio/100.0)*2.0))/yMulti);
		rearTire.setScaleZ((diameter+((width*ratio/100.0)*2.0))/yMulti);
		
		rearHub.setTranslateX(offset-(hubWidth/2.0));
		
		rearWheel.setTranslateY(((diameter+((width*ratio/100.0)*2.0))/2.0));
	}
	
	public void wheelUpdate() {
		
		
	}
}
