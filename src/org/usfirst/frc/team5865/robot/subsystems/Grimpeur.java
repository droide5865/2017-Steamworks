package org.usfirst.frc.team5865.robot.subsystems;

import org.usfirst.frc.team5865.joystick.XboxControllerUD;
import org.usfirst.frc.team5865.robot.Const;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Grimpeur extends Subsystem {
	
    public static CANTalon grimpeurCANTalonDrive;
	
    public Grimpeur() {
        grimpeurCANTalonDrive = new CANTalon(Const.GRIMPEUR_CAN_ID);
        LiveWindow.addActuator("Grimpeur", "CANTalonGrimpeur", grimpeurCANTalonDrive);
    }
	
    public void initDefaultCommand() {        
    }
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.    
    public void monter(double valeur) {
    	grimpeurCANTalonDrive.set(valeur);	
    }
    
    public void arreter() {
    	grimpeurCANTalonDrive.set(0);
    }
}

