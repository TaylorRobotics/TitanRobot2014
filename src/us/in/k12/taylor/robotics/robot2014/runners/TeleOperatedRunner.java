package us.in.k12.taylor.robotics.robot2014.runners;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;
import us.in.k12.taylor.robotics.robot2014.handlers.DriveDirectionButtonHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.DriveSpeedButtonHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.PickupButtonHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.ShoulderControlHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.TankDriveHandler;

/**
 * @author Taylor Robotics 2014
 */
public class TeleOperatedRunner implements RobotParameters {

    private final TitanRobot robot;
    private final RobotRegistry registry;
    private final DriveDirectionButtonHandler directionButtonHandler;
    private final DriveSpeedButtonHandler speedButtonHandler;
    private final PickupButtonHandler pickupButtonHandler;
    private final ShoulderControlHandler shoulderControlHandler;

    /* Handlers */
    TankDriveHandler tankDriveHandler;

    public TeleOperatedRunner(TitanRobot pRobot) {
        robot = pRobot;
        registry = robot.getRegistry();

        /* Create handlers */
        tankDriveHandler = new TankDriveHandler(robot);
        directionButtonHandler = new DriveDirectionButtonHandler(robot);
        speedButtonHandler = new DriveSpeedButtonHandler(robot);
        pickupButtonHandler = new PickupButtonHandler(robot);
        shoulderControlHandler = new ShoulderControlHandler(robot);
    }

    public void run() {
        /* Run in teleoperated loop as long as robot is enabled */
        while (robot.isOperatorControl() && robot.isEnabled()) {
            /* Handle operations */
            directionButtonHandler.run();
            speedButtonHandler.run();
            registry.getRobotDrive().drive(0.0, 0);
//            tankDriveHandler.run();

            pickupButtonHandler.run();

            shoulderControlHandler.run();

            /* Feed watchdog to prevent shutdown and then wait */
            Watchdog.getInstance().feed();
            Timer.delay(TELEOPERATED_LOOP_DELAY);
        }
        registry.getRobotDrive().drive(0.0, 0);
    }
}
