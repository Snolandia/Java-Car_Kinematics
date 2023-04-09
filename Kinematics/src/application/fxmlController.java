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
import values.Statics;

public class fxmlController {
	
	IntegerSpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE); 
	@FXML private AnchorPane renderView;
	@FXML private AnchorPane rightView;
	@FXML private Spinner<Integer> frontTireWidthSpinner;
	@FXML private Spinner<Integer> frontTireDiameterSpinner;
	@FXML private Spinner<Integer> frontTireRatioSpinner;
	@FXML private Spinner<Integer> frontTireOffsetSpinner;
	@FXML private Spinner<Integer> rearTireWidthSpinner;
	@FXML private Spinner<Integer> rearTireDiameterSpinner;
	@FXML private Spinner<Integer> rearTireRatioSpinner;
	@FXML private Spinner<Integer> rearTireOffsetSpinner;
	@FXML private Spinner<Integer> frontCamberSpinner;
	@FXML private Spinner<Integer> frontToeSpinner;
	@FXML private Spinner<Integer> frontRideHeightSpinner;
	@FXML private Spinner<Integer> frontSuspensionTravelSpinner;
	@FXML private Spinner<Integer> rearCamberSpinner;
	@FXML private Spinner<Integer> rearToeSpinner;
	@FXML private Spinner<Integer> rearRideHeightSpinner;
	@FXML private Spinner<Integer> rearSuspensionTravelSpinner;
	@FXML private Spinner<Integer> wheelbaseSpinner;
	@FXML private Spinner<Integer> trackSpinner;
	@FXML private Spinner<Integer> weightSpinner;
	@FXML private Spinner<Integer> chassisStiffnessSpinner;
	@FXML private Spinner<Integer> cogXSpinner;
	@FXML private Spinner<Integer> cogYSpinner;
	@FXML private Spinner<Integer> cogZSpinner;
	
	private Spinner<?>[][][][] spinnerList = new Spinner<?>[2][5][2][3];
	@FXML VBox frontSuspensionPane;
	@FXML VBox rearSuspensionPane;
	
	vehicleBuilder vehicle = new vehicleBuilder();
	
	@SuppressWarnings("unchecked")
	@FXML public void setupSpinners() {
		//loop through spinners, looping through boxes, looping through title pain
		System.out.println("SetUp Spinner Listeners");
		
		frontTireWidthSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.frontTireWidth = arg2;
			};
		});
		frontTireDiameterSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.frontTireDiameter = arg2;
			};
		});
		frontTireRatioSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.frontTireRatio = arg2;
			};
		});
		frontTireOffsetSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.frontTireOffset = arg2;
			};
		});
		rearTireWidthSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.rearTireWidth = arg2;
			};
		});
		rearTireDiameterSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.rearTireDiameter = arg2;
			};
		});
		rearTireRatioSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.rearTireRatio = arg2;
			};
		});
		rearTireOffsetSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.rearTireOffset = arg2;
			};
		});
		frontCamberSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.frontCamber = arg2;
			};
		});
		frontToeSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.frontToe = arg2;
			};
		});
		frontRideHeightSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.frontRideHeight = arg2;
			};
		});
		frontSuspensionTravelSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.frontSuspensionTravel = arg2;
			};
		});
		rearCamberSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.rearCamber = arg2;
			};
		});
		rearToeSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.rearToe = arg2;
			};
		});
		rearRideHeightSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.rearRideHeight = arg2;
			};
		});
		rearSuspensionTravelSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.rearSuspensionTravel = arg2;
			};
		});
		wheelbaseSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.wheelbase = arg2;
			};
		});
		trackSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.track = arg2;
			};
		});
		weightSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.weight = arg2;
			};
		});
		chassisStiffnessSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.chassisStiffness = arg2;
			};
		});
		cogXSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.cog[0] = arg2;
				vehicle.cogAdjustment(0,arg2);
			};
		});
		cogYSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.cog[1] = arg2;
				vehicle.cogAdjustment(1,arg2);
			};
		});
		cogZSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				Statics.cog[2] = arg2;
				vehicle.cogAdjustment(2,arg2);
			};
		});
		
		int val = 0;
		
		for(int frontRear = 0;frontRear<2;frontRear++) {
			for(int linkNumber = 0;linkNumber<5;linkNumber++) {
				for(int inboardOutboard = 0;inboardOutboard<2;inboardOutboard++) {
					for(int xYZ = 0;xYZ<3;xYZ++) {
						
						final int innerFrontRear = frontRear;
						final int innerLinkNumber = linkNumber;
						final int innerInboardOutboard = inboardOutboard;
						final int innerXYZ = xYZ;
						
						
						spinnerList[frontRear][linkNumber][inboardOutboard][xYZ]=(Spinner<Integer>)((HBox)((VBox)frontSuspensionPane.getChildren().get(linkNumber)).getChildren().get(xYZ+1)).getChildren().get(inboardOutboard+1);
						val = (int)((Spinner<Integer>)((HBox)((VBox)frontSuspensionPane.getChildren().get(linkNumber)).getChildren().get(xYZ+1)).getChildren().get(inboardOutboard+1)).getValue();
						vehicle.suspensionAdjustment(frontRear,linkNumber,inboardOutboard,xYZ,val);
						Statics.updateLinks(frontRear,linkNumber,inboardOutboard,xYZ,val);
						
						
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
									Statics.updateLinks(listenerFrontRear,listenerLinkNumber,listenerInboardOutboard,listenerXYZ,arg2);
									
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
		
		
	
	@FXML public void staticSetter() {
		//todo set up listeners for vehicle specs
		Statics.updateStatics();
		
		
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