package org.usfirst.frc.team5865.robot.subsystems;

import org.usfirst.frc.team5865.robot.Const;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Lanceur extends Subsystem {

	public static CANTalon LanceurCANTalonDrive;

	public Lanceur() {
		LanceurCANTalonDrive = new CANTalon(Const.LANCEUR_CAN_ID);
		LiveWindow.addActuator("Lanceur", "CANTalonLanceur", LanceurCANTalonDrive);
	}

	public void initDefaultCommand() {
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.    
	public void lancer(double valeur) {
		LanceurCANTalonDrive.set(valeur);	
	}

	public void arreter() {
		LanceurCANTalonDrive.set(0);
	}
}

