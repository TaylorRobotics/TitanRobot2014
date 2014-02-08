package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class TriggerButtonHandler implements RobotParameters {
    private final RobotRegistry registry;
    private final JoystickButton triggerFireButton;
    private final JoystickButton triggerLockButton;
    private final TitanSpeedController triggerMotor;

    public TriggerButtonHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        triggerMotor = registry.getTriggerMotor();
        triggerFireButton = registry.getTriggerFireButton();
        triggerLockButton = registry.getTriggerLockButton();
    }

    public void run() {
        /* Run trigger motor based upon trigger buttons */
        if (triggerFireButton.isSwitchOn()) {
            registry.setShooting(true);
        }
        else if (triggerLockButton.isSwitchOn()) {
            triggerMotor.set(TRIGGER_LOCK_SPEED);
        }
        else if (triggerFireButton.getStateChange() || triggerLockButton.getStateChange()){
            registry.setShooting(false);
        }
    }
}
