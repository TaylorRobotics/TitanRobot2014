package org.usfirst.frc.team1760.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot;
	Joystick leftStick;
	Joystick rightStick;
	Joystick operatorStick;
	int autoLoopCounter;
	DoubleSolenoid solenoid;
	Victor liftMotor;
	DigitalInput upperLimitSwitch;
	DigitalInput lowerLimitSwitch;
	boolean holdHigh;
	boolean parkLow;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	myRobot = new RobotDrive(0,1,2,3);
    	myRobot.setInvertedMotor(MotorType.kFrontLeft, true);
    	myRobot.setInvertedMotor(MotorType.kRearLeft, true);
    	myRobot.setInvertedMotor(MotorType.kFrontRight, true);
    	myRobot.setInvertedMotor(MotorType.kRearRight, true);
    	leftStick = new Joystick(0);
    	rightStick = new Joystick(1);
    	operatorStick = new Joystick(2);
    	solenoid = new DoubleSolenoid(0, 1);
    	liftMotor = new Victor(4);
    	upperLimitSwitch = new DigitalInput(0);
    	lowerLimitSwitch = new DigitalInput(1);
    	holdHigh = false;
    	parkLow = false;
    }
    
    /**
     * This function is run once each time the robot enters autonomous mode
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if(autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
		{
			myRobot.drive(-0.5, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
			} else {
			myRobot.drive(0.0, 0.0); 	// stop robot
		}
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        myRobot.tankDrive(leftStick, rightStick);
        if (operatorStick.getRawButton(4)) {
        	solenoid.set(DoubleSolenoid.Value.kReverse);
        }
        else if (operatorStick.getRawButton(5)) {
        	solenoid.set(DoubleSolenoid.Value.kForward);
        }
        else {
        	solenoid.set(DoubleSolenoid.Value.kOff);
        }

        if (operatorStick.getRawButton(6)) {
        	holdHigh = true;
        	parkLow = false;
        }
        else if (operatorStick.getRawButton(7)) {
        	parkLow = true;
        	holdHigh = false;
        }
        else if (operatorStick.getRawButton(8)) {
        	parkLow = false;
        	holdHigh = false;
        }

        double speed = 0.0;

        if (parkLow) {
            if (upperLimitSwitch.get()) {
            	parkLow = false;
            }
            else {
            	speed = -0.5;
            }
        }

        if (holdHigh) {
        	speed = 0.75;
        }
        else {
            double y = operatorStick.getY();
            speed = y * y;
        }

        if ((speed > 0.0) && upperLimitSwitch.get()) {
        	liftMotor.set(0.0);
        }
        else if ((speed < 0.0) && lowerLimitSwitch.get()) {
        	liftMotor.set(0.0);
        }
        else {
        	liftMotor.set(speed);
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
