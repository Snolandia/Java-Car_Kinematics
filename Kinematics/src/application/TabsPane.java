package application;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.stage.Stage;

public class TabsPane extends Main {
	
	public static TabPane tabAdd() {
		
		TabPane tabPane = new TabPane();
		
		Tab tab1 = new Tab("Tab1", new Label("Tabby Tab1"));
		//Tab inputTab = new Tab("Inputs", new Label("Interior"));
		Tab inputTab = inputsTabPane.inputsadd();
		Tab tab3 = new Tab("Tab3", new Label("Tabby Tab3"));
		Tab tab4 = new Tab("Tab4", new Label("Tabby Tab4"));
		
		tabPane.getTabs().add(tab1);
		
		tabPane.getTabs().add(inputTab);
		
		tabPane.getTabs().add(tab3);
		
		tabPane.getTabs().add(tab4);
		
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		return tabPane;
				
	}
}
