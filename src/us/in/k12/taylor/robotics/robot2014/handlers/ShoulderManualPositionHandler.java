package us.in.k12.taylor.robotics.robot2014.handlers;

import edu.wpi.first.wpilibj.Joystick;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class ShoulderManualPositionHandler implements RobotParameters {
    private final ComponentRegistry registry;
    private final StateRegistry stateRegistry;
    private final TitanSpeedController shoulderMotor;
    private final Joystick operationJoystick;
    private boolean stopped;

    public ShoulderManualPositionHandler(TitanRobot pRobot) {
        registry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        operationJoystick = registry.getOperatorJoystick();
        shoulderMotor = registry.getShoulderMotor();
        stopped = true;
    }

    public void run() {
        if (stateRegistry.getShoulderPositionMode() == SHOULDER_JOYSTICK_MODE) {
            double y = operationJoystick.getY();
            double speed = y * y;
            if (y > 0.05) {
                stopped = false;
                shoulderMotor.set(speed);
            }
            else if (y < 0.05) {
                shoulderMotor.set(-speed);
                stopped = false;
            }
            else if (!stopped) {
                shoulderMotor.set(0.0);
                stopped = true;
            }
        }
    }
}
