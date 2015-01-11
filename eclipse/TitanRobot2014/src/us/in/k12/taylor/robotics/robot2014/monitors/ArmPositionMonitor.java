package us.in.k12.taylor.robotics.robot2014.monitors;

import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.MessageDisplay;
import us.in.k12.taylor.robotics.robot2014.components.Potentiometer;

/**
 * @author Taylor Robotics 2014
 */
public class ArmPositionMonitor implements RobotParameters {
    Potentiometer shoulderPotentiometer;
    MessageDisplay messageDisplay;
    double lastPosition;

    public ArmPositionMonitor(TitanRobot pRobot) {
        ComponentRegistry componentRegistry = pRobot.getComponentRegistry();
        shoulderPotentiometer = componentRegistry.getShoulderPotentiometer();
        messageDisplay = componentRegistry.getMessageDisplay();
        lastPosition = 0.0;
        update(true);
    }

    public boolean update() {
        return update(false);
    }

    private boolean update(boolean pForce) {
        boolean changed = false;
        double currentPosition = ((int)(shoulderPotentiometer.getValue() * 100)) / 100.0;
        double currentRatio = ((int)(shoulderPotentiometer.getRatio()* 1000)) / 1000.0;
        if (pForce || (lastPosition != currentPosition)) {
            messageDisplay.setLine(4, "Arm: " + currentPosition + " (" + currentRatio + ")   ");
            lastPosition = currentPosition;
            changed = true;
        }
        return changed;
    }
}
