package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;
import us.in.k12.taylor.robotics.robot2014.components.MaxSonarDistanceSensor;

/**
 * @author Taylor Robtics 2014
 */
public class AutoShootHandler implements RobotParameters {
    private final ComponentRegistry registry;
    private final StateRegistry stateRegistry;
    private final JoystickButton autoShootButton;
    private final MaxSonarDistanceSensor distanceSensor;

    public AutoShootHandler(TitanRobot pRobot) {
        registry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        autoShootButton = registry.getAutoShootButton();
        distanceSensor = registry.getDistanceSensor();
    }

    public void run() {
        if (autoShootButton.isSwitchOn()) {
            if (atDistance() && !stateRegistry.isShooting()) {
                stateRegistry.setShooting(true);
            }
        }
    }

    private boolean atDistance() {
        double distance = distanceSensor.getDistance();
        return (Math.abs(AUTO_SHOOT_DISTANCE - distance) <= AUTO_SHOOT_DISTANCE_TOLERANCE);
    }
}
