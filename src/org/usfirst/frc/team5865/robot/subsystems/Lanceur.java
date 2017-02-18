package org.usfirst.frc.team5865.robot.subsystems;

import org.usfirst.frc.team5865.robot.Const;
import org.usfirst.frc.team5865.robot.Utils;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Lanceur extends Subsystem {

	private CANTalon LanceurCANTalonDrive;

	private double mSpeed;

	public Lanceur() {
		LanceurCANTalonDrive = new CANTalon(Const.LANCEUR_CAN_ID);
		LanceurCANTalonDrive.changeControlMode(CANTalon.TalonControlMode.Speed);
		LanceurCANTalonDrive.configPeakOutputVoltage(+0.0f, -12.0f);
		LanceurCANTalonDrive.set(0);

		LanceurCANTalonDrive.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		LanceurCANTalonDrive.configEncoderCodesPerRev(20);
		LanceurCANTalonDrive.reverseSensor(false);
		LanceurCANTalonDrive.reverseOutput(true);

		LanceurCANTalonDrive.setProfile(0);
		LanceurCANTalonDrive.setF(Const.SHOOTER_F_GAIN);
		LanceurCANTalonDrive.setP(Const.SHOOTER_P_GAIN);
		LanceurCANTalonDrive.setI(Const.SHOOTER_I_GAIN); 
		LanceurCANTalonDrive.setD(Const.SHOOTER_D_GAIN);

		LiveWindow.addActuator("Lanceur", "CANTalonLanceur", LanceurCANTalonDrive);

		mSpeed = Const.LANCEUR_DEF_SPEED;
	}

	public void initDefaultCommand() {
	}

	public void lancer() {
		LanceurCANTalonDrive.set(mSpeed / 4);	
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
}

