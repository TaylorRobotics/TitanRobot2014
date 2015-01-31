package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;

/**
 * This class handles Autonomous mode 0 operations.
 * Autonomous Mode 0 is a dead mode.  The robot should not perform any action.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class AutonomousMode0 extends AutonomousMode {

	public AutonomousMode0(TitanRobot pRobot) {
		super(pRobot);
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		// Nothing to do in Dead Mode
	}

}
