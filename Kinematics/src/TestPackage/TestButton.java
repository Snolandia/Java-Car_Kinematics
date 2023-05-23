package TestPackage;

import java.text.DecimalFormat;

import application.fxmlController;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import values.Calculated;

public class TestButton {
	
	static DecimalFormat df = new DecimalFormat("#");
	
	
	public static void testButton(Spinner<?>[][][][] spinnerList) {
		df.setMaximumFractionDigits(30);
		double totalIters = 0;
		Calculated.calculateLinksMovements(0);
		totalIters++;
		//spinnerList[frontRear][linkNumber][inboardOutboard][xYZ]
		boolean test = false;
		//boolean test = true;
		double startTime = System.nanoTime();
		if(test) {
			DoubleSpinnerValueFactory vFactoryX;
			DoubleSpinnerValueFactory vFactoryY;
			DoubleSpinnerValueFactory vFactoryZ;
			double tests = 0;
			double valueX = 0.0;
			double valueY = 0.0;
			double valueZ = 0.0;
			double x = 0;
			double y = 0;
			double z = 0;
			int iters = 10;
			double steps = 50.0;
			for(int link = 0;link<5;link++) {
				x = (double) spinnerList[0][link][1][0].getValueFactory().getValue();
				y = (double) spinnerList[0][link][1][1].getValueFactory().getValue();
				z = (double) spinnerList[0][link][1][2].getValueFactory().getValue();
				vFactoryX = (DoubleSpinnerValueFactory) spinnerList[0][link][1][0].getValueFactory();
				vFactoryY = (DoubleSpinnerValueFactory) spinnerList[0][link][1][1].getValueFactory();
				vFactoryZ = (DoubleSpinnerValueFactory) spinnerList[0][link][1][2].getValueFactory();
				valueX = 0;
				for(int i = 0;i<iters;i++) {
					//System.out.println(tests);
					tests++;
					vFactoryX.setValue(valueX);
					valueX = valueX + steps+1;
					Calculated.calculateLinksMovements(0);
					totalIters++;
					valueY = 0;
					for(int j = 0;j<iters;j++) {
						//System.out.println(tests);
						tests++;
						vFactoryY.setValue(valueY);
						valueY = valueY + steps+2;
						Calculated.calculateLinksMovements(0);
						totalIters++;
						valueZ = 0;
						for(int k = 0;k<iters;k++) {
							//System.out.println(tests);
							tests++;
							vFactoryZ.setValue(valueZ);
							valueZ = valueZ + steps+3;
							Calculated.calculateLinksMovements(0);
							totalIters++;
							//System.out.println("XYZ : " + valueX + "," + valueY + ","+ valueZ);
						}
					}
				}
				
				vFactoryX.setValue(x);
				vFactoryY.setValue(y);
				vFactoryZ.setValue(z);
				
			}
		}
		double endTime = System.nanoTime();
		System.out.println("Link movements Tested with run time of : M:" + (Math.floorDiv((Math.round(((endTime-startTime)/1000000000))),60)) + "/S:" + ((Math.round(((endTime-startTime)/1000000000)))-(Math.floorDiv((Math.round(((endTime-startTime)/1000000000))),60)*60)));
		System.out.println("Total Iterations : " + totalIters);
//		for(int i = 0;i<iters;i++) {
//			vFactory = (DoubleSpinnerValueFactory) spinnerList[0][0][1][0].getValueFactory();
//			vFactory.setValue(value);
//			value = value + steps;
//		}
	}

}
