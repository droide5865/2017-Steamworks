package org.usfirst.frc.team5865.robot.subsystems;

import org.usfirst.frc.team5865.joystick.XboxControllerUD;
import org.usfirst.frc5865.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Grimpeur extends Subsystem {
	
	private XboxControllerUD piloteXbox;
	public static CANTalon grimpeurCANTalonDrive;
	
	private int stateGrimpeur = 0;
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	grimpeurCANTalonDrive = new CANTalon(3);    /////// à modifier (le no du CAN)
         LiveWindow.addActuator("Grimpeur", "CANTalonGrimpeur", grimpeurCANTalonDrive);
    }
}

