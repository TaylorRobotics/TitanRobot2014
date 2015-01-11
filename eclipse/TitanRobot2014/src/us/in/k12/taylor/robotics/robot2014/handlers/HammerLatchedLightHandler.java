package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.SimpleRelay;
import us.in.k12.taylor.robotics.robot2014.components.Switch;

/**
 * @author Taylor Robotics 2014
 */
public class HammerLatchedLightHandler implements RobotParameters {
    private final ComponentRegistry componentRegistry;
    private final Switch hammerLatchedSwitch;
    private final Switch hammerLockedSwitch;
    private final SimpleRelay hammerLatchedLightRelay;

    public HammerLatchedLightHandler(TitanRobot pRobot) {
        componentRegistry = pRobot.getComponentRegistry();
        hammerLatchedSwitch = componentRegistry.getHammerLatchedSwitch();
        hammerLockedSwitch = componentRegistry.getHammerLockedSwitch();
        hammerLatchedLightRelay = componentRegistry.getHammerLatchedLightRelay();
    }

    public void run() {
        boolean latchedAndLocked = hammerLatchedSwitch.isSwitchOn() && hammerLockedSwitch.isSwitchOn();
        hammerLatchedLightRelay.set(latchedAndLocked);
    }
}
