package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;

/**
 * @author Taylor Robotics 2014
 */
public class DriveDirectionButtonHandler implements RobotParameters {
    private final ComponentRegistry registry;
    private final StateRegistry stateRegistry;
    private final JoystickButton reverseDirectionButton;

    public DriveDirectionButtonHandler(TitanRobot pRobot) {
        registry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        reverseDirectionButton = registry.getReverseDirectionButton();
    }

    public void run() {
        /* Reverse drive direction on button push */
        if (reverseDirectionButton.isSwitchOn() && reverseDirectionButton.getStateChange()) {
            if (stateRegistry.getDriveDirection() == FORWARD) {
                stateRegistry.setDriveDirection(REVERSE);
            }
            else {
                stateRegistry.setDriveDirection(FORWARD);
            }
        }
    }
}
