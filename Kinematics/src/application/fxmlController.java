package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class fxmlController {
	
	IntegerSpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE); 
	@FXML private AnchorPane renderView;
	@FXML private AnchorPane rightView;
	@FXML private Spinner<Integer> spinnerF1iX;
	@FXML private Spinner<Integer> spinnerF1iY;
	@FXML private Spinner<Integer> spinnerF1iZ;
	@FXML private Spinner<Integer> spinnerF1oX;
	@FXML private Spinner<Integer> spinnerF1oY;
	@FXML private Spinner<Integer> spinnerF1oZ;
	private Spinner<?>[][][][] spinnerList = new Spinner<?>[2][5][2][3];
	@FXML AnchorPane frontSuspensionPane;
	@FXML AnchorPane rearSuspensionPane;
	
	vehicleBuilder vehicle = new vehicleBuilder();
	
	@SuppressWarnings("unchecked")
	@FXML public void setupSpinners() {
		//loop through spinners, looping through boxes, looping through title pain
		System.out.println("SetUp Spinner Listeners");
		
		for(int frontRear = 0;frontRear<2;frontRear++) {
			for(int linkNumber = 0;linkNumber<5;linkNumber++) {
				for(int inboardOutboard = 0;inboardOutboard<2;inboardOutboard++) {
					for(int xYZ = 0;xYZ<3;xYZ++) {
						
						final int innerFrontRear = frontRear;
						final int innerLinkNumber = linkNumber;
						final int innerInboardOutboard = inboardOutboard;
						final int innerXYZ = xYZ;
						
						spinnerList[frontRear][linkNumber][inboardOutboard][xYZ]=(Spinner<Integer>)((HBox)((VBox)frontSuspensionPane.getChildren().get(linkNumber)).getChildren().get(xYZ+1)).getChildren().get(inboardOutboard+1);
						
						if (frontRear == 0){
							((Spinner<Integer>)((HBox)((VBox)frontSuspensionPane.getChildren().get(linkNumber)).getChildren().get(xYZ+1)).getChildren().get(inboardOutboard+1)).valueProperty().addListener(new ChangeListener<Integer>() {
								@Override
								public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {

									int listenerFrontRear = innerFrontRear;
									int listenerLinkNumber = innerLinkNumber;
									int listenerInboardOutboard = innerInboardOutboard;
									int listenerXYZ = innerXYZ;
									System.out.println(arg0);
									vehicle.suspensionAdjustment(listenerFrontRear,listenerLinkNumber,listenerInboardOutboard,listenerXYZ,arg2);
								
								}
							});
						}
						if (frontRear == 1){
							((Spinner<Integer>)((HBox)((VBox)rearSuspensionPane.getChildren().get(linkNumber)).getChildren().get(xYZ+1)).getChildren().get(inboardOutboard+1)).valueProperty().addListener(new ChangeListener<Integer>() {
								@Override
								public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {

									int listenerFrontRear = innerFrontRear;
									int listenerLinkNumber = innerLinkNumber;
									int listenerInboardOutboard = innerInboardOutboard;
									int listenerXYZ = innerXYZ;
									System.out.println(arg0);
									vehicle.suspensionAdjustment(listenerFrontRear,listenerLinkNumber,listenerInboardOutboard,listenerXYZ,arg2);
								
								}
							});
						}
					}
				}
			}
		}
	}
		
		
	
	@FXML public void addRender() throws Exception{
		testing();
		setupSpinners();
	}
	
	
	@FXML private void testing() throws Exception {

		vehicle.createVehicle();
		renderView.getChildren().add(vehicle.group);
		vehicle.setHeightWidth();
		
		
	}
}