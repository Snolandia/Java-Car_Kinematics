package values;




public class Statics {
	//set up variables that will not be changing once set
	
	String frontType = "k"; // MacPherson, 3-Link, 4-Link, 5-Link
	String rearType = "k";  // MacPherson, 3-Link, 4-Link, 5-Link
	int rearLinkCount = 0;  // 3,4,5
	int frontLinkCount = 0; // 3,4,5
	
	
	double wheelbase = 0;
	double track = 0;
	double staticCastor = 0;
	double staticCamber = 0;
	double staticToe = 0;
	double frontRideHeight = 0;
	double rearRideHeight = 0;
	double frontSuspensionTravel = 0;
	double rearSuspensionTravel = 0;
	double weight = 0;
	double[] cog = {0,0,0};
	double[][] frontMountBox = {
			{0,0,0},
			{0,0,0},
	};
	double[][] rearMountBox = {
			{0,0,0},
			{0,0,0},
	};		
	double chassisStiffness = 0;
	
	double frontTireWidth = 0;
	double frontTireDiameter = 0;
	double frontTireRatio = 0;
	double frontTireOffset = 0;
	
	double rearTireWidth = 0;
	double rearTireDiameter = 0;
	double rearTireRatio = 0;
	double rearTireOffset = 0;
	
	
}
