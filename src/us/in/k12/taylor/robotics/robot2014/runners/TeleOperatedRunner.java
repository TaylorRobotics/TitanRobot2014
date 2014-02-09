package us.in.k12.taylor.robotics.robot2014.runners;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;
import us.in.k12.taylor.robotics.robot2014.handlers.AutoShootHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.DriveDirectionButtonHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.DriveSpeedButtonHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.PickupButtonHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.ShootingDistanceLightHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.ShootingHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.ShoulderButtonHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.ShoulderManualPositionHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.ShoulderSeekPositionHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.ShoulderServoHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.TankDriveHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.TriggerButtonHandler;
import us.in.k12.taylor.robotics.robot2014.handlers.TriggerLockedLightHandler;

/**
 * @author Taylor Robotics 2014
 */
public class TeleOperatedRunner implements RobotParameters {
    private final TitanRobot robot;
    private final RobotRegistry registry;

    /* Handlers */
    TankDriveHandler tankDriveHandler;
    private final DriveDirectionButtonHandler directionButtonHandler;
    private final DriveSpeedButtonHandler speedButtonHandler;
    private final PickupButtonHandler pickupButtonHandler;
    private final ShoulderButtonHandler shoulderButtonHandler;
    private final ShoulderSeekPositionHandler shoulderSeekPositionHandler;
    private final ShoulderManualPositionHandler shoulderManualPositionHandler;
    private final ShoulderServoHandler shoulderServoHandler;
    private final TriggerButtonHandler triggerButtonHandler;
    private final AutoShootHandler autoShootHandler;
    private final ShootingHandler shootingHandler;
    private final ShootingDistanceLightHandler shootingDistanceLightHandler;
    private final TriggerLockedLightHandler triggerLockedLightHandler;

    public TeleOperatedRunner(TitanRobot pRobot) {
        robot = pRobot;
        registry = robot.getRegistry();

        /* Create handlers */
        tankDriveHandler = new TankDriveHandler(robot);
        directionButtonHandler = new DriveDirectionButtonHandler(robot);
        speedButtonHandler = new DriveSpeedButtonHandler(robot);
        pickupButtonHandler = new PickupButtonHandler(robot);
        shoulderButtonHandler = new ShoulderButtonHandler(robot);
        shoulderSeekPositionHandler = new ShoulderSeekPositionHandler(robot);
        shoulderManualPositionHandler = new ShoulderManualPositionHandler(robot);
        shoulderServoHandler = new ShoulderServoHandler(robot);
        triggerButtonHandler = new TriggerButtonHandler(robot);
        autoShootHandler = new AutoShootHandler(robot);
        shootingHandler = new ShootingHandler(robot);
        shootingDistanceLightHandler = new ShootingDistanceLightHandler(robot);
        triggerLockedLightHandler = new TriggerLockedLightHandler(robot);
    }

    public void run() {
        /* Run in teleoperated loop as long as robot is enabled */
        while (robot.isOperatorControl() && robot.isEnabled()) {
            /* Handle operations */
            directionButtonHandler.run();
            speedButtonHandler.run();
            registry.getRobotDrive().drive(0.0, 0);
            tankDriveHandler.run();

            pickupButtonHandler.run();

            /* Handle shoulder operations - order of handlers is important */
            shoulderButtonHandler.run();
            shoulderManualPositionHandler.run();
            shoulderSeekPositionHandler.run();
            shoulderServoHandler.run();

            /* Handle shooting operations */
            triggerButtonHandler.run();
            autoShootHandler.run();
            shootingHandler.run();

            /* Update indicator lights */
            shootingDistanceLightHandler.run();
            triggerLockedLightHandler.run();

            /* Feed watchdog to prevent shutdown and then wait */
            Watchdog.getInstance().feed();
            Timer.delay(TELEOPERATED_LOOP_DELAY);
        }
        registry.getRobotDrive().drive(0.0, 0);
    }
}
