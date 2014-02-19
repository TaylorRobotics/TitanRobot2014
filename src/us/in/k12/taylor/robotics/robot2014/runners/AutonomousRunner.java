package us.in.k12.taylor.robotics.robot2014.runners;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.Switch;
import us.in.k12.taylor.robotics.robot2014.components.TimeLimit;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class AutonomousRunner implements RobotParameters {
    private final TitanRobot robot;
    private final ComponentRegistry componentRegistry;
    private final Switch leftToggleSwitch;
    private final Switch rightToggleSwitch;
    private final RobotDrive robotDrive;
    private final TitanSpeedController pickupMotor;
    private final TitanSpeedController hammerMotor;

    public AutonomousRunner(TitanRobot pRobot) {
        robot = pRobot;
        componentRegistry = robot.getComponentRegistry();
        leftToggleSwitch = componentRegistry.getLeftAutonomousModeSwitch();
        rightToggleSwitch = componentRegistry.getRightAutonomousModeSwitch();
        robotDrive = componentRegistry.getRobotDrive();
        pickupMotor = componentRegistry.getPickupMotor();
        hammerMotor = componentRegistry.getHammerMotor();
    }

    public void run() {
        if (robot.isAutonomous() && robot.isEnabled()) {
            int autonomousMode = getAutonomousMode();
            switch (autonomousMode) {
                case 0:
                    runDeadAutonomousMode();
                    break;
                case 1:
                    runAutonomousMode2();
                    break;
                case 2:
                    runAutonomousMode3();
                    break;
                case 3:
                    runAutonomousMode4();
                    break;
            }
        }
    }

    private int getAutonomousMode() {
        int autonomousMode = 0;
        if (rightToggleSwitch.isSwitchOn()) {
            autonomousMode |= 0x1;
        }
        if (leftToggleSwitch.isSwitchOn()) {
            autonomousMode |= 0x2;
        }
        return autonomousMode;
    }

    private void runDeadAutonomousMode() {
        // Dead mode, do nothing
    }

    private void runAutonomousMode2() {
        for (int count = 0; count < 1000; count++) {
            pickupMotor.set(PICKUP_MOTOR_SPEED);
            if (pickupMotor.isHardLimitReached()) {
                break;
            }

            /* Feed watchdog to prevent shutdown and then wait */
            Watchdog.getInstance().feed();
            Timer.delay(AUTONOMOUS_LOOP_DELAY);
        }

        for (int count = 0; count < 1000; count++) {
            pickupMotor.set(PICKUP_MOTOR_SPEED);
            robotDrive.drive(0.5, 1);

            /* Feed watchdog to prevent shutdown and then wait */
            Watchdog.getInstance().feed();
            Timer.delay(AUTONOMOUS_LOOP_DELAY);
        }
        robotDrive.drive(0.0, 1);

        for (int count = 0; count < 500; count++) {
            pickupMotor.setTimedOperation(PICKUP_MOTOR_FIRE_TIME);
            pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
            hammerMotor.set(HAMMER_FIRE_SPEED);

            /* Feed watchdog to prevent shutdown and then wait */
            Watchdog.getInstance().feed();
            Timer.delay(AUTONOMOUS_LOOP_DELAY);
        }
        hammerMotor.set(0.0);
        pickupMotor.set(0.0);
    }

    private void runAutonomousMode3() {
        // Set keep mode
        // Move forward for x seconds
        // Lower arm to shoot position
        // Shoot
    }

    private void runAutonomousMode4() {
        /* Drive 0.75 for 3 seconds */
        TimeLimit timeLimit = new TimeLimit(300);
        while (robot.isEnabled() && !timeLimit.isTimeLimitReached()) {
            drive(0.75, 0);
            Timer.delay(AUTONOMOUS_LOOP_DELAY);
        }

        /* Drive 0.50 for 3 seconds */
        timeLimit.setTimeLimit(300);
        while (robot.isEnabled() && !timeLimit.isTimeLimitReached()) {
            drive(0.50, 0);
            Timer.delay(AUTONOMOUS_LOOP_DELAY);
        }

        /* Stop Drive */
        drive(0.0, 0);
    }

    private void drive(double pSpeed, int pTurn) {
        boolean reverseLeftMotor = (FRONT_LEFT_DRIVE_MOTOR_DIRECTION == REVERSE);
        boolean reverseRightMotor = (FRONT_RIGHT_DRIVE_MOTOR_DIRECTION == REVERSE);

        /* Drive after adjusting for drive direction */
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, !reverseLeftMotor);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, !reverseRightMotor);

        robotDrive.drive(pSpeed, pTurn);
        Watchdog.getInstance().feed();
    }
}
