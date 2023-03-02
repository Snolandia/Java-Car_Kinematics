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
	
	
	vehicleBuilder staff = new vehicleBuilder();
	
	@FXML public void setupSpinners() {
		//loop through spinners, looping through boxes, looping through title pain
		for(int i = 0;i<5;i++) {
			for(int j = 1;j<4;j++) {
				for(int k = 1;k<3;k++) {
				//System.out.println(((HBox)((VBox)frontSuspensionPane.getChildren().get(i)).getChildren().get(j)).getChildren().get(k));
				spinnerList[0][i][k-1][j-1]=(Spinner<?>)((HBox)((VBox)frontSuspensionPane.getChildren().get(i)).getChildren().get(j)).getChildren().get(k);
				}
			}
		}
		for(int i = 0;i<5;i++) {
			for(int j = 1;j<3;j++) {
				for(int k = 1;k<4;k++) {
					System.out.println(spinnerList[0][i][j-1][k-1]);
				}
			}
		}
	}
	
	@FXML public void addRender() throws Exception{
		testing();
		link1Setup();
		setupSpinners();
		//System.out.println(spinnerF1iX.valueProperty().getBean());
	}
	@FXML private void link1Setup() {
		
		spinnerF1iX.valueProperty().addListener(new ChangeListener<Integer>() {
			
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
				((Xform)staff.frontSuspension.getChildren().get(0)).setTx(arg2);
				
			}
		});

		spinnerF1iY.valueProperty().addListener(new ChangeListener<Integer>() {
			
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
				((Xform)staff.frontSuspension.getChildren().get(0)).setTy(arg2);
			}
		});

		spinnerF1iZ.valueProperty().addListener(new ChangeListener<Integer>() {
			
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
				((Xform)staff.frontSuspension.getChildren().get(0)).setTz(arg2);
			}
		});
		
		spinnerF1oX.valueProperty().addListener(new ChangeListener<Integer>() {
			
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
				((Xform)staff.frontSuspension.getChildren().get(0)).setRx(arg2);
				
			}
		});

		spinnerF1oY.valueProperty().addListener(new ChangeListener<Integer>() {
			
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
				((Xform)staff.frontSuspension.getChildren().get(0)).setRy(arg2);
			}
		});

		spinnerF1oZ.valueProperty().addListener(new ChangeListener<Integer>() {
			
			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
				((Xform)staff.frontSuspension.getChildren().get(0)).setRz(arg2);
			}
		});
		
	}
	@FXML private void testing() throws Exception {
		
		
		
		
		
		
		
		
		staff.addMoly();
		renderView.getChildren().add(staff.group);
//		
		staff.frontSuspension.setTranslate(0, 15, 0);
		
		((Xform)staff.frontSuspension.getChildren().get(0)).setTranslate(15, 0, 15);
		((Xform)staff.frontSuspension.getChildren().get(1)).setTranslate(15, 0, -15);
		((Xform)staff.frontSuspension.getChildren().get(2)).setTranslate(-15, 0, 15);
		((Xform)staff.frontSuspension.getChildren().get(3)).setTranslate(-15, 0, -15);
		((Xform)staff.frontSuspension.getChildren().get(4)).setTranslate(0, 0, 0);
		
		
		staff.frontSuspension.setTranslate(0, -15, 0);
		
		for(int i = 0;i<5;i++){
			//System.out.println(staff.frontSuspension.getChildren().get(i));
		}
		
		staff.setHeightWidth();
		
	}
}