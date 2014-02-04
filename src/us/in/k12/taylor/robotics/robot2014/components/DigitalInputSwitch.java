package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.DigitalInput;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;

/**
 * @author Taylor Robotics 2014
 */
public class DigitalInputSwitch extends Switch implements RobotParameters {
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
