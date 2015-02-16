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

	public static final int FORWARD = 0;
	public static final int REVERSE = 1;

	public static final int DRIVING_BACKWARD = 0;
	public static final int DRIVING_WAIT = 1;
	public static final int DRIVING_FORWARD = 3;
	public static final int DRIVING_ARC = 4;
	public static final int DRIVING_LIFT_WAIT = 5;
	public static final int COMPLETE = 44;

	public static final long DRIVE_BACKWARD_TIME = 100;
	public static final long DRIVE_WAIT_TIME = 300;
	public static final long DRIVE_FORWARD_TIME = 4000;
	public static final long DRIVE_ARC_TIME = 2300;
	public static final long DRIVE_LIFT_WAIT_TIME = 200;

	public static final double DRIVE_BACKWARD_SPEED = -0.30;
	public static final double DRIVE_WAIT_SPEED = 0.00;
	public static final double DRIVE_FORWARD_SPEED = 0.30;
	public static final double DRIVE_ARC_SPEED = 0.60;
	public static final double DRIVE_LIFT_WAIT_SPEED = 0.00;

	private TimeLimit driveBackwardTimeLimit;
	private TimeLimit driveWaitTimeLimit;
	private TimeLimit driveForwardTimeLimit;
	private TimeLimit driveArcTimeLimit;
	private TimeLimit driveLiftTimeLimit;
	
	private DoubleSolenoid tailLiftSolenoid;
	private int drive_mode;
	private int direction;
	
	public AutonomousMode6(TitanRobot pRobot) {
		super(pRobot);
		direction = FORWARD;
	    tailLiftSolenoid = robot.getSolenoidStore().getTailLiftSolenoid();
		drive_mode = DRIVING_BACKWARD;
		driveBackwardTimeLimit = new TimeLimit(DRIVE_BACKWARD_TIME);
		driveWaitTimeLimit = new TimeLimit();
		driveForwardTimeLimit = new TimeLimit();
		driveArcTimeLimit = new TimeLimit();
		driveLiftTimeLimit = new TimeLimit();
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		// Insert autonomous code for mode 6 here
		
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
        	tailLiftSolenoid.set(DoubleSolenoid.Value.kReverse); // synch this with the drive
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
				drive_mode = DRIVING_ARC;
				driveArcTimeLimit.setTimeLimit(DRIVE_ARC_TIME);
			}
			else {
				speed = DRIVE_FORWARD_SPEED;
			}
		}

		if (drive_mode == DRIVING_ARC) {
			if (driveArcTimeLimit.isTimeLimitReached()) {
				drive_mode = DRIVING_LIFT_WAIT;
				driveLiftTimeLimit.setTimeLimit(DRIVE_LIFT_WAIT_TIME);
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
			}
			else {
				speed = DRIVE_ARC_SPEED;
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
		
		if (drive_mode == DRIVING_ARC) {
			robotDrive.tankDrive(0, speed);
		} 
		else {
		    robotDrive.drive(speed, 0.0);
		}
	}

}
