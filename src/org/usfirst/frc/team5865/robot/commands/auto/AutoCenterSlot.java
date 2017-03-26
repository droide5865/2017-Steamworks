package org.usfirst.frc.team5865.robot.commands.auto;

import org.usfirst.frc.team5865.robot.Const;
import org.usfirst.frc.team5865.robot.Utils;
import org.usfirst.frc.team5865.robot.commands.AutoDriveTimedCommand;
import org.usfirst.frc.team5865.robot.commands.AutoDriveVelocityCommand;
import org.usfirst.frc.team5865.robot.commands.AutoDriveTimedCommand.AutoDriveTimedCmdMode;
import org.usfirst.frc.team5865.robot.commands.AutoDriveVelocityCommand.AutoDriveVelocityCmdMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterSlot extends CommandGroup {

	// Chaque action doit etre associee a un delai
	public AutoCenterSlot(boolean turnLeft) {
		// Avancer en ligne droite et attendre le retrait de la gear
		addSequential(new AutoDriveVelocityCommand(Utils.inchesToMeters(125), AutoDriveVelocityCmdMode.kFoward));
		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 3 /*seconde*/));
		
//		// Reculer
//		addSequential(new AutoDriveVelocityCommand(Utils.inchesToMeters(65), AutoDriveVelocityCmdMode.kBackward));
//		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));
//		
//		// Tourner de 90 degree a gauche
//		addSequential(new AutoDriveVelocityCommand(90 /*degree*/, turnLeft ? AutoDriveVelocityCmdMode.kTurnLeft : AutoDriveVelocityCmdMode.kTurnRight));
//		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));
//		
//		// Avancer en ligne droite
//		addSequential(new AutoDriveVelocityCommand(Utils.inchesToMeters(75), AutoDriveVelocityCmdMode.kFoward));
//		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));
//		
//		// Tourner de 75 degree a droite
//		addSequential(new AutoDriveVelocityCommand(75 /*degree*/, turnLeft ? AutoDriveVelocityCmdMode.kTurnRight : AutoDriveVelocityCmdMode.kTurnLeft));
//		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));
//		
//		// Avancer en ligne droite
//		addSequential(new AutoDriveVelocityCommand(Utils.inchesToMeters(250), AutoDriveVelocityCmdMode.kFoward));
//		addSequential(new AutoDriveTimedCommand(AutoDriveTimedCmdMode.kArret, 1 /*seconde*/));
	}
}
