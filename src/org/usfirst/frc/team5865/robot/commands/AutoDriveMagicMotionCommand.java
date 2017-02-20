package org.usfirst.frc.team5865.robot.commands;

import org.usfirst.frc.team5865.robot.Const;
import org.usfirst.frc.team5865.robot.Robot;
import org.usfirst.frc.team5865.robot.Utils;
import org.usfirst.frc.team5865.robot.subsystems.Drive.DriveMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveMagicMotionCommand extends Command {

	public enum AutoMagicMotionCmdMode { 
		kTurnLeft,
		kTurnRight,
		kFoward,
		kBackward
	};
	
	private AutoMagicMotionCmdMode mMode;
	private double mPosition;
	
	public AutoDriveMagicMotionCommand(double value, AutoMagicMotionCmdMode mode) {
		requires(Robot.drive);

		if (value < 0)
			throw new IllegalArgumentException("Le premier parametre doit etre plus grand que 0");
		
		double croppedValue = value < 0 ? 0 : value; // Si plus petit que 0 on prends 0
		if (mode == AutoMagicMotionCmdMode.kTurnLeft || mode == AutoMagicMotionCmdMode.kTurnRight) {
			// If command is a turn, value is an angle in degree. 
			// Convert in distance then in number of rotation
			double distance = Utils.distAngle(croppedValue, Const.ROBOT_WHEEL2WHEEL_WIDTH_M);
			mPosition = Utils.distanceToRotation(distance, Const.ROBOT_WHEELS_DIAMETER_M);
		} else {
			// Else, value is a distance in meter.
			// Convert in distance then in number of rotation
			mPosition = Utils.distanceToRotation(croppedValue, Const.ROBOT_WHEELS_DIAMETER_M);
		}
		
		mMode = mode;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drive.setDriveMode(DriveMode.kMotionMagic);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (mMode) {
		case kTurnLeft:
			Robot.drive.driveAuto(-mPosition, mPosition);
			break;
		case kTurnRight:
			Robot.drive.driveAuto(mPosition, -mPosition);
			break;
		case kBackward:
			Robot.drive.driveAuto(-mPosition, -mPosition);
			break;
		case kFoward:
			Robot.drive.driveAuto(mPosition, mPosition);
			break;
		default:
			break;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.drive.getDistance() >= mPosition - (mPosition * Const.AUTO_PERMITTED_DIST_ERROR);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drive.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
