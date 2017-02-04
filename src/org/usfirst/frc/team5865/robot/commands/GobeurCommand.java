package org.usfirst.frc.team5865.robot.commands;

import org.usfirst.frc.team5865.joystick.XboxControllerUD;
import org.usfirst.frc.team5865.robot.Const;
import org.usfirst.frc.team5865.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GobeurCommand extends Command {
	
	private int stateGobeur = 0;      ///// 0 = arreter       1 == monter

    public GobeurCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 if(stateGobeur == 0){
        	 Robot.grimpeur.monter(Const.GOBEUR_MAX_SPEED);
        	 stateGobeur = 1;
         }else{
        	 Robot.grimpeur.arreter();
        	 stateGobeur = 0;
         }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
