package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;

public abstract class AutonomousMode {
	private TitanRobot robot;

	public AutonomousMode(TitanRobot pRobot) {
		robot = pRobot;
	}

	/**
     * This function is run once each time the robot enters autonomous mode
     */
    public abstract void autonomousInit();

    /**
     * This function is called periodically during autonomous
     */
    public abstract void autonomousPeriodic();

}
