package org.usfirst.frc.team1760.robot.components;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;

public class MotorStore {
	public static final int FRONT_LEFT_DRIVE_MOTOR_CHANNEL = 0;
	public static final int REAR_LEFT_DRIVE_MOTOR_CHANNEL = 1;
	public static final int FRONT_RIGHT_DRIVE_MOTOR_CHANNEL = 2;
	public static final int REAR_RIGHT_DRIVE_MOTOR_CHANNEL = 3;
	public static final int FORK_LIFT_MOTOR_CHANNEL = 4;

	private RobotDrive robotDrive = null;
	private Victor forkLiftMotor = null;

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

	public synchronized Victor getForkLiftMotor() {
		if (forkLiftMotor == null) {
			forkLiftMotor = new Victor(FORK_LIFT_MOTOR_CHANNEL);
		}
		return forkLiftMotor;
	}

	
}
