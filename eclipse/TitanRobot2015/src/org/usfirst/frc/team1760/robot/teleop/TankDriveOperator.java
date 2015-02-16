package org.usfirst.frc.team1760.robot.teleop;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.JoystickButton;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This class handles the TankDrive operation.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class TankDriveOperator {
	public static final int FORWARD = 0;
	public static final int REVERSE = 1;
	public static final double SLOW_SPEED_FACTOR = 0.5;
	public static final double DEFAULT_SPEED_FACTOR = 0.75;
	public static final double FAST_SPEED_FACTOR = 1.0;

	private TitanRobot robot;
	private double speedFactor;
	private int direction;
	private Joystick leftDriveJoystick;
	private Joystick rightDriveJoystick;
	private JoystickButton driveSlowButton;
	private JoystickButton driveFastButton;
	private JoystickButton driveForwardButton;
	private JoystickButton driveBackwardButton;

	public TankDriveOperator(TitanRobot pRobot) {
		robot = pRobot;
		direction = REVERSE;
		speedFactor = DEFAULT_SPEED_FACTOR;
		leftDriveJoystick = robot.getJoystickStore().getLeftDriveJoystick();
		rightDriveJoystick = robot.getJoystickStore().getRightDriveJoystick();
		driveSlowButton = robot.getJoystickStore().getDriveSlowButton();
		driveFastButton = robot.getJoystickStore().getDriveFastButton();
		driveForwardButton = robot.getJoystickStore().getDriveForwardButton();
		driveBackwardButton = robot.getJoystickStore().getDriveBackwardButton();
	}

	public void periodic() {
		if (driveForwardButton.isSwitchOn()) {
			direction = FORWARD;
		}
		else if (driveBackwardButton.isSwitchOn()) {
			direction = REVERSE;
		}
		RobotDrive robotDrive = robot.getMotorStore().getRobotDrive(direction == FORWARD);

		if (driveSlowButton.isSwitchOn()) {
			speedFactor = SLOW_SPEED_FACTOR;
		}
		else if (driveFastButton.isSwitchOn()) {
			speedFactor = FAST_SPEED_FACTOR;
		}
		else {
			speedFactor = DEFAULT_SPEED_FACTOR;
		}
		robotDrive.setMaxOutput(speedFactor);

		if (direction == FORWARD) {
			robotDrive.tankDrive(rightDriveJoystick, leftDriveJoystick);
		}
		else {
			robotDrive.tankDrive(leftDriveJoystick, rightDriveJoystick);
		}
	}
}
