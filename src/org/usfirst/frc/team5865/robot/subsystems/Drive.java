package org.usfirst.frc.team5865.robot.subsystems;

import org.usfirst.frc.team5865.robot.Const;
import org.usfirst.frc.team5865.robot.commands.DriveCommand;
import org.usfirst.frc.team5865.robot.Utils;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Subsystem {
	private final CANTalon driveSRX_RightMaster, driveSRX_RightSlave, driveSRX_LeftMaster, driveSRX_LeftSlave;
	private final RobotDrive driveRobotDrive;
	
	public enum DriveMode { kOpenLoop, kClosedLoop, kMotionMagic, kNone };
	private DriveMode mMode;
	
	private boolean m_RightSensorIsPresent, m_LeftSensorIsPresent;

	public Drive() {
		driveSRX_LeftMaster = new CANTalon(Const.DRIVE_LEFT_MASTER_CAN_ID);
		driveSRX_LeftSlave = new CANTalon(Const.DRIVE_LEFT_SLAVE_CAN_ID);
		driveSRX_RightMaster = new CANTalon(Const.DRIVE_RIGHT_MASTER_CAN_ID);
		driveSRX_RightSlave = new CANTalon(Const.DRIVE_RIGHT_SLAVE_CAN_ID);
		
		// Set brake mode
		driveSRX_LeftMaster.enableBrakeMode(true);
		driveSRX_LeftSlave.enableBrakeMode(true);
		driveSRX_RightMaster.enableBrakeMode(true);
		driveSRX_RightSlave.enableBrakeMode(true);

		// Set status at 100Hz
		driveSRX_LeftMaster.setStatusFrameRateMs(CANTalon.StatusFrameRate.Feedback, 10);
		driveSRX_RightMaster.setStatusFrameRateMs(CANTalon.StatusFrameRate.Feedback, 10);

		driveSRX_LeftMaster.configNominalOutputVoltage(0f, 0f);
		driveSRX_RightMaster.configNominalOutputVoltage(0f, 0f);
		
		driveSRX_LeftMaster.configPeakOutputVoltage(+12f, -12f);
		driveSRX_RightMaster.configPeakOutputVoltage(+12f, -12f);

		// Start in open loop
		setDriveMode(DriveMode.kOpenLoop);

		// Set slaves
		driveSRX_LeftSlave.changeControlMode(TalonControlMode.Follower);
		driveSRX_LeftSlave.set(Const.DRIVE_LEFT_MASTER_CAN_ID);
		driveSRX_RightSlave.changeControlMode(TalonControlMode.Follower);
		driveSRX_RightSlave.set(Const.DRIVE_RIGHT_MASTER_CAN_ID);
		
		// Set drive class helper
		driveRobotDrive = new RobotDrive(driveSRX_LeftMaster, driveSRX_RightMaster);
		driveRobotDrive.setSensitivity(0.5);
		driveRobotDrive.setSafetyEnabled(false);
		
		// Set up the encoders
		driveSRX_LeftMaster.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		m_LeftSensorIsPresent = driveSRX_LeftMaster.isSensorPresent(CANTalon.FeedbackDevice.CtreMagEncoder_Relative) != CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent;
		if (m_LeftSensorIsPresent) {
			DriverStation.reportError("Could not detect left drive encoder!", false);
		}
		driveSRX_LeftMaster.reverseSensor(true);
		driveSRX_LeftMaster.reverseOutput(false);
		
		driveSRX_RightMaster.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		m_RightSensorIsPresent = driveSRX_RightMaster.isSensorPresent(CANTalon.FeedbackDevice.CtreMagEncoder_Relative) != CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent;
		if (m_RightSensorIsPresent) {
			DriverStation.reportError("Could not detect right drive encoder!", false);
		}
		driveSRX_RightMaster.reverseSensor(false);
		driveSRX_RightMaster.reverseOutput(true);
		
		resetEncoders();
		
		// Set PIDs
		driveSRX_LeftMaster.setProfile(0);
		driveSRX_LeftMaster.setF(Const.DRIVE_F_GAIN);
		driveSRX_LeftMaster.setP(Const.DRIVE_P_GAIN);
		driveSRX_LeftMaster.setI(Const.DRIVE_I_GAIN); 
		driveSRX_LeftMaster.setD(Const.DRIVE_D_GAIN);
		
		driveSRX_RightMaster.setProfile(0);
		driveSRX_RightMaster.setF(Const.DRIVE_F_GAIN);
		driveSRX_RightMaster.setP(Const.DRIVE_P_GAIN);
		driveSRX_RightMaster.setI(Const.DRIVE_I_GAIN); 
		driveSRX_RightMaster.setD(Const.DRIVE_D_GAIN);
		
		// Set magic motion profile
		driveSRX_LeftMaster.setMotionMagicCruiseVelocity(Const.AUTO_CRUISING_RPM);
		driveSRX_LeftMaster.setMotionMagicAcceleration(Const.AUTO_ACCELERATION_RPM_PER_SEC);
		
		driveSRX_RightMaster.setMotionMagicCruiseVelocity(Const.AUTO_CRUISING_RPM);
		driveSRX_RightMaster.setMotionMagicAcceleration(Const.AUTO_ACCELERATION_RPM_PER_SEC);
	}

	public void setDriveMode(DriveMode mode) {
		setDriveMode(mode, false);
		}
	
	public void setDriveMode(DriveMode mode, boolean force) {
		if (mMode == mode && !force)
			return;
		
		mMode = mode;
		switch (mMode) {
		case kOpenLoop:
			driveSRX_LeftMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			driveSRX_LeftMaster.setInverted(false);
			driveSRX_LeftMaster.set(0);
			driveSRX_RightMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			driveSRX_RightMaster.setInverted(false);
			driveSRX_RightMaster.set(0);
			
//			driveRobotDrive.setSafetyEnabled(true);
			break;
		case kClosedLoop:
//			driveRobotDrive.setSafetyEnabled(false);
			driveSRX_LeftMaster.changeControlMode(CANTalon.TalonControlMode.Speed);
			driveSRX_RightMaster.changeControlMode(CANTalon.TalonControlMode.Speed);
			break;
		case kMotionMagic:
//			driveRobotDrive.setSafetyEnabled(false);
			driveSRX_LeftMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
			driveSRX_RightMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
			break;
		case kNone:
		default:
			break;
		}
		
		resetEncoders();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveCommand());
	}

	public synchronized void drive(double yspeed, double xspeed) {
		double curve = xspeed;
		if (yspeed > 0)
			curve = 0 - xspeed;

		driveRobotDrive.drive(yspeed, Utils.Limit(curve));
	}
	
	public void driveAuto(double leftMotor, double rightMotor) {
		driveSRX_LeftMaster.set(leftMotor);
		driveSRX_RightMaster.set(rightMotor);
	}

	public double getEncPosition() {
		// Return max of left/right drive
		return Math.max(driveSRX_LeftMaster.getPosition(), driveSRX_RightMaster.getPosition());
	}

//	public void outputToSmartDashboard() {
//		if (m_LeftSensorIsPresent & m_RightSensorIsPresent) {
//			SmartDashboard.putNumber("left_distance", 	rotationsToInches(driveSRX_LeftMaster.getPosition()));
//			SmartDashboard.putNumber("right_distance", 	rotationsToInches(driveSRX_RightMaster.getPosition()));
//			SmartDashboard.putNumber("left_velocity", 	rpmToInchesPerSecond(driveSRX_LeftMaster.getSpeed()));
//			SmartDashboard.putNumber("right_velocity", 	rpmToInchesPerSecond(driveSRX_RightMaster.getSpeed()));
//			SmartDashboard.putNumber("left_error", 		driveSRX_LeftMaster.getClosedLoopError());
//			SmartDashboard.putNumber("right_error", 	driveSRX_RightMaster.getClosedLoopError());
//		}
//	}

	public synchronized void resetEncoders() {
		driveSRX_LeftMaster.setPosition(0);
		driveSRX_RightMaster.setPosition(0);

		driveSRX_LeftMaster.setEncPosition(0);
		driveSRX_RightMaster.setEncPosition(0);
	}

	private static double rotationsToInches(double rotations) {
		return rotations * (Const.ROBOT_WHEELS_DIAMETER_IN * Math.PI);
	}

	private static double rotationsToMeters(double rotations) {
		return rotations * (Const.ROBOT_WHEELS_DIAMETER_M * Math.PI);
	}

	private static double rpmToInchesPerSecond(double rpm) {
		return rotationsToInches(rpm) / 60;
	}

	private static double rpmToMetersPerSecond(double rpm) {
		return rotationsToMeters(rpm) / 60;
	}

	private static double inchesPerSecondToRpm(double inches_per_second) {
		return inchesToRotations(inches_per_second) * 60;
	}

	private static double inchesToRotations(double inches) {
		return inches / (Const.ROBOT_WHEELS_DIAMETER_IN * Math.PI);
	}

	public synchronized void stop() {
		resetEncoders();
		driveSRX_LeftMaster.set(0);
		driveSRX_RightMaster.set(0);
//		driveRobotDrive.stopMotor();
	}

}

