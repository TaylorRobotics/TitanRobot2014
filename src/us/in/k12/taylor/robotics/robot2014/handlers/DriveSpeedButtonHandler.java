package us.in.k12.taylor.robotics.robot2014.handlers;

import edu.wpi.first.wpilibj.RobotDrive;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;

/**
 * @author Taylor Robotics 2014
 */
public class DriveSpeedButtonHandler implements RobotParameters {
    private final ComponentRegistry registry;
    private final StateRegistry stateRegistry;
    private final JoystickButton lowSpeedButton;
    private final JoystickButton mediumSpeedButton;
    private final JoystickButton highSpeedButton;
    private final JoystickButton speedBoostButton;
    private final JoystickButton speedDragButton;
    private final RobotDrive robotDrive;

    public DriveSpeedButtonHandler(TitanRobot pRobot) {
        registry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
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
            stateRegistry.setDriveSpeedMode(LOW_SPEED);
        }
        else if (mediumSpeedButton.isSwitchOn() && mediumSpeedButton.getStateChange()) {
            stateRegistry.setDriveSpeedMode(MEDIUM_SPEED);
        }
        else if (highSpeedButton.isSwitchOn() && highSpeedButton.getStateChange()) {
            stateRegistry.setDriveSpeedMode(HIGH_SPEED);
        }

        switch (stateRegistry.getDriveSpeedMode()) {
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
            stateRegistry.setSpeedBoostDrag(SPEED_BOOST_ON);
            boostDragSpeed = BOOST_SPEED;
        }
        else if (speedDragButton.isSwitchOn()) {
            stateRegistry.setSpeedBoostDrag(SPEED_DRAG_ON);
            boostDragSpeed = DRAG_SPEED;
        }
        else {
            stateRegistry.setSpeedBoostDrag(SPEED_BOOST_DRAG_OFF);
            boostDragSpeed = 0.0;
        }
        robotDrive.setMaxOutput(maxDriveSpeed + boostDragSpeed);
    }
}
