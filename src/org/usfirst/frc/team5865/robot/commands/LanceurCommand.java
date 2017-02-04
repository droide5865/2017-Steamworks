package org.usfirst.frc.team5865.robot.commands;

import org.usfirst.frc.team5865.robot.Const;
import org.usfirst.frc.team5865.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LanceurCommand extends Command {

	private int stateLanceur = 0;      ///// 0 = arreter       1 == monter

	public LanceurCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.lanceur);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (stateLanceur == 0){
			Robot.lanceur.lancer(Const.LANCEUR_MAX_SPEED);
			stateLanceur = 1;
		} else {
			Robot.lanceur.arreter();
			stateLanceur = 0;
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
