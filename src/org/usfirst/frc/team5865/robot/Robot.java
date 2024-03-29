
package org.usfirst.frc.team5865.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5865.robot.commands.auto.AutoCenterSlot;
import org.usfirst.frc.team5865.robot.commands.auto.AutoSideSlot;
import org.usfirst.frc.team5865.robot.commands.auto.AutoStraightLine;
import org.usfirst.frc.team5865.robot.subsystems.BallFeeder;
import org.usfirst.frc.team5865.robot.subsystems.Drive;
import org.usfirst.frc.team5865.robot.subsystems.Gobeur;
import org.usfirst.frc.team5865.robot.subsystems.Grimpeur;
import org.usfirst.frc.team5865.robot.subsystems.Lanceur;
import org.usfirst.frc.team5865.robot.subsystems.Drive.DriveMode;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drive drive;
	public static Grimpeur grimpeur;
	public static Gobeur gobeur;
	public static Lanceur lanceur;
	public static BallFeeder feeder;

	Command autonomousCommand;
//	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		// Declare subsystem
		drive = new Drive();
		grimpeur = new Grimpeur();
		gobeur = new Gobeur();
//		lanceur = new Lanceur();
//		feeder = new BallFeeder();

		// OI must be constructed after subsystems. If the OI creates Commands
		//(which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// instantiate the command used for the autonomous period
		autonomousCommand = new AutoCenterSlot(true /*turnLeft*/);
//		autonomousCommand = new AutoSideSlot(true /*isLeftSlot*/);
		//		autonomousCommand = new AutoStraightLine();
		
//		chooser.addDefault(Const.AUTO_MODE_STRAIGHT, new AutoStraightLine());
//		chooser.addObject("My Auto", new MyAutoCommand());
//		SmartDashboard.putData("Auto mode", chooser);

		// Back camera (basse resolution pour respecter le bandwidth usb)
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(160, 120); //320x240
		camera.setFPS(5);

		// 
		UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
		camera2.setResolution(320, 240); // 640x480
		camera2.setFPS(15);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		// schedule the autonomous command (example)
		if (autonomousCommand != null) autonomousCommand.start();
		//		autonomousCommand = chooser.getSelected();

//		String autoSelected = SmartDashboard.getString("Auto Selector", "Default"); 
//		switch(autoSelected) { 
//		case "Left Slot (gear)": 
//			autonomousCommand = new AutoSideSlot(true /*isLeftSlot*/); 
//			break;
//		case "Right Slot (gear)": 
//			autonomousCommand = new AutoSideSlot(false /*isLeftSlot*/); 
//			break; 
//		case "Center Slot":
//			autonomousCommand = new AutoCenterSlot(true /*turnLeft*/); 
//			break;
//		case "Straight (no gear)": 
//		default:
//			autonomousCommand = new AutoStraightLine(); 
//			break; 
//		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();

		// Set drive in open loop
		drive.setDriveMode(DriveMode.kNone);
		drive.setDriveMode(DriveMode.kOpenLoop);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
