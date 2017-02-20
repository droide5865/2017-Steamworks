package org.usfirst.frc.team5865.robot.commands;

import org.usfirst.frc.team5865.robot.Const;
import org.usfirst.frc.team5865.robot.Robot;
import org.usfirst.frc.team5865.robot.Utils;
import org.usfirst.frc.team5865.robot.subsystems.Drive.DriveMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveTimedCommand extends Command {

	public static enum AutoDriveTimedCmdMode {
		kAvancer,
		kReculer,
		kTournerDroite,
		kTournerGauche,
		kArret
	}

	private AutoDriveTimedCmdMode m_mode;
	private double m_startTime;
	private double m_timeout;

	public AutoDriveTimedCommand(AutoDriveTimedCmdMode mode, double seconds) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		m_timeout = seconds;
		m_mode = mode;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drive.setDriveMode(DriveMode.kOpenLoop);
		m_startTime = Timer.getFPGATimestamp();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() { 
		double speed = Utils.Limit(Const.AUTO_MAX_SPEED_IN_TIMED_MODE, 0, 1);

		switch (m_mode) {
		case kAvancer:
			Robot.drive.drive(speed, 0);
			break;
		case kReculer:
			Robot.drive.drive(-speed, 0);
			break;
		case kTournerDroite:
			Robot.drive.drive(speed, 1);
			break;
		case kTournerGauche:
			Robot.drive.drive(speed, -1);
			break;
		case kArret:
			Robot.drive.stop();
			break;
		default:
			break;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (Timer.getFPGATimestamp() - m_startTime > m_timeout);
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
