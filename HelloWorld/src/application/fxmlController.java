package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class fxmlController {
	
	@FXML private AnchorPane renderView;
	@FXML private AnchorPane rView;
	
	@FXML public void addRender() throws Exception{
		testing();
		
	}

	@FXML private void testing() throws Exception {
		//Group stuff = new Group(Simple3DBoxApp.createContent());
		Group stuff = new Group();
		stuff = MoleculeSampleApp.addMoly(stuff);
		Label sft = new Label("opkee");
		renderView.getChildren().add(stuff);
		
	}
}