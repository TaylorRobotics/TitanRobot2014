package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;
import us.in.k12.taylor.robotics.robot2014.components.Potentiometer;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class ShootingHandler implements RobotParameters {
    private final ComponentRegistry registry;
    private final StateRegistry stateRegistry;
    private final TitanSpeedController hammerMotor;
    private final TitanSpeedController pickupMotor;
    private final Potentiometer shoulderPotentiometer;
    private final JoystickButton forceHammerFireButton;
    private boolean lastShootingMode;

    public ShootingHandler(TitanRobot pRobot) {
        registry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        hammerMotor = registry.getHammerMotor();
        pickupMotor = registry.getPickupMotor();
        shoulderPotentiometer = registry.getShoulderPotentiometer();
        forceHammerFireButton = registry.getForceHammerFireButton();
        lastShootingMode = false;
    }

    public void run() {
        if (stateRegistry.isShooting()) {
            if (lastShootingMode) {
                /* In shooting process.  Run motor until time limit reached */
                pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
            }
            else {
                /* Start shooting process, and cancel keep ball when shooting */
                pickupMotor.setTimedOperation(PICKUP_MOTOR_FIRE_TIME);
                pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
                if (forceHammerFireButton.isSwitchOn() || (shoulderPotentiometer.getValue() >= HAMMER_FIRE_LIMIT)) {
                    hammerMotor.set(HAMMER_FIRE_SPEED);
                }
            }
        }
        else if (lastShootingMode) {
            /* Stop shooting process */
            hammerMotor.set(0.0);
            pickupMotor.set(0.0);
            pickupMotor.setNonTimedOperation();
        }
        lastShootingMode = stateRegistry.isShooting();
    }
}
