package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.Potentiometer;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class ShoulderControlHandler implements RobotParameters {
    private final RobotRegistry registry;
    private final Potentiometer shoulderPotentiometer;
    private final TitanSpeedController shoulderMotor;

    public ShoulderControlHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        shoulderPotentiometer = registry.getShoulderPotentiometer();
        shoulderMotor = registry.getShoulderMotor();
    }

    public void run() {
//        System.out.println(shoulderPotentiometer.getRatio() + " " + shoulderPotentiometer.getValue());
//        shoulderMotor.set(shoulderPotentiometer.getRatio());
    }
}
