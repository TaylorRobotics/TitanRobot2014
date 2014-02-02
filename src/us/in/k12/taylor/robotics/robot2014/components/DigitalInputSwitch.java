package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.DigitalInput;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;

/**
 * @author Taylor Robotics 2014
 */
public class DigitalInputSwitch implements Switch, RobotParameters {
    private final DigitalInput digitalInput;
    private final int switchMode;

    public DigitalInputSwitch(int pChannel, int pSwitchMode) {
        digitalInput = new DigitalInput(pChannel);
        switchMode = pSwitchMode;
    }

    public final boolean isSwitchOn() {
        return digitalInput.get() == (switchMode == NORMALLY_OPEN);
    }
}
