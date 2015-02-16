package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This class handles Autonomous mode 1 operations.
 * Autonomous 1 mode will grab a barrel from the step in serial mode.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class AutonomousMode1 extends AutonomousMode {
	public static final int FORWARD = 0;
	public static final int REVERSE = 1;

	public static final int DRIVING_BACKWARD = 0;
	public static final int DROPPING_DRAGON_TAIL = 1;
	public static final int RAISING_DRAGON_TAIL = 2;
	public static final int DRIVING_FORWARD = 3;
	public static final int COMPLETE = 4;

	public static final long DRIVE_BACKWARD_TIME = 500;
	public static final long DRAGON_TAIL_DROP_TIME = 3000;
	public static final long DRIVE_FORWARD_TIME = 1300;
	public static final long DRAGON_TAIL_RAISE_TIME = 5000;

	public static final double DRIVE_BACKWARD_SPEED = -0.50;
	public static final double DRIVE_FORWARD_SPEED = 1.00;

	private TimeLimit driveBackwardTimeLimit;
	private TimeLimit dragonTailDropTimeLimit;
	private TimeLimit dragonTailRaiseTimeLimit;
	private TimeLimit driveForwardTimeLimit;

	private DoubleSolenoid dragonTailSolenoid;
	private DoubleSolenoid tailLiftSolenoid;
	private int mode;
	private int direction;

	public AutonomousMode1(TitanRobot pRobot) {
		super(pRobot);
		direction = FORWARD;
	    dragonTailSolenoid = robot.getSolenoidStore().getDragonTailSolenoid();
	    tailLiftSolenoid = robot.getSolenoidStore().getTailLiftSolenoid();
		mode = DRIVING_BACKWARD;
		driveBackwardTimeLimit = new TimeLimit(DRIVE_BACKWARD_TIME);
		dragonTailDropTimeLimit = new TimeLimit();
		dragonTailRaiseTimeLimit = new TimeLimit();
		driveForwardTimeLimit = new TimeLimit();
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		RobotDrive robotDrive = robot.getMotorStore().getRobotDrive(direction == FORWARD);
		double speed = 0.0;
		if (mode == DRIVING_BACKWARD) {
			if (driveBackwardTimeLimit.isTimeLimitReached()) {
				mode = DROPPING_DRAGON_TAIL;
				dragonTailDropTimeLimit.setTimeLimit(DRAGON_TAIL_DROP_TIME);
			}
			else {
				speed = DRIVE_BACKWARD_SPEED;
			}
		}
		if (mode == DROPPING_DRAGON_TAIL) {
        	dragonTailSolenoid.set(DoubleSolenoid.Value.kReverse);
        	tailLiftSolenoid.set(DoubleSolenoid.Value.kReverse);
			if (dragonTailDropTimeLimit.isTimeLimitReached()) {
				mode = DRIVING_FORWARD;
				driveForwardTimeLimit.setTimeLimit(DRIVE_FORWARD_TIME);
			}
		}
		if (mode == DRIVING_FORWARD) {
			if (driveForwardTimeLimit.isTimeLimitReached()) {
				mode = RAISING_DRAGON_TAIL;
				dragonTailRaiseTimeLimit.setTimeLimit(DRAGON_TAIL_RAISE_TIME);
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
			}
			else {
				speed = DRIVE_FORWARD_SPEED;
			}
		}
		if (mode == RAISING_DRAGON_TAIL) {
			if (dragonTailRaiseTimeLimit.isTimeLimitReached()) {
				mode = COMPLETE;
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kOff);
	        	dragonTailSolenoid.set(DoubleSolenoid.Value.kOff);
			}
			else {
	        	dragonTailSolenoid.set(DoubleSolenoid.Value.kForward);
				
			}
		}
		robotDrive.drive(speed, 0.0);
	}

}
