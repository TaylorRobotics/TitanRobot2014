package us.in.k12.taylor.robotics.robot2014.factories;

import edu.wpi.first.wpilibj.SpeedController;
import us.in.k12.taylor.robotics.robot2014.components.Switch;

/**
 * This class implements a SpeedController with optional Hard Limit switches
 * and also provides timed operation.  The TitanSpeedController also provides
 * the ability to invert the motor direction to account for motors that are
 * wired backwards.
 * 
 * @author Titan Robotics 2014
 */
public class TitanSpeedController implements SpeedController {
    private final SpeedController coreSpeedController;
    private final Switch forwardLimitSwitch;
    private final Switch reverseLimitSwitch;
    private final boolean invertDirection;
    private boolean reachedHardLimit;
    private long timeLimit;
    private boolean reachedTimeLimit;
    private double currentSpeed;

    public TitanSpeedController(SpeedController pCoreSpeedController, Switch pForwardLimitSwitch,
            Switch pReverseLimitSwitch, boolean pInvertDirection) {
        coreSpeedController = pCoreSpeedController;
        forwardLimitSwitch = pForwardLimitSwitch;
        reverseLimitSwitch = pReverseLimitSwitch;
        invertDirection = pInvertDirection;
        timeLimit = 0;
        reachedTimeLimit = false;
        reachedHardLimit = false;
        currentSpeed = 0.0;
    }

    /**
     * Gets the recently set value of the PWM as passed to this TitanSpeedController.
     * @return A double of the recently set value of the PWM as passed to this TitanSpeedController
     */
    public double get() {
        return currentSpeed;
    }

    public void set(double pSpeed, byte pSyncGroup) {
        currentSpeed = getAdjustedSpeed(pSpeed);
        coreSpeedController.set(currentSpeed, pSyncGroup);
        if (currentSpeed != 0.0) {
            currentSpeed = pSpeed;
        }
    }

    public void set(double pSpeed) {
        currentSpeed = getAdjustedSpeed(pSpeed);
        coreSpeedController.set(currentSpeed);
        if (currentSpeed != 0.0) {
            currentSpeed = pSpeed;
        }
    }

    public void disable() {
        coreSpeedController.disable();
    }

    public void pidWrite(double pOutput) {
        System.out.println("Warning: pidWrite may not be properly implemented.");
        coreSpeedController.pidWrite(pOutput);
    }

    /**
     * Sets the TitanSpeedController timer to allow set speeds until the designated time limit has been
     * reached, or a hard limit is reached.
     * @param pTimeLimit A long value in milliseconds limiting the time period to allow set speeds 
     */
    public void setTimedOperation(long pTimeLimit) {
        reachedHardLimit = false;
        reachedTimeLimit = false;
        timeLimit = System.currentTimeMillis() + pTimeLimit;
    }

    /**
     * Disables the TitanSpeedController timer to allow set speeds until a hard limit is reached.
     */
    public void setNonTimedOperation() {
        reachedHardLimit = false;
        reachedTimeLimit = false;
        timeLimit = 0;
    }

    /**
     * Gets the state of a time limit being reached.
     * @return True if a time limit being reached
     */
    public boolean isHardLimitReached() {
        return reachedHardLimit;
    }

    /**
     * Gets the state of a hard limit being reached.
     * @return True if a hard limit (LimitSwitch) was reached, otherwise false
     */
    public boolean isTimeLimitReached() {
        return reachedTimeLimit;
    }

    /**
     * Gets the state of a hard limit or time limit being reached.
     * @return A boolean of true if a hard limit or time limit was reached, otherwise false
     */
    public boolean isLimitReached() {
        return (isHardLimitReached() || isTimeLimitReached());
    }

    /**
     * Adjusts the passed speed based upon the direction inversion setting and reaching time limits or
     * hard limits.
     * @param pSpeed A double containing the speed to be adjusted
     * @return A double adjusted speed value based upon the direction inversion setting and reaching time limits or hard limits
     */
    private double getAdjustedSpeed(double pSpeed) {
        double speed = pSpeed;
        reachedTimeLimit = ((timeLimit > 0) && (System.currentTimeMillis() >= timeLimit));
        if (reachedTimeLimit) {
            speed = 0.0;
        }
        else {
            if (invertDirection) {
                speed = -speed;
            }
            if (speed > 0.0) {
                if ((forwardLimitSwitch != null) && forwardLimitSwitch.isSwitchOn()) {
                    reachedHardLimit = true;
                    speed = 0.0;
                }
                else {
                    reachedHardLimit = false;
                }
            }
            else if (speed < 0.0) {
                if ((reverseLimitSwitch != null) && reverseLimitSwitch.isSwitchOn()) {
                    reachedHardLimit = true;
                    speed = 0.0;
                }
                else {
                    reachedHardLimit = false;
                }
            }
        }
        return speed;
    }
}
