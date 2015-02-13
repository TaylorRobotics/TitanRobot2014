package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.Switch;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * This class handles Autonomous mode 1 operations.
 * Autonomous 1 mode will drop 1 barrel hook, and drive forward to auto zone.
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
	public static final int RAISING_FORK_LIFT = 4;
	public static final int COMPLETE = 5;

	public static final long DRIVE_BACKWARD_TIME = 500;
	public static final long DRAGON_TAIL_DROP_TIME = 3000;
	public static final long DRIVE_FORWARD_TIME = 1300;
	public static final long DRAGON_TAIL_RAISE_TIME = 5000;
	public static final long FORK_LIFT_RAISE_TIME = 500;

	public static final double DRIVE_BACKWARD_SPEED = -0.50;
	public static final double DRIVE_FORWARD_SPEED = 1.00;
	public static final double FORK_LIFT_SPEED = 0.5;

	private DoubleSolenoid dragonTailSolenoid;
	private DoubleSolenoid tailLiftSolenoid;
	private SpeedController forkLiftMotor;
	private Switch forkLiftUpperLimitSwitch;
	RobotDrive robotDrive;

	private int mode;
	private TimeLimit modeTimeLimit;
	private int direction;

	public AutonomousMode1(TitanRobot pRobot) {
		super(pRobot);
		direction = FORWARD;
	    dragonTailSolenoid = robot.getSolenoidStore().getDragonTailSolenoid();
	    tailLiftSolenoid = robot.getSolenoidStore().getTailLiftSolenoid();
		mode = DRIVING_BACKWARD;
		modeTimeLimit = new TimeLimit(DRIVE_BACKWARD_TIME);
    	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
    	forkLiftMotor = robot.getMotorStore().getForkLiftMotor();
		forkLiftUpperLimitSwitch = robot.getSwitchStore().getForkLiftUpperLimitSwitch();
		robotDrive = robot.getMotorStore().getRobotDrive(direction == FORWARD);
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		double driveSpeed = 0.0;
		double forkSpeed = 0.0;
		if (mode == DRIVING_BACKWARD) {
			if (modeTimeLimit.isTimeLimitReached()) {
				mode = DROPPING_DRAGON_TAIL;
				modeTimeLimit.setTimeLimit(DRAGON_TAIL_DROP_TIME);
			}
			else {
				driveSpeed = DRIVE_BACKWARD_SPEED;
			}
		}
		if (mode == DROPPING_DRAGON_TAIL) {
        	dragonTailSolenoid.set(DoubleSolenoid.Value.kReverse);
        	tailLiftSolenoid.set(DoubleSolenoid.Value.kReverse);
			if (modeTimeLimit.isTimeLimitReached()) {
				mode = DRIVING_FORWARD;
				modeTimeLimit.setTimeLimit(DRIVE_FORWARD_TIME);
			}
		}
		if (mode == DRIVING_FORWARD) {
			if (modeTimeLimit.isTimeLimitReached()) {
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
				mode = RAISING_DRAGON_TAIL;
				modeTimeLimit.setTimeLimit(DRAGON_TAIL_RAISE_TIME);
			}
			else {
				driveSpeed = DRIVE_FORWARD_SPEED;
			}
		}
		if (mode == RAISING_DRAGON_TAIL) {
			if (modeTimeLimit.isTimeLimitReached()) {
	        	tailLiftSolenoid.set(DoubleSolenoid.Value.kOff);
	        	dragonTailSolenoid.set(DoubleSolenoid.Value.kOff);
				mode = RAISING_FORK_LIFT;
	        	modeTimeLimit.setTimeLimit(FORK_LIFT_RAISE_TIME);
			}
			else {
	        	dragonTailSolenoid.set(DoubleSolenoid.Value.kForward);
			}
		}
		if (mode == RAISING_FORK_LIFT) {
			if (modeTimeLimit.isTimeLimitReached()) {
				mode = COMPLETE;
			}
			else if (!forkLiftUpperLimitSwitch.isSwitchOn()){
				forkSpeed = FORK_LIFT_SPEED;
			}
		}
		robotDrive.drive(driveSpeed, 0.0);
		forkLiftMotor.set(forkSpeed);
	}

}
