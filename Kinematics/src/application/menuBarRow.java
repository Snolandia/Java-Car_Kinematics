package application;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;

public class menuBarRow extends Main {
	
	
	public static MenuBar menuAdd() {
	
		//MenuBar menuBar = new MenuBar();
		
		Menu menuFile = fileAdd();
		
		Menu menuSettings = new Menu("Settings");
		
		Menu menuAbout = new Menu("About");
		
		MenuBar menuBar = new MenuBar();
		
		menuBar.getMenus().add(menuFile);
		menuBar.getMenus().add(menuSettings);
		menuBar.getMenus().add(menuAbout);
		
		
		return menuBar;
	}
	
	private static Menu fileAdd() {
		
		Menu menu = new Menu("File");
		
		MenuItem mLoad = new MenuItem("Load");
		MenuItem mSave = new MenuItem("Save");
		MenuItem mExit = new MenuItem("Exit");		
		
		menu.getItems().add(mLoad);
		menu.getItems().add(mSave);
		menu.getItems().add(mExit);
		
		
		return menu;
	}
}
