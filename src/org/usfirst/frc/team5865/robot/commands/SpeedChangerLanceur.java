package org.usfirst.frc.team5865.robot.commands;

import org.usfirst.frc.team5865.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpeedChangerLanceur extends Command {
	
	public enum SpeedChangerMode { mMonter, mDescendre }
	public SpeedChangerMode mMode;

	public SpeedChangerLanceur(SpeedChangerMode mode) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lanceur);
		mMode = mode;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (mMode) {
		case mMonter:
			Robot.lanceur.speedUp();
			break;
		case mDescendre:
			Robot.lanceur.speedDown();
			break;
		default:
			break;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
