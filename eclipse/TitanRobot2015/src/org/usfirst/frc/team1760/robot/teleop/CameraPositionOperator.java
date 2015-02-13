package org.usfirst.frc.team1760.robot.teleop;

import org.usfirst.frc.team1760.robot.TitanRobot;

import edu.wpi.first.wpilibj.Servo;

/**
 * This class handles the operation of the tote lift.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class CameraPositionOperator {
	public static final long SOLENOID_ON_LAG = 3000;  // 3 turn off solenoid

	private TitanRobot robot;
	Servo horizontalCameraServo;
	Servo verticalCameraServo;

    public CameraPositionOperator(TitanRobot pRobot) {
		robot = pRobot;
		horizontalCameraServo = robot.getServoStore().getHorizontalCameraServo();
		verticalCameraServo = robot.getServoStore().getVerticalCameraServo();
	}


	public void periodic() {
		// Add logic to read buttons and set angles
		horizontalCameraServo.setAngle(90.0);
		verticalCameraServo.setAngle(90.0);
	}

}
