package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.SimpleRelay;
import us.in.k12.taylor.robotics.robot2014.components.Switch;

/**
 * @author Taylor Robotics 2014
 */
public class HammerLatchedLightHandler implements RobotParameters {
    private final ComponentRegistry registry;
    private final Switch hammerLatchedSwitch;
    private final SimpleRelay hammerLatchedLightRelay;

    public HammerLatchedLightHandler(TitanRobot pRobot) {
        registry = pRobot.getComponentRegistry();
        hammerLatchedSwitch = registry.getHammerLatchedSwitch();
        hammerLatchedLightRelay = registry.getHammerLatchedLightRelay();
    }

    public void run() {
        // Consider putting in a time check (only check every x milisecons
        hammerLatchedLightRelay.set(hammerLatchedSwitch.isSwitchOn());
    }
}
