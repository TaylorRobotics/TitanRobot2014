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
    private final ComponentRegistry componentRegistry;
    private final StateRegistry stateRegistry;
    private final TeleOperatedRunner teleOperatedRunner;
    private final AutonomousRunner autonomousRunner;
    private final TestRunner testRunner;

    /**
     * Constructs a TitanRobot object.
     */
    public TitanRobot() {
        System.out.println("Creating TitanRobot instance for 2014.");
        componentRegistry = new ComponentRegistry();
        stateRegistry = new StateRegistry();
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
     * @return The Singleton TitanRobot instance
     */
    public static TitanRobot getInstance() {
        return robotInstance;
    }

    /**
     * Gets the ComponentRegistry used to store robot components.
     * @return The Singleton instance of the ComponentRegistry
     */
    public ComponentRegistry getComponentRegistry() {
        return componentRegistry;
    }

    /**
     * Gets the StateRegistry used to store robot state information.
     * @return The Singleton instance of the StateRegistry
     */
    public StateRegistry getStateRegistry() {
        return stateRegistry;
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
