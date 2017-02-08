package org.usfirst.frc.team5865.robot.subsystems;

import org.usfirst.frc.team5865.robot.Const;
import org.usfirst.frc.team5865.robot.Utils;
import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Lanceur extends Subsystem {

	private CANTalon LanceurCANTalonDrive;
	private Servo AngleAdjuster;
		
	private double mSpeed;
	private double mAngle;

	public Lanceur() {
		LanceurCANTalonDrive = new CANTalon(Const.LANCEUR_CAN_ID);
		LanceurCANTalonDrive.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		LanceurCANTalonDrive.set(0);
		
		AngleAdjuster = new Servo(0);
		AngleAdjuster.set(Const.LANCEUR_DEF_ANGLE);
		
		LiveWindow.addActuator("Lanceur", "CANTalonLanceur", LanceurCANTalonDrive);
		
		mSpeed = Const.LANCEUR_DEF_SPEED;
		mAngle = Const.LANCEUR_DEF_ANGLE;
	}

	public void initDefaultCommand() {
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.    
	public void lancer() {
		LanceurCANTalonDrive.set(Utils.Limit(mSpeed));	
	}

	public void arreter() {
		LanceurCANTalonDrive.set(0);
	}

	public double speedUp() {
		mSpeed = Utils.Limit(mSpeed + Const.LANCEUR_INCREMENT_SPEED, 0, Const.LANCEUR_MAX_SPEED);
		return mSpeed;
	}
	
	public double speedDown() {
		mSpeed = Utils.Limit(mSpeed - Const.LANCEUR_INCREMENT_SPEED, 0, Const.LANCEUR_MAX_SPEED);
		return mSpeed;
	}
	
	public void angleUp() {
		mAngle = Utils.Limit(mAngle + Const.LANCEUR_INCREMENT_ANGLE, Const.LANCEUR_MIN_ANGLE, Const.LANCEUR_MAX_ANGLE);
		AngleAdjuster.set(mAngle);
	}
	
	public void angleDown() {
		mAngle = Utils.Limit(mAngle - Const.LANCEUR_INCREMENT_ANGLE, Const.LANCEUR_MIN_ANGLE, Const.LANCEUR_MAX_ANGLE);
		AngleAdjuster.set(mAngle);
	}
}

