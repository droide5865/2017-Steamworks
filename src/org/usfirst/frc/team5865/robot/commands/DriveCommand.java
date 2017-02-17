package org.usfirst.frc.team5865.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5865.joystick.XboxControllerUD;
import org.usfirst.frc.team5865.robot.Robot;

/**
 *
 */
public class DriveCommand extends Command {

	public enum DriveCmdMode { mDrive, mResetEncoders };
	private DriveCmdMode mMode;

	private XboxControllerUD piloteXbox;

	public DriveCommand(DriveCmdMode mode) {
		mMode = mode;
		requires(Robot.drive);
	}

	public DriveCommand() {
		this(DriveCmdMode.mDrive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		piloteXbox = Robot.oi.getXboxPilot();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Calls the takeJoystickInput() function from the
		// driveTrain class. Takes input until the command ends.
		switch (mMode) {
		case mDrive:
			double Yspeed = piloteXbox.rt.getX() - piloteXbox.lt.getX();
			double Xspeed = piloteXbox.leftStick.getX();

			Robot.drive.drive(Yspeed, Xspeed);
			break;
		case mResetEncoders:
			Robot.drive.resetEncoders();
			break;
		default:
			break;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		switch (mMode) {
		case mDrive:
			return false;
		case mResetEncoders:
		default:
			return true;
		}
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
