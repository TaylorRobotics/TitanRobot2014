package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class ShootingHandler implements RobotParameters {
    private final RobotRegistry registry;
    private final TitanSpeedController triggerMotor;
    private final TitanSpeedController pickupMotor;
    private boolean lastShootingMode;

    public ShootingHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        triggerMotor = registry.getTriggerMotor();
        pickupMotor = registry.getPickupMotor();
        lastShootingMode = false;
    }

    public void run() {
        if (registry.isShooting()) {
            if (lastShootingMode) {
                /* In shooting process.  Run motor until time limit reached */
                pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
                if (pickupMotor.isTimeLimitReached()) {
                    registry.setShooting(false);
                }
            }
            else {
                /* Start shooting process, and cancel keep ball when shooting */
                registry.setKeepBallMode(false);
                pickupMotor.setTimedOperation(PICKUP_MOTOR_FIRE_TIME);
                pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
                triggerMotor.set(TRIGGER_FIRE_SPEED);
            }
        }

        if (!registry.isShooting() && lastShootingMode) {
            /* Stop shooting process */
            triggerMotor.set(0.0);
            pickupMotor.set(0.0);
            pickupMotor.setNonTimedOperation();
        }
        lastShootingMode = registry.isShooting();
    }
}
