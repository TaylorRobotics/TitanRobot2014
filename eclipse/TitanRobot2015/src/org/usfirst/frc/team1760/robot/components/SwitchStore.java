package org.usfirst.frc.team1760.robot.components;

public class SwitchStore {
	public static final int ELEVATOR_UPPER_LIMIT_SWITCH_CHANNEL = 0;
	public static final int ELEVATOR_LOWER_LIMIT_SWITCH_CHANNEL = 1;
	public static final int AUTONOMOUS_SWITCH_1_CHANNEL = 2;
	public static final int AUTONOMOUS_SWITCH_2_CHANNEL = 3;
	public static final int AUTONOMOUS_SWITCH_3_CHANNEL = 4;

	private DigitalInputSwitch elevatorUpperLimitSwitch = null;
	private DigitalInputSwitch elevatorLowerLimitSwitch = null;
	private DigitalInputSwitch autonomousSwitch1 = null;
	private DigitalInputSwitch autonomousSwitch2 = null;
	private DigitalInputSwitch autonomousSwitch3 = null;

	public synchronized DigitalInputSwitch getElevatorUpperLimitSwitch() {
		if (elevatorUpperLimitSwitch == null) {
			elevatorUpperLimitSwitch = new DigitalInputSwitch(ELEVATOR_UPPER_LIMIT_SWITCH_CHANNEL, Switch.NORMALLY_OPEN);
		}
		return elevatorUpperLimitSwitch;
	}

	public synchronized DigitalInputSwitch getElevatorLowerLimitSwitch() {
		if (elevatorLowerLimitSwitch == null) {
			elevatorLowerLimitSwitch = new DigitalInputSwitch(ELEVATOR_LOWER_LIMIT_SWITCH_CHANNEL, Switch.NORMALLY_OPEN);
		}
		return elevatorLowerLimitSwitch;
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
