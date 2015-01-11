package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;

/**
 * @author Taylor Robtics 2014
 */
public class ParkButtonHandler implements RobotParameters {
    private final ComponentRegistry componentRegistry;
    private final StateRegistry stateRegistry;
    private final JoystickButton parkButton;

    public ParkButtonHandler(TitanRobot pRobot) {
        componentRegistry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        parkButton = componentRegistry.getParkRobotButton();
    }

    public void run() {
        /* Run pickup motor when pickup button is pressed */
        if (parkButton.isSwitchOn() && parkButton.getStateChange()) {
            stateRegistry.setParking(true);
        }
    }
}
