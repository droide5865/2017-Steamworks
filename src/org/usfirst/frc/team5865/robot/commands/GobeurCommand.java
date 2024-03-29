package org.usfirst.frc.team5865.robot.commands;

import org.usfirst.frc.team5865.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GobeurCommand extends Command {

	public enum GobeurCmdMode { mGober, mArreter };
	private GobeurCmdMode mMode;

	public GobeurCommand(GobeurCmdMode mode) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.gobeur);
		mMode = mode;
	}
	
	public GobeurCommand() {
		this(GobeurCmdMode.mGober);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (mMode) {
		case mGober:
			Robot.gobeur.gober();
			break;
		case mArreter:
			Robot.gobeur.arreter();
			break;
		default:
			break;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.gobeur.arreter();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
