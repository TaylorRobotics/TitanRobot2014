package us.in.k12.taylor.robotics.robot2014.handlers;

import edu.wpi.first.wpilibj.Joystick;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;

/**
 * @author Taylor Robotics 2014
 */
public class DriveDirectionButtonHandler implements RobotParameters {
    RobotRegistry registry;
    JoystickButton reverseDirectionButton;

    public DriveDirectionButtonHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        Joystick leftDriveJoystick = registry.getLeftDriveJoystick();
        reverseDirectionButton = new JoystickButton(leftDriveJoystick, REVERSE_DRIVE_DIRECTION_BUTTON, false);
    }

    public void run() {
        /* Reverse drive direction on button push */
        if (reverseDirectionButton.getStateChange() && reverseDirectionButton.isButtonPushed()) {
            if (registry.getDriveDirection() == FORWARD) {
                registry.setDriveDirection(REVERSE);
            }
            else {
                registry.setDriveDirection(FORWARD);
            }
        }
    }
}
