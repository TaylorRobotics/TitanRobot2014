package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.MaxSonarDistanceSensor;

/**
 * @author Taylor Robotics 2014
 */
public class ShoulderSeekPositionHandler implements RobotParameters {
    private final ComponentRegistry componentRegistry;
    private final StateRegistry stateRegistry;
    private final MaxSonarDistanceSensor distanceSensor;

    public ShoulderSeekPositionHandler(TitanRobot pRobot) {
        componentRegistry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        distanceSensor = componentRegistry.getDistanceSensor();
    }

    public void run() {
        if (stateRegistry.getShoulderPositionMode() == SHOULDER_SEEK_MODE) {
            // Replace with code that adjusts the target position based upon distance
            double position = stateRegistry.getShoulderPositionTarget();
            double distance = distanceSensor.getDistance();
            stateRegistry.setShoulderPositionTarget(position);
        }
    }
}
