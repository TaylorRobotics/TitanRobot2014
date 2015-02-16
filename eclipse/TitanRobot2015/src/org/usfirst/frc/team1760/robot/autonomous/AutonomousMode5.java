package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.Switch;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
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
	public static final int DRIVING_WAIT = 1;
	public static final int DRIVING_BACKWARD = 3;
	public static final int DRIVING_ARC = 4;
	public static final int COMPLETE = 44;

	public static final long DRIVE_FORWARD_TIME = 100;
	public static final long DRIVE_WAIT_TIME = 2000;
	public static final long DRIVE_BACKWARD_TIME = 1800;
	public static final long DRIVE_ARC_TIME = 1800;

	public static final double DRIVE_FORWARD_SPEED = 0.00;
	public static final double DRIVE_WAIT_SPEED = 0.00;
	public static final double DRIVE_BACKWARD_SPEED = -0.45;
	public static final double DRIVE_ARC_SPEED = -0.75;
	public static final double FORK_SPEED = 0.50;

	private TimeLimit driveBackwardTimeLimit;
	private TimeLimit driveWaitTimeLimit;
	private TimeLimit driveForwardTimeLimit;
	private TimeLimit driveArcTimeLimit;
	
	private SpeedController forkLiftMotor;
	private Switch forkLiftUpperLimitSwitch;
	private Switch forkLiftLowerLimitSwitch;

	private int drive_mode;
	private int direction;
	
	public AutonomousMode5(TitanRobot pRobot) {
		super(pRobot);
		direction = FORWARD;
		drive_mode = DRIVING_FORWARD;
		driveBackwardTimeLimit = new TimeLimit(DRIVE_FORWARD_TIME);
		driveWaitTimeLimit = new TimeLimit();
		driveForwardTimeLimit = new TimeLimit();
		driveArcTimeLimit = new TimeLimit();
		forkLiftMotor = robot.getMotorStore().getForkLiftMotor();
		forkLiftUpperLimitSwitch = robot.getSwitchStore().getForkLiftUpperLimitSwitch();
		forkLiftLowerLimitSwitch = robot.getSwitchStore().getForkLiftLowerLimitSwitch();
		
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		// Insert autonomous code for mode 5 here
		
		RobotDrive robotDrive = robot.getMotorStore().getRobotDrive(direction == FORWARD);
		double speed = 0.0;
		double forkSpeed = 0.0;
		if (drive_mode == DRIVING_FORWARD) {
			if (driveForwardTimeLimit.isTimeLimitReached()) {
				drive_mode = DRIVING_WAIT;
				driveWaitTimeLimit.setTimeLimit(DRIVE_WAIT_TIME);
			}
			else {
				speed = DRIVE_FORWARD_SPEED;
			}
		}

		if (drive_mode == DRIVING_WAIT) {
			if (driveWaitTimeLimit.isTimeLimitReached()) {
				drive_mode = DRIVING_BACKWARD;
				driveBackwardTimeLimit.setTimeLimit(DRIVE_BACKWARD_TIME);
			}
			else {
				speed = DRIVE_WAIT_SPEED;
				forkSpeed = FORK_SPEED;
				if ((forkSpeed > 0.0) && forkLiftUpperLimitSwitch.isSwitchOn()) {
	        		forkSpeed = 0.0;
	            }
			}
		}

		if (drive_mode == DRIVING_BACKWARD) {
			if (driveBackwardTimeLimit.isTimeLimitReached()) {
				drive_mode = DRIVING_ARC;
				driveArcTimeLimit.setTimeLimit(DRIVE_ARC_TIME);
			}
			else {
				speed = DRIVE_BACKWARD_SPEED;
			}
		}

		if (drive_mode == DRIVING_ARC) {
			if (driveArcTimeLimit.isTimeLimitReached()) {
				drive_mode = COMPLETE;
			}
			else {
				speed = DRIVE_ARC_SPEED;
			}
		}
		
		if (drive_mode == DRIVING_ARC) {
			robotDrive.tankDrive(0, speed);
		} 
		else {
		    robotDrive.drive(speed, 0.0);
		}

    	forkLiftMotor.set(forkSpeed);
	}

}
