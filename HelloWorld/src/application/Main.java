package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane toot = new BorderPane();
//			Scene tcene = new Scene(toot,400,400);
//			tcene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(tcene);
//			primaryStage.show();
			
			Button button = new Button();
			button.setText("Sample Button");
			button.setTranslateX(150);
		    button.setTranslateY(60);
//		    Group root = new Group(button);
//		    Scene scene = new Scene(root, 500, 500);
		    Rectangle r = new Rectangle(25,25,250,250);
		    //toot.getChildren().add(button);
		    button.setTranslateX(150);
		    button.setTranslateY(60);
		    //toot.getChildren().add(r);
//		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    
		    toot.setLeft(button);
			toot.setRight(r);
		    
		    Scene tcene = new Scene(toot,400,400);
			tcene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    
			
		    
		    primaryStage.setTitle("Button Example");
//		    primaryStage.setScene(scene);
//		    primaryStage.show();
		    
		    
		    
		    primaryStage.setScene(tcene);
			primaryStage.show();
		    
		} catch(Exception e) {
			e.printStackTrace();}
		}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
