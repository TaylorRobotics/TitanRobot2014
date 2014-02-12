package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class TriggerButtonHandler implements RobotParameters {
    private final ComponentRegistry registry;
    private final StateRegistry stateRegistry;
    private final JoystickButton triggerFireButton;
    private final JoystickButton triggerLockButton;
    private final TitanSpeedController triggerMotor;

    public TriggerButtonHandler(TitanRobot pRobot) {
        registry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        triggerMotor = registry.getTriggerMotor();
        triggerFireButton = registry.getTriggerFireButton();
        triggerLockButton = registry.getTriggerLockButton();
    }

    public void run() {
        /* Run trigger motor based upon trigger buttons */
        if (triggerFireButton.isSwitchOn()) {
            if (triggerFireButton.getStateChange()) {
                stateRegistry.setShooting(true);
            }
        }
        else if (triggerFireButton.getStateChange()) {
                stateRegistry.setShooting(false);
        }
        if (triggerLockButton.isSwitchOn()) {
            triggerMotor.set(TRIGGER_LOCK_SPEED);
            stateRegistry.setShooting(false);
        }
        else if (triggerLockButton.getStateChange()) {
            triggerMotor.set(0.0);
        }
    }
}
