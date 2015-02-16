package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This class handles Autonomous mode 7 operations.
 * Autonomous Mode 7 will drive forward 6 feet into auto zone.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class AutonomousMode7 extends AutonomousMode {
	private static final long DRIVE_TIME = 1700;
	private static final double DRIVE_SPEED = 0.30;
	private RobotDrive robotDrive;
	private TimeLimit driveTime;

	public AutonomousMode7(TitanRobot pRobot) {
		super(pRobot);
		robotDrive = robot.getMotorStore().getRobotDrive(true);
		driveTime = new TimeLimit(DRIVE_TIME);
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		if (driveTime.isTimeLimitReached()) {
			robotDrive.tankDrive(0.0, 0.0);
		}
		else {
			robotDrive.tankDrive(DRIVE_SPEED, DRIVE_SPEED);
		}
	}

}
