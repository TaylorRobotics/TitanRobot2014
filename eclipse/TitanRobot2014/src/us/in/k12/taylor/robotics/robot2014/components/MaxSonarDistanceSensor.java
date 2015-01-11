package us.in.k12.taylor.robotics.robot2014.components;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * @author Taylor Robotics 2014
 */
public class MaxSonarDistanceSensor implements RobotParameters {
    private final AnalogInput analogVoltageMeter;
    private final AnalogInput maxSonar;

    public MaxSonarDistanceSensor(int pMaxSonarChannel, AnalogInput pAnalogVoltageMeter) {
        maxSonar = new AnalogInput(pMaxSonarChannel);
        analogVoltageMeter = pAnalogVoltageMeter;
    }

    public int getDistance() {
        double inch = analogVoltageMeter.getVoltage() / MAXSONAR_INCH_CONSTANT;
        int distance = (int)((maxSonar.getVoltage() / inch) + 0.5);
        return distance;
    }
}
