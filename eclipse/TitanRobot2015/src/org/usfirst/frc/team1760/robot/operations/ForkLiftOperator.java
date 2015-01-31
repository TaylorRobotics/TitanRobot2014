package org.usfirst.frc.team1760.robot.operations;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.JoystickButton;
import org.usfirst.frc.team1760.robot.components.Switch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class ForkLiftOperator {
	private TitanRobot robot;
	private SpeedController forkLiftMotor;
	private Joystick operatorJoystick;
	private Switch forkLiftUpperLimitSwitch;
	private Switch forkLiftLowerLimitSwitch;
	private Switch forkLiftMiddleLimitSwitch;
	private JoystickButton stopForkAtMiddleButton;
	private DoubleSolenoid forkLiftBrakeSolenoid;

	public ForkLiftOperator(TitanRobot pRobot) {
		robot = pRobot;
		forkLiftMotor = robot.getMotorStore().getForkLiftMotor();
		operatorJoystick = robot.getJoystickStore().getOperatorJoystick();
		forkLiftUpperLimitSwitch = robot.getSwitchStore().getForkLiftUpperLimitSwitch();
		forkLiftLowerLimitSwitch = robot.getSwitchStore().getForkLiftLowerLimitSwitch();
		forkLiftMiddleLimitSwitch = robot.getSwitchStore().getForkLiftMiddleLimitSwitch();
		forkLiftBrakeSolenoid = robot.getSolenoidStore().getForkLiftBrakeSolenoid();
		stopForkAtMiddleButton = robot.getJoystickStore().getStopForkAtMiddleButton();
	}

	public void periodic() {
        double speed = 0.0;
	    double y = operatorJoystick.getY();
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

	    if (forkLiftMiddleLimitSwitch.isSwitchOn() && stopForkAtMiddleButton.isSwitchOn()) {
	    	speed = 0.0;
	    }

	    if (speed == 0.0) {
	    	forkLiftBrakeSolenoid.set(DoubleSolenoid.Value.kForward);
        	forkLiftMotor.set(0.0);
	    }
	    else {
	    	forkLiftBrakeSolenoid.set(DoubleSolenoid.Value.kReverse);
        	forkLiftMotor.set(speed);
	    }
	}
}
