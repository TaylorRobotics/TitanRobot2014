package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.SimpleRelay;
import us.in.k12.taylor.robotics.robot2014.components.Switch;

/**
 * @author Taylor Robotics 2014
 */
public class ShootingDistanceLightHandler implements RobotParameters {
    private final ComponentRegistry componentRegistry;
    private final Switch distanceSwitch;
    private final SimpleRelay shootDistanceLightRelay;

    public ShootingDistanceLightHandler(TitanRobot pRobot) {
        componentRegistry = pRobot.getComponentRegistry();
        distanceSwitch = componentRegistry.getShootingDistanceSwitch();
        shootDistanceLightRelay = componentRegistry.getShootDistanceLightRelay();
    }

    public void run() {
        // Consider putting in a time check (only check every x milisecons
        shootDistanceLightRelay.set(distanceSwitch.isSwitchOn());
    }
}
