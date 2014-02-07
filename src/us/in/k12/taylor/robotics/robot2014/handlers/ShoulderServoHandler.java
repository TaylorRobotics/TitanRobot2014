package us.in.k12.taylor.robotics.robot2014.handlers;
 
import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.Potentiometer;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;
 
/**
* @author Taylor Robotics 2014
*/
public class ShoulderServoHandler implements RobotParameters {
    private final RobotRegistry registry;
    private final Potentiometer shoulderPotentiometer;
    private final TitanSpeedController shoulderMotor;
 
    private long safetyTime;
    private double motorSpeed;
    private long lastTimeCheck;
    private long nextTimeCheck;
    private double lastPosition;
 
    public ShoulderServoHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        shoulderPotentiometer = registry.getShoulderPotentiometer();
        shoulderMotor = registry.getShoulderMotor();
        safetyTime = 0;
        motorSpeed = 0.0;
        lastTimeCheck = 0;
        nextTimeCheck = 0;
        lastPosition = shoulderPotentiometer.getValue();
    }
 
// Change ShoulderControllerHandler to direct drive from Joystick (If in servo mode, Joystick adjusts target position)
    public void run() {
        if (registry.getShoulderPositionTarget() >= 0.0) {  // targetPosition < 0.0 means no target
        long currentTimeCheck = System.currentTimeMillis();
        if (currentTimeCheck > nextTimeCheck) {
            double position = shoulderPotentiometer.getValue();
            double distanceToTarget = registry.getShoulderPositionTarget() - position;
            if ((safetyTime > 0) && (currentTimeCheck > safetyTime)) {
                /* Safety time limit reached */
                stopMovingToTarget(position);
//                registry.setShoulderPositionTarget(lastPosition);
            }
            else if (Math.abs(distanceToTarget) <= SHOULDER_POSITION_TOLERANCE) {
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
        shoulderMotor.set(motorSpeed);
    }
    }
 
    private void startMovingToTarget(long pCurrentTimeCheck, double pDistanceToTarget, double pPosition) {
        safetyTime = pCurrentTimeCheck + SHOULDER_SAFETY_TIME_LIMIT;
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
            }
            else {
                motorSpeed = motorSpeed + SHOULDER_SPEED_INCREMENT;
            }
        }
        else if ((armSpeed > 0.0) && (pDistanceToTarget < 0.0)) {
            if (motorSpeed > 0.0) {
                motorSpeed = -MINIMUM_SHOULDER_SPEED;
            }
            else {
                motorSpeed = motorSpeed - SHOULDER_SPEED_INCREMENT;
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
        safetyTime = 0;
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