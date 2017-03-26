package org.usfirst.frc.team5865.robot.commands;

import org.usfirst.frc.team5865.robot.Const;
import org.usfirst.frc.team5865.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunFeederCommand extends Command {
	public enum FeederCmdMode { mRun, mReset }
	private FeederCmdMode mMode;
	private double m_LastTimeToggled;

	public RunFeederCommand(FeederCmdMode mode) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.feeder);
		m_LastTimeToggled = 0;
		mMode = mode;
	}

	public RunFeederCommand() {
		this(FeederCmdMode.mRun);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (mMode) {
		case mRun:
			Robot.feeder.run();
			if (Timer.getFPGATimestamp() - m_LastTimeToggled >= Const.FEEDER_PERIOD) {
				Robot.feeder.toggleFeeder();

				m_LastTimeToggled = Timer.getFPGATimestamp();
			}
			break;
		case mReset:
			Robot.feeder.resetAngle();
			m_LastTimeToggled = 0;
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
		Robot.feeder.resetAngle();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
