package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.MaxSonarDistanceSensor;

/**
 * @author Taylor Robotics 2014
 */
public class ShoulderSeekPositionHandler implements RobotParameters {
    private final RobotRegistry registry;
    private final MaxSonarDistanceSensor distanceSensor;

    public ShoulderSeekPositionHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        distanceSensor = registry.getDistanceSensor();
    }

    public void run() {
        if (registry.getShoulderPositionMode() == SHOULDER_SEEK_MODE) {
            // Replace with code that adjusts the target position based upon distance
            double position = registry.getShoulderPositionTarget();
            double distance = distanceSensor.getDistance();
            registry.setShoulderPositionTarget(position);
        }
    }
}
