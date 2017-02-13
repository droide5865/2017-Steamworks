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
