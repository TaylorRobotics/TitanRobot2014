package us.in.k12.taylor.robotics.robot2014.handlers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;

/**
 * @author Taylor Robotics 2014
 */
public class DriveSpeedButtonHandler implements RobotParameters {
    RobotRegistry registry;
    JoystickButton lowSpeedButton;
    JoystickButton mediumSpeedButton;
    JoystickButton highSpeedButton;
    JoystickButton speedBoostButton;
    JoystickButton speedDragButton;
    RobotDrive robotDrive;

    public DriveSpeedButtonHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        robotDrive = registry.getRobotDrive();
        Joystick rightDriveJoystick = registry.getRightDriveJoystick();
        Joystick leftDriveJoystick = registry.getLeftDriveJoystick();

        speedDragButton = new JoystickButton(leftDriveJoystick, SPEED_DRAG_BUTTON, false);
        speedBoostButton = new JoystickButton(rightDriveJoystick, SPEED_BOOST_BUTTON, false);

        lowSpeedButton = new JoystickButton(rightDriveJoystick, LOW_SPEED_BUTTON, false);
        mediumSpeedButton = new JoystickButton(rightDriveJoystick, MEDIUM_SPEED_BUTTON, true);
        highSpeedButton = new JoystickButton(rightDriveJoystick, HIGH_SPEED_BUTTON, false);
    }

    public void run() {
        double maxDriveSpeed;
        double boostDragSpeed;
 
        /* Check speed buttons and set the drivespeed */
        if (lowSpeedButton.getStateChange() && lowSpeedButton.isButtonPushed()) {
            registry.setDriveSpeedMode(LOW_SPEED);
        }
        else if (mediumSpeedButton.getStateChange() && mediumSpeedButton.isButtonPushed()) {
            registry.setDriveSpeedMode(MEDIUM_SPEED);
        }
        else if (highSpeedButton.getStateChange() && highSpeedButton.isButtonPushed()) {
            registry.setDriveSpeedMode(HIGH_SPEED);
        }

        switch (registry.getDriveSpeedMode()) {
            case LOW_SPEED:
                maxDriveSpeed = MAX_LOW_SPEED;
                break;
            case MEDIUM_SPEED:
                maxDriveSpeed = MAX_MEDIUM_SPEED;
                break;
            default:
                maxDriveSpeed = MAX_HIGH_SPEED;
                break;
        }

        /* Check if boost or drag buttons are pushed */
        if (speedBoostButton.isButtonPushed()) {
            registry.setSpeedBoostDrag(SPEED_BOOST_ON);
            boostDragSpeed = BOOST_SPEED;
        }
        else if (speedDragButton.isButtonPushed()) {
            registry.setSpeedBoostDrag(SPEED_DRAG_ON);
            boostDragSpeed = DRAG_SPEED;
        }
        else {
            registry.setSpeedBoostDrag(SPEED_BOOST_DRAG_OFF);
            boostDragSpeed = 0.0;
        }
        robotDrive.setMaxOutput(maxDriveSpeed + boostDragSpeed);
    }
}
