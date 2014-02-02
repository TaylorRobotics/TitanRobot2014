package us.in.k12.taylor.robotics.robot2014.runners;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;

/**
 * @author Taylor Robotics 2014
 */
public class TestRunner implements RobotParameters {

    private final TitanRobot robot;
    private final RobotRegistry registry;

    public TestRunner(TitanRobot pRobot) {
        robot = pRobot;
        registry = robot.getRegistry();
    }

    public void run() {
    }
}
