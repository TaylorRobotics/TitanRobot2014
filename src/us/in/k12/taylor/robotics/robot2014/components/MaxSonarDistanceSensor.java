package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.AnalogChannel;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;

/**
 * @author Taylor Robotics 2014
 */
public class MaxSonarDistanceSensor implements RobotParameters {
    private final AnalogChannel analogVoltageMeter;
    private final AnalogChannel maxSonar;

    public MaxSonarDistanceSensor(int pMaxSonarChannel, AnalogChannel pAnalogVoltageMeter) {
        maxSonar = new AnalogChannel(pMaxSonarChannel);
        analogVoltageMeter = pAnalogVoltageMeter;
    }

    public int getDistance() {
        double inch = analogVoltageMeter.getVoltage() / MAXSONAR_INCH_CONSTANT;
        int distance = (int)((maxSonar.getVoltage() / inch) + 0.5);
        return distance;
    }
}
