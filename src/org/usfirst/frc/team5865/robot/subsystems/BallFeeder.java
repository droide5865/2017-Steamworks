package org.usfirst.frc.team5865.robot.subsystems;

import org.usfirst.frc.team5865.robot.Const;
import org.usfirst.frc.team5865.robot.commands.RunFeederCommand;
import org.usfirst.frc.team5865.robot.commands.RunFeederCommand.FeederCmdMode;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallFeeder extends Subsystem {

	private enum ServoPosition { mDeployed, mRetracted, mZero }
	ServoPosition m_ServoPos;
	
	private Servo SmartServo;
	private double m_ServoAngle;

	public BallFeeder() {
		SmartServo = new Servo(Const.FEEDER_SERVO_PWM_CHANNEL);
		resetAngle();
	}

	public void initDefaultCommand() {
	}

	public void toggleFeeder() {
		// Switch position
		m_ServoPos = (m_ServoPos == ServoPosition.mDeployed) ? ServoPosition.mRetracted : ServoPosition.mDeployed;
		
		// Set servo to new position
		setServoToPosition(m_ServoPos);
	}

	public void resetAngle() {
		m_ServoPos = ServoPosition.mZero;
		setServoToPosition(m_ServoPos);
	}
	
	private void setServoToPosition(ServoPosition pos) {
		m_ServoAngle = computeAngle(pos);
		SmartServo.set(m_ServoAngle);
	}
	
	private double computeAngle(ServoPosition pos) {
		switch (pos) {
		case mDeployed:
			return Const.FEEDER_DEPLOYED_POSITION;
		case mRetracted:
			return Const.FEEDER_RETRACTED_POSITION;
		case mZero:
			return Const.FEEDER_RETRACTED_POSITION;
		default:
			break;
		}
		
		return 0;
	}
}
