package org.usfirst.frc.team5865.robot.commands.auto;

import org.usfirst.frc.team5865.robot.Utils;
import org.usfirst.frc.team5865.robot.commands.AutoDriveTimedCommand;
import org.usfirst.frc.team5865.robot.commands.AutoDriveVelocityCommand;
import org.usfirst.frc.team5865.robot.commands.AutoDriveTimedCommand.AutoDriveTimedCmdMode;
import org.usfirst.frc.team5865.robot.commands.AutoDriveVelocityCommand.AutoDriveVelocityCmdMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStraightLine extends CommandGroup {

	public AutoStraightLine() {
		// Avancer ligne droite
		addSequential(new AutoDriveVelocityCommand(Utils.inchesToMeters(250), AutoDriveVelocityCmdMode.kFoward));
		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 3 /*seconde*/));
		
		// Tourner de 180 degree
		addSequential(new AutoDriveVelocityCommand(180 /*degree*/, AutoDriveVelocityCmdMode.kTurnRight));
		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));
	}
}
