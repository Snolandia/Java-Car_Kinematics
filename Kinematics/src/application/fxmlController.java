package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;

public class fxmlController {
	
	@FXML private AnchorPane renderView;
	@FXML private AnchorPane rightView;
	@FXML private Spinner<Integer> spinnerF1iX;
	
	
	
	vehicleBuilder staff = new vehicleBuilder();
	
	
	
	@FXML protected void spinnerChange(ActionEvent event) {
		System.out.println("value");
		System.out.println(spinnerF1iX.getValue());
		//Sort of works, not really
	}
	
	@FXML public void addRender() throws Exception{
		testing();
		
	}

	@FXML private void testing() throws Exception {
		
		spinnerF1iX.valueProperty().addListener((ObservableValue obs,Object oldValue,Object newValue)System.out.println("New value: "+ newValue));
		
		spinnerF1iX.getEditor().setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("value");
				System.out.println(spinnerF1iX.getValue());
			}
			
			
		});
//		
//		
		staff.addMoly();
		renderView.getChildren().add(staff.group);
//		
		staff.frontSuspension.setTranslate(0, 15, 0);
		
		((Xform)staff.frontSuspension.getChildren().get(0)).setTranslate(15, 0, 15);
		((Xform)staff.frontSuspension.getChildren().get(1)).setTranslate(15, 0, -15);
		((Xform)staff.frontSuspension.getChildren().get(2)).setTranslate(-15, 0, 15);
		((Xform)staff.frontSuspension.getChildren().get(3)).setTranslate(-15, 0, -15);
		((Xform)staff.frontSuspension.getChildren().get(4)).setTranslate(0, 0, 0);
		
		
		staff.frontSuspension.setTranslate(0, -15, 0);
		
		for(int i = 0;i<5;i++){
			System.out.println(staff.frontSuspension.getChildren().get(i));
		}
		
		staff.setHeightWidth();
		
	}
}