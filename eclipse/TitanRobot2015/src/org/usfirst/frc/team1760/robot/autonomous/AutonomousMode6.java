package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This class handles Autonomous mode 6 operations.
 * Autonomous mode 6 will pick up one tote, drive to auto zone.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class AutonomousMode6 extends AutonomousMode {
	private static final int DRIVING_BACKWARD = 0;
	private static final int TAIL_LIFT_WAIT = 1;
	private static final int DRIVING_FORWARD = 3;
	private static final int DRIVING_TURN = 4;
	private static final int DRIVING_LIFT_WAIT = 5;
	private static final int COMPLETE = 44;

	private static final long DRIVE_BACKWARD_TIME = 100;
	private static final long DRIVE_WAIT_TIME = 300;
	private static final long DRIVE_FORWARD_TIME_WITH_RAMP = 1900;
	private static final long DRIVE_FORWARD_TIME_WITHOUT_RAMP = 1900;
	private static final long DRIVE_TURNING_TIME = 1725;
	private static final long DRIVE_LIFT_WAIT_TIME = 200;

	private static final double DRIVE_BACKWARD_SPEED = -0.0;
	private static final double DRIVE_FORWARD_SPEED = 0.70;
	private static final double LEFT_DRIVE_TURN_SPEED = 0.50;
	private static final double RIGHT_DRIVE_TURN_SPEED = -0.50;

	private TimeLimit timeLimit;
	private long driveForwardTime;

	private RobotDrive robotDrive;
	private DoubleSolenoid tailLiftSolenoid;
	private int drive_mode;
	
	public AutonomousMode6(TitanRobot pRobot) {
		super(pRobot);
		robotDrive = robot.getMotorStore().getRobotDrive(true);
	    tailLiftSolenoid = robot.getSolenoidStore().getTailLiftSolenoid();

	    if (isRampMode()) {
	    	driveForwardTime = DRIVE_FORWARD_TIME_WITH_RAMP;
	    }
	    else {
	    	driveForwardTime = DRIVE_FORWARD_TIME_WITHOUT_RAMP;
	    }

	    /* Start drive mode */
	    drive_mode = DRIVING_BACKWARD;
		timeLimit = new TimeLimit(DRIVE_BACKWARD_TIME);
    	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		double leftSpeed = 0.0;
		double rightSpeed = 0.0;

		if (drive_mode == DRIVING_BACKWARD) {
			if (timeLimit.isTimeLimitReached()) {
				drive_mode = TAIL_LIFT_WAIT;
				timeLimit.setTimeLimit(DRIVE_WAIT_TIME);
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kReverse); // synch this with the drive
			}
			else {
				leftSpeed = DRIVE_BACKWARD_SPEED;
				rightSpeed = DRIVE_BACKWARD_SPEED;
			}
		}

		if (drive_mode == TAIL_LIFT_WAIT) {
			if (timeLimit.isTimeLimitReached()) {
				drive_mode = DRIVING_FORWARD;
				timeLimit.setTimeLimit(driveForwardTime);
			}
		}

		if (drive_mode == DRIVING_FORWARD) {
			if (timeLimit.isTimeLimitReached()) {
				drive_mode = DRIVING_TURN;
				timeLimit.setTimeLimit(DRIVE_TURNING_TIME);
			}
			else {
				leftSpeed = DRIVE_FORWARD_SPEED + 0.025;
				rightSpeed = DRIVE_FORWARD_SPEED;
			}
		}

		if (drive_mode == DRIVING_TURN) {
			if (timeLimit.isTimeLimitReached()) {
				drive_mode = DRIVING_LIFT_WAIT;
				timeLimit.setTimeLimit(DRIVE_LIFT_WAIT_TIME);
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
			}
			else {
				leftSpeed = LEFT_DRIVE_TURN_SPEED;
				rightSpeed = RIGHT_DRIVE_TURN_SPEED;
			}
		}
		
		if (drive_mode == DRIVING_LIFT_WAIT) {
			if (timeLimit.isTimeLimitReached()) {
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kOff);
				drive_mode = COMPLETE;
			}
		}		

		robotDrive.tankDrive(leftSpeed, rightSpeed);
	}

}
