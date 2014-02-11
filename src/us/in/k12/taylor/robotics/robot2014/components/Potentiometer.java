package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.AnalogChannel;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;

/**
 * @author Taylor Robotics 2014
 */
public class Potentiometer implements RobotParameters {
    private final AnalogChannel analogVoltageMeter;
    private final AnalogChannel potentiometer;
    private final boolean scaled;
    private final double scale;
    private final double minValue;
    private final double maxValue;
    private final boolean reverse;

    public Potentiometer(int pPotentiometerChannel, AnalogChannel pAnalogVoltageMeter, boolean pReverse) {
        potentiometer = new AnalogChannel(pPotentiometerChannel);
        analogVoltageMeter = pAnalogVoltageMeter;
        scaled = false;
        scale = 0.0;
        minValue = 0.0;
        maxValue = 0.0;
        reverse = pReverse;
    }

    public Potentiometer(int pPotentiometerChannel, AnalogChannel pAnalogVoltageMeter, boolean pReverse, double pScale, double pMinValue, double pMaxValue) {
        potentiometer = new AnalogChannel(pPotentiometerChannel);
        analogVoltageMeter = pAnalogVoltageMeter;
        scaled = true;
        scale = pScale;
        minValue = pMinValue;
        maxValue = pMaxValue;
        reverse = pReverse;
    }

    public double getRatio() {
        double ratio;
        if (reverse) {
            ratio = 1.0 - (potentiometer.getVoltage() / analogVoltageMeter.getVoltage());       
        }
        else {
            ratio = potentiometer.getVoltage() / analogVoltageMeter.getVoltage();       
        }
        if (ratio < 0.0) {
            ratio = 0.0;
        }
        else if (ratio > 1.0) {
            ratio = 1.0;
        }
        return ratio;       
    }

    public double getValue() {
        double value = getRatio() - minValue;
        double range = maxValue - minValue;
        if (scaled) {
            value = value / range;
            if (value < 0.0) {
                value = 0.0;
            }
            else if (value > 1.0) {
                value = 1.0;
            }
            value = value * scale;
        }
        return value;
    }
}
