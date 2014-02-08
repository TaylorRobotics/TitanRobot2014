package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.MaxSonarDistanceSwitch;
import us.in.k12.taylor.robotics.robot2014.components.Switch;

/**
 * @author Taylor Robotics 2014
 */
public class TriggerLockedLightHandler implements RobotParameters {
    private final RobotRegistry registry;
    private final Switch triggerLockedSwitch;
    private final DualSpikeRelay lightRelay;

    public TriggerLockedLightHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        triggerLockedSwitch = registry.getTriggerLockedSwitch();
        lightRelay = registry.get...
    }

    public void run() {
        // Consider putting in a time check (only check every x milisecons
        relay.setFirstRelay(triggerLockedSwitch.isSwitchOn());
    }
}
