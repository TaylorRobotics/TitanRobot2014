package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;

/**
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public abstract class AutonomousMode {
	protected TitanRobot robot;

	public AutonomousMode(TitanRobot pRobot) {
		robot = pRobot;
	}

    /**
     * This function is called periodically during autonomous
     */
    public abstract void periodic();

}
