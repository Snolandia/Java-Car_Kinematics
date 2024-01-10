package main;

import gui.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import values.generalFormulas;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
					
			MainController controller = new MainController();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("./gui/main.fxml"));
			
			loader.setController(controller);
			Parent root = loader.load();
			
			
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("Application");
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
//			controller.addRender();
			
			generalFormulas.testTest();
			
		} catch(Exception e) {
			e.printStackTrace();}
		}

	public static void main(String[] args) {
		launch(args);

	}
}
