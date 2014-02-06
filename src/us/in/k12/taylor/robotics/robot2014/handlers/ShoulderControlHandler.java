package us.in.k12.taylor.robotics.robot2014.handlers;

import us.in.k12.taylor.robotics.robot2014.RobotParameters;
import us.in.k12.taylor.robotics.robot2014.RobotRegistry;
import us.in.k12.taylor.robotics.robot2014.TitanRobot;
import us.in.k12.taylor.robotics.robot2014.components.Potentiometer;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class ShoulderControlHandler implements RobotParameters {
    private final RobotRegistry registry;
    private final Potentiometer shoulderPotentiometer;
    private final TitanSpeedController shoulderMotor;
    private long lastTimeCheck;
    private long nextTimeCheck;
    private long safetyTime;
    private double lastPosition;
    private double motorSpeed;

    public ShoulderControlHandler(TitanRobot pRobot) {
        registry = pRobot.getRegistry();
        shoulderPotentiometer = registry.getShoulderPotentiometer();
        shoulderMotor = registry.getShoulderMotor();
        lastTimeCheck = 0;
        nextTimeCheck = 0;
        safetyTime = 0;
        lastPosition = shoulderPotentiometer.getValue();
        motorSpeed = 0.0;
        registry.setShoulderPositionTarget(lastPosition);
    }

    public void run() {
        System.out.println(shoulderPotentiometer.getRatio() + " " + shoulderPotentiometer.getValue());
        shoulderMotor.set(shoulderPotentiometer.getRatio());
    }

    public void servo() {
        long currentTimeCheck = System.currentTimeMillis();
        if (currentTimeCheck > nextTimeCheck) {
            double position = shoulderPotentiometer.getValue();
            double delta = registry.getShoulderPositionTarget() - position;
            if ((safetyTime > 0) && (currentTimeCheck > safetyTime)) {
                /* Safety time limit reached */
                stopMovingToTarget(position);
                registry.setShoulderPositionTarget(lastPosition);
            }
            else if (Math.abs(delta) < SHOULDER_POSITION_TOLERANCE) {
                /* Reached target position */
                stopMovingToTarget(position);
            }
            else if (lastTimeCheck == 0) {
                startMovingToTarget(currentTimeCheck, delta, position);
            }
            else if (currentTimeCheck >= nextTimeCheck) {
                /* Continue moving toward target */
                moveToTarget(delta, position, currentTimeCheck);
            }
        }
        shoulderMotor.set(motorSpeed);
    }

    private void startMovingToTarget(long pCurrentTimeCheck, double pDelta, double pPosition) {
        safetyTime = pCurrentTimeCheck + SHOULDER_SAFETY_TIME_LIMIT;
        if (pDelta < 0.0) {
            motorSpeed = -SHOULDER_SPEED_INCREMENT;
        }
        else {
            motorSpeed = SHOULDER_SPEED_INCREMENT;
        }
        lastTimeCheck = pCurrentTimeCheck;
        nextTimeCheck = pCurrentTimeCheck + SHOULDER_SPEED_INTERVAL;
        lastPosition = pPosition;
    }

    private void moveToTarget(double pDelta, double pPosition, long pCurrentTimeCheck) {
        double armSpeed = (pPosition - lastPosition) / (pCurrentTimeCheck + lastTimeCheck);
        lastTimeCheck = pCurrentTimeCheck;
        if ((armSpeed < 0.0) && (pDelta > 0.0)) {
            motorSpeed = motorSpeed + SHOULDER_SPEED_INCREMENT;
        }
        else if ((armSpeed > 0.0) && (pDelta < 0.0)) {
            motorSpeed = motorSpeed - SHOULDER_SPEED_INCREMENT;
        }
        else if (pDelta > 0.0) {
            if (armSpeed < TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed + SHOULDER_SPEED_INCREMENT;
            }
            else if (armSpeed > TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed - SHOULDER_SPEED_INCREMENT;
            }
        }
        else if (pDelta < 0.0) {
            if (armSpeed < -TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed - SHOULDER_SPEED_INCREMENT;
            }
            else if (armSpeed > -TARGET_ARM_SPEED) {
                motorSpeed = motorSpeed + SHOULDER_SPEED_INCREMENT;
            }
        }
        motorSpeed = getLimitedSpeed(motorSpeed, pDelta);
    }

    private void stopMovingToTarget(double pPosition) {
        safetyTime = 0;
        motorSpeed = 0.0;
        lastTimeCheck = 0;
        nextTimeCheck = 0;
        lastPosition = pPosition;
    }

    private double getLimitedSpeed(double pSpeed, double pDelta) {
        double speed = pSpeed;
        if (pDelta < 0.0) {
            if (speed < -MAXIMUM_SHOULDER_SPEED) {
                speed = -MAXIMUM_SHOULDER_SPEED;
            }
            else if (speed > -MINIMUM_SHOULDER_SPEED) {
                speed = -MINIMUM_SHOULDER_SPEED;
            }
        }
        else if (pDelta > 0.0) {
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
