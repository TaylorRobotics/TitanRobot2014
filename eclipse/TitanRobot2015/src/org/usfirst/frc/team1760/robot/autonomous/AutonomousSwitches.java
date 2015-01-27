package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;

public class AutonomousSwitches {
	private TitanRobot robot;
	private int mode;

	public AutonomousSwitches(TitanRobot pRobot) {
		robot = pRobot;
		// Read switches and set mode
		mode = 0;
	}

	public int getMode() {
		return mode;
	}
}
