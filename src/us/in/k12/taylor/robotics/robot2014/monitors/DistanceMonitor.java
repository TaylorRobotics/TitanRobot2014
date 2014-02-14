package us.in.k12.taylor.robotics.robot2014.monitors;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.MaxSonarDistanceSensor;
import us.in.k12.taylor.robotics.robot2014.components.MessageDisplay;

/**
 * @author Taylor Robotics 2014
 */
public class DistanceMonitor implements RobotParameters {
    MaxSonarDistanceSensor distanceSensor;
    MessageDisplay messageDisplay;
    int lastDistance;

    public DistanceMonitor(TitanRobot pRobot) {
        ComponentRegistry componentRegistry = pRobot.getComponentRegistry();
        distanceSensor = componentRegistry.getDistanceSensor();
        messageDisplay = componentRegistry.getMessageDisplay();
        update(true);
    }

    public boolean update() {
        return update(false);
    }

    private boolean update(boolean pForce) {
        boolean changed = false;
        int currentDistance = distanceSensor.getDistance();
        if (pForce || (lastDistance != currentDistance)) {
            messageDisplay.setLine(5, "Distance: " + currentDistance);
            lastDistance = currentDistance;
            changed = true;
        }
        return changed;
    }
}
