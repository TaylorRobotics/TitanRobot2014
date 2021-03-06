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

    public static final int LOWER_TOTE_BUTTON = 4;    // On left driver joystick
    public static final int RAISE_TOTE_BUTTON = 5;  // On left driver joystick

    public static final int DRIVE_SLOW_BUTTON = 1; // On left driver joystick
    public static final int DRIVE_FAST_BUTTON = 1; // On right driver joystick

    public static final int DRIVE_FORWARD_BUTTON = 6;  // On left driver joystick
    public static final int DRIVE_BACKWARD_BUTTON = 7; // On left driver joystick

    public static final int STOP_FORK_AT_MIDDLE_BUTTON = 1; // On operator joystick

    public static final int LOWER_TAIL_LIFT_BUTTON = 4;  // On operator joystick
    public static final int RAISE_TAIL_LIFT_BUTTON = 5;  // On operator joystick

    public static final int LOWER_DRAGON_TAIL_BUTTON = 2;  // On left driver joystick
    public static final int RAISE_DRAGON_TAIL_BUTTON = 3;  // On left driver joystick

    private Joystick leftDriveJoystick = null;
    private Joystick rightDriveJoystick = null;
    private Joystick operatorJoystick = null;
    private JoystickButton raiseToteButton = null;
    private JoystickButton lowerToteButton = null;
	private JoystickButton driveSlowButton = null;
	private JoystickButton driveFastButton = null;
	private JoystickButton stopForkAtMiddleButton = null;
	private JoystickButton lowerTailLiftButton = null;
	private JoystickButton raiseTailLiftButton = null;
	private JoystickButton lowerDragonTailButton = null;
	private JoystickButton raiseDragonTailButton = null;
	private JoystickButton driveForwardButton = null;
	private JoystickButton driveBackwardButton = null;

	/**
	 * Gets the left drive Joystick.
	 * @return The left drive Joystck
	 */
    public synchronized Joystick getLeftDriveJoystick() {
    	if (leftDriveJoystick == null) {
            leftDriveJoystick = new Joystick(LEFT_DRIVE_JOYSTICK);
    	}
		return leftDriveJoystick;
	}

    /**
     * Gets the right drive JOystick.
     * @return The right drive Joystick
     */
    public synchronized Joystick getRightDriveJoystick() {
    	if (rightDriveJoystick == null) {
            rightDriveJoystick = new Joystick(RIGHT_DRIVE_JOYSTICK);
    	}
		return rightDriveJoystick;
	}

    /**
     * Gets the operator Joystick.
     * @return The operator Joystick
     */
    public synchronized Joystick getOperatorJoystick() {
    	if (operatorJoystick == null) {
            operatorJoystick = new Joystick(OPERATOR_JOYSTICK);
    	}
		return operatorJoystick;
	}

    /**
     * Gets the JoystickButton that indicates when the tote lift is to be raised.
     * @return The JoystickButton that indicates when the tote lift is to be raised
     */
	public synchronized JoystickButton getRaiseToteButton() {
		if (raiseToteButton == null) {
			 raiseToteButton = new JoystickButton(getLeftDriveJoystick(), RAISE_TOTE_BUTTON, false);
		}
		return raiseToteButton;
	}

    /**
     * Gets the JoystickButton that indicates when the tote lift is to be lowered.
     * @return The JoystickButton that indicates when the tote lift is to be lowered
     */
	public synchronized JoystickButton getLowerToteButton() {
		if (lowerToteButton == null) {
			 lowerToteButton = new JoystickButton(getLeftDriveJoystick(), LOWER_TOTE_BUTTON, false);
		}
		return lowerToteButton;
	}

    /**
     * Gets the JoystickButton that indicates when the tail lift is to be raised.
     * @return The JoystickButton that indicates when the tail lift is to be raised
     */
	public synchronized JoystickButton getRaiseTailLiftButton() {
		if (raiseTailLiftButton == null) {
			 raiseTailLiftButton = new JoystickButton(getOperatorJoystick(), RAISE_TAIL_LIFT_BUTTON, false);
		}
		return raiseTailLiftButton;
	}

    /**
     * Gets the JoystickButton that indicates when the tail lift is to be lowered.
     * @return The JoystickButton that indicates when the tail lift is to be lowered
     */
	public synchronized JoystickButton getLowerTailLiftButton() {
		if (lowerTailLiftButton == null) {
			 lowerTailLiftButton = new JoystickButton(getOperatorJoystick(), LOWER_TAIL_LIFT_BUTTON, false);
		}
		return lowerTailLiftButton;
	}

    /**
     * Gets the JoystickButton that indicates when the tail lift is to be raised.
     * @return The JoystickButton that indicates when the tail lift is to be raised
     */
	public synchronized JoystickButton getRaiseDragonTailButton() {
		if (raiseDragonTailButton == null) {
			 raiseDragonTailButton = new JoystickButton(getLeftDriveJoystick(), RAISE_DRAGON_TAIL_BUTTON, false);
		}
		return raiseDragonTailButton;
	}

    /**
     * Gets the JoystickButton that indicates when the tail lift is to be lowered.
     * @return The JoystickButton that indicates when the tail lift is to be lowered
     */
	public synchronized JoystickButton getLowerDragonTailButton() {
		if (lowerDragonTailButton == null) {
			 lowerDragonTailButton = new JoystickButton(getLeftDriveJoystick(), LOWER_DRAGON_TAIL_BUTTON, false);
		}
		return lowerDragonTailButton;
	}

	/**
	 * Gets the JoystickButton that indicates, while pressed, that the drive speed is to be reduced.
	 * @return The JoystickButton that indicates, while pressed, that the drive speed is to be reduced
	 */
	public synchronized JoystickButton getDriveSlowButton() {
		if (driveSlowButton == null) {
			driveSlowButton = new JoystickButton(getRightDriveJoystick(), DRIVE_SLOW_BUTTON, false);
		}
		return driveSlowButton;
	}

	/**
	 * Gets the JoystickButton that indicates, while pressed, that the drive speed is to be increased.
	 * @return The JoystickButton that indicates, while pressed, that the drive speed is to be increased
	 */
	public synchronized JoystickButton getDriveFastButton() {
		if (driveFastButton == null) {
			driveFastButton = new JoystickButton(getLeftDriveJoystick(), DRIVE_FAST_BUTTON, false);
		}
		return driveFastButton;
	}

	/**
	 * Gets the JoystickButton that indicates that drive operation should be in the forward direction.
	 * @return The JoystickButton that indicates that drive operation should be in the forward direction
	 */
	public synchronized JoystickButton getDriveForwardButton() {
		if (driveForwardButton == null) {
			driveForwardButton = new JoystickButton(getLeftDriveJoystick(), DRIVE_FORWARD_BUTTON, false);
		}
		return driveForwardButton;
	}

	/**
	 * Gets the JoystickButton that indicates that drive operation should be in the backward direction.
	 * @return The JoystickButton that indicates that drive operation should be in the backward direction
	 */
	public synchronized JoystickButton getDriveBackwardButton() {
		if (driveBackwardButton == null) {
			driveBackwardButton = new JoystickButton(getLeftDriveJoystick(), DRIVE_BACKWARD_BUTTON, false);
		}
		return driveBackwardButton;
	}

	/**
	 * Gets the JoystickButton that indicates, while presesed, that the fork lift is to stop when the 
	 * middle limit switch of the fork lift is reached.
	 * @return The JoystickButton that indicates the fork lift is to stop when the middle limit switch is reached
	 */
	public synchronized JoystickButton getStopForkAtMiddleButton() {
		if (stopForkAtMiddleButton == null) {
			stopForkAtMiddleButton = new JoystickButton(getOperatorJoystick(), STOP_FORK_AT_MIDDLE_BUTTON, false);
		}
		return stopForkAtMiddleButton;
	}

}
