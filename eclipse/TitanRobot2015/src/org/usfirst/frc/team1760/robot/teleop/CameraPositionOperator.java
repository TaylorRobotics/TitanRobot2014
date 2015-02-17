package org.usfirst.frc.team1760.robot.teleop;

import org.usfirst.frc.team1760.robot.TitanRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;

/**
 * This class handles the operation of the tote lift.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class CameraPositionOperator {
	private TitanRobot robot;
	private Servo horizontalCameraServo;
	private Servo verticalCameraServo;
	private Joystick operatorJoystick;

    public CameraPositionOperator(TitanRobot pRobot) {
		robot = pRobot;
		horizontalCameraServo = robot.getServoStore().getHorizontalCameraServo();
		verticalCameraServo = robot.getServoStore().getVerticalCameraServo();
		operatorJoystick = robot.getJoystickStore().getOperatorJoystick();

		/* Set default position */
		horizontalCameraServo.setAngle(100.0);
		verticalCameraServo.setAngle(20.0);
	}

	public void periodic() {
		if (operatorJoystick.getRawButton(6)) {
			horizontalCameraServo.setAngle(100.0);
			verticalCameraServo.setAngle(20.0);
		}
		else if (operatorJoystick.getRawButton(7)) {
			horizontalCameraServo.setAngle(100.0);
			verticalCameraServo.setAngle(60.0);
		}
		else if (operatorJoystick.getRawButton(10)) {
			horizontalCameraServo.setAngle(100.0);
			verticalCameraServo.setAngle(160.0);
		}
		else if (operatorJoystick.getRawButton(11)) {
			horizontalCameraServo.setAngle(100.0);
			verticalCameraServo.setAngle(80.0);
		}
	}

}
