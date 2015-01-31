package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;

/**
 * @author Robo-Titans Team 1790 Taylor High School 2015
 */
public abstract class AutonomousMode {
	protected TitanRobot robot;

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
