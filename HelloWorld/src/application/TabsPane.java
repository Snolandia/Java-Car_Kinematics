package application;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class TabsPane extends Main {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		TabPane tabPane = new TabPane();
		
		Tab tab1 = new Tab("Tab1", new Label("Tabby Tab"));
		Tab tab2 = new Tab("Tab2", new Label("Tabby Tab"));
		Tab tab3 = new Tab("Tab3", new Label("Tabby Tab"));
		Tab tab4 = new Tab("Tab4", new Label("Tabby Tab"));
		
		tabPane.getTabs().add(tab1);
		tabPane.getTabs().add(tab2);
		tabPane.getTabs().add(tab3);
		tabPane.getTabs().add(tab4);
		
	}
	
	public static TabPane tabAdd(TabPane tabPane) {
		
		tabPane = new TabPane();
		
		Tab tab1 = new Tab("Tab1", new Label("Tabby Tab1"));
		Tab tab2 = new Tab("Tab2", new Label("Tabby Tab2"));
		Tab tab3 = new Tab("Tab3", new Label("Tabby Tab3"));
		Tab tab4 = new Tab("Tab4", new Label("Tabby Tab4"));
		
		tabPane.getTabs().add(tab1);
		tabPane.getTabs().add(tab2);
		tabPane.getTabs().add(tab3);
		tabPane.getTabs().add(tab4);
		return tabPane;
		
	}
}
