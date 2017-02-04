package org.usfirst.frc.team5865.robot.subsystems;

import org.usfirst.frc.team5865.joystick.XboxControllerUD;
import org.usfirst.frc.team5865.robot.Const;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Gobeur extends Subsystem {
	
    public static CANTalon GobeurCANTalonDrive;
	
    public Gobeur() {
        GobeurCANTalonDrive = new CANTalon(Const.GOBEUR_CAN_ID);
        LiveWindow.addActuator("Gobeur", "CANTalonGobeur", GobeurCANTalonDrive);
    }
	
    public void initDefaultCommand() {        
    }
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.    
    public void gob(double valeur) {
    	GobeurCANTalonDrive.set(valeur);	
    }
    
    public void arreter() {
    	GobeurCANTalonDrive.set(0);
    }
}
