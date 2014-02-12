package us.in.k12.taylor.robotics.robot2014.handlers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;

/**
 * @author Taylor Robotics 2014
 */
public class TankDriveHandler implements RobotParameters {
    private final ComponentRegistry registry;
    private final StateRegistry stateRegistry;
    private final RobotDrive robotDrive;

    private final Joystick leftDriveJoystick;
    private final Joystick rightDriveJoystick;

    public TankDriveHandler(TitanRobot pRobot) {
        registry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        robotDrive = registry.getRobotDrive();

        leftDriveJoystick = registry.getLeftDriveJoystick();
        rightDriveJoystick = registry.getRightDriveJoystick();
    }

    public void run() {
        /* Call tank drive after adjusting for drive direction */
        if (stateRegistry.getDriveDirection() == REVERSE) {
            robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
            robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
            robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
            robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
            robotDrive.tankDrive(rightDriveJoystick, leftDriveJoystick);
        }
        else {
            robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
            robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
            robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
            robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
            robotDrive.tankDrive(leftDriveJoystick, rightDriveJoystick);
        }
    }
}
