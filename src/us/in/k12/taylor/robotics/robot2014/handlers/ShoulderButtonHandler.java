package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;

/**
 * @author Taylor Robtics 2014
 */
public class ShoulderButtonHandler implements RobotParameters {
    private final ComponentRegistry componentRegistry;
    private final StateRegistry stateRegistry;
    private final JoystickButton pickupPositionButton;
    private final JoystickButton lowShotPositionButton;
    private final JoystickButton highShotPositionButton;
    private final JoystickButton seekShotButton;
    private final JoystickButton startPositionButton;
    private final JoystickButton manualPositionButton;

    public ShoulderButtonHandler(TitanRobot pRobot) {
        componentRegistry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        pickupPositionButton = componentRegistry.getPickupPositionButton();
        lowShotPositionButton = componentRegistry.getLowShotPositionButton();
        highShotPositionButton = componentRegistry.getHighShotPositionButton();
        seekShotButton = componentRegistry.getSeekShotButton();
        startPositionButton = componentRegistry.getStartPositionButton();
        manualPositionButton = componentRegistry.getManualPositionButton();
    }

    public void run() {
        if (manualPositionButton.isSwitchOn() && manualPositionButton.getStateChange()) {
            stateRegistry.setShoulderPositionMode(SHOULDER_JOYSTICK_MODE);
        }
        else if (pickupPositionButton.isSwitchOn() && pickupPositionButton.getStateChange()) {
            stateRegistry.setShoulderPositionMode(SHOULDER_SERVO_MODE);
            stateRegistry.setShoulderPositionTarget(SHOULDER_PICKUP_POSITION);
        }
        else if (lowShotPositionButton.isSwitchOn() && lowShotPositionButton.getStateChange()) {
            stateRegistry.setShoulderPositionMode(SHOULDER_SERVO_MODE);
            stateRegistry.setShoulderPositionTarget(SHOULDER_LOW_SHOT_POSITION);
        }
        else if (highShotPositionButton.isSwitchOn() && highShotPositionButton.getStateChange()) {
            stateRegistry.setShoulderPositionMode(SHOULDER_SERVO_MODE);
            stateRegistry.setShoulderPositionTarget(SHOULDER_HIGH_SHOT_POSITION);
        }
        else if (seekShotButton.isSwitchOn() && seekShotButton.getStateChange()) {
            // Add state SHOULDER_SEEK_MODE
            stateRegistry.setShoulderPositionMode(SHOULDER_SERVO_MODE);
            stateRegistry.setShoulderPositionTarget(SHOULDER_HIGH_SHOT_POSITION);
        }
        else if (startPositionButton.isSwitchOn() && startPositionButton.getStateChange()) {
            stateRegistry.setShoulderPositionMode(SHOULDER_SERVO_MODE);
            stateRegistry.setShoulderPositionTarget(SHOULDER_START_POSITION);
        }
    }
}
