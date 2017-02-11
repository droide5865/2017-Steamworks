package org.usfirst.frc.team5865.robot;

import org.usfirst.frc.team5865.joystick.XboxControllerUD;
import org.usfirst.frc.team5865.robot.commands.AngleChangerLanceur;
import org.usfirst.frc.team5865.robot.commands.AngleChangerLanceur.AngleAdjusterMode;
import org.usfirst.frc.team5865.robot.commands.GobeurCommand;
import org.usfirst.frc.team5865.robot.commands.GrimpeurCommand;
import org.usfirst.frc.team5865.robot.commands.LanceurCommand;
import org.usfirst.frc.team5865.robot.commands.SpeedChangerLanceur;
import org.usfirst.frc.team5865.robot.commands.SpeedChangerLanceur.SpeedChangerMode;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public XboxControllerUD xboxPilot;

	public OI() {
		xboxPilot = new XboxControllerUD(0);

		xboxPilot.y.toggleWhenPressed(new GrimpeurCommand());
		xboxPilot.b.toggleWhenPressed(new LanceurCommand());
		xboxPilot.a.toggleWhenPressed(new GobeurCommand());
		
		xboxPilot.rb.whenPressed(new SpeedChangerLanceur(SpeedChangerMode.mMonter));
		xboxPilot.lb.whenPressed(new SpeedChangerLanceur(SpeedChangerMode.mDescendre));
		xboxPilot.start.whenPressed(new AngleChangerLanceur(AngleAdjusterMode.mMonter));
		xboxPilot.back.whenPressed(new AngleChangerLanceur(AngleAdjusterMode.mDescendre));
		
	}

	public XboxControllerUD getXboxPilot() {
		return xboxPilot;
	}
}
