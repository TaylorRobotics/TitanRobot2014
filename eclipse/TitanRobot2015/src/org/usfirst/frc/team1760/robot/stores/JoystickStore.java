package org.usfirst.frc.team1760.robot.stores;

import org.usfirst.frc.team1760.robot.components.JoystickButton;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class holds Joysticks and Joystick buttons defined for robot operation.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class JoystickStore {
    public static final int LEFT_DRIVE_JOYSTICK = 0;
    public static final int RIGHT_DRIVE_JOYSTICK = 1;
    public static final int OPERATOR_JOYSTICK = 2;

    public static final int LOWER_TOTE_BUTTON = 4;    // On operator joystick
    public static final int RAISE_TOTE_BUTTON = 5;  // On operator joystick

    public static final int DRIVE_SLOW_BUTTON = 1; // On left driver joystick
    public static final int DRIVE_FAST_BUTTON = 1; // On right driver joystick

    public static final int STOP_FORK_AT_MIDDLE_BUTTON = 1; // On operator joystick

    private Joystick leftDriveJoystick = null;
    private Joystick rightDriveJoystick = null;
    private Joystick operatorJoystick = null;
    private JoystickButton raiseToteButton = null;
    private JoystickButton lowerToteButton = null;
	private JoystickButton driveSlowButton = null;
	private JoystickButton driveFastButton = null;
	private JoystickButton stopForkAtMiddleButton = null;

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

	public synchronized JoystickButton getRaiseToteButton() {
		if (raiseToteButton == null) {
			 raiseToteButton = new JoystickButton(rightDriveJoystick, RAISE_TOTE_BUTTON, false);
		}
		return raiseToteButton;
	}

	public synchronized JoystickButton getLowerToteButton() {
		if (lowerToteButton == null) {
			 lowerToteButton = new JoystickButton(rightDriveJoystick, LOWER_TOTE_BUTTON, false);
		}
		return lowerToteButton;
	}

	public synchronized JoystickButton getDriveSlowButton() {
		if (driveSlowButton == null) {
			driveSlowButton = new JoystickButton(leftDriveJoystick, DRIVE_SLOW_BUTTON, false);
		}
		return driveSlowButton;
	}

	public synchronized JoystickButton getDriveFastButton() {
		if (driveFastButton == null) {
			driveFastButton = new JoystickButton(rightDriveJoystick, DRIVE_FAST_BUTTON, false);
		}
		return driveFastButton;
	}

	public synchronized JoystickButton getStopForkAtMiddleButton() {
		if (stopForkAtMiddleButton == null) {
			stopForkAtMiddleButton = new JoystickButton(operatorJoystick, STOP_FORK_AT_MIDDLE_BUTTON, false);
		}
		return stopForkAtMiddleButton;
	}

}
