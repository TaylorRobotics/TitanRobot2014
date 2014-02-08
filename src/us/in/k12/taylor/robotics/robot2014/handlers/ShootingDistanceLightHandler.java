package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.Switch;

/**
 * @author Taylor Robotics 2014
 */
public class ShootingDistanceLightHandler implements RobotParameters {
    private final RobotRegistry registry;
    private final Switch distanceSwitch;
    private final DualSpikeRelay lightRelay;

    public ShootingDistanceLightHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        distanceSwitch = registry.getShootingDistanceSwitch();
        lightRelay = registry.get...
    }

    public void run() {
        // Consider putting in a time check (only check every x milisecons
        relay.setFirstRelay(distanceSwitch.isSwitchOn());
    }
}
