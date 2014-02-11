package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;
import us.in.k12.taylor.robotics.robot2014.components.Potentiometer;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class ShootingHandler implements RobotParameters {
    private final RobotRegistry registry;
    private final TitanSpeedController triggerMotor;
    private final TitanSpeedController pickupMotor;
    private final Potentiometer shoulderPotentiometer;
    private final JoystickButton forceTriggerFireButton;
    private boolean lastShootingMode;

    public ShootingHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        triggerMotor = registry.getTriggerMotor();
        pickupMotor = registry.getPickupMotor();
        shoulderPotentiometer = registry.getShoulderPotentiometer();
        forceTriggerFireButton = registry.getForceTriggerFireButton();
        lastShootingMode = false;
    }

    public void run() {
        if (registry.isShooting()) {
            if (lastShootingMode) {
                /* In shooting process.  Run motor until time limit reached */
                pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
            }
            else {
                /* Start shooting process, and cancel keep ball when shooting */
                pickupMotor.setTimedOperation(PICKUP_MOTOR_FIRE_TIME);
                pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
                if (forceTriggerFireButton.isSwitchOn() || (shoulderPotentiometer.getValue() >= TRIGGER_FIRE_LIMIT)) {
                    triggerMotor.set(TRIGGER_FIRE_SPEED);
                }
            }
        }
        else if (lastShootingMode) {
            /* Stop shooting process */
            System.out.println("Stop");
            triggerMotor.set(0.0);
            pickupMotor.set(0.0);
            pickupMotor.setNonTimedOperation();
        }
        lastShootingMode = registry.isShooting();
    }
}
