package us.in.k12.taylor.robotics.robot2014.handlers;

import edu.wpi.first.wpilibj.SpeedController;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;

/**
 * @author Taylor Robotics 2014
 */
public class TriggerButtonHandler implements RobotParameters {
    private final JoystickButton triggerFireButton;
    private final JoystickButton triggerLockButton;
    private final SpeedController triggerMotor;

    public TriggerButtonHandler(TitanRobot pRobot) {
        RobotRegistry registry = pRobot.getRegistry();
        triggerMotor = registry.getTriggerMotor();
        triggerFireButton = registry.getTriggerFireButton();
        triggerLockButton = registry.getTriggerLockButton();
    }

    public void run() {
        /* Run trigger motor based upon trigger buttons */
        if (triggerFireButton.isSwitchOn()) {
            triggerMotor.set(TRIGGER_FIRE_SPEED);
        }
        else if (triggerLockButton.isSwitchOn()) {
            triggerMotor.set(TRIGGER_LOCK_SPEED);
        }
        else if (triggerFireButton.getStateChange() || triggerLockButton.getStateChange()){
            triggerMotor.set(0.0);
        }
    }
}
