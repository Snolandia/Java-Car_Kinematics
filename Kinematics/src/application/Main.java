package application;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import values.generalFormulas;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
					
			fxmlController controller = new fxmlController();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("testview.fxml"));
			loader.setController(controller);
			Parent rooty = loader.load();
			
			
			Scene sc = new Scene(rooty);
			primaryStage.setTitle("oktest");
			primaryStage.setScene(sc);
			primaryStage.show();
			controller.addRender();
			generalFormulas.testTest();
			
		} catch(Exception e) {
			e.printStackTrace();}
		}

	public static void main(String[] args) {
		launch(args);

	}
}
