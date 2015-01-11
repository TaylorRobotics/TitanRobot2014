package us.in.k12.taylor.robotics.robot2014.runners;

import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.Switch;
import us.in.k12.taylor.robotics.robot2014.components.TimeLimit;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

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
    private final TitanSpeedController shoulderMotor;

    public AutonomousRunner(TitanRobot pRobot) {
        robot = pRobot;
        componentRegistry = robot.getComponentRegistry();
        leftToggleSwitch = componentRegistry.getLeftAutonomousModeSwitch();
        rightToggleSwitch = componentRegistry.getRightAutonomousModeSwitch();
        robotDrive = componentRegistry.getRobotDrive();
        pickupMotor = componentRegistry.getPickupMotor();
        hammerMotor = componentRegistry.getHammerMotor();
        shoulderMotor = componentRegistry.getShoulderMotor();
    }

    public void run() {
        if (robot.isAutonomous() && robot.isEnabled()) {
            int autonomousMode = getAutonomousMode();
            switch (autonomousMode) {
                case 0:
                    runDeadAutonomousMode();
                    break;
                case 1:
                    runAutonomousMode2(true);
                    break;
                case 2:
                    runAutonomousMode2(false);
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

    private void runAutonomousMode2(boolean pShoot) {
        /* Keep shoulder up, pull in ball and keep */
        for (int count = 0; count < 550; count++) {
            if (robot.isAutonomous() && robot.isEnabled()) {
                shoulderMotor.set(0.4);
                pickupMotor.set(PICKUP_MOTOR_SPEED);
                if (pickupMotor.isHardLimitReached()) {
                    break;
                }
                robotDrive.drive(0.0, 0.0);

                /* Feed watchdog to prevent shutdown and then wait */
//                Watchdog.getInstance().feed();
                Timer.delay(AUTONOMOUS_LOOP_DELAY);
            }
        }
// was 175
        /* Keep shoulder up and drive forward */
        for (int count = 0; count < 300; count++) {
            if (robot.isAutonomous() && robot.isEnabled()) {
                shoulderMotor.set(0.4);
                pickupMotor.set(PICKUP_MOTOR_SPEED);
                robotDrive.drive(-0.4, 0.0);

                /* Feed watchdog to prevent shutdown and then wait */
//                Watchdog.getInstance().feed();
                Timer.delay(AUTONOMOUS_LOOP_DELAY);
            }
        }

        /* Stop drive and keep shoulder up and ball */
        for (int count = 0; count < 100; count++) {
            if (robot.isAutonomous() && robot.isEnabled()) {
                shoulderMotor.set(0.15);
                pickupMotor.set(PICKUP_MOTOR_SPEED);
                robotDrive.drive(0.0, 0.0);
            }
        }

        /* Keep shoulder up and roll ball out */
        pickupMotor.setNonTimedOperation();
        for (int count = 0; count < 75; count++) {
            if (robot.isAutonomous() && robot.isEnabled()) {
                shoulderMotor.set(0.15);
                pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
                robotDrive.drive(0.0, 0.0);
            }
        }

        /* Shoot the ball */
        pickupMotor.setTimedOperation(PICKUP_MOTOR_FIRE_TIME);
        for (int count = 0; count < 500; count++) {
            if (robot.isAutonomous() && robot.isEnabled()) {
                shoulderMotor.set(0.15);
                if (pShoot) {
                    pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
                    hammerMotor.set(HAMMER_FIRE_SPEED);
                }
                robotDrive.drive(0.0, 0.0);

                /* Feed watchdog to prevent shutdown and then wait */
//                Watchdog.getInstance().feed();
                Timer.delay(AUTONOMOUS_LOOP_DELAY);
            }
        }

        /* Stop all motors */
        while (robot.isAutonomous() && robot.isEnabled()) {
            shoulderMotor.set(0.0);
            pickupMotor.set(0.0);
            hammerMotor.set(0.0);
            robotDrive.drive(0.0, 0.0);

            /* Feed watchdog to prevent shutdown and then wait */
//            Watchdog.getInstance().feed();
            Timer.delay(AUTONOMOUS_LOOP_DELAY);
        }
    }

    private void runAutonomousMode3() {
        for (int count = 0; count < 500; count++) {
            pickupMotor.set(PICKUP_MOTOR_SPEED);
            if (pickupMotor.isHardLimitReached()) {
                break;
            }
            robotDrive.drive(0.0, 0.0);

            /* Feed watchdog to prevent shutdown and then wait */
//            Watchdog.getInstance().feed();
            Timer.delay(AUTONOMOUS_LOOP_DELAY);
        }

        for (int count = 0; count < 500; count++) {
            if (!robot.isAutonomous() || !robot.isEnabled()) {
                break;
            }
            pickupMotor.set(PICKUP_MOTOR_SPEED);
            robotDrive.drive(-0.4, 0.0);

            /* Feed watchdog to prevent shutdown and then wait */
//            Watchdog.getInstance().feed();
            Timer.delay(AUTONOMOUS_LOOP_DELAY);
        }

        for (int count = 0; count < 75; count++) {
            if (!robot.isAutonomous() || !robot.isEnabled()) {
                break;
            }
            robotDrive.drive(0.0, 0.0);
            pickupMotor.setTimedOperation(PICKUP_MOTOR_FIRE_TIME);
            pickupMotor.set(PICKUP_MOTOR_FIRE_SPEED);
            hammerMotor.set(HAMMER_FIRE_SPEED);

            /* Feed watchdog to prevent shutdown and then wait */
//            Watchdog.getInstance().feed();
            Timer.delay(AUTONOMOUS_LOOP_DELAY);
        }

        while (robot.isAutonomous() && robot.isEnabled()) {
            robotDrive.drive(0.0, 0.0);
            hammerMotor.set(0.0);
            pickupMotor.set(0.0);

            /* Feed watchdog to prevent shutdown and then wait */
//            Watchdog.getInstance().feed();
            Timer.delay(AUTONOMOUS_LOOP_DELAY);
        }
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
//        Watchdog.getInstance().feed();
    }
}
