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
public class AutoSideSlot extends CommandGroup {

	public AutoSideSlot(boolean leftSlot) {
		// Avancer en ligne droite
		addSequential(new AutoDriveVelocityCommand(Utils.inchesToMeters(105), AutoDriveVelocityCmdMode.kFoward));
		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));

		// Tourner 45 degrees
		addSequential(new AutoDriveVelocityCommand(45 /*degree*/, leftSlot ? AutoDriveVelocityCmdMode.kTurnRight : AutoDriveVelocityCmdMode.kTurnLeft));
		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));

		// Avancer jusqu'au crochet
		addSequential(new AutoDriveVelocityCommand(Utils.inchesToMeters(15), AutoDriveVelocityCmdMode.kFoward));
		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 3 /*seconde*/));

		// Reculer
		addSequential(new AutoDriveVelocityCommand(Utils.inchesToMeters(20), AutoDriveVelocityCmdMode.kBackward));
		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));

		// Tourner de 45 degrees
		addSequential(new AutoDriveVelocityCommand(45 /*degree*/, leftSlot ? AutoDriveVelocityCmdMode.kTurnLeft : AutoDriveVelocityCmdMode.kTurnRight));
		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));

		// Avancer en ligne droite pour traverser la ligne
		addSequential(new AutoDriveVelocityCommand(Utils.inchesToMeters(150), AutoDriveVelocityCmdMode.kFoward));
		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));
	}
}
