package us.in.k12.taylor.robotics.robot2014.runners;

import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;

/**
 * @author Taylor Robotics 2014
 */
public class TestRunner implements RobotParameters {

    private final TitanRobot robot;
    private final ComponentRegistry componentRegistry;

    public TestRunner(TitanRobot pRobot) {
        robot = pRobot;
        componentRegistry = robot.getComponentRegistry();
    }

    public void run() {
    }
}
