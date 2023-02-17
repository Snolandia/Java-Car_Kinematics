package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
			BorderPane root = new BorderPane();
		    Scene scene = new Scene(root,700,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    
			VBox vbox = new VBox();
		    vbox.setPadding(new Insets(10));
		    vbox.setSpacing(20);
		    
		    Button button = new Button();
		    Button button1 = new Button();
		    Button button2 = new Button();
		    Button button3 = new Button();

		    Text title = new Text("Data");
		    title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		    vbox.getChildren().add(title);
		    vbox.getChildren().add(button);
		    vbox.getChildren().add(button1);
		    vbox.getChildren().add(button2);
		    vbox.getChildren().add(button3);
			
		    root.getChildren().add(vbox);
		    
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
