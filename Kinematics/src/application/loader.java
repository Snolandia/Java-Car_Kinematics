package application;

public class loader {
	
	public static double[][][][] linkArray = new double[2][5][2][3];
	
	public static int rearLinkCount = 0;  // 3,4,5
	public static int frontLinkCount = 0; // 3,4,5
	
	public static double wheelbase = 1000;
	
	public static double frontTrack = 500;
	public static double rearTrack = 500;
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
	
	public static double frontTireWidth = 60;
	public static double frontTireDiameter = 4;
	public static double frontTireRatio = 35;
	public static double frontTireOffset = -26;
	
	public static double rearTireWidth = 60;
	public static double rearTireDiameter = 4;
	public static double rearTireRatio = 35;
	public static double rearTireOffset = -26;
	public static double[][] hubCenter = new double[2][3];
	// [front/rear][x,y,z]
	public static double[][][][] hubLink = new double[2][5][2][3];
	public static double[][] hubLinkLength = new double[2][5];
	public static double linkPoints[][][][] = new double[2][5][2][3];
	
	public static void initialLoad() {
		
		setLinkArray();
		updateHubCenter();
		
		
		
		
	}
	
	private static void setLinkArray() {
		
		linkArray[0][0][0][0] = 125;
		linkArray[0][0][0][1] = 50;
		linkArray[0][0][0][2] = 550;
		
		linkArray[0][0][1][0] = 200;
		linkArray[0][0][1][1] = 50;
		linkArray[0][0][1][2] = 550;
		//-----------------------------------------------------
		linkArray[0][1][0][0] = 125;
		linkArray[0][1][0][1] = 50;
		linkArray[0][1][0][2] = 450;
		
		linkArray[0][1][1][0] = 200;
		linkArray[0][1][1][1] = 50;
		linkArray[0][1][1][2] = 450;
		//-----------------------------------------------------
		linkArray[0][2][0][0] = 125;
		linkArray[0][2][0][1] = 90;
		linkArray[0][2][0][2] = 550;
		
		linkArray[0][2][1][0] = 200;
		linkArray[0][2][1][1] = 90;
		linkArray[0][2][1][2] = 550;
		//-----------------------------------------------------
		linkArray[0][3][0][0] = 125;
		linkArray[0][3][0][1] = 90;
		linkArray[0][3][0][2] = 450;
		
		linkArray[0][3][1][0] = 200;
		linkArray[0][3][1][1] = 90;
		linkArray[0][3][1][2] = 450;
		//-----------------------------------------------------
		linkArray[0][4][0][0] = 200;
		linkArray[0][4][0][1] = 50;
		linkArray[0][4][0][2] = 300;
		
		linkArray[0][4][1][0] = 200;
		linkArray[0][4][1][1] = 60;
		linkArray[0][4][1][2] = 450;
		///////////////////////////////////////////////////////////////////
		linkArray[1][0][0][0] = 125;
		linkArray[1][0][0][1] = 50;
		linkArray[1][0][0][2] = -550;
		
		linkArray[1][0][1][0] = 200;
		linkArray[1][0][1][1] = 50;
		linkArray[1][0][1][2] = -550;
		//-----------------------------------------------------
		linkArray[1][1][0][0] = 125;
		linkArray[1][1][0][1] = 50;
		linkArray[1][1][0][2] = -450;
		
		linkArray[1][1][1][0] = 200;
		linkArray[1][1][1][1] = 50;
		linkArray[1][1][1][2] = -450;
		//-----------------------------------------------------
		linkArray[1][2][0][0] = 125;
		linkArray[1][2][0][1] = 90;
		linkArray[1][2][0][2] = -550;
		
		linkArray[1][2][1][0] = 200;
		linkArray[1][2][1][1] = 90;
		linkArray[1][2][1][2] = -550;
		//-----------------------------------------------------
		linkArray[1][3][0][0] = 125;
		linkArray[1][3][0][1] = 90;
		linkArray[1][3][0][2] = -450;
		
		linkArray[1][3][1][0] = 200;
		linkArray[1][3][1][1] = 90;
		linkArray[1][3][1][2] = -450;
		//-----------------------------------------------------
		linkArray[1][4][0][0] = 200;
		linkArray[1][4][0][1] = 50;
		linkArray[1][4][0][2] = -300;
		
		linkArray[1][4][1][0] = 200;
		linkArray[1][4][1][1] = 60;
		linkArray[1][4][1][2] = -440;
		
		for(int frontRear = 0;frontRear<2;frontRear++) {
			for(int linkNumber = 0;linkNumber<5;linkNumber++) {
				for(int inboardOutboard = 0;inboardOutboard<2;inboardOutboard++) {
					for(int xYZ = 0;xYZ<3;xYZ++) {
						updateLinks(frontRear,linkNumber,inboardOutboard,xYZ,linkArray[frontRear][linkNumber][inboardOutboard][xYZ]);
					}
				}
			}
		}
		
		updateHubCenter();
	}
	
	private static void updateLinks(int frontRear,int linkNumber,int inboardOutboard,int xYZ,double arg2) {
		linkPoints[frontRear][linkNumber][inboardOutboard][xYZ] = arg2;
		if(inboardOutboard == 1) {
			hubLink[frontRear][linkNumber][0][xYZ] = arg2;
			hubLinkLength[frontRear][linkNumber] = hubLinkLengthCalc(frontRear, linkNumber);
		}
		
	}
	
	private static double hubLinkLengthCalc(int frontRear, int linkNumber) {
		double x = linkPoints[frontRear][linkNumber][1][0]-hubLink[frontRear][linkNumber][1][0];
		double y = linkPoints[frontRear][linkNumber][1][1]-hubLink[frontRear][linkNumber][1][1];
		double z = linkPoints[frontRear][linkNumber][1][2]-hubLink[frontRear][linkNumber][1][2];
		double length = Math.sqrt(x*x + y*y + z*z);
		
		return length;
	}
	
	private static void updateHubCenter() {
		
		//System.out.println("updateHubCenter");
		
		hubCenter[0][0] = (frontTrack/2.0)+frontTireOffset;
		for(int linkNumber = 0;linkNumber<5;linkNumber++) {
			hubLink[0][linkNumber][1][0] = (frontTrack/2.0)+frontTireOffset;
		}
		hubCenter[0][1] = (frontTireDiameter + (frontTireWidth * frontTireRatio/100 * 2.0))/2.0;
		for(int linkNumber = 0;linkNumber<5;linkNumber++) {
			hubLink[0][linkNumber][1][1] = ((frontTireDiameter + (frontTireWidth * frontTireRatio/100 * 2.0))/2.0);
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

}
