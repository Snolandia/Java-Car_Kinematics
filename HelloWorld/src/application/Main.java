package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
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

			
		
		    TabPane tabPane = new TabPane(); 
		    tabPane = TabsPane.tabAdd(tabPane);

		    
			VBox vBox = new VBox(tabPane);
	        Scene scene = new Scene(vBox);
			
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
