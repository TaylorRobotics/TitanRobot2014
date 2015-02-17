package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;

/**
 * This defines the basic requirements for an AutonomousMode class.  THis class must be
 * extended by a a specific AutonomousMode class that provides periodic behavior.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public abstract class AutonomousMode {
	protected TitanRobot robot;
	private boolean rampMode;

	public AutonomousMode(TitanRobot pRobot) {
		robot = pRobot;
		rampMode = robot.getSwitchStore().getAutonomousRampModeSwitch().isSwitchOn();
		System.out.println("Ramp Mode: " + rampMode);
	}

	public boolean isRampMode() {
		return rampMode;
	}

	/**
     * This method is called periodically during autonomous and provides the
     * appropriate autonomous behavior based upon the autonomous mode selected by the
     * autonomous switches.
     */
    public abstract void periodic();

}
