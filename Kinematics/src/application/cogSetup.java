package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.Box;

public class cogSetup extends Group{
	
	groupForm cog = new groupForm();
	
	public cogSetup() {
	
		Sphere sphere = new Sphere(2);
	
		final PhongMaterial redMaterial = new PhongMaterial();
    	redMaterial.setDiffuseColor(Color.DARKRED);
    	redMaterial.setSpecularColor(Color.RED);

    	final PhongMaterial greenMaterial = new PhongMaterial();
    	greenMaterial.setDiffuseColor(Color.DARKGREEN);
    	greenMaterial.setSpecularColor(Color.GREEN);

    	final PhongMaterial blueMaterial = new PhongMaterial();
    	blueMaterial.setDiffuseColor(Color.DARKBLUE);
    	blueMaterial.setSpecularColor(Color.BLUE);
	
		final Box xAxis = new Box(10, 1, 1);
    	final Box yAxis = new Box(1, 10, 1);
    	final Box zAxis = new Box(1, 1, 10);
	
    	xAxis.setMaterial(redMaterial);
    	yAxis.setMaterial(greenMaterial);
    	zAxis.setMaterial(blueMaterial);
    	
    	cog.getChildren().add(sphere);
    	cog.getChildren().add(xAxis);
    	cog.getChildren().add(yAxis);
    	cog.getChildren().add(zAxis);
    	
	}
	
	
	public void adjustments(int xyz,double arg2) {
		switch(xyz){
			case 0:
				cog.setTx(arg2);
				break;
			case 1:
				cog.setTy(arg2);
				break;
			case 2:
				cog.setTz(arg2);
				break;
		}
		
	}
}
