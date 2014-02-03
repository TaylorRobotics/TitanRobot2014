package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Taylor Robotics 2014
 */
public class JoystickButton {
    private final Joystick joystick;
    private final int buttonId;
    private boolean currentState;
    private boolean stateChange;

    public JoystickButton(Joystick pJoystick, int pButtonId, boolean pForceStateChange) {
        joystick = pJoystick;
        buttonId = pButtonId;

        /* If pForceStateChange is true, the next call to getStateChange() will return true */
        if (pForceStateChange) {
            currentState = !joystick.getRawButton(buttonId);
        }
        else {
            currentState = joystick.getRawButton(buttonId);
        }
    }

    public boolean isSwitchOn() {
        boolean newState = joystick.getRawButton(buttonId);
        stateChange = (currentState != newState);
        currentState = newState;
        return currentState;
    }

    /**
     * Gets whether the last call to isSwitchOn resulted in a button state change.
     * It is important that isSwitchOn is called before a call is made to this method.
     * @return A boolean of true if the last call to isSwitchOn() resulted in a state chagne
     */
    public boolean getStateChange() {
        return stateChange;
    }
}
