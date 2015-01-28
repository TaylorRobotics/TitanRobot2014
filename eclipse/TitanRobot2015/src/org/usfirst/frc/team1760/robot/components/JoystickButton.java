package org.usfirst.frc.team1760.robot.components;

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
        joystick = pJoystick;
        buttonId = pButtonId;
        setForceStateChange(pForceStateChange);
    }

    public boolean getSwitchState() {
        return joystick.getRawButton(buttonId);
    }
}
