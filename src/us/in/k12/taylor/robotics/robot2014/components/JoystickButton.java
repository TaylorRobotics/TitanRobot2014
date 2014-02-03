package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Taylor Robotics 2014
 */
public class JoystickButton extends Switch {
    private final Joystick joystick;
    private final int buttonId;

    public JoystickButton(Joystick pJoystick, int pButtonId) {
        this(pJoystick, pButtonId, false);
    }

    public JoystickButton(Joystick pJoystick, int pButtonId, boolean pForceStateChange) {
        super(pForceStateChange);
        joystick = pJoystick;
        buttonId = pButtonId;
    }

    public boolean getSwitchState() {
        return joystick.getRawButton(buttonId);
    }
}
