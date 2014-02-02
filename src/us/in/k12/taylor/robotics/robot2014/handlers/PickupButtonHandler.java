package us.in.k12.taylor.robotics.robot2014.handlers;

import edu.wpi.first.wpilibj.Joystick;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;
import us.in.k12.taylor.robotics.robot2014.components.Switch;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robtics 2014
 */
public class PickupButtonHandler implements RobotParameters {
    RobotRegistry registry;
    JoystickButton pickupButton;
    TitanSpeedController pickupMotor;
    Switch pickupStopSwitch;
    boolean lastStopSwitchState;

    public PickupButtonHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        pickupMotor = registry.getPickupMotor();
        pickupStopSwitch = registry.getPickupStopSwitch();
        Joystick operatorJoystick = registry.getOperatorJoystick();
        pickupButton = new JoystickButton(operatorJoystick, PICKUP_BUTTON, false);
        lastStopSwitchState = pickupStopSwitch.isSwitchOn();
    }

    public void run() {
        /* Run pickup motor when pickup button is pressed */
        if (pickupButton.isButtonPushed()) {
            pickupMotor.setNonTimedOperation();
            pickupMotor.set(PICKUP_MOTOR_SPEED);
            if (pickupMotor.isHardLimitReached()) {
                registry.setKeepBallMode(true);
                lastStopSwitchState = true;
            }
            else {
                registry.setKeepBallMode(false);
                lastStopSwitchState = false;
            }
        }
        else if (registry.isKeepBallMode()) {
            if (!pickupStopSwitch.isSwitchOn() && lastStopSwitchState) {
                pickupMotor.setTimedOperation(PICKUP_TRY_TIME);
                lastStopSwitchState = false;
            }
            pickupMotor.set(PICKUP_MOTOR_SPEED);
            if (pickupMotor.isLimitReached()) {
                lastStopSwitchState = true;
                if (pickupMotor.isTimeLimitReached()) {
                    registry.setKeepBallMode(false);
                }
                pickupMotor.setNonTimedOperation();
                pickupMotor.set(0.0);
            }
        }
        else {
            pickupMotor.setNonTimedOperation();
            pickupMotor.set(0.0);
        }
    }
}
