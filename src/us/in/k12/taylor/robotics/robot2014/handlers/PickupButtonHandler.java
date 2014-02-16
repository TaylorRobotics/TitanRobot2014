package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;
import us.in.k12.taylor.robotics.robot2014.components.Switch;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robtics 2014
 */
public class PickupButtonHandler implements RobotParameters {
    private final ComponentRegistry componentRegistry;
    private final StateRegistry stateRegistry;

    private final JoystickButton pickupButton;
    private final JoystickButton reversePickupButton;
    private final TitanSpeedController pickupMotor;
    private final Switch pickupStopSwitch;
    private boolean lastStopSwitchState;

    public PickupButtonHandler(TitanRobot pRobot) {
        componentRegistry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();

        pickupMotor = componentRegistry.getPickupMotor();
        pickupStopSwitch = componentRegistry.getPickupStopSwitch();
        pickupButton = componentRegistry.getPickupButton();
        reversePickupButton = componentRegistry.getReversePickupButton();
        lastStopSwitchState = pickupStopSwitch.isSwitchOn();
    }

    public void run() {
        /* Reverse pickup motor when reverse pickup button is pressed */
        if (reversePickupButton.isSwitchOn()) {
            pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
            stateRegistry.setKeepBallMode(false);
            lastStopSwitchState = false;
        }
        else if (reversePickupButton.getStateChange()) {
            // Just released so stop motor
            pickupMotor.set(0.0);
        }
        /* Run pickup motor when pickup button is pressed */
        else if (pickupButton.isSwitchOn()) {
            pickupMotor.setNonTimedOperation();
            pickupMotor.set(PICKUP_MOTOR_SPEED);
            if (pickupMotor.isHardLimitReached()) {
                stateRegistry.setKeepBallMode(true);
                lastStopSwitchState = true;
            }
            else {
                stateRegistry.setKeepBallMode(false);
                lastStopSwitchState = false;
            }
        }
        else if (stateRegistry.isKeepBallMode()) {
            if (!pickupStopSwitch.isSwitchOn() && lastStopSwitchState) {
                pickupMotor.setTimedOperation(PICKUP_TRY_TIME);
                lastStopSwitchState = false;
            }
            pickupMotor.set(PICKUP_MOTOR_KEEP_BALL_SPEED);
            if (pickupMotor.isLimitReached()) {
                lastStopSwitchState = true;
                if (pickupMotor.isTimeLimitReached()) {
                    stateRegistry.setKeepBallMode(false);
                }
                pickupMotor.setNonTimedOperation();
                pickupMotor.set(0.0);
            }
        }
        else if (pickupButton.getStateChange()) {
            pickupMotor.setNonTimedOperation();
            pickupMotor.set(0.0);
        }
    }
}
