package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This class handles Autonomous mode 4 operations.
 * Autonomous mode 4 will push a tote into the autozone.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class AutonomousMode4 extends AutonomousMode {
	private static final int DRIVING_BACKWARD = 0;
	private static final int DRIVING_WAIT = 1;
	private static final int DRIVING_FORWARD = 3;
	private static final int DRIVING_LIFT_WAIT = 4;
	private static final int COMPLETE = 44;

	private static final long DRIVE_BACKWARD_TIME = 100;
	private static final long DRIVE_WAIT_TIME = 100;
	private static final long DRIVE_FORWARD_TIME_WITH_RAMP = 2700;
	private static final long DRIVE_FORWARD_TIME_WITHOUT_RAMP = 2700;
	private static final long DRIVE_LIFT_WAIT_TIME = 200;

	private static final double DRIVE_BACKWARD_SPEED = -0.00;
	private static final double DRIVE_FORWARD_SPEED = 0.70;

	private TimeLimit driveTimeLimit;
	private long driveForwardTime;

	private RobotDrive robotDrive;
	private DoubleSolenoid tailLiftSolenoid;
	private int driveMode;
	
	public AutonomousMode4(TitanRobot pRobot) {
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
	    driveMode = DRIVING_BACKWARD;
		driveTimeLimit = new TimeLimit(DRIVE_BACKWARD_TIME);
    	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		double leftSpeed = 0.0;
		double rightSpeed = 0.0;

		if (driveMode == DRIVING_BACKWARD) {
			if (driveTimeLimit.isTimeLimitReached()) {
				driveMode = DRIVING_WAIT;
				driveTimeLimit.setTimeLimit(DRIVE_WAIT_TIME);
			}
			else {
				leftSpeed = DRIVE_BACKWARD_SPEED;
				rightSpeed = DRIVE_BACKWARD_SPEED;
			}
		}

		if (driveMode == DRIVING_WAIT) {
			if (driveTimeLimit.isTimeLimitReached()) {
				driveMode = DRIVING_FORWARD;
				driveTimeLimit.setTimeLimit(driveForwardTime);
			}
		}

		if (driveMode == DRIVING_FORWARD) {
			if (driveTimeLimit.isTimeLimitReached()) {
				driveMode = DRIVING_LIFT_WAIT;
				driveTimeLimit.setTimeLimit(DRIVE_LIFT_WAIT_TIME);
			}
			else {
				leftSpeed = DRIVE_FORWARD_SPEED + 0.025;
				rightSpeed = DRIVE_FORWARD_SPEED;
			}
		}

		if (driveMode == DRIVING_LIFT_WAIT) {
			if (driveTimeLimit.isTimeLimitReached()) {
				driveMode = COMPLETE;
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kOff);
			}
		}		
		
	    robotDrive.tankDrive(leftSpeed, rightSpeed);
	}

}
