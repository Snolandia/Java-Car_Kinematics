package values;




public class Statics {
	//set up variables that will not be changing once set
	// X = Width
	// z = Length
	// y = Height
	
	String frontType = "k"; // MacPherson, 3-Link, 4-Link, 5-Link
	String rearType = "k";  // MacPherson, 3-Link, 4-Link, 5-Link
	public static int rearLinkCount = 0;  // 3,4,5
	public static int frontLinkCount = 0; // 3,4,5
	
	
	public static double wheelbase = 0;
	public static double track = 0;
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
	
	public static double frontTireWidth = 0;
	public static double frontTireDiameter = 0;
	public static double frontTireRatio = 0;
	public static double frontTireOffset = 0;
	
	public static double rearTireWidth = 0;
	public static double rearTireDiameter = 0;
	public static double rearTireRatio = 0;
	public static double rearTireOffset = 0;
	
	public static void updateStatics() {
		
	}
	
	public static double linkPoints[][][][] = new double[2][5][2][3];
	
	public static void updateLinks(int frontRear,int linkNumber,int inboardOutboard,int xYZ,double arg2) {
		linkPoints[frontRear][linkNumber][inboardOutboard][xYZ] = arg2;
	}
	
}
