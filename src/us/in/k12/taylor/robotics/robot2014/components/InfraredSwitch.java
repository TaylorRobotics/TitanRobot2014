package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.AnalogChannel;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;

/**
 * @author Taylor Robotics 2014
 */
public class InfraredSwitch implements Switch, RobotParameters {
    private final AnalogChannel analogVoltageMeter;
    private final AnalogChannel infraredDetector;
    private final double triggerLevel; // % of VCC (0 - 1.0)
    private final int onState;

    public InfraredSwitch(int pInfraredDetectorChannel, AnalogChannel pAnalogVoltageMeter, double pTriggerLevel, int pOnState) {
        infraredDetector = new AnalogChannel(pInfraredDetectorChannel);
        analogVoltageMeter = pAnalogVoltageMeter;
        triggerLevel = pTriggerLevel;
        onState = pOnState;
    }

    public boolean isSwitchOn() {
        double ratio = infraredDetector.getVoltage() / analogVoltageMeter.getVoltage();
        return ((ratio >= triggerLevel) && (onState == ON_HIGH));
    }
}
