package us.in.k12.taylor.robotics.robot2014;


/**
 * @author Taylor Robotics 2014
 */
public class StateRegistry implements RobotParameters {
    private int driveDirection;
    private int driveSpeedMode; // Modes = LOW_SPEED, MEDIUM_SPEED, HIGH_SPEED (default)
    private int speedBoostDrag; // Modes = SPEED_BOOST_ON, SPEED_DRAG_ON, SPEED_BOOST_DRAG_OFF (default)
    private boolean keepBallMode;
    private double shoulderPositionTarget;
    private int shoulderPositionMode;
    private boolean shooting;
    private boolean parking;

    public StateRegistry() {
        /* Set intitial/default states */
        driveDirection = DEFAULT_DRIVE_DIRECTION;
        driveSpeedMode = MEDIUM_SPEED;
        speedBoostDrag = SPEED_BOOST_DRAG_OFF;
        keepBallMode = false;
        shoulderPositionTarget = -1.0;
        shoulderPositionMode = SHOULDER_JOYSTICK_MODE;
        shooting = false;
        parking = false;
    }

    public boolean isKeepBallMode() {
        return keepBallMode;
    }

    public void setKeepBallMode(boolean keepBallMode) {
        this.keepBallMode = keepBallMode;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public int getDriveDirection() {
        return driveDirection;
    }

    public void setDriveDirection(int driveDirection) {
        this.driveDirection = driveDirection;
    }

    public int getDriveSpeedMode() {
        return driveSpeedMode;
    }

    public void setDriveSpeedMode(int driveSpeedMode) {
        this.driveSpeedMode = driveSpeedMode;
    }

    public int getSpeedBoostDrag() {
        return speedBoostDrag;
    }

    public void setSpeedBoostDrag(int speedBoostDrag) {
        this.speedBoostDrag = speedBoostDrag;
    }

    public double getShoulderPositionTarget() {
        return shoulderPositionTarget;
    }

    public void setShoulderPositionTarget(double shoulderPositionTarget) {
        this.shoulderPositionTarget = shoulderPositionTarget;
    }

    public int getShoulderPositionMode() {
        return shoulderPositionMode;
    }

    public void setShoulderPositionMode(int shoulderPositionMode) {
        this.shoulderPositionMode = shoulderPositionMode;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }
}
