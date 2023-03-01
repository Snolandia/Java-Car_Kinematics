package values;

public class Calculated  {
	
	// X = Width
	// Y = Length
	// Z = Height
	
	double camber = 0;
	public static double camberCalc(double[][] points,int linkCount) {
		
		double[] groundPlane = {0,0,-8,0};
		double[] tireAngle = generalFormulas.planeFromPoints(points,linkCount);
		double camber = generalFormulas.angleFromPlanes(tireAngle, groundPlane);	
		
		return camber;
	}
	
	double castor = 0;
	
	double toe = 0;
	public static double toeCalc(double[][] points,int linkCount) {
		
		double[] widthPlane = {0,-8,0,0};
		double[] tireAngle = generalFormulas.planeFromPoints(points,linkCount);
		double toe = generalFormulas.angleFromPlanes(tireAngle, widthPlane);	
		
		return toe;
	}
	
	double[][] innerPoints = {
			{0,0,0},
			{0,0,0},
			{0,0,0},
			{0,0,0},
			{0,0,0}
	};
	
	double[][] outerPoints = {
			{0,0,0},
			{0,0,0},
			{0,0,0},
			{0,0,0},
			{0,0,0}
	};
	public static double[][] outerPointsCalc(double height){
		double[][] points = new double[2][2];
		
		
		return points;
	}
	
	double frontRideHeight = 0;
	
	double rearRideHeight = 0;
	
	double[] frontSuspensionTravel = {0,0};
	
	double[] rearSuspensionTravel = {0,0};
	
	double weight = 0;
	
	
	
	// King Pin Inclination - Produces vertical displacement on turn, which produces a self centering torque. Also generates scrub radius
	// Calculated by steering axis, against vertical plane, in degrees. Larger degrees = bigger effect
	double kingPinInclination = 0;
	
	// Scrub Radius - Steering axis line extrapolated to ground, offset of tyre center contact point. Negative is when axis line is outside of center point.
	// Positive radius allows easier turning, less feel. Negative give harder feel. Small scrub radius is better
	double scrubRadius = 0;
	
	// Camber Profile - Graphed, Change in camber from static
	double [][] camberProfile = {
			{0,0},
			{0,0}
	};
	
	// Castor Profile - Graphed, Angle of wheel, with change in from static
	double [][] castorProfile = {
			{0,0},
			{0,0}
	};
	
	// Toe Profile - Graphed, Change in Toe from Static
	double [][] toeProfile = {
			{0,0},
			{0,0}
	};
	
	// Roll Profile - Graphed, Change in with Lateral Loads
	double [][] rollProfile = {
			{0,0},
			{0,0}
	};
	
	// Anti-Squat - Used to decrease amount the rear squats during acceleration. 100% means no squat
	// Calculated by (Side View Swing Arm = SVSA) : AntiSquat % = (SQR(SVSA Height^2 + SVSA Length^2))/(COG Height/WheelBase) 
	// Notes- Reduces Driver feedback. 80% is reasonable. Over 100% can cause jacking
	double antiSquat = 0;
	
	// Anti-Lift(Front) - Mostly for AWD/FWD. Used to decrease amount front end lifts on acceleration. 
	// Calculated by Using Instant Center of SVSA : Thrust * (Angle between Instant Center and height of wheel center.
	// Notes- Reduces Driver feedback. 80% is reasonable. Over 100% can cause jacking
	double frontAntiLift = 0;
	
	//Anti-Lift(Rear) - Used to prevent rear from lifting during heavy breaking.
	//Calculated by : % Rear Lift = % Rear Braking * (SQR(SVSA Height^2 + SVSA Length^2)) * (COG Height/WheelBase) 
	// Notes- Reduces Driver feedback. 80% is reasonable. Over 100% can cause jacking
	double rearAntiLift = 0;
	
	// Anti-Dive - Used to prevent front from diving during heavy breaking.
	//Calculated by : % Anti-Dive = % Front Braking * (SQR(SVSA Height^2 + SVSA Length^2)) * (COG Height/WheelBase) 
	// Notes- Reduces Driver feedback. 80% is reasonable. Over 100% can cause jacking
	double antiDive = 0;
	
	// Roll Center - Center point of which the vehicles rotates
	double [] rollCenter = {0,0,0};
	
	// Ackerman/Anti-Ackerman - Angle differences between steering tires, graphed, by steering angle. Used to balance slip angles / scrub of tires through a turn
	double ackerman = 0;
	
	// Bump Steer - Change in the steering angle due to suspension actuation. Undesirable. Can be measured as the difference between the arc of the tie rod
	// and the arc of the suspension.
	double bumpSteer = 0;
	
	// Front View Swing Arm (FVSA)- Moment Arm when viewed from the Front or Rear
	double [] frontViewSwingArm = {0,0,0};
	
	// Side View Swing Arm (SVSA)- Moment Arm when viewed from the side
	double [] sideViewSwingArm = {0,0,0};
	
	// Instant Center - point of which the moment arm rotates about
	double [] instantCenter = {0,0,0};
	
	// Slip Angles - Angle difference between direction the tire is pointing, and the direction the tire is traveling
	double slipAngles = 0;
	
	// Installation Ratio - Ratio of which the wheel actuates on the springs and dampers
	double installationRatio = 0;
	
	// Wheel Rate vs. Spring Rate - Wheel rate is the effective spring rate calculated at the wheel after accounting for the installation ratio
	double wheelRate = 0;
	double springRate = 0;
	
}
