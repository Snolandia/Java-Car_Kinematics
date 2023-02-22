package application;
	
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = new BorderPane();
			root.setPrefSize(800,600);
			MenuBar menuBar = menuBarRow.menuAdd();

			VBox vBox = new VBox(menuBar);
			root.setTop(vBox);
			
			TabPane tabPane = TabsPane.tabAdd();
			VBox vBox1 = new VBox(tabPane);
			root.setLeft(vBox1);
			
	        Scene scene = new Scene(root);
			
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		    primaryStage.setTitle("Testing Program");
		    primaryStage.setScene(scene);
			primaryStage.show();
		    
		} catch(Exception e) {
			e.printStackTrace();}
		}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
