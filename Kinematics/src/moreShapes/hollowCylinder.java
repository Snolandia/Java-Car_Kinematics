package moreShapes;

import java.net.URL;
import java.util.Arrays;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;

import javafx.collections.ObservableFloatArray;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

public class hollowCylinder extends MeshView{
		
	TriangleMesh mesh = hollowCylinderGet();
	public double diameter = 12.0;
	public double width = 6.0;
	
	public hollowCylinder() {
		setMesh(mesh);
	}
	
	public TriangleMesh hollowCylinderGet() {
		StlMeshImporter stlImporter = new StlMeshImporter();
		try {
			URL modelUrl = this.getClass().getResource("/Shorthollowcylinder.stl");
			stlImporter.read(modelUrl);            
		}
			catch (ImportException e) {
				// handle exception
		}
	
		TriangleMesh stlMesh = stlImporter.getImport();
//		String testString = "[6.0, 1.5, -6.0, 5.91, 1.5, -7.035, 6.0, 7.5, -6.0, 5.91, 7.5, -7.035, 5.64, 1.5, -8.055, 5.64, 7.5, -8.055, 5.19, 1.5, -9.0, 5.19, 7.5, -9.0, 4.59, 1.5, -9.855, 4.59, 7.5, -9.855, 3.855, 1.5, -10.59, 3.855, 7.5, -10.59, 3.0, 1.5, -11.19, 3.0, 7.5, -11.19, 2.055, 1.5, -11.64, 2.055, 7.5, -11.64, 1.035, 7.5, -11.91, 1.035, 1.5, -11.91, 0.0, 7.5, -12.0, 0.0, 1.5, -12.0, -1.035, 1.5, -11.91, -1.035, 7.5, -11.91, -2.055, 7.5, -11.64, -2.055, 1.5, -11.64, -3.0, 1.5, -11.19, -3.0, 7.5, -11.19, -3.855, 1.5, -10.59, -3.855, 7.5, -10.59, -4.59, 7.5, -9.855, -4.59, 1.5, -9.855, -5.19, 1.5, -9.0, -5.19, 7.5, -9.0, -5.64, 1.5, -8.055, -5.64, 7.5, -8.055, -5.91, 1.5, -7.035, -5.91, 7.5, -7.035, -6.0, 1.5, -6.0, -6.0, 7.5, -6.0, -5.91, 7.5, -4.965, -5.91, 1.5, -4.965, -5.64, 1.5, -3.945, -5.64, 7.5, -3.945, -5.19, 1.5, -3.0, -5.19, 7.5, -3.0, -4.59, 1.5, -2.145, -4.59, 7.5, -2.145, -3.855, 1.5, -1.41, -3.855, 7.5, -1.41, -3.0, 1.5, -0.81, -3.0, 7.5, -0.81, -2.055, 1.5, -0.36, -2.055, 7.5, -0.36, -1.035, 1.5, -0.09, -1.035, 7.5, -0.09, 0.0, 1.5, -3.330669E-16, 0.0, 7.5, -1.6653345E-15, 1.035, 1.5, -0.09, 1.035, 7.5, -0.09, 2.055, 1.5, -0.36, 2.055, 7.5, -0.36, 3.0, 1.5, -0.81, 3.0, 7.5, -0.81, 3.855, 1.5, -1.41, 3.855, 7.5, -1.41, 4.59, 1.5, -2.145, 4.59, 7.5, -2.145, 5.19, 1.5, -3.0, 5.19, 7.5, -3.0, 5.64, 1.5, -3.945, 5.64, 7.5, -3.945, 5.91, 7.5, -4.965, 5.91, 1.5, -4.965, -3.285, 7.5, -2.925, -2.595, 7.5, -2.325, -3.84, 7.5, -3.66, -1.8, 7.5, -1.875, -4.245, 7.5, -4.5, -0.915, 7.5, -1.59, -4.455, 7.5, -5.385, 0.0, 7.5, -1.5, -4.485, 7.5, -6.3, 1.005, 7.5, -1.62, -4.335, 7.5, -7.215, 1.95, 7.5, -1.95, -3.99, 7.5, -8.07, 2.805, 7.5, -2.475, -3.495, 7.5, -8.835, -2.835, 7.5, -9.495, 3.525, 7.5, -3.195, 4.05, 7.5, -4.05, -2.07, 7.5, -9.99, 4.38, 7.5, -4.995, -1.215, 7.5, -10.335, 4.5, 7.5, -6.0, -0.3, 7.5, -10.485, 0.615, 7.5, -10.455, 4.41, 7.5, -6.915, 1.5, 7.5, -10.245, 4.125, 7.5, -7.8, 2.34, 7.5, -9.84, 3.675, 7.5, -8.595, 3.075, 7.5, -9.285, -3.285, 1.5, -2.925, -2.595, 1.5, -2.325, -3.84, 1.5, -3.66, -1.8, 1.5, -1.875, -4.245, 1.5, -4.5, -0.915, 1.5, -1.59, -4.455, 1.5, -5.385, 0.0, 1.5, -1.5, -4.485, 1.5, -6.3, 1.005, 1.5, -1.62, -4.335, 1.5, -7.215, 1.95, 1.5, -1.95, -3.99, 1.5, -8.07, 2.805, 1.5, -2.475, -3.495, 1.5, -8.835, -2.835, 1.5, -9.495, 3.525, 1.5, -3.195, 4.05, 1.5, -4.05, -2.07, 1.5, -9.99, 4.38, 1.5, -4.995, -1.215, 1.5, -10.335, 4.5, 1.5, -6.0, -0.3, 1.5, -10.485, 0.615, 1.5, -10.455, 4.41, 1.5, -6.915, 1.5, 1.5, -10.245, 4.125, 1.5, -7.8, 2.34, 1.5, -9.84, 3.675, 1.5, -8.595, 3.075, 1.5, -9.285]";
//		int count = 0;
//		char someChar = ',';
//		
//		for (int i = 0; i < testString.length(); i++) {
//		    if (testString.charAt(i) == someChar) {
//		        count++;
//		        if (count % 3 == 0) {
//		        	System.out.println(" ");
//		        }
//		    }
//		    System.out.print(testString.charAt(i));
//		}
		
		//System.out.println(tester);
		return stlMesh;
	}
}
