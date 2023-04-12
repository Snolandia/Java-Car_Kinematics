package values;




public class Statics {
	//set up variables that will not be changing once set
	// X = Width
	// y = Height
	// z = Length
	
	String frontType = "k"; // MacPherson, 3-Link, 4-Link, 5-Link
	String rearType = "k";  // MacPherson, 3-Link, 4-Link, 5-Link
	public static int rearLinkCount = 0;  // 3,4,5
	public static int frontLinkCount = 0; // 3,4,5
	
	
	public static double wheelbase = 50;
	
	public static void updateWheelbase(double arg2) {
		wheelbase = arg2;
		
	}
	
	public static double frontTrack = 45;
	public static double rearTrack = 55;
	public static double staticCastor = 0;
	public static double frontCamber = 0;
	public static double frontToe = 0;
	public static double rearCamber = 0;
	public static double rearToe = 0;
	public static double frontRideHeight = 0;
	public static double rearRideHeight = 0;
	public static double frontSuspensionTravel = 0;
	public static double rearSuspensionTravel = 0;
	public static double weight = 0;
	public static double[] cog = {0,0,0};
	public static double[][] frontMountBox = {
			{0,0,0},
			{0,0,0},
	};
	public static double[][] rearMountBox = {
			{0,0,0},
			{0,0,0},
	};		
	public static double chassisStiffness = 0;
	
	public static double frontTireWidth = 50;
	public static double frontTireDiameter = 2;
	public static double frontTireRatio = 12;
	public static double frontTireOffset = 0;
	
	public static double rearTireWidth = 50;
	public static double rearTireDiameter = 2;
	public static double rearTireRatio = 12;
	public static double rearTireOffset = 0;
	
	public static void updateStatics() {
		
	}
	
	public static double[][] hubCenter = new double[2][3];
	// [front/rear][x,y,z]
	public static double[][][][] hubLink = new double[2][5][2][3];
	public static double[][] hubLinkLength = new double[2][5];
	
	public static void hubLinkLengthUpdate(int frontRear, int linkNumber) {
		hubLinkLength[frontRear][linkNumber] = hubLinkLengthCalc(frontRear,linkNumber);
	}
	
	
	public static double hubLinkLengthCalc(int frontRear, int linkNumber) {
		double x = linkPoints[frontRear][linkNumber][1][0]-hubLink[frontRear][linkNumber][1][0];
		double y = linkPoints[frontRear][linkNumber][1][1]-hubLink[frontRear][linkNumber][1][1];
		double z = linkPoints[frontRear][linkNumber][1][2]-hubLink[frontRear][linkNumber][1][2];
		double length = Math.sqrt(x*x + y*y + z*z);
		
		return length;
	}
	
	public static double frontTireDiameterCalc() {
		return frontTireDiameter + (frontTireWidth * (frontTireRatio/100.0) * 2.0);
	}
	
	public static double rearTireDiameterCalc() {
		return rearTireDiameter + (rearTireWidth * (rearTireRatio/100.0) * 2.0);
	}
	
	public static void updateHubCenter() {
		
		//System.out.println("updateHubCenter");
		
		hubCenter[0][0] = (frontTrack/2.0)+frontTireOffset;
		for(int linkNumber = 0;linkNumber<5;linkNumber++) {
			hubLink[0][linkNumber][1][0] = (frontTrack/2.0)+frontTireOffset;
		}
		hubCenter[0][1] = (frontTireDiameter + (frontTireWidth * frontTireRatio/100.0 * 2.0))/2.0;
		for(int linkNumber = 0;linkNumber<5;linkNumber++) {
			hubLink[0][linkNumber][1][1] = ((frontTireDiameter + (frontTireWidth * frontTireRatio/100.0 * 2.0))/2.0);
		}
		
		hubCenter[0][2] = wheelbase/2.0;
		for(int linkNumber = 0;linkNumber<5;linkNumber++) {
			hubLink[0][linkNumber][1][2] = wheelbase/2.0;
		}
		
		hubCenter[1][0] = (rearTrack/2.0)+rearTireOffset;
		for(int linkNumber = 0;linkNumber<5;linkNumber++) {
			hubLink[1][linkNumber][1][0] = (rearTrack/2.0)+rearTireOffset;
		}
		
		hubCenter[1][1] = (rearTireDiameter + (rearTireWidth * rearTireRatio/100.0 * 2.0))/2.0;
		for(int linkNumber = 0;linkNumber<5;linkNumber++) {
			hubLink[1][linkNumber][1][1] = ((rearTireDiameter + (rearTireWidth * rearTireRatio/100.0 * 2.0))/2.0);
		}
		
		hubCenter[1][2] = -wheelbase/2.0;
		for(int linkNumber = 0;linkNumber<5;linkNumber++) {
			hubLink[1][linkNumber][1][2] = -wheelbase/2.0;
		}
		
	}
	
	public static double linkPoints[][][][] = new double[2][5][2][3];
	
	public static void updateLinks(int frontRear,int linkNumber,int inboardOutboard,int xYZ,double arg2) {
		linkPoints[frontRear][linkNumber][inboardOutboard][xYZ] = arg2;
		if(inboardOutboard == 1) {
			hubLink[frontRear][linkNumber][0][xYZ] = arg2;
			hubLinkLength[frontRear][linkNumber] = hubLinkLengthCalc(frontRear, linkNumber);
		}
		
	}
	
}
