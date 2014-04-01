package us.in.k12.taylor.robotics.robot2014.handlers;

import edu.wpi.first.wpilibj.Joystick;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.Potentiometer;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class ShoulderManualPositionHandler implements RobotParameters {
    private final ComponentRegistry componentRegistry;
    private final StateRegistry stateRegistry;
    private final TitanSpeedController shoulderMotor;
    private final Joystick operationJoystick;
    Potentiometer shoulderPotentiometer;
    private boolean stopped;

    public ShoulderManualPositionHandler(TitanRobot pRobot) {
        componentRegistry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        operationJoystick = componentRegistry.getOperatorJoystick();
        shoulderMotor = componentRegistry.getShoulderMotor();
        shoulderPotentiometer = componentRegistry.getShoulderPotentiometer();
        stopped = true;
    }

    public void run() {
        if (stateRegistry.getShoulderPositionMode() == SHOULDER_JOYSTICK_MODE) {
            double arm = shoulderPotentiometer.getRatio();
            double y = operationJoystick.getY();
            double speed = y * y;
//                System.out.println("" + arm + " " + speed);
            if (y > 0.05) {
                stopped = false;
                shoulderMotor.set(speed);
            }
            else if (y < -0.05) {
//            else if (y < 0.05) {
//                System.out.println("Speed: " + speed);
                shoulderMotor.set(-speed);
                stopped = false;
            }
            else if (arm > 0.50) {
//                System.out.println("X" + arm);
                shoulderMotor.set(0.15);
            }
            else if (!stopped) {
                shoulderMotor.set(0.0);
                stopped = true;
            }
        }
    }
}
