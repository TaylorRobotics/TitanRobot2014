package org.usfirst.frc.team1760.robot.teleop;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.JoystickButton;
import org.usfirst.frc.team1760.robot.components.Switch;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * This class handles oepration of the fork lift.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class ForkLiftOperator {
	public static final int MIDDLE_HOLD_TIME = 2000;
	public static final double DIRECTION_NORMAL = 1.0;
	public static final double DIRECTION_REVERSE = -1.0;
	private TitanRobot robot;
	private SpeedController forkLiftMotor;
	private Joystick operatorJoystick;
	private Switch forkLiftUpperLimitSwitch;
	private Switch forkLiftLowerLimitSwitch;
	private Switch forkLiftMiddleLimitSwitch;
	private JoystickButton stopForkAtMiddleButton;
	private TimeLimit middleHoldLimit;
	private double direction;

	public ForkLiftOperator(TitanRobot pRobot) {
		robot = pRobot;
		forkLiftMotor = robot.getMotorStore().getForkLiftMotor();
		operatorJoystick = robot.getJoystickStore().getOperatorJoystick();
		forkLiftUpperLimitSwitch = robot.getSwitchStore().getForkLiftUpperLimitSwitch();
		forkLiftLowerLimitSwitch = robot.getSwitchStore().getForkLiftLowerLimitSwitch();
		forkLiftMiddleLimitSwitch = robot.getSwitchStore().getForkLiftMiddleLimitSwitch();
		stopForkAtMiddleButton = robot.getJoystickStore().getStopForkAtMiddleButton();
		middleHoldLimit = new TimeLimit();
		direction = DIRECTION_REVERSE;
	}

	public void periodic() {
        double speed = 0.0;
        if (middleHoldLimit.isTimeLimitReached()) {
    	    double y = operatorJoystick.getY() * direction;
    	    speed = y * Math.abs(y);
    	    if ((speed >= -0.05) && (speed <= 0.05)) {
    	    	speed = 0.0;
    	    }
    	    else if ((speed > 0.0) && forkLiftUpperLimitSwitch.isSwitchOn()) {
        		speed = 0.0;
            }
            else if ((speed < 0.0) && forkLiftLowerLimitSwitch.isSwitchOn()) {
               	speed = 0.0;
            }

    	    if (forkLiftMiddleLimitSwitch.isSwitchOn()) {
    	    	if (stopForkAtMiddleButton.isSwitchOn()) {
        	    	speed = 0.0;
    	    	}
       	    	else if (stopForkAtMiddleButton.getStateChange()) {
    	    		middleHoldLimit.setTimeLimit(MIDDLE_HOLD_TIME);
        	    	speed = 0.0;
    	    	}
    	    }
        }

	    if (speed == 0.0) {
        	forkLiftMotor.set(0.0);
	    }
	    else {
        	forkLiftMotor.set(speed);
	    }
	}
}
