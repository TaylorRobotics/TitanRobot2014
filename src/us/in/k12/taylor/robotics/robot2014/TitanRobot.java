package us.in.k12.taylor.robotics.robot2014;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Watchdog;
import us.in.k12.taylor.robotics.robot2014.runners.AutonomousRunner;
import us.in.k12.taylor.robotics.robot2014.runners.TeleOperatedRunner;
import us.in.k12.taylor.robotics.robot2014.runners.TestRunner;

/**
 * @author Taylor Robotics 2014
 */
public class TitanRobot extends SimpleRobot implements RobotParameters {
    private static TitanRobot robotInstance;
    private final RobotRegistry robotRegistry;
    private final TeleOperatedRunner teleOperatedRunner;
    private final AutonomousRunner autonomousRunner;
    private final TestRunner testRunner;

    /**
     * Constructs a TitanRobot object.
     */
    public TitanRobot() {
        System.out.println("Creating TitanRobot instance for 2014.");
        robotRegistry = new RobotRegistry();
        autonomousRunner = new AutonomousRunner(this);
        teleOperatedRunner = new TeleOperatedRunner(this);
        testRunner = new TestRunner(this);
        setInstance();
        Watchdog.getInstance().setEnabled(WATCHDOG_ENABLE);
        System.out.println("TitanRobot ready for operation.");
    }

    /**
     * Store running Robot instance in global variable.
     */
    private void setInstance() {
        robotInstance = this;
    }

    /**
     * Gets the Singleton instance of the TitanRobot object.
     */
    public static TitanRobot getInstance() {
        return robotInstance;
    }

    /**
     * Gets the RobotRegistry used to store components and state information.
     */
    public RobotRegistry getRegistry() {
        return robotRegistry;
    }

    public void robotInit() {
        System.out.println("Robot inited");
    }

    public void disabled() {
        System.out.println("Robot disabled");
    }

    /**
     * This method is automatically called each time the robot enters autonomous mode.
     */
    public void autonomous() {
        autonomousRunner.run();
    }

    /**
     * This method is automatically called each time the robot enters operator control.
     */
    public void operatorControl() {
        teleOperatedRunner.run();
    }
    
    /**
     * This method is automatically called each time the robot enters test mode.
     */
    public void test() {
        testRunner.run();
    }
}
