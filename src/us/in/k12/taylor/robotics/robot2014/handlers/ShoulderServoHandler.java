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
//        registry.setShoulderPositionTarget(lastPosition);  // current location, -1.0, just leave for default (set to -1.0 in registry)?
    }
 
// Change ShoulderControllerHandler to direct drive from Joystick (If in servo mode, Joystick adjusts target position)
    public void run() {
        if (registry.getShoulderPositionTarget() >= 0.0) {  // targetPosition < 0.0 means no target
        long currentTimeCheck = System.currentTimeMillis();
        if (currentTimeCheck > nextTimeCheck) {
            double position = shoulderPotentiometer.getValue();
            double travel = registry.getShoulderPositionTarget() - position;
            if ((safetyTime > 0) && (currentTimeCheck > safetyTime)) {
                /* Safety time limit reached */
                stopMovingToTarget(position);
                registry.setShoulderPositionTarget(lastPosition);
            }
            else if (Math.abs(travel) < SHOULDER_POSITION_TOLERANCE) {
                /* Reached target position */
                stopMovingToTarget(position);
            }
            else if (lastTimeCheck == 0) {
                startMovingToTarget(currentTimeCheck, travel, position);
            }
            else if (currentTimeCheck >= nextTimeCheck) {
                /* Continue moving toward target */
                moveToTarget(travel, position, currentTimeCheck);
            }
        }
        shoulderMotor.set(motorSpeed);
    }
    }
 
    private void startMovingToTarget(long pCurrentTimeCheck, double pTravel, double pPosition) {
        safetyTime = pCurrentTimeCheck + SHOULDER_SAFETY_TIME_LIMIT;
        if (pTravel < 0.0) {
            motorSpeed = -SHOULDER_SPEED_INCREMENT;
        }
       else {
            motorSpeed = SHOULDER_SPEED_INCREMENT;
        }
        lastTimeCheck = pCurrentTimeCheck;
        nextTimeCheck = pCurrentTimeCheck + SHOULDER_SPEED_INTERVAL;
        lastPosition = pPosition;
    }
 
    private void moveToTarget(double pTravel, double pPosition, long pCurrentTimeCheck) {
        double armSpeed = (pPosition - lastPosition) / (pCurrentTimeCheck + lastTimeCheck);
        lastTimeCheck = pCurrentTimeCheck;
        if ((armSpeed < 0.0) && (pTravel > 0.0)) {
            motorSpeed = motorSpeed + SHOULDER_SPEED_INCREMENT;
        }
        else if ((armSpeed > 0.0) && (pTravel < 0.0)) {
            motorSpeed = motorSpeed - SHOULDER_SPEED_INCREMENT;
        }
        else if (pTravel > 0.0) {
            if (armSpeed < TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed + SHOULDER_SPEED_INCREMENT;
            }
            else if (armSpeed > TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed - SHOULDER_SPEED_INCREMENT;
            }
        }
        else if (pTravel < 0.0) {
            if (armSpeed < -TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed - SHOULDER_SPEED_INCREMENT;
            }
            else if (armSpeed > -TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed + SHOULDER_SPEED_INCREMENT;
            }
        }
        motorSpeed = getLimitedSpeed(motorSpeed, pTravel);
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
 
    private double getLimitedSpeed(double pSpeed, double pTravel) {
        double speed = pSpeed;
        if (pTravel < 0.0) {
            if (speed < -MAXIMUM_SHOULDER_SPEED) {
                speed = -MAXIMUM_SHOULDER_SPEED;
            }
            else if (speed > -MINIMUM_SHOULDER_SPEED) {
                speed = -MINIMUM_SHOULDER_SPEED;
            }
        }
        else if (pTravel > 0.0) {
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