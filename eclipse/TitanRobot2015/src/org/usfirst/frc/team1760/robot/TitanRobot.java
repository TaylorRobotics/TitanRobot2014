package org.usfirst.frc.team1760.robot;

import org.usfirst.frc.team1760.robot.autonomous.AutonomousMode;
import org.usfirst.frc.team1760.robot.autonomous.AutonomousModeFactory;
import org.usfirst.frc.team1760.robot.stores.JoystickStore;
import org.usfirst.frc.team1760.robot.stores.MotorStore;
import org.usfirst.frc.team1760.robot.stores.SolenoidStore;
import org.usfirst.frc.team1760.robot.stores.SwitchStore;
import org.usfirst.frc.team1760.robot.teleop.TeleopMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * This class is the software entry point for the Robot.
 * When the robot code is booted, this class will be instantiated, and the robotInit() method will be called.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class TitanRobot extends IterativeRobot {
	/**
	 * The component store for motor based components.
	 */
	private MotorStore motorStore;
	/**
	 * The component store for Joystick and Joystick button components.
	 */
	private JoystickStore joystickStore;
	/**
	 * The component store for pneumatics Solenoid components.
	 */
	private SolenoidStore solenoidStore;
	/**
	 * The component store for switch based components.
	 */
	private SwitchStore switchStore;
	/**
	 * The instance for running autonomouse mode.
	 */
	private AutonomousMode autonomousMode = null;
	/**
	 * The instance for running teleop mode.
	 */
	private TeleopMode teleopMode = null;

	/**
     * This method is called once when the robot is first started up.
     * It will create the component stores and turn on the pneumatics compressor.
     */
    public void robotInit() {
    	/* Create component stores */
		motorStore = new MotorStore();
		joystickStore = new JoystickStore();
		solenoidStore = new SolenoidStore();
		switchStore = new SwitchStore();

		/* Turn on compressor by getting any pneumatics solenoid */
		solenoidStore.getToteLiftSolenoid();
    }

    public MotorStore getMotorStore() {
    	return motorStore;
    }

    public JoystickStore getJoystickStore() {
    	return joystickStore;
    }

    public SolenoidStore getSolenoidStore() {
    	return solenoidStore;
    }

    public SwitchStore getSwitchStore() {
    	return switchStore;
    }

    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autonomousMode = new AutonomousModeFactory(this).create();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	autonomousMode.periodic();
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    	teleopMode = new TeleopMode(this);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	teleopMode.teleopPeriodic();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
