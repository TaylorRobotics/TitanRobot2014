package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.Switch;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * This class handles Autonomous mode 5 operations.
 * Autonomous mode 5 will pick up barrel, drive to auto zone.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class AutonomousMode5 extends AutonomousMode {

	public static final int FORWARD = 0;
	public static final int REVERSE = 1;

	public static final int DRIVING_FORWARD = 0;
	public static final int LIFTING_FORK = 1;
	public static final int DRIVING_BACKWARD = 2;
	public static final int DRIVING_TURN = 3;
	public static final int COMPLETE = 44;

	public static final long DRIVE_FORWARD_TIME = 0;
	public static final long LIFTING_FORK_TIME = 2000;
	public static final long DRIVE_BACKWARD_TIME = 1800;
	public static final long DRIVE_TURNING_TIME = 1800;

	public static final double DRIVE_FORWARD_SPEED = 0.00;

	public static final double DRIVE_BACKWARD_BEGIN_SPEED = -0.35;
	public static final double DRIVE_BACKWARD_STEP_SPEED = -0.002;
	public static final long DRIVE_BACKWARD_STEP_INTERVAL = 10; // ms
	public static final double DRIVE_BACKWARD_END_SPEED = -0.50;
	
	public static final double LEFT_DRIVE_TURN_SPEED = -0.45;
	public static final double RIGHT_DRIVE_TURN_SPEED = 0.45;

	public static final double FORK_SPEED = 0.50;

	private TimeLimit timeLimit;
	private TimeLimit backwardStepIntervalTimeLimit;
	
	private RobotDrive robotDrive;
	private SpeedController forkLiftMotor;
	private Switch forkLiftUpperLimitSwitch;

	private int driveMode;
	private double backwardSpeed = 0.0;

	public AutonomousMode5(TitanRobot pRobot) {
		super(pRobot);
		robotDrive = robot.getMotorStore().getRobotDrive(true);
		forkLiftMotor = robot.getMotorStore().getForkLiftMotor();
		forkLiftUpperLimitSwitch = robot.getSwitchStore().getForkLiftUpperLimitSwitch();
		backwardStepIntervalTimeLimit = new TimeLimit();

		/* Start drive mode */
		driveMode = DRIVING_FORWARD;
		timeLimit = new TimeLimit(DRIVE_FORWARD_TIME);
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		double leftSpeed = 0.0;
		double rightSpeed = 0.0;
		double forkSpeed = 0.0;

		if (driveMode == DRIVING_FORWARD) {
			if (timeLimit.isTimeLimitReached()) {
				driveMode = LIFTING_FORK;
				timeLimit.setTimeLimit(LIFTING_FORK_TIME);
			}
			else {
				leftSpeed = DRIVE_FORWARD_SPEED;
				rightSpeed = DRIVE_FORWARD_SPEED;
			}
		}

		if (driveMode == LIFTING_FORK) {
			if (timeLimit.isTimeLimitReached()) {
				driveMode = DRIVING_BACKWARD;
				timeLimit.setTimeLimit(DRIVE_BACKWARD_TIME);
				backwardSpeed = DRIVE_BACKWARD_BEGIN_SPEED;
				backwardStepIntervalTimeLimit.setTimeLimit(DRIVE_BACKWARD_STEP_INTERVAL);
			}
			else if (!forkLiftUpperLimitSwitch.isSwitchOn()) {
				forkSpeed = FORK_SPEED;
			}
		}

		if (driveMode == DRIVING_BACKWARD) {
			if (timeLimit.isTimeLimitReached()) {
				driveMode = DRIVING_TURN;
				timeLimit.setTimeLimit(DRIVE_TURNING_TIME);
			}
			else {
				if (backwardStepIntervalTimeLimit.isTimeLimitReached()) {
					backwardSpeed = backwardSpeed + DRIVE_BACKWARD_STEP_SPEED;
					if (backwardSpeed < DRIVE_BACKWARD_END_SPEED) {
						backwardSpeed = DRIVE_BACKWARD_END_SPEED;
					}
					else {
						backwardStepIntervalTimeLimit.setTimeLimit(DRIVE_BACKWARD_STEP_INTERVAL);
					}
				}
				leftSpeed = backwardSpeed;
				rightSpeed = backwardSpeed;
			}
		}

		if (driveMode == DRIVING_TURN) {
			if (timeLimit.isTimeLimitReached()) {
				driveMode = COMPLETE;
			}
			else {
				leftSpeed = LEFT_DRIVE_TURN_SPEED;
				rightSpeed = RIGHT_DRIVE_TURN_SPEED;
			}
		}
		
	    robotDrive.tankDrive(leftSpeed, rightSpeed);
    	forkLiftMotor.set(forkSpeed);
	}

}
