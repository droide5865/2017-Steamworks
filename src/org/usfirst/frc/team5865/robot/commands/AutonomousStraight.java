package org.usfirst.frc.team5865.robot.commands;

import org.usfirst.frc.team5865.robot.Const;
import org.usfirst.frc.team5865.robot.commands.AutoDriveMagicMotionCommand.AutoMagicMotionCmdMode;
import org.usfirst.frc.team5865.robot.commands.AutoDriveTimedCommand.AutoDriveTimedCmdMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousStraight extends CommandGroup {

	public AutonomousStraight() {
		// Avancer en ligne droite
		addSequential(new AutoDriveMagicMotionCommand(Const.AUTO_DIST_2_GEAR_STRAIGHT, AutoMagicMotionCmdMode.kFoward));

		// Delai - stopped robot
		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));
	}
}
