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
    private final TitanSpeedController pickupMotor;

    public TriggerButtonHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        triggerMotor = registry.getTriggerMotor();
        triggerFireButton = registry.getTriggerFireButton();
        triggerLockButton = registry.getTriggerLockButton();
        pickupMotor = registry.getPickupMotor();
    }

    public void run() {
        /* Run trigger motor based upon trigger buttons */
        if (triggerFireButton.isSwitchOn()) {
            /* Cancel keep ball when trigger button pressed */
            registry.setKeepBallMode(false);
            /* If fire button was just clicked, set pickup motor timed run */
            if (triggerFireButton.getStateChange()) {
                pickupMotor.setTimedOperation(PICKUP_MOTOR_FIRE_TIME);
            }
            triggerMotor.set(TRIGGER_FIRE_SPEED);
        }
        else if (triggerLockButton.isSwitchOn()) {
            triggerMotor.set(TRIGGER_LOCK_SPEED);
        }
        else if (triggerFireButton.getStateChange() || triggerLockButton.getStateChange()){
            triggerMotor.set(0.0);
        }

        /* If in fire mode, run motor until time limit reached */
        if (registry.isFireMode()) {
            pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
            if (pickupMotor.isTimeLimitReached()) {
                registry.setFireMode(false);
                pickupMotor.set(0.0);
                pickupMotor.setNonTimedOperation();
            }
        }
    }
}
