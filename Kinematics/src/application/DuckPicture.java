package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DuckPicture extends Application {
	
	@Override
	
   public void start(Stage stage) throws IOException, InterruptedException {
      //creating the image object
	   
	   var client = HttpClient.newHttpClient();
	   
	   var request = HttpRequest.newBuilder(
			   URI.create("https://random-d.uk/api/v2/random"))
			   .GET()
			   .build();
	  
	   HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
	   
	   String string = response.body();
	   
	   string = string.substring(string.indexOf("url")+6,string.length()-3);
	   
	   System.out.println(string);
	   
	   URLConnection openConnection = new URL(string).openConnection();
	   
	  InputStream input = openConnection.getInputStream();

      Image image = new Image(input);
      //Creating the image view
      ImageView imageView = new ImageView();
      //Setting image to the image view
      
      double width          = image.getWidth();
      double height         = image.getHeight();
      
      imageView.setImage(image);
      //Setting the image view parameters
      imageView.setX(0);
      imageView.setY(0);
      //imageView.setFitWidth(575);
      imageView.setPreserveRatio(true);
      //Setting the Scene object
      Group root = new Group(imageView);
      Scene scene = new Scene(root, width, height);
      stage.setTitle("Displaying Image");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String args[]) {
      launch(args);
   }
}