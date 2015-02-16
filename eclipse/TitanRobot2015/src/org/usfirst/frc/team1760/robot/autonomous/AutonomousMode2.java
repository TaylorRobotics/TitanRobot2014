package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This class handles Autonomous mode 2 operations.
 * Autonomous mode 2 will grab a barrel from the step in parallel mode.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class AutonomousMode2 extends AutonomousMode {
	public static final int FORWARD = 0;
	public static final int REVERSE = 1;

	public static final int DRIVING_BACKWARD = 0;
	public static final int DRIVING_WAIT = 1;
	public static final int DRIVING_FORWARD = 2;
	public static final int DRIVING_LIFT_WAIT = 3;
	public static final int DROPPING_DRAGON_TAIL = 10;
	public static final int WAITING_DRAGON_TAIL = 11;
	public static final int RAISING_DRAGON_TAIL = 12;

	public static final int COMPLETE = 44;

	public static final long DRIVE_BACKWARD_TIME = 400;
	public static final long DRIVE_WAIT_TIME = 800;
	public static final long DRIVE_FORWARD_TIME = 1700;
	public static final long DRIVE_LIFT_WAIT_TIME = 200;
	public static final long DRAGON_TAIL_DROP_TIME = 700;
	public static final long DRAGON_TAIL_WAIT_TIME = 400;
	public static final long DRAGON_TAIL_RAISE_TIME = 300;
	
	public static final double DRIVE_BACKWARD_SPEED = -0.30;
	public static final double DRIVE_WAIT_SPEED = 0.00;
	public static final double DRIVE_FORWARD_SPEED = 0.60;
	public static final double DRIVE_LIFT_WAIT_SPEED = 0.00;
	
	private TimeLimit driveBackwardTimeLimit;
	private TimeLimit driveWaitTimeLimit;
	private TimeLimit driveForwardTimeLimit;
	private TimeLimit driveLiftTimeLimit;
	private TimeLimit dragonTailDropTimeLimit;
	private TimeLimit dragonTailWaitTimeLimit;
	private TimeLimit dragonTailRaiseTimeLimit;


	private DoubleSolenoid dragonTailSolenoid;
	private DoubleSolenoid tailLiftSolenoid;
	private int drive_mode;
	private int dragonTail_mode;
	private int direction;

	public AutonomousMode2(TitanRobot pRobot) {
		super(pRobot);
		direction = FORWARD;
	    dragonTailSolenoid = robot.getSolenoidStore().getDragonTailSolenoid();
	    tailLiftSolenoid = robot.getSolenoidStore().getTailLiftSolenoid();
		drive_mode = DRIVING_BACKWARD;
		dragonTail_mode = DROPPING_DRAGON_TAIL;
		driveBackwardTimeLimit = new TimeLimit(DRIVE_BACKWARD_TIME);
		driveWaitTimeLimit = new TimeLimit();
		driveForwardTimeLimit = new TimeLimit();
		driveLiftTimeLimit = new TimeLimit();
		dragonTailDropTimeLimit = new TimeLimit(DRAGON_TAIL_DROP_TIME);
		dragonTailWaitTimeLimit = new TimeLimit();
		dragonTailRaiseTimeLimit = new TimeLimit();

	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		// Insert autonomous code for mode 2 here
		RobotDrive robotDrive = robot.getMotorStore().getRobotDrive(direction == FORWARD);
		double speed = 0.0;
		if (drive_mode == DRIVING_BACKWARD) {
        	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
			if (driveBackwardTimeLimit.isTimeLimitReached()) {
				drive_mode = DRIVING_WAIT;
				driveWaitTimeLimit.setTimeLimit(DRIVE_WAIT_TIME);
			}
			else {
				speed = DRIVE_BACKWARD_SPEED;
			}
		}

		if (drive_mode == DRIVING_WAIT) {
        	tailLiftSolenoid.set(DoubleSolenoid.Value.kReverse);
			if (driveWaitTimeLimit.isTimeLimitReached()) {
				drive_mode = DRIVING_FORWARD;
				driveForwardTimeLimit.setTimeLimit(DRIVE_FORWARD_TIME);
			}
			else {
				speed = DRIVE_WAIT_SPEED;
			}
		}
		if (drive_mode == DRIVING_FORWARD) {
			if (driveForwardTimeLimit.isTimeLimitReached()) {
				drive_mode = DRIVING_LIFT_WAIT;
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
			}
			else {
				speed = DRIVE_FORWARD_SPEED;
			}
		}
		
		if (drive_mode == DRIVING_LIFT_WAIT) {
			if (driveLiftTimeLimit.isTimeLimitReached()) {
				drive_mode = COMPLETE;
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kOff);
			}
			else {
				speed = DRIVE_LIFT_WAIT_SPEED;
			}
		}
		if (dragonTail_mode == DROPPING_DRAGON_TAIL) {
        	dragonTailSolenoid.set(DoubleSolenoid.Value.kReverse);
			if (dragonTailDropTimeLimit.isTimeLimitReached()) {
				dragonTail_mode = WAITING_DRAGON_TAIL;
				dragonTailWaitTimeLimit.setTimeLimit(DRAGON_TAIL_WAIT_TIME);
			}
		}
		if (dragonTail_mode == WAITING_DRAGON_TAIL) {
        	dragonTailSolenoid.set(DoubleSolenoid.Value.kReverse);
			if (dragonTailWaitTimeLimit.isTimeLimitReached()) {
				dragonTail_mode = RAISING_DRAGON_TAIL;
				dragonTailRaiseTimeLimit.setTimeLimit(DRAGON_TAIL_RAISE_TIME);
			}
		}

		if (dragonTail_mode == RAISING_DRAGON_TAIL) {
        	dragonTailSolenoid.set(DoubleSolenoid.Value.kForward);
			if (dragonTailRaiseTimeLimit.isTimeLimitReached()) {
				dragonTail_mode = COMPLETE;
	        	dragonTailSolenoid.set(DoubleSolenoid.Value.kOff);
			}

		}
		robotDrive.drive(speed, 0.0);
	}

}
