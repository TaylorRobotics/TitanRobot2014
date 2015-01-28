package org.usfirst.frc.team1760.robot.components;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickStore {
    public static final int LEFT_DRIVE_JOYSTICK = 1;
    public static final int RIGHT_DRIVE_JOYSTICK = 2;
    public static final int OPERATOR_JOYSTICK = 3;

    public static final int LIFT_DOWN_BUTTON = 4;  // On operator joystick
    public static final int LIFT_UP_BUTTON = 5;    // On operator joystick

    public static final int SLOW_SPEED_BUTTON = 1; // On left driver joystick
    public static final int FAST_SPEED_BUTTON = 1; // On right driver joystick

    public static final int HOLD_ELEVATOR_HIGH_BUTTON = 6;   // On operator joystick
    public static final int PARK_ELEVATOR_BUTTON = 7;    // On operator joystick
    public static final int CANCEL_ELEVATOR_HOLD_BUTTON = 8; // On operator joystick

    private Joystick leftDriveJoystick = null;
    private Joystick rightDriveJoystick = null;
    private Joystick operatorJoystick = null;
    private JoystickButton liftDownButton = null;
    private JoystickButton liftUpButton = null;
	private JoystickButton slowSpeedButton = null;
	private JoystickButton fastSpeedButton = null;
	private JoystickButton holdElevatorHighButton = null;
	private JoystickButton parkElevatorButton = null;
	private JoystickButton cancelElevatorHoldButton = null;

    public synchronized Joystick getLeftDriveJoystick() {
    	if (leftDriveJoystick == null) {
            leftDriveJoystick = new Joystick(LEFT_DRIVE_JOYSTICK);
    	}
		return leftDriveJoystick;
	}

    public synchronized Joystick getRightDriveJoystick() {
    	if (rightDriveJoystick == null) {
            rightDriveJoystick = new Joystick(RIGHT_DRIVE_JOYSTICK);
    	}
		return rightDriveJoystick;
	}

    public synchronized Joystick getOperatorJoystick() {
    	if (operatorJoystick == null) {
            operatorJoystick = new Joystick(OPERATOR_JOYSTICK);
    	}
		return operatorJoystick;
	}

	public synchronized JoystickButton getLiftDownButton() {
		if (liftDownButton == null) {
			 liftDownButton = new JoystickButton(rightDriveJoystick, LIFT_DOWN_BUTTON, false);
		}
		return liftDownButton;
	}

	public synchronized JoystickButton getLiftUpButton() {
		if (liftUpButton == null) {
			 liftUpButton = new JoystickButton(rightDriveJoystick, LIFT_UP_BUTTON, false);
		}
		return liftUpButton;
	}

	public synchronized JoystickButton getSlowSpeedButton() {
		if (slowSpeedButton == null) {
			slowSpeedButton = new JoystickButton(leftDriveJoystick, SLOW_SPEED_BUTTON, false);
		}
		return slowSpeedButton;
	}

	public synchronized JoystickButton getFastSpeedButton() {
		if (fastSpeedButton == null) {
			fastSpeedButton = new JoystickButton(rightDriveJoystick, FAST_SPEED_BUTTON, false);
		}
		return fastSpeedButton;
	}

	public synchronized JoystickButton getParkElevatorButton() {
		if (parkElevatorButton == null) {
			parkElevatorButton = new JoystickButton(operatorJoystick, PARK_ELEVATOR_BUTTON, false);
		}
		return parkElevatorButton;
	}

	public synchronized JoystickButton getHoldElevatorHighButton() {
		if (holdElevatorHighButton == null) {
			holdElevatorHighButton = new JoystickButton(operatorJoystick, HOLD_ELEVATOR_HIGH_BUTTON, false);
		}
		return holdElevatorHighButton;
	}

	public synchronized JoystickButton getCancelElevatorHoldButton() {
		if (cancelElevatorHoldButton == null) {
			cancelElevatorHoldButton = new JoystickButton(operatorJoystick, CANCEL_ELEVATOR_HOLD_BUTTON, false);
		}
		return cancelElevatorHoldButton;
	}

}
