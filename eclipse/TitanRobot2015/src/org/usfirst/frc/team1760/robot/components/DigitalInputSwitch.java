package org.usfirst.frc.team1760.robot.components;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * @author Robo-Titans Team 1790 Taylor High School 2015
 */
public class DigitalInputSwitch extends Switch {
	private final DigitalInput digitalInput;
    private final int switchMode;

    public DigitalInputSwitch(int pChannel, int pSwitchMode) {
        this(pChannel, pSwitchMode, false);
    }

    public DigitalInputSwitch(int pChannel, int pSwitchMode, boolean pForceStateChange) {
        digitalInput = new DigitalInput(pChannel);
        switchMode = pSwitchMode;
        setForceStateChange(pForceStateChange);
    }

    public boolean getSwitchState() {
        return (digitalInput.get() == (switchMode == NORMALLY_OPEN));
    }
}
