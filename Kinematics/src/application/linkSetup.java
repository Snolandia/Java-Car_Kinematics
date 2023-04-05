package application;

import application.linkForm.RotateOrder;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

public class linkSetup extends Group {
	
	Cylinder cylinder = new Cylinder(1,30);
	Sphere inSphere = new Sphere(2);
	Sphere outSphere = new Sphere(2);
	linkForm rod = new linkForm(RotateOrder.XYZ);
	linkForm inboard = new linkForm();
	linkForm outboard = new linkForm();
	linkForm link = new linkForm();
	
	double distance = 0;
    
	public linkSetup() {
		
		rod.getChildren().add(cylinder);
		inboard.getChildren().add(inSphere);
		outboard.getChildren().add(outSphere);
		
		link.getChildren().add(inboard);
		link.getChildren().add(rod);
		link.getChildren().add(outboard);
		
		final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);
        
		inSphere.setMaterial(redMaterial);
		outSphere.setMaterial(redMaterial);
		cylinder.setMaterial(redMaterial);
		
	}
	
	public void adjustments(int inboardOutboard,int xYZ, double arg2) {
		if(inboardOutboard == 0) {
			switch (xYZ) {
				case 0:
					rod.setRx(arg2);
					//inboard.setTx(arg2);
					break;
				case 1:
					rod.setRy(arg2);
					//inboard.setTy(arg2);
					break;
				case 2:
					rod.setRz(arg2);
					//inboard.setTz(arg2);
					break;
			}
			System.out.println("inboard" + inboard);
		}
		if(inboardOutboard == 1) {
			switch (xYZ) {
				case 0:
					outboard.setTx(arg2);
					break;
				case 1:
					outboard.setTy(arg2);
					break;
				case 2:
					outboard.setTz(arg2);
					break;
			}
			System.out.println("outboard" + outboard);
		}
		distanceCalc();
		cylinder.setHeight(distance);
		//setRotates();
		reAdjustRod();
		System.out.println("rod" + rod);
	}
	
	private void reAdjustRod() {
		double inX = inboard.getX();
		double inY = inboard.getY();
		double inZ = inboard.getZ();
		double outX = outboard.getX();
		double outY = outboard.getY();
		double outZ = outboard.getZ();
		
		//rod.setTranslate(((outX-inX)/2)+inX, ((outY-inY)/2)+inY, ((outZ-inZ)/2)+inZ);
		rod.setTranslate(10,10 ,10 );
		
	}
	
	private void setRotates() {
		double inX = inboard.getX();
		double inY = inboard.getY();
		double inZ = inboard.getZ();
		double outX = outboard.getX();
		double outY = outboard.getY();
		double outZ = outboard.getZ();
		double rX = 45;
		double rY = 45;
		double rZ = 0;
		
		if(Math.sqrt(((outY-inY)*(outY-inY))+((outZ-inZ)*(outZ-inZ)))!=0){
			//rX = Math.toDegrees(Math.acos((inY-outY)/Math.sqrt(((outY-inY)*(outY-inY))+((outZ-inZ)*(outZ-inZ)))));
		}
		if(Math.sqrt(((outX-inX)*(outX-inX))+((outZ-inZ)*(outZ-inZ)))!=0) {
			//rY = Math.toDegrees(Math.acos((inZ-outZ)/Math.sqrt(((outX-inX)*(outX-inX))+((outZ-inZ)*(outZ-inZ)))));
		}
		if(Math.sqrt(((outX-inX)*(outX-inX))+((outY-inY)*(outY-inY)))!=0) {
			//rZ = Math.toDegrees(Math.acos((outX-inX)/Math.sqrt(((outX-inX)*(outX-inX))+((outY-inY)*(outY-inY)))));
		}
		
		System.out.println("Distance: " + distance);
		System.out.println("inx:" + inX + " iny:" + inY + " inz:" + inZ);
		System.out.println("outx:" + outX + " outy:" + outY + " outz:" + outZ);
		System.out.println("rx:" + rX + " ry:" + rY + " rz:" + rZ);
		rod.setRotate(rX,rY,rZ);
		
	}
	private void distanceCalc() {
		double inX = inboard.getX();
		double inY = inboard.getY();
		double inZ = inboard.getZ();
		double outX = outboard.getX();
		double outY = outboard.getY();
		double outZ = outboard.getZ();
		distance = Math.sqrt(((inX-outX)*(inX-outX)) + ((inY-outY)*(inY-outY)) + ((inZ- outZ)*(inZ- outZ)));
		
	}
	
		
}









