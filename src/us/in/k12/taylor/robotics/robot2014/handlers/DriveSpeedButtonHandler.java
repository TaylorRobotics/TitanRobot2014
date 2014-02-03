package us.in.k12.taylor.robotics.robot2014.handlers;

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

        speedDragButton = registry.getSpeedDragButton();
        speedBoostButton = registry.getSpeedBoostButton();

        lowSpeedButton = registry.getLowSpeedButton();
        mediumSpeedButton = registry.getMediumSpeedButton();
        highSpeedButton = registry.getHighSpeedButton();
    }

    public void run() {
        double maxDriveSpeed;
        double boostDragSpeed;
 
        /* Check speed buttons and set the drivespeed */
        if (lowSpeedButton.isSwitchOn() && lowSpeedButton.getStateChange()) {
            registry.setDriveSpeedMode(LOW_SPEED);
        }
        else if (mediumSpeedButton.isSwitchOn() && mediumSpeedButton.getStateChange()) {
            registry.setDriveSpeedMode(MEDIUM_SPEED);
        }
        else if (highSpeedButton.isSwitchOn() && highSpeedButton.getStateChange()) {
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
        if (speedBoostButton.isSwitchOn()) {
            registry.setSpeedBoostDrag(SPEED_BOOST_ON);
            boostDragSpeed = BOOST_SPEED;
        }
        else if (speedDragButton.isSwitchOn()) {
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
