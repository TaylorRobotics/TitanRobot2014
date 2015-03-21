package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This class handles Autonomous mode 3 operations.
 * Autonomous mode 1 mode without grabbing tote with tail lift.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class AutonomousMode3 extends AutonomousMode {
	private static final int START_WAIT = 0;
	private static final int DRIVING_BACKWARD = 1;
	private static final int DRIVING_WAIT = 2;
	private static final int DRIVING_FORWARD = 3;
	private static final int DRIVING_LIFT_WAIT = 4;
	private static final int DROPPING_DRAGON_TAIL = 10;
	private static final int WAITING_DRAGON_TAIL = 11;
	private static final int RAISING_DRAGON_TAIL = 12;

	private static final int COMPLETE = 44;

	private static final long START_WAIT_TIME = 2000;
	private static final long DRIVE_BACKWARD_TIME = 400;
	private static final long DRIVE_WAIT_TIME = 800;
	private static final long DRIVE_FORWARD_TIME_WITH_RAMP = 1700;
	private static final long DRIVE_FORWARD_TIME_WITHOUT_RAMP = 1700;
	private static final long DRIVE_LIFT_WAIT_TIME = 200;
	private static final long DRAGON_TAIL_DROP_TIME = 700;
	private static final long DRAGON_TAIL_WAIT_TIME = 800;
	private static final long DRAGON_TAIL_RAISE_TIME = 300;
	
	private static final double DRIVE_BACKWARD_SPEED = -0.45;
	private static final double DRIVE_FORWARD_SPEED = 0.70;

	private TimeLimit startWaitTimeLimit;
	private TimeLimit driveTimeLimit;
	private TimeLimit dragonTailTimeLimit;
	private long driveForwardTime;

	private RobotDrive robotDrive;
	private DoubleSolenoid dragonTailSolenoid;
	private DoubleSolenoid tailLiftSolenoid;
	private int driveMode;
	private int dragonTailMode;

	public AutonomousMode3(TitanRobot pRobot) {
		super(pRobot);
		robotDrive = robot.getMotorStore().getRobotDrive(true);
	    dragonTailSolenoid = robot.getSolenoidStore().getDragonTailSolenoid();
	    tailLiftSolenoid = robot.getSolenoidStore().getTailLiftSolenoid();

	    if (isRampMode()) {
	    	driveForwardTime = DRIVE_FORWARD_TIME_WITH_RAMP;
	    }
	    else {
	    	driveForwardTime = DRIVE_FORWARD_TIME_WITHOUT_RAMP;
	    }

	    /* Starting (wait) mode */
	    driveMode = START_WAIT;
	    dragonTailMode = START_WAIT;
		startWaitTimeLimit = new TimeLimit(START_WAIT_TIME);
    	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		double speed = 0.0;

		if (driveMode == START_WAIT) {
			if (startWaitTimeLimit.isTimeLimitReached()) {
			    /* Starting drive mode */
			    driveMode = DRIVING_BACKWARD;
				driveTimeLimit = new TimeLimit(DRIVE_BACKWARD_TIME);

		    	/* Starting Dragon Tail mode */
		    	dragonTailMode = DROPPING_DRAGON_TAIL;
				dragonTailTimeLimit = new TimeLimit(DRAGON_TAIL_DROP_TIME);
		    	dragonTailSolenoid.set(DoubleSolenoid.Value.kReverse);
			}
		}

		if (driveMode == DRIVING_BACKWARD) {
			if (driveTimeLimit.isTimeLimitReached()) {
				driveMode = DRIVING_WAIT;
				driveTimeLimit.setTimeLimit(DRIVE_WAIT_TIME);
			}
			else {
				speed = DRIVE_BACKWARD_SPEED;
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
				speed = DRIVE_FORWARD_SPEED;
			}
		}
		if (driveMode == DRIVING_LIFT_WAIT) {
			if (driveTimeLimit.isTimeLimitReached()) {
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kOff);
				driveMode = COMPLETE;
			}
		}

		if (dragonTailMode == DROPPING_DRAGON_TAIL) {
			if (dragonTailTimeLimit.isTimeLimitReached()) {
				dragonTailMode = WAITING_DRAGON_TAIL;
				dragonTailTimeLimit.setTimeLimit(DRAGON_TAIL_WAIT_TIME);
			}
		}
		if (dragonTailMode == WAITING_DRAGON_TAIL) {
			if (dragonTailTimeLimit.isTimeLimitReached()) {
				dragonTailMode = RAISING_DRAGON_TAIL;
				dragonTailTimeLimit.setTimeLimit(DRAGON_TAIL_RAISE_TIME);
	        	dragonTailSolenoid.set(DoubleSolenoid.Value.kForward);
			}
		}
		if (dragonTailMode == RAISING_DRAGON_TAIL) {
			if (dragonTailTimeLimit.isTimeLimitReached()) {
				dragonTailMode = COMPLETE;
	        	dragonTailSolenoid.set(DoubleSolenoid.Value.kOff);
			}
		}

		robotDrive.tankDrive(speed, speed);
	}

}
