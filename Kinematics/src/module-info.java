module HelloWorld {
	requires javafx.controls;
	requires java.net.http;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires jimStlMeshImporterJFX;
	
	opens application to javafx.graphics, javafx.fxml;
}
