package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.AnalogChannel;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;

/**
 * @author Taylor Robotics 2014
 */
public class Potentiometer implements RobotParameters {
    private final AnalogChannel analogVoltageMeter;
    private final AnalogChannel potentiometer;

    public Potentiometer(int pPotentiometerChannel, AnalogChannel pAnalogVoltageMeter) {
        potentiometer = new AnalogChannel(pPotentiometerChannel);
        analogVoltageMeter = pAnalogVoltageMeter;
    }

    public double getRatio() {
        return potentiometer.getVoltage() / analogVoltageMeter.getVoltage();
    }
}
