package us.in.k12.taylor.robotics.robot2014.handlers;
 
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.ComponentRegistry;
import us.in.k12.taylor.robotics.robot2014.StateRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.Potentiometer;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;
 
/**
* @author Taylor Robotics 2014
*/
public class ShoulderServoHandler implements RobotParameters {
    private final ComponentRegistry componentRegistry;
    private final StateRegistry stateRegistry;
    private final Potentiometer shoulderPotentiometer;
    private final TitanSpeedController shoulderMotor;
 
    private double motorSpeed;
    private long lastTimeCheck;
    private long nextTimeCheck;
    private double lastPosition;
 
    public ShoulderServoHandler(TitanRobot pRobot) {
        componentRegistry = pRobot.getComponentRegistry();
        stateRegistry = pRobot.getStateRegistry();
        shoulderPotentiometer = componentRegistry.getShoulderPotentiometer();
        shoulderMotor = componentRegistry.getShoulderMotor();
        motorSpeed = 0.0;
        lastTimeCheck = 0;
        nextTimeCheck = 0;
        lastPosition = shoulderPotentiometer.getValue();
    }
 
// Change ShoulderControllerHandler to direct drive from Joystick (If in servo mode, Joystick adjusts target position)
    public void run() {
        if ((stateRegistry.getShoulderPositionMode() == SHOULDER_SERVO_MODE) ||
                (stateRegistry.getShoulderPositionMode() == SHOULDER_SEEK_MODE)) {
            long currentTimeCheck = System.currentTimeMillis();
            if (currentTimeCheck > nextTimeCheck) {
                double position = shoulderPotentiometer.getValue();
                double distanceToTarget = stateRegistry.getShoulderPositionTarget() - position;
                if (Math.abs(distanceToTarget) <= SHOULDER_POSITION_TOLERANCE) {
                    /* Reached target position */
                    stopMovingToTarget(position);
                }
                else if (lastTimeCheck == 0) {
                    startMovingToTarget(currentTimeCheck, distanceToTarget, position);
                }
                else if (currentTimeCheck >= nextTimeCheck) {
                    /* Continue moving toward target */
                    moveToTarget(distanceToTarget, position, currentTimeCheck);
            System.out.println("Speed: " + motorSpeed);
                }
            }
            shoulderMotor.set(motorSpeed); // Need to do more often than check?
        }
    }
 
    private void startMovingToTarget(long pCurrentTimeCheck, double pDistanceToTarget, double pPosition) {
        if (pDistanceToTarget < 0.0) {
            motorSpeed = -MINIMUM_SHOULDER_SPEED;
        }
       else {
            motorSpeed = MINIMUM_SHOULDER_SPEED;
        }
        lastTimeCheck = pCurrentTimeCheck;
        nextTimeCheck = pCurrentTimeCheck + SHOULDER_SPEED_INTERVAL;
        lastPosition = pPosition;
    }
 
    private void moveToTarget(double pDistanceToTarget, double pPosition, long pCurrentTimeCheck) {
        System.out.println("Travel: " + pDistanceToTarget);
        System.out.println("Position: " + pPosition);
        double armSpeed = (pPosition - lastPosition) / (pCurrentTimeCheck - lastTimeCheck) * 1000;
        System.out.println("Arm Speed " + armSpeed);
        lastTimeCheck = pCurrentTimeCheck;
        if ((armSpeed < 0.0) && (pDistanceToTarget > 0.0)) {
            if (motorSpeed < 0.0) {
                motorSpeed = MINIMUM_SHOULDER_SPEED;
                motorSpeed = 0.3;
            }
            else {
                motorSpeed = motorSpeed + SHOULDER_SPEED_INCREMENT;
            }
        }
        else if ((armSpeed > 0.0) && (pDistanceToTarget < 0.0)) {
            if (motorSpeed > 0.0) {
                motorSpeed = -MINIMUM_SHOULDER_SPEED;
                motorSpeed = -0.01;
            }
            else {
                motorSpeed = motorSpeed - SHOULDER_SPEED_INCREMENT;
                motorSpeed = motorSpeed - 0.01;
            }
        }
        else if (pDistanceToTarget > 0.0) {
            if (armSpeed < TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed + SHOULDER_SPEED_INCREMENT;
            }
            else if (armSpeed > TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed - SHOULDER_SPEED_INCREMENT;
            }
        }
        else if (pDistanceToTarget < 0.0) {
            if (armSpeed < -TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed + SHOULDER_SPEED_INCREMENT;
            }
            else if (armSpeed > -TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed - SHOULDER_SPEED_INCREMENT;
            }
        }
        motorSpeed = getLimitedSpeed(motorSpeed, pDistanceToTarget);
        lastTimeCheck = pCurrentTimeCheck;
        nextTimeCheck = pCurrentTimeCheck + SHOULDER_SPEED_INTERVAL;
        lastPosition = pPosition;
    }
 
    private void stopMovingToTarget(double pPosition) {
        motorSpeed = 0.0;
        lastTimeCheck = 0;
        nextTimeCheck = 0;
        lastPosition = pPosition;
    }
 
    private double getLimitedSpeed(double pSpeed, double pDistanceToTarget) {
        double speed = pSpeed;
        if (pDistanceToTarget < 0.0) {
            if (speed < -MAXIMUM_SHOULDER_SPEED) {
                speed = -MAXIMUM_SHOULDER_SPEED;
            }
            else if (speed > -MINIMUM_SHOULDER_SPEED) {
                speed = -MINIMUM_SHOULDER_SPEED;
            }
        }
        else if (pDistanceToTarget > 0.0) {
            if (speed > MAXIMUM_SHOULDER_SPEED) {
                speed = MAXIMUM_SHOULDER_SPEED;
            }
            else if (speed < MINIMUM_SHOULDER_SPEED) {
                speed = MINIMUM_SHOULDER_SPEED;
            }
        }
        return speed;
    }
}
