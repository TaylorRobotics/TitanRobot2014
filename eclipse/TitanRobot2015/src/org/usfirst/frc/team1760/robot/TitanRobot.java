package org.usfirst.frc.team1760.robot;

import org.usfirst.frc.team1760.robot.autonomous.AutonomousMode;
import org.usfirst.frc.team1760.robot.autonomous.AutonomousModeFactory;
import org.usfirst.frc.team1760.robot.components.JoystickStore;
import org.usfirst.frc.team1760.robot.components.MotorStore;
import org.usfirst.frc.team1760.robot.components.SolenoidStore;
import org.usfirst.frc.team1760.robot.components.SwitchStore;
import org.usfirst.frc.team1760.robot.operations.ForkLiftOperator;
import org.usfirst.frc.team1760.robot.operations.ToteLiftOperator;
import org.usfirst.frc.team1760.robot.operations.TankDriveOperator;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class TitanRobot extends IterativeRobot {
	private MotorStore motorStore;
	private JoystickStore joystickStore;
	private SolenoidStore solenoidStore;
	private SwitchStore switchStore;

	private TankDriveOperator tankDriveOperator = null;
	private ToteLiftOperator toteLiftOperator = null;
	private ForkLiftOperator forkLiftOperator = null;

	private AutonomousMode autonomousMode = null;

	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		motorStore = new MotorStore();
		joystickStore = new JoystickStore();
		solenoidStore = new SolenoidStore();
		switchStore = new SwitchStore();
		solenoidStore.getToteLiftSolenoid();  // Start compressor by getting any solenoid
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
    	autonomousMode.autonomousInit();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	autonomousMode.autonomousPeriodic();
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    	tankDriveOperator = new TankDriveOperator(this);
    	toteLiftOperator = new ToteLiftOperator(this);
    	forkLiftOperator = new ForkLiftOperator(this);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	tankDriveOperator.periodic();
    	toteLiftOperator.periodic();
    	forkLiftOperator.periodic();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
