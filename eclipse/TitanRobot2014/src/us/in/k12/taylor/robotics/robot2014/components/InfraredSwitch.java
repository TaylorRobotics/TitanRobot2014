package us.in.k12.taylor.robotics.robot2014.components;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * @author Taylor Robotics 2014
 */
public class InfraredSwitch extends Switch implements RobotParameters {
    private final AnalogInput analogVoltageMeter;
    private final AnalogInput infraredDetector;
    private final double hammerLevel; // % of VCC (0 - 1.0)
    private final int onState;

    public InfraredSwitch(int pInfraredDetectorChannel, AnalogInput pAnalogVoltageMeter, double pHammerLevel, int pOnState) {
        this(pInfraredDetectorChannel, pAnalogVoltageMeter, pHammerLevel, pOnState, false);
    }

    public InfraredSwitch(int pInfraredDetectorChannel, AnalogInput pAnalogVoltageMeter, double pHammerLevel, int pOnState, boolean pForceStateChange) {
        infraredDetector = new AnalogInput(pInfraredDetectorChannel);
        analogVoltageMeter = pAnalogVoltageMeter;
        hammerLevel = pHammerLevel;
        onState = pOnState;
        setForceStateChange(pForceStateChange);
    }

    public boolean getSwitchState() {
        double ratio = infraredDetector.getVoltage() / analogVoltageMeter.getVoltage();
        return ((ratio >= hammerLevel) && (onState == ON_HIGH));
    }
}
