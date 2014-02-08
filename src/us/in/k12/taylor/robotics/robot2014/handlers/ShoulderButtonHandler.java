package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;

/**
 * @author Taylor Robtics 2014
 */
public class ShoulderButtonHandler implements RobotParameters {
    private final RobotRegistry registry;
    private final JoystickButton pickupPositionButton;
    private final JoystickButton lowShotPositionButton;
    private final JoystickButton highShotPositionButton;
    private final JoystickButton seekShotButton;
    private final JoystickButton startPositionButton;
    private final JoystickButton manualPositionButton;

    public ShoulderButtonHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        pickupPositionButton = registry.getPickupPositionButton();
        lowShotPositionButton = registry.getLowShotPositionButton();
        highShotPositionButton = registry.getHighShotPositionButton();
        seekShotButton = registry.getSeekShotButton();
        startPositionButton = registry.getStartPositionButton();
        manualPositionButton = registry.getManualPositionButton();
    }

    public void run() {
        if (manualPositionButton.isSwitchOn() && manualPositionButton.getStateChange()) {
            registry.setShoulderPositionMode(SHOULDER_JOYSTICK_MODE);
        }
        else if (pickupPositionButton.isSwitchOn() && pickupPositionButton.getStateChange()) {
            registry.setShoulderPositionMode(SHOULDER_SERVO_MODE);
            registry.setShoulderPositionTarget(SHOULDER_PICKUP_POSITION);
        }
        else if (lowShotPositionButton.isSwitchOn() && lowShotPositionButton.getStateChange()) {
            registry.setShoulderPositionMode(SHOULDER_SERVO_MODE);
            registry.setShoulderPositionTarget(SHOULDER_LOW_SHOT_POSITION);
        }
        else if (highShotPositionButton.isSwitchOn() && highShotPositionButton.getStateChange()) {
            registry.setShoulderPositionMode(SHOULDER_SERVO_MODE);
            registry.setShoulderPositionTarget(SHOULDER_HIGH_SHOT_POSITION);
        }
        else if (seekShotButton.isSwitchOn() && seekShotButton.getStateChange()) {
            // Add state SHOULDER_SEEK_MODE
            registry.setShoulderPositionMode(SHOULDER_SERVO_MODE);
            registry.setShoulderPositionTarget(SHOULDER_HIGH_SHOT_POSITION);
        }
        else if (startPositionButton.isSwitchOn() && startPositionButton.getStateChange()) {
            registry.setShoulderPositionMode(SHOULDER_SERVO_MODE);
            registry.setShoulderPositionTarget(SHOULDER_START_POSITION);
        }
    }
}
