package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This class handles Autonomous mode 7 operations.
 * Autonomous Mode 7 will drive forward 6 feet into auto zone.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class AutonomousMode7 extends AutonomousMode {
	private static final int DRIVING = 0;
	private static final int COMPLETE = 44;

	private static final long DRIVE_FORWARD_TIME_WITH_RAMP = 1100;
	private static final long DRIVE_FORWARD_TIME_WITHOUT_RAMP = 1100;
	private static final double DRIVE_SPEED = 0.70;
	private RobotDrive robotDrive;
	private DoubleSolenoid tailLiftSolenoid;
	private TimeLimit driveTime;

	private int mode;
	private long driveForwardTime;

	public AutonomousMode7(TitanRobot pRobot) {
		super(pRobot);
		robotDrive = robot.getMotorStore().getRobotDrive(true);

	    if (isRampMode()) {
	    	driveForwardTime = DRIVE_FORWARD_TIME_WITH_RAMP;
	    }
	    else {
	    	driveForwardTime = DRIVE_FORWARD_TIME_WITHOUT_RAMP;
	    }

		/* Start driving mode */
		mode = DRIVING;
		driveTime = new TimeLimit(driveForwardTime);
    	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		double leftSpeed = 0.0;
		double rightSpeed = 0.0;

		if (mode == DRIVING) {
			if (driveTime.isTimeLimitReached()) {
		    	tailLiftSolenoid.set(DoubleSolenoid.Value.kOff);
				mode = COMPLETE;
			}
			else {
				leftSpeed = DRIVE_SPEED + 0.025;
				rightSpeed = DRIVE_SPEED;
			}
		}
		robotDrive.tankDrive(leftSpeed, rightSpeed);
	}

}
