package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.SimpleRelay;
import us.in.k12.taylor.robotics.robot2014.components.Switch;

/**
 * @author Taylor Robotics 2014
 */
public class TriggerLockedLightHandler implements RobotParameters {
    private final ComponentRegistry registry;
    private final Switch triggerLockedSwitch;
    private final SimpleRelay triggerLockedLightRelay;

    public TriggerLockedLightHandler(TitanRobot pRobot) {
        registry = pRobot.getComponentRegistry();
        triggerLockedSwitch = registry.getTriggerLockedSwitch();
        triggerLockedLightRelay = registry.getTriggerLockedLightRelay();
    }

    public void run() {
        // Consider putting in a time check (only check every x milisecons
        triggerLockedLightRelay.set(triggerLockedSwitch.isSwitchOn());
    }
}
