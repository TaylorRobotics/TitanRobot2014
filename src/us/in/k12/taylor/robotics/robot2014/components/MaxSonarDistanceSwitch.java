package us.in.k12.taylor.robotics.robot2014.components;

/**
 * @author Taylor Robotics 2014
 */
public class MaxSonarDistanceSwitch extends Switch {
    private final MaxSonarDistanceSensor distanceSensor;
    private final double targetDistance;
    private final double tolerance;

    public MaxSonarDistanceSwitch(MaxSonarDistanceSensor pDistanceSensor, double pTargetDistance, double pTolerance, boolean pForceStateChange) {
        distanceSensor = pDistanceSensor;
        targetDistance = pTargetDistance;
        tolerance = pTolerance;
        setForceStateChange(pForceStateChange);
    }
    
    public boolean getSwitchState() {
        double distance = distanceSensor.getDistance();
        return (Math.abs(targetDistance - distance) <= tolerance);
    }
}
