package org.usfirst.frc.team1760.robot.stores;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

/**
 * This class holds motor base components defined for robot operation.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class MotorStore {
	public static final int FRONT_LEFT_DRIVE_MOTOR_CHANNEL = 0;
	public static final int REAR_LEFT_DRIVE_MOTOR_CHANNEL = 1;
	public static final int FRONT_RIGHT_DRIVE_MOTOR_CHANNEL = 2;
	public static final int REAR_RIGHT_DRIVE_MOTOR_CHANNEL = 3;
	public static final int FORK_LIFT_MOTOR_CHANNEL = 4;

	public static final int LEFT_DRIVE_ENCODER_A_CHANNEL = -1;
	public static final int LEFT_DRIVE_ENCODER_B_CHANNEL = -1;
	public static final int RIGHT_DRIVE_ENCODER_A_CHANNEL = -1;
	public static final int RIGHT_DRIVE_ENCODER_B_CHANNEL = -1;

	private RobotDrive robotDrive = null;
	private Victor forkLiftMotor = null;
	private Encoder leftDriveEncoder = null;
	private Encoder rightDriveEncoder = null;

	/**
	 * Gets the RobotDrive to control movement of the robot
	 * @param pForward Indicates that the robot should drive in the forward direction, otherwise drive direction is reversed
	 * @return The RobotDrive for the robot
	 */
	public synchronized RobotDrive getRobotDrive(boolean pForward) {
		if (robotDrive == null) {
			robotDrive = new RobotDrive(FRONT_LEFT_DRIVE_MOTOR_CHANNEL, REAR_LEFT_DRIVE_MOTOR_CHANNEL, FRONT_RIGHT_DRIVE_MOTOR_CHANNEL, REAR_RIGHT_DRIVE_MOTOR_CHANNEL);
		}
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, !pForward);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, !pForward);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, !pForward);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, !pForward);

        return robotDrive;
	}

	/**
	 * Gets the SpeedController for the fork lift motor.
	 * @return The SpeedController for the fork lift motor
	 */
	public synchronized Victor getForkLiftMotor() {
		if (forkLiftMotor == null) {
			forkLiftMotor = new Victor(FORK_LIFT_MOTOR_CHANNEL);
		}
		return forkLiftMotor;
	}

	/**
	 * Gets the Encoder for the left drive.
	 * @return The Encoder for the left drive
	 */
	public synchronized Encoder getLeftDriveEncoder() {
		if (leftDriveEncoder == null) {
			leftDriveEncoder = new Encoder(LEFT_DRIVE_ENCODER_A_CHANNEL, LEFT_DRIVE_ENCODER_B_CHANNEL);
		}
		return leftDriveEncoder;
	}

	/**
	 * Gets the Encoder for the right drive.
	 * @return The Encoder for the right drive
	 */
	public synchronized Encoder getRightDriveEncoder() {
		if (rightDriveEncoder == null) {
			rightDriveEncoder = new Encoder(RIGHT_DRIVE_ENCODER_A_CHANNEL, RIGHT_DRIVE_ENCODER_B_CHANNEL);
		}
		return rightDriveEncoder;
	}
}
