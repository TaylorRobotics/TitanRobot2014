package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class HammerButtonHandler implements RobotParameters {
    private final ComponentRegistry componentRegistry;
    private final StateRegistry stateRegistry;
    private final JoystickButton hammerFireButton;
    private final JoystickButton hammerLatchButton;
    private final TitanSpeedController hammerMotor;

    public HammerButtonHandler(TitanRobot pRobot) {
        componentRegistry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        hammerMotor = componentRegistry.getHammerMotor();
        hammerFireButton = componentRegistry.getHammerFireButton();
        hammerLatchButton = componentRegistry.getHammerLatchButton();
    }

    public void run() {
        /* Run hammer motor based upon hammer buttons */
        if (hammerFireButton.isSwitchOn()) {
            if (hammerFireButton.getStateChange()) {
                stateRegistry.setShooting(true);
            }
        }
        else if (hammerFireButton.getStateChange()) {
                stateRegistry.setShooting(false);
        }
        if (hammerLatchButton.isSwitchOn()) {
            hammerMotor.set(HAMMER_LATCH_SPEED);
            stateRegistry.setShooting(false);
        }
        else if (hammerLatchButton.getStateChange()) {
            hammerMotor.set(0.0);
        }
    }
}
