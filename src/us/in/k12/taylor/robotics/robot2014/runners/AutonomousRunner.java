package us.in.k12.taylor.robotics.robot2014.runners;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.Switch;
import us.in.k12.taylor.robotics.robot2014.components.TimeLimit;

/**
 * @author Taylor Robotics 2014
 */
public class AutonomousRunner implements RobotParameters {
    private final TitanRobot robot;
    private final RobotRegistry registry;
    private final Switch leftToggleSwitch;
    private final Switch rightToggleSwitch;
    private final RobotDrive robotDrive;

    public AutonomousRunner(TitanRobot pRobot) {
        robot = pRobot;
        registry = robot.getRegistry();
        leftToggleSwitch = registry.getLeftAutonomousModeSwitch();
        rightToggleSwitch = registry.getRightAutonomousModeSwitch();
        robotDrive = registry.getRobotDrive();
    }

    public void run() {
        if (robot.isAutonomous() && robot.isEnabled()) {
            int autonomousMode = getAutonomousMode();
            switch (autonomousMode) {
                case 0:
                    runAutonomousMode1();
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

    private void runAutonomousMode1() {
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

    private void runAutonomousMode2() {
        
    }

    private void runAutonomousMode3() {
        
    }

    private void runAutonomousMode4() {
        
    }
}
