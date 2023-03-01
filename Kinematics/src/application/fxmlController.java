package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class fxmlController {
	
	@FXML private AnchorPane renderView;
	@FXML private AnchorPane rightView;
	vehicleBuilder staff = new vehicleBuilder();
	
	@FXML protected void handleSubmitButtonAction(ActionEvent event) {
		staff.addShape();
	}
	
	@FXML public void addRender() throws Exception{
		testing();
		
	}

	@FXML private void testing() throws Exception {

		
		staff.addMoly();
		renderView.getChildren().add(staff.group);
//		for(int i = 0;i<5;i++){
//			staff.frontSuspension.getChildren().get(i).setTranslateX((i*10));;
//			System.out.println(staff.frontSuspension.getChildren().get(i));
//		}
		staff.frontSuspension.getChildren().get(0).setTranslateX((10));;
		staff.frontSuspension.getChildren().get(1).setTranslateX((20));;
		staff.frontSuspension.getChildren().get(2).setTranslateX((30));;
		staff.frontSuspension.getChildren().get(3).setTranslateX((40));;
		staff.frontSuspension.getChildren().get(4).setTranslateX((50));;
		staff.setHeightWidth();
		//staff.addShape();
		
	}
}