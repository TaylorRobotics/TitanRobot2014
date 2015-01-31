package org.usfirst.frc.team1760.robot.stores;

import org.usfirst.frc.team1760.robot.components.DigitalInputSwitch;
import org.usfirst.frc.team1760.robot.components.Switch;

/**
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class SwitchStore {
	public static final int FORK_LIFT_UPPER_LIMIT_SWITCH_CHANNEL = 0;
	public static final int FORK_LIFT_LOWER_LIMIT_SWITCH_CHANNEL = 1;
	public static final int FORK_LIFT_MIDDLE_LIMIT_SWITCH_CHANNEL = 2;
	public static final int AUTONOMOUS_SWITCH_1_CHANNEL = 3;
	public static final int AUTONOMOUS_SWITCH_2_CHANNEL = 4;
	public static final int AUTONOMOUS_SWITCH_3_CHANNEL = 5;

	private DigitalInputSwitch forkLiftUpperLimitSwitch = null;
	private DigitalInputSwitch forkLiftLowerLimitSwitch = null;
	private DigitalInputSwitch forkLiftMiddleLimitSwitch = null;
	private DigitalInputSwitch autonomousSwitch1 = null;
	private DigitalInputSwitch autonomousSwitch2 = null;
	private DigitalInputSwitch autonomousSwitch3 = null;

	public synchronized DigitalInputSwitch getForkLiftUpperLimitSwitch() {
		if (forkLiftUpperLimitSwitch == null) {
			forkLiftUpperLimitSwitch = new DigitalInputSwitch(FORK_LIFT_UPPER_LIMIT_SWITCH_CHANNEL, Switch.NORMALLY_CLOSED);
		}
		return forkLiftUpperLimitSwitch;
	}

	public synchronized DigitalInputSwitch getForkLiftLowerLimitSwitch() {
		if (forkLiftLowerLimitSwitch == null) {
			forkLiftLowerLimitSwitch = new DigitalInputSwitch(FORK_LIFT_LOWER_LIMIT_SWITCH_CHANNEL, Switch.NORMALLY_CLOSED);
		}
		return forkLiftLowerLimitSwitch;
	}

	public synchronized DigitalInputSwitch getForkLiftMiddleLimitSwitch() {
		if (forkLiftMiddleLimitSwitch == null) {
			forkLiftMiddleLimitSwitch = new DigitalInputSwitch(FORK_LIFT_MIDDLE_LIMIT_SWITCH_CHANNEL, Switch.NORMALLY_CLOSED);
		}
		return forkLiftMiddleLimitSwitch;
	}

	public synchronized DigitalInputSwitch getAutonomousSwitch1() {
		if (autonomousSwitch1 == null) {
			autonomousSwitch1 = new DigitalInputSwitch(AUTONOMOUS_SWITCH_1_CHANNEL, Switch.NORMALLY_OPEN);
		}
		return autonomousSwitch1;
	}

	public synchronized DigitalInputSwitch getAutonomousSwitch2() {
		if (autonomousSwitch2 == null) {
			autonomousSwitch2 = new DigitalInputSwitch(AUTONOMOUS_SWITCH_2_CHANNEL, Switch.NORMALLY_OPEN);
		}
		return autonomousSwitch2;
	}

	public synchronized DigitalInputSwitch getAutonomousSwitch3() {
		if (autonomousSwitch3 == null) {
			autonomousSwitch3 = new DigitalInputSwitch(AUTONOMOUS_SWITCH_3_CHANNEL, Switch.NORMALLY_OPEN);
		}
		return autonomousSwitch3;
	}

}
