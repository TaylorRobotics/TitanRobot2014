package org.usfirst.frc.team1760.robot.components;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickStore {
    public static final int LEFT_DRIVE_JOYSTICK = 1;
    public static final int RIGHT_DRIVE_JOYSTICK = 2;
    public static final int OPERATOR_JOYSTICK = 3;

    public static final int LIFT_DOWN_BUTTON = 4;  // On operator joystick  (Change to RAISE_FORK_BUTTON)
    public static final int LIFT_UP_BUTTON = 5;    // On operator joystick  (Change to LOWER_FORK_BUTTON)

    public static final int SLOW_SPEED_BUTTON = 1; // On left driver joystick
    public static final int FAST_SPEED_BUTTON = 1; // On right driver joystick

    private Joystick leftDriveJoystick = null;
    private Joystick rightDriveJoystick = null;
    private Joystick operatorJoystick = null;
    private JoystickButton liftDownButton = null;
    private JoystickButton liftUpButton = null;
	private JoystickButton slowSpeedButton = null;
	private JoystickButton fastSpeedButton = null;

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

}
