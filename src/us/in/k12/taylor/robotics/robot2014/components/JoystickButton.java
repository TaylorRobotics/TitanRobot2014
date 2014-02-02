package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Taylor Robotics 2014
 */
public class JoystickButton {
    private final Joystick joystick;
    private final int buttonId;
    private boolean buttonState;

    public JoystickButton(Joystick pJoystick, int pButtonId, boolean pForceStateChange) {
        joystick = pJoystick;
        buttonId = pButtonId;

        /* If pForceStateChange is true, the next call to getStateChange() will return true */
        if (pForceStateChange) {
            buttonState = !joystick.getRawButton(buttonId);
        }
        else {
            buttonState = joystick.getRawButton(buttonId);
        }
    }

    public boolean isButtonPushed() {
        buttonState = joystick.getRawButton(buttonId);
        return buttonState;
    }

    public boolean getStateChange() {
        boolean stateChange;
        boolean currentState = joystick.getRawButton(buttonId);
        if (buttonState == currentState) {
            stateChange = false;
        }
        else {
            stateChange = true;
            buttonState = currentState;
        }
        return stateChange;
    }
}
