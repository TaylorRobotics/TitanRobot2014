package org.usfirst.frc.team1760.robot.stores;

import edu.wpi.first.wpilibj.Servo;

/**
 * This class holds motor base components defined for robot operation.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class ServoStore {
	public static final int HORIZONTAL_CAMERA_SERVO_CHANNEL = 9;
	public static final int VERTICAL_CAMERA_SERVO_CHANNEL = 10;

	private Servo verticalCameraServo = null;
	private Servo horizontalCameraServo = null;

	public synchronized Servo getVerticalCameraServo() {
		if (verticalCameraServo == null) {
			verticalCameraServo = new Servo(VERTICAL_CAMERA_SERVO_CHANNEL);
		}
		return verticalCameraServo;
	}

	public synchronized Servo getHorizontalCameraServo() {
		if (horizontalCameraServo == null) {
			horizontalCameraServo = new Servo(HORIZONTAL_CAMERA_SERVO_CHANNEL);
		}
		return horizontalCameraServo;
	}
}
