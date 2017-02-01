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
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Drive() {
        driveSRX_LeftMaster = new CANTalon(Const.DRIVE_LEFT_MASTER_CAN_ID);
        driveSRX_LeftSlave = new CANTalon(Const.DRIVE_LEFT_SLAVE_CAN_ID);
        driveSRX_RightMaster = new CANTalon(Const.DRIVE_RIGHT_MASTER_CAN_ID);
        driveSRX_RightSlave = new CANTalon(Const.DRIVE_RIGHT_SLAVE_CAN_ID);
             
        // Get status at 100Hz
        driveSRX_LeftMaster.setStatusFrameRateMs(CANTalon.StatusFrameRate.Feedback, 10);
        driveSRX_RightMaster.setStatusFrameRateMs(CANTalon.StatusFrameRate.Feedback, 10);
        
        // Start in open loop
        driveSRX_LeftMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        driveSRX_LeftMaster.set(0);
        driveSRX_RightMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        driveSRX_RightMaster.set(0);
        
        // Set slaves
        driveSRX_LeftSlave.changeControlMode(TalonControlMode.Follower);
        driveSRX_LeftSlave.set(Const.DRIVE_LEFT_MASTER_CAN_ID);
        driveSRX_RightSlave.changeControlMode(TalonControlMode.Follower);
        driveSRX_RightSlave.set(Const.DRIVE_RIGHT_MASTER_CAN_ID);
        
        // Set up the encoders
        driveSRX_LeftMaster.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        if (driveSRX_LeftMaster.isSensorPresent(
                CANTalon.FeedbackDevice.CtreMagEncoder_Relative) != CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent) {
            DriverStation.reportError("Could not detect left drive encoder!", false);
        }
        driveSRX_LeftMaster.reverseSensor(true);
        driveSRX_LeftMaster.reverseOutput(false);
        driveSRX_LeftSlave.reverseOutput(false);
        
        driveSRX_RightMaster.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        if (driveSRX_RightMaster.isSensorPresent(
                CANTalon.FeedbackDevice.CtreMagEncoder_Relative) != CANTalon.FeedbackDeviceStatus.FeedbackStatusPresent) {
            DriverStation.reportError("Could not detect right drive encoder!", false);
        }
        driveSRX_RightMaster.reverseSensor(false);
        driveSRX_RightMaster.reverseOutput(true);
        driveSRX_RightSlave.reverseOutput(false);

        
        driveRobotDrive = new RobotDrive(driveSRX_LeftMaster, driveSRX_RightMaster);
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveCommand());
    }
    
    public void drive(double yspeed, double xspeed) {
    	double curve = xspeed;
    	if (yspeed > 0)
    		curve = 0 - xspeed;
    		
    	driveRobotDrive.drive(yspeed, Utils.Limit(curve));
    }
    
    public void outputToSmartDashboard() {
        SmartDashboard.putNumber("left_distance", 	rotationsToInches(driveSRX_LeftMaster.getPosition()));
        SmartDashboard.putNumber("right_distance", 	rotationsToInches(driveSRX_RightMaster.getPosition()));
        SmartDashboard.putNumber("left_velocity", 	rpmToInchesPerSecond(driveSRX_LeftMaster.getSpeed()));
        SmartDashboard.putNumber("right_velocity", 	rpmToInchesPerSecond(driveSRX_RightMaster.getSpeed()));
        SmartDashboard.putNumber("left_error", 		driveSRX_LeftMaster.getClosedLoopError());
        SmartDashboard.putNumber("right_error", 	driveSRX_RightMaster.getClosedLoopError());
    }
    
    public synchronized void resetEncoders() {
    	driveSRX_LeftMaster.setPosition(0);
    	driveSRX_RightMaster.setPosition(0);

        driveSRX_LeftMaster.setEncPosition(0);
        driveSRX_RightMaster.setEncPosition(0);
    }
    
    private static double rotationsToInches(double rotations) {
        return rotations * (Const.WHEELS_DIAMETER_IN * Math.PI);
    }
    
    private static double rotationsToMeters(double rotations) {
        return rotations * (Const.WHEELS_DIAMETER_M * Math.PI);
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
        return inches / (Const.WHEELS_DIAMETER_IN * Math.PI);
    }
    
    public void stop() {
    	driveRobotDrive.stopMotor();
    }
}
