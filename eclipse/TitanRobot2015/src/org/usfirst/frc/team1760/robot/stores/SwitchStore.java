package org.usfirst.frc.team1760.robot.stores;

import org.usfirst.frc.team1760.robot.components.DigitalInputSwitch;
import org.usfirst.frc.team1760.robot.components.Switch;

/**
 * This class holds switch components defined for robot operation.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class SwitchStore {
	public static final int FORK_LIFT_UPPER_LIMIT_SWITCH_CHANNEL = 0;
	public static final int FORK_LIFT_LOWER_LIMIT_SWITCH_CHANNEL = 1;
	public static final int FORK_LIFT_MIDDLE_LIMIT_SWITCH_CHANNEL = 2;
	public static final int AUTONOMOUS_SWITCH_1_CHANNEL = 3;
	public static final int AUTONOMOUS_SWITCH_2_CHANNEL = 4;
	public static final int AUTONOMOUS_SWITCH_3_CHANNEL = 5;
	public static final int AUTONOMOUS_RAMP_MODE_SWITCH_CHANNEL = 6;

	private DigitalInputSwitch forkLiftUpperLimitSwitch = null;
	private DigitalInputSwitch forkLiftLowerLimitSwitch = null;
	private DigitalInputSwitch forkLiftMiddleLimitSwitch = null;
	private DigitalInputSwitch autonomousSwitch1 = null;
	private DigitalInputSwitch autonomousSwitch2 = null;
	private DigitalInputSwitch autonomousSwitch3 = null;
	private DigitalInputSwitch autonomousRampModeSwitch = null;

	/**
	 * Gets the fork lift upper limit switch.
	 * @return The DigitalInputSwitch serving as the fork lift upper limit switch
	 */
	public synchronized DigitalInputSwitch getForkLiftUpperLimitSwitch() {
		if (forkLiftUpperLimitSwitch == null) {
			forkLiftUpperLimitSwitch = new DigitalInputSwitch(FORK_LIFT_UPPER_LIMIT_SWITCH_CHANNEL, Switch.NORMALLY_CLOSED);
		}
		return forkLiftUpperLimitSwitch;
	}

	/**
	 * Gets the fork lift lower limit switch.
	 * @return The DigitalInputSwitch serving as the fork lift lower limit switch
	 */
	public synchronized DigitalInputSwitch getForkLiftLowerLimitSwitch() {
		if (forkLiftLowerLimitSwitch == null) {
			forkLiftLowerLimitSwitch = new DigitalInputSwitch(FORK_LIFT_LOWER_LIMIT_SWITCH_CHANNEL, Switch.NORMALLY_CLOSED);
		}
		return forkLiftLowerLimitSwitch;
	}

	/**
	 * Gets the fork lift middle limit switch.
	 * @return The DigitalInputSwitch serving as the fork lift middle limit switch
	 */
	public synchronized DigitalInputSwitch getForkLiftMiddleLimitSwitch() {
		if (forkLiftMiddleLimitSwitch == null) {
			forkLiftMiddleLimitSwitch = new DigitalInputSwitch(FORK_LIFT_MIDDLE_LIMIT_SWITCH_CHANNEL, Switch.NORMALLY_CLOSED);
		}
		return forkLiftMiddleLimitSwitch;
	}


	/**
	 * Gets the right-most autonomous mode switch.
	 * @return The DigitalInputSwitch serving as the right-most autonomous mode switch
	 */
	public synchronized DigitalInputSwitch getAutonomousSwitch1() {
		if (autonomousSwitch1 == null) {
			autonomousSwitch1 = new DigitalInputSwitch(AUTONOMOUS_SWITCH_1_CHANNEL, Switch.NORMALLY_OPEN);
		}
		return autonomousSwitch1;
	}

	/**
	 * Gets the middle autonomous mode switch.
	 * @return The DigitalInputSwitch serving as the middle autonomous mode switch
	 */
	public synchronized DigitalInputSwitch getAutonomousSwitch2() {
		if (autonomousSwitch2 == null) {
			autonomousSwitch2 = new DigitalInputSwitch(AUTONOMOUS_SWITCH_2_CHANNEL, Switch.NORMALLY_OPEN);
		}
		return autonomousSwitch2;
	}

	/**
	 * Gets the left-most autonomous mode switch.
	 * @return The DigitalInputSwitch serving as the left-most autonomous mode switch
	 */
	public synchronized DigitalInputSwitch getAutonomousSwitch3() {
		if (autonomousSwitch3 == null) {
			autonomousSwitch3 = new DigitalInputSwitch(AUTONOMOUS_SWITCH_3_CHANNEL, Switch.NORMALLY_OPEN);
		}
		return autonomousSwitch3;
	}

	/**
	 * Gets the ramp mode autonomous switch.
	 * @return The DigitalInputSwitch serving as the ramp mode autonomous switch
	 */
	public synchronized DigitalInputSwitch getAutonomousRampModeSwitch() {
		if (autonomousRampModeSwitch == null) {
			autonomousRampModeSwitch = new DigitalInputSwitch(AUTONOMOUS_RAMP_MODE_SWITCH_CHANNEL, Switch.NORMALLY_OPEN);
		}
		return autonomousRampModeSwitch;
	}

}
