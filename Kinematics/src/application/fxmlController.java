package application;


import TestPackage.TestButton;
import TestPackage.loadTestValues;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import values.Statics;

public class fxmlController {
	
	DoubleSpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE); 
	@FXML private AnchorPane renderView;
	@FXML private AnchorPane rightView;
	@FXML private Spinner<Double> frontTireWidthSpinner;
	@FXML private Spinner<Double> frontTireDiameterSpinner;
	@FXML private Spinner<Double> frontTireRatioSpinner;
	@FXML private Spinner<Double> frontTireOffsetSpinner;
	@FXML private Spinner<Double> rearTireWidthSpinner;
	@FXML private Spinner<Double> rearTireDiameterSpinner;
	@FXML private Spinner<Double> rearTireRatioSpinner;
	@FXML private Spinner<Double> rearTireOffsetSpinner;
	@FXML private Spinner<Double> frontCamberSpinner;
	@FXML private Spinner<Double> frontToeSpinner;
	@FXML private Spinner<Double> frontRideHeightSpinner;
	@FXML private Spinner<Double> frontSuspensionTravelSpinner;
	@FXML private Spinner<Double> rearCamberSpinner;
	@FXML private Spinner<Double> rearToeSpinner;
	@FXML private Spinner<Double> rearRideHeightSpinner;
	@FXML private Spinner<Double> rearSuspensionTravelSpinner;
	@FXML private Spinner<Double> wheelbaseSpinner;
	@FXML private Spinner<Double> frontTrackSpinner;
	@FXML private Spinner<Double> rearTrackSpinner;
	@FXML private Spinner<Double> weightSpinner;
	@FXML private Spinner<Double> chassisStiffnessSpinner;
	@FXML private Spinner<Double> cogXSpinner;
	@FXML private Spinner<Double> cogYSpinner;
	@FXML private Spinner<Double> cogZSpinner;
	@FXML private CheckBox offsetUpdatesTrack;
	@FXML private CheckBox tireUpdatesRideHeight;
	
	private Spinner<?>[][][][] spinnerList = new Spinner<?>[2][5][2][3];
	@FXML VBox frontSuspensionPane;
	@FXML VBox rearSuspensionPane;
	
	@FXML private void selectAll(MouseEvent event) {
		@SuppressWarnings("unchecked")
		Spinner<Double> spins = (Spinner<Double>)event.getSource();
		spins.getEditor().selectAll();
	}
	
	@FXML private void testButton() {
		TestButton.testButton(spinnerList);
		//Calculated.calculateLinksMovements(0);
		//System.out.println("Link movements Tested");
	}
	
	vehicleBuilder vehicle = new vehicleBuilder();
	
	@SuppressWarnings("unchecked")
	@FXML public void setupSpinners() {
		//loop through spinners, looping through boxes, looping through title pain
		System.out.println("SetUp Spinner Listeners");
		
		frontTireWidthSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				double oldTire = Statics.frontTireDiameterCalc();
				Statics.frontTireWidth = arg2;
				if (tireUpdatesRideHeight.isSelected()){
					Statics.frontRideHeight = Statics.frontRideHeight + ((Statics.frontTireDiameterCalc()-oldTire)/2);
					frontRideHeightSpinner.getValueFactory().setValue((double) Statics.frontRideHeight);
				}
				Statics.updateHubCenter();
				vehicle.wheelAdjustment();
				for(int i = 0;i<5;i++) {
					vehicle.hubLinkAdjustment(0, i);
				}
			};
		});
		
		frontTireDiameterSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				double oldTire = Statics.frontTireDiameterCalc();
				Statics.frontTireDiameter = arg2*25.4;
				if (tireUpdatesRideHeight.isSelected()){
					Statics.frontRideHeight = (Statics.frontRideHeight + ((Statics.frontTireDiameterCalc()-oldTire)/2.0));
					frontRideHeightSpinner.getValueFactory().setValue((double) Statics.frontRideHeight);
				}
				Statics.updateHubCenter();
				vehicle.wheelAdjustment();
				for(int i = 0;i<5;i++) {
					vehicle.hubLinkAdjustment(0, i);
				}
			};
		});
		
		frontTireRatioSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				double oldTire = Statics.frontTireDiameterCalc();
				Statics.frontTireRatio = arg2;
				if (tireUpdatesRideHeight.isSelected()){
					Statics.frontRideHeight = (Statics.frontRideHeight + ((Statics.frontTireDiameterCalc()-oldTire)/2.0));
					frontRideHeightSpinner.getValueFactory().setValue((double) Statics.frontRideHeight);
				}
				Statics.updateHubCenter();
				vehicle.wheelAdjustment();
				for(int i = 0;i<5;i++) {
					vehicle.hubLinkAdjustment(0, i);
				}
			};
		});
		
		frontTireOffsetSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.frontTireOffset = arg2;
				if (offsetUpdatesTrack.isSelected()){
					Statics.frontTrack = Statics.frontTrack + arg2 - arg1;
					frontTrackSpinner.getValueFactory().setValue((double) Statics.frontTrack);
				}
				Statics.updateHubCenter();
				vehicle.wheelAdjustment();
				for(int i = 0;i<5;i++) {
					vehicle.hubLinkAdjustment(0, i);
				}
			};
		});
		
		rearTireWidthSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				double oldTire = Statics.rearTireDiameterCalc();
				Statics.rearTireWidth = arg2;
				if (tireUpdatesRideHeight.isSelected()){
					Statics.rearRideHeight = (Statics.rearRideHeight + ((Statics.rearTireDiameterCalc()-oldTire)/2.0));
					rearRideHeightSpinner.getValueFactory().setValue((double) Statics.rearRideHeight);
				}
				Statics.updateHubCenter();
				vehicle.wheelAdjustment();
				for(int i = 0;i<5;i++) {
					vehicle.hubLinkAdjustment(1, i);
				}
			};
		});
		
		rearTireDiameterSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				double oldTire = Statics.rearTireDiameterCalc();
				Statics.rearTireDiameter = arg2*25.4;
				if (tireUpdatesRideHeight.isSelected()){
					Statics.rearRideHeight = (Statics.rearRideHeight + ((Statics.rearTireDiameterCalc()-oldTire)/2.0));
					rearRideHeightSpinner.getValueFactory().setValue((double) Statics.rearRideHeight);
				}
				Statics.updateHubCenter();
				vehicle.wheelAdjustment();
				for(int i = 0;i<5;i++) {
					vehicle.hubLinkAdjustment(1, i);
				}
			};
		});
		
		rearTireRatioSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				double oldTire = Statics.rearTireDiameterCalc();
				Statics.rearTireRatio = arg2;
				if (tireUpdatesRideHeight.isSelected()){
					Statics.rearRideHeight = (Statics.rearRideHeight + ((Statics.rearTireDiameterCalc()-oldTire)/2.0));
					rearRideHeightSpinner.getValueFactory().setValue((double) Statics.rearRideHeight);
				}
				Statics.updateHubCenter();
				vehicle.wheelAdjustment();
				for(int i = 0;i<5;i++) {
					vehicle.hubLinkAdjustment(1, i);
				}
			};
		});
		
		rearTireOffsetSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.rearTireOffset = arg2;
				if (offsetUpdatesTrack.isSelected()){
					Statics.rearTrack = Statics.rearTrack + arg2 - arg1;
					rearTrackSpinner.getValueFactory().setValue((double) Statics.rearTrack);
				}
				Statics.updateHubCenter();
				vehicle.wheelAdjustment();
				for(int i = 0;i<5;i++) {
					vehicle.hubLinkAdjustment(1, i);
				}
			};
		});
		
		frontCamberSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.frontCamber = arg2;
				vehicle.wheelAdjustment();
			};
		});
		
		frontToeSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.frontToe = arg2;
				vehicle.wheelAdjustment();
			};
		});
		
		frontRideHeightSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.frontRideHeight = arg2;
			};
		});
		
		frontSuspensionTravelSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.frontSuspensionTravel = arg2;
			};
		});
		
		rearCamberSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.rearCamber = arg2;
				vehicle.wheelAdjustment();
			};
		});
		
		rearToeSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.rearToe = arg2;
				vehicle.wheelAdjustment();
			};
		});
		
		rearRideHeightSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.rearRideHeight = arg2;
			};
		});
		
		rearSuspensionTravelSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.rearSuspensionTravel = arg2;
			};
		});
		
		wheelbaseSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.updateWheelbase(arg2);
				vehicle.wheelAdjustment();
				//System.out.println("wheelbaseSpinner");
				for(int i = 0;i<5;i++) {
					vehicle.hubLinkAdjustment(0, i);
					vehicle.hubLinkAdjustment(1, i);
				}
			};
		});
		
		frontTrackSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.frontTrack = arg2;
				vehicle.wheelAdjustment();
				//System.out.println("frontTrackSpinner");
				for(int i = 0;i<5;i++) {
					vehicle.hubLinkAdjustment(0, i);
				}
			};
		});
		
		rearTrackSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.rearTrack = arg2;
				vehicle.wheelAdjustment();
				for(int i = 0;i<5;i++) {
					vehicle.hubLinkAdjustment(1, i);
				}
			};
		});
		
		weightSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.weight = arg2;
			};
		});
		
		chassisStiffnessSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.chassisStiffness = arg2;
			};
		});
		
		cogXSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.cog[0] = arg2;
				vehicle.cogAdjustment(0,arg2);
			};
		});
		
		cogYSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.cog[1] = arg2;
				vehicle.cogAdjustment(1,arg2);
			};
		});
		
		cogZSpinner.valueProperty().addListener(new ChangeListener<Double>() {
			@Override
			public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {
				Statics.cog[2] = arg2;
				vehicle.cogAdjustment(2,arg2);
			};
		});
		
		double val = 0;
		
		for(int frontRear = 0;frontRear<2;frontRear++) {
			for(int linkNumber = 0;linkNumber<5;linkNumber++) {
				for(int inboardOutboard = 0;inboardOutboard<2;inboardOutboard++) {
					for(int xYZ = 0;xYZ<3;xYZ++) {
						
						final int innerFrontRear = frontRear;
						final int innerLinkNumber = linkNumber;
						final int innerInboardOutboard = inboardOutboard;
						final int innerXYZ = xYZ;
						
						
						spinnerList[frontRear][linkNumber][inboardOutboard][xYZ]=(Spinner<Double>)((HBox)((VBox)frontSuspensionPane.getChildren().get(linkNumber)).getChildren().get(xYZ+1)).getChildren().get(inboardOutboard+1);
						val = (double)((Spinner<Double>)((HBox)((VBox)frontSuspensionPane.getChildren().get(linkNumber)).getChildren().get(xYZ+1)).getChildren().get(inboardOutboard+1)).getValue();
						vehicle.suspensionAdjustment(frontRear,linkNumber,inboardOutboard,xYZ,val);
						
						
						if (frontRear == 0){
							((Spinner<Double>)((HBox)((VBox)frontSuspensionPane.getChildren().get(linkNumber)).getChildren().get(xYZ+1)).getChildren().get(inboardOutboard+1)).valueProperty().addListener(new ChangeListener<Double>() {
								@Override
								public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {

									int listenerFrontRear = innerFrontRear;
									int listenerLinkNumber = innerLinkNumber;
									int listenerInboardOutboard = innerInboardOutboard;
									int listenerXYZ = innerXYZ;
									vehicle.suspensionAdjustment(listenerFrontRear,listenerLinkNumber,listenerInboardOutboard,listenerXYZ,arg2);
									
								}
							});
						}
						if (frontRear == 1){
							((Spinner<Double>)((HBox)((VBox)rearSuspensionPane.getChildren().get(linkNumber)).getChildren().get(xYZ+1)).getChildren().get(inboardOutboard+1)).valueProperty().addListener(new ChangeListener<Double>() {
								@Override
								public void changed(ObservableValue<? extends Double> arg0, Double arg1, Double arg2) {

									int listenerFrontRear = innerFrontRear;
									int listenerLinkNumber = innerLinkNumber;
									int listenerInboardOutboard = innerInboardOutboard;
									int listenerXYZ = innerXYZ;
									//System.out.println(arg0);
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
		//no longer needed
		Statics.updateStatics();
		
		
	}
	
	@FXML public void addRender() throws Exception{
		vehicle.createVehicle();
		 BorderPane borderPane = new BorderPane();
//       borderPane.getChildren().add(subScene);
		 borderPane.setCenter(vehicle.group.getChildren().get(0));
//       borderPane
//		renderView.getChildren().add(vehicle.group.getChildren().get(0));
		renderView.getChildren().add(borderPane);
//		borderPane.setManaged(false);
		
		AnchorPane.setBottomAnchor(borderPane, 0.0);
		AnchorPane.setTopAnchor(borderPane, 0.0);
		AnchorPane.setLeftAnchor(borderPane, 0.0);
		AnchorPane.setRightAnchor(borderPane, 0.0);
//		renderView.set
		vehicle.setHeightWidth();
		setupSpinners();
		loadTestValues();
	}
	
	@SuppressWarnings("unchecked")
	public void loadTestValues() {
		
		loadTestValues.initialLoad();
		
		frontTireWidthSpinner.getValueFactory().setValue(loadTestValues.frontTireWidth);
		frontTireDiameterSpinner.getValueFactory().setValue(loadTestValues.frontTireDiameter);
		frontTireRatioSpinner.getValueFactory().setValue(loadTestValues.frontTireRatio);
		frontTireOffsetSpinner.getValueFactory().setValue(loadTestValues.frontTireOffset);
		rearTireWidthSpinner.getValueFactory().setValue(loadTestValues.rearTireWidth);
		rearTireDiameterSpinner.getValueFactory().setValue(loadTestValues.rearTireDiameter);
		rearTireRatioSpinner.getValueFactory().setValue(loadTestValues.rearTireRatio);
		rearTireOffsetSpinner.getValueFactory().setValue(loadTestValues.rearTireOffset);
		frontCamberSpinner.getValueFactory().setValue(loadTestValues.frontCamber);
		frontToeSpinner.getValueFactory().setValue(loadTestValues.frontToe);
		frontRideHeightSpinner.getValueFactory().setValue(loadTestValues.frontRideHeight);
		frontSuspensionTravelSpinner.getValueFactory().setValue(loadTestValues.frontSuspensionTravel);
		rearCamberSpinner.getValueFactory().setValue(loadTestValues.rearCamber);
		rearToeSpinner.getValueFactory().setValue(loadTestValues.rearToe);
		rearRideHeightSpinner.getValueFactory().setValue(loadTestValues.rearRideHeight);
		rearSuspensionTravelSpinner.getValueFactory().setValue(loadTestValues.rearSuspensionTravel);
		wheelbaseSpinner.getValueFactory().setValue(loadTestValues.wheelbase);
		frontTrackSpinner.getValueFactory().setValue(loadTestValues.frontTrack);
		rearTrackSpinner.getValueFactory().setValue(loadTestValues.rearTrack);
		weightSpinner.getValueFactory().setValue(loadTestValues.weight);
		chassisStiffnessSpinner.getValueFactory().setValue(loadTestValues.chassisStiffness);
		cogXSpinner.getValueFactory().setValue(loadTestValues.cog[0]);
		cogYSpinner.getValueFactory().setValue(loadTestValues.cog[1]);
		cogZSpinner.getValueFactory().setValue(loadTestValues.cog[2]);
		
		for(int frontRear = 0;frontRear<2;frontRear++) {
			for(int linkNumber = 0;linkNumber<5;linkNumber++) {
				for(int inboardOutboard = 0;inboardOutboard<2;inboardOutboard++) {
					for(int xYZ = 0;xYZ<3;xYZ++) {
						if(frontRear == 0) {
							((Spinner<Double>)((HBox)((VBox)frontSuspensionPane.getChildren().get(linkNumber)).getChildren().get(xYZ+1)).getChildren().get(inboardOutboard+1)).getValueFactory().setValue(loadTestValues.linkArray[frontRear][linkNumber][inboardOutboard][xYZ]);
						}
						if(frontRear == 1) {
							((Spinner<Double>)((HBox)((VBox)rearSuspensionPane.getChildren().get(linkNumber)).getChildren().get(xYZ+1)).getChildren().get(inboardOutboard+1)).getValueFactory().setValue(loadTestValues.linkArray[frontRear][linkNumber][inboardOutboard][xYZ]);
						}
					}
				}
			}
		}
	}
	
	
	@FXML private void testing() throws Exception {

		
	}
}