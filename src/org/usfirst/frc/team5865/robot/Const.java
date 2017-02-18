package org.usfirst.frc.team5865.robot;

public class Const {
	
	// Talon mapping
	public static final int DRIVE_LEFT_MASTER_CAN_ID = 11;
	public static final int DRIVE_LEFT_SLAVE_CAN_ID = 21;
	public static final int DRIVE_RIGHT_MASTER_CAN_ID = 30;
	public static final int DRIVE_RIGHT_SLAVE_CAN_ID = 31;
	
	
	public static final int LANCEUR_CAN_ID = 42;
	public static final int GRIMPEUR_CAN_ID = 41;
	public static final int GOBEUR_CAN_ID = 40;
	
	// PWM mapping
	public static final int FEEDER_SERVO_PWM_CHANNEL = 1;

	// Robot measurements
	public static final double WHEELS_DIAMETER_IN = 6;
	public static final double WHEELS_DIAMETER_M = WHEELS_DIAMETER_IN * 0.0254;
	public static final double ROBOT_WHEEL2WHEEL_WIDTH_IN = 25;
	public static final double ROBOT_WHEEL2WHEEL_WIDTH_M = ROBOT_WHEEL2WHEEL_WIDTH_IN * 0.0254;
	
	// Drive
	private static final double DRIVE_MAX_RPM = 600; // Measured 2017/02/18 (roboRio web interface)
	public static final double 	DRIVE_F_GAIN = (1023 * 60 * 10) / (DRIVE_MAX_RPM * 4096);
	
	private static final double DRIVE_ERR_MOY = 600; // In native units (measured with CTR VelocityClosedLoop example)
	public static final double 	DRIVE_P_GAIN = (0.1 * 1023) / (DRIVE_ERR_MOY);
	public static final double 	DRIVE_I_GAIN = 0;
	public static final double 	DRIVE_D_GAIN = 0;
	
	// grimpeur
	public static final double GRIMPEUR_DEF_SPEED = 0.5;
	public static final double GRIMPEUR_MAX_SPEED = 1;
	public static final double GRIMPEUR_INCREMENT_SPEED = 0.1;
	public static final double GRIMPEUR_TIME_BEFORE_ACTIVATION = 30; // Seconds
	
	// gobeur
	public static final double GOBEUR_MAX_SPEED = 1;
	
	// lanceur
	public static final double LANCEUR_DEF_SPEED = 4100;
	public static final double LANCEUR_INCREMENT_SPEED = 100;
	public static final double LANCEUR_MAX_SPEED = 4500;
	
	private static final double SHOOTER_SRX_MAX_RPM = 4700; 	// Measured 14/02/2017 (To adjust with measured value)
	public static final double 	SHOOTER_F_GAIN = (1023 * 60 * 10) / (SHOOTER_SRX_MAX_RPM * 20);
	
	private static final double SHOOTER_SRX_ERR_MOY = 120; // In native units (measured with CTR VelocityClosedLoop example)
	public static final double 	SHOOTER_P_GAIN = (0.1 * 1023) / (SHOOTER_SRX_ERR_MOY);
	public static final double 	SHOOTER_I_GAIN = 0;
	public static final double 	SHOOTER_D_GAIN = 0;
	
	// Ball feeder
	public static final double FEEDER_RETRACTED_POSITION = 0.4;
	public static final double FEEDER_DEPLOYED_POSITION = 0.8;
	public static final double FEEDER_PERIOD = 2.0;
	

}
