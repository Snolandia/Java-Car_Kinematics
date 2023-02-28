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
			//loader.setLocation(getClass().getResource("testview.fxml"));
			loader.setController(controller);
			Parent rooty = loader.load();
			
			//System.out.println(loader.getController());
			
			
			
			Scene sc = new Scene(rooty);
			primaryStage.setTitle("oktest");
			primaryStage.setScene(sc);
			primaryStage.show();
			controller.addRender();
			generalFormulas.testTest();
			//System.exit(0);

//			BorderPane root = new BorderPane();
//			root.setPrefSize(800,600);
//			MenuBar menuBar = menuBarRow.menuAdd();
//
//			VBox vBox = new VBox(menuBar);
//			root.setTop(vBox);
//
//			TabPane tabPane = TabsPane.tabAdd();
//			VBox vBox1 = new VBox(tabPane);
//			root.setLeft(vBox1);
//
//	        Scene scene = new Scene(root);
//
//	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//
//		    primaryStage.setTitle("Testing Program");
//		    primaryStage.setScene(scene);
//			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();}
		}

	public static void main(String[] args) {
		launch(args);

	}
}
