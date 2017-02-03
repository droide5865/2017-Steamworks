package org.usfirst.frc.team5865.robot;

public class Const {
	
	// Talon mapping
	public static final int DRIVE_LEFT_MASTER_CAN_ID = 11;
	public static final int DRIVE_LEFT_SLAVE_CAN_ID = 21;
	public static final int DRIVE_RIGHT_MASTER_CAN_ID = 30;
	public static final int DRIVE_RIGHT_SLAVE_CAN_ID = 31;
	
	// Robot measurements
	public static final double WHEELS_DIAMETER_IN = 3;
	public static final double WHEELS_DIAMETER_M = WHEELS_DIAMETER_IN * 0.0254;
	
	// Drive
	private static final double SRX_MAX_RPM = 460; 	// Measured 12/29/2016 (To adjust with measured value)
	public static final double F_GAIN = (1023 * 60 * 10) / (SRX_MAX_RPM * 4096);
	
	private static final double SRX_ERR_MOY = 600; // In native units (measured with CTR VelocityClosedLoop example)
	public static final double P_GAIN = (0.1 * 1023) / (SRX_ERR_MOY);
	
	// grimpeur
	public static final double GRIMPEUR_UP_MAX_SPEED = 0.5;

}
