package application;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class inputsTabPane extends TabsPane {
	
	public static Tab inputsadd() {
		
		Tab tab = new Tab("Input");
		
		BorderPane borderPane = new BorderPane();
		
		VBox vBox = new VBox();
		Label title = new Label("Data");
		Label title1 = new Label("Dataa");
		Label title2 = new Label("Datab");
		Label title3 = new Label("Datac");
		Label title4 = new Label("Datad");
		Button button = new Button("Current");
		TextField userTextField = new TextField();
		
		vBox.getChildren().add(title4);
		vBox.getChildren().add(title3);
		vBox.getChildren().add(title2);
		vBox.getChildren().add(title1);
		vBox.getChildren().add(title);
		vBox.getChildren().add(button);
		vBox.getChildren().add(userTextField);

		
		borderPane.setRight(vBox);
		
		tab.setContent(borderPane);
		
		return tab;
	}
}
