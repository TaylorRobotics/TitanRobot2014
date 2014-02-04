package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.AnalogChannel;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;

/**
 * @author Taylor Robotics 2014
 */
public class InfraredSwitch extends Switch implements RobotParameters {
    private final AnalogChannel analogVoltageMeter;
    private final AnalogChannel infraredDetector;
    private final double triggerLevel; // % of VCC (0 - 1.0)
    private final int onState;

    public InfraredSwitch(int pInfraredDetectorChannel, AnalogChannel pAnalogVoltageMeter, double pTriggerLevel, int pOnState) {
        this(pInfraredDetectorChannel, pAnalogVoltageMeter, pTriggerLevel, pOnState, false);
    }

    public InfraredSwitch(int pInfraredDetectorChannel, AnalogChannel pAnalogVoltageMeter, double pTriggerLevel, int pOnState, boolean pForceStateChange) {
        infraredDetector = new AnalogChannel(pInfraredDetectorChannel);
        analogVoltageMeter = pAnalogVoltageMeter;
        triggerLevel = pTriggerLevel;
        onState = pOnState;
        setForceStateChange(pForceStateChange);
    }

    public boolean getSwitchState() {
        double ratio = infraredDetector.getVoltage() / analogVoltageMeter.getVoltage();
        return ((ratio >= triggerLevel) && (onState == ON_HIGH));
    }
}
