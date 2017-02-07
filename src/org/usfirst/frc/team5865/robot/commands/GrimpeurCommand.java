package org.usfirst.frc.team5865.robot.commands;

import org.usfirst.frc.team5865.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrimpeurCommand extends Command {

	public enum GrimpeurCmdMode { mGrimper, mArreter };
	private GrimpeurCmdMode mMode;

	public GrimpeurCommand(GrimpeurCmdMode mode) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.grimpeur);
		mMode = mode;
	}
	
	public GrimpeurCommand() {
		this(GrimpeurCmdMode.mGrimper);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (mMode) {
		case mGrimper:
			Robot.grimpeur.monter();
			break;
		case mArreter:
			Robot.grimpeur.arreter();
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
		Robot.lanceur.arreter();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
