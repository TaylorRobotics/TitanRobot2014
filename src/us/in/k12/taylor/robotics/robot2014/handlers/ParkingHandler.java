package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;

/**
 * @author Taylor Robotics 2014
 */
public class ParkingHandler implements RobotParameters {
    private final ComponentRegistry componentRegistry;
    private final StateRegistry stateRegistry;
    private boolean lastParkingMode;

    public ParkingHandler(TitanRobot pRobot) {
        componentRegistry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        lastParkingMode = false;
    }

    // Tasks = lower, lock hammer, raise to start position
    public void run() {
        if (stateRegistry.isParking()) {
            if (lastParkingMode) {
                // Place starting tasks here
            }
            else {
                // Place continuing tasks here
            }
        }
        else if (lastParkingMode) {
            /* Stop parking process */
        }
        lastParkingMode = stateRegistry.isShooting();
    }
}
