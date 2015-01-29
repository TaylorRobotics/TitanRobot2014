package org.usfirst.frc.team1760.robot.operations;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.Switch;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class ForkLiftOperator {
	private TitanRobot robot;
	private SpeedController forkLiftMotor;
	private Joystick operatorJoystick;
	private Switch forkLiftUpperLimitSwitch;
	private Switch forkLiftLowerLimitSwitch;

	public ForkLiftOperator(TitanRobot pRobot) {
		robot = pRobot;
		forkLiftMotor = robot.getMotorStore().getForkLiftMotor();
		operatorJoystick = robot.getJoystickStore().getOperatorJoystick();
		forkLiftUpperLimitSwitch = robot.getSwitchStore().getForkLiftUpperLimitSwitch();
		forkLiftLowerLimitSwitch = robot.getSwitchStore().getForkListLowerLimitSwitch();
	}

	public void periodic() {
        double speed = 0.0;
	    double y = operatorJoystick.getY();
	    speed = y * y;
	    if ((speed >= -0.05) && (speed <= 0.05)) {
	    	forkLiftMotor.set(0.0);
	    	// Apply Brake
	    }
	    else {
	    	//release brake
	        if ((speed > 0.0) && forkLiftUpperLimitSwitch.isSwitchOn()) {
	            	forkLiftMotor.set(0.0);
	        }
	        else if ((speed < 0.0) && forkLiftLowerLimitSwitch.isSwitchOn()) {
	            	forkLiftMotor.set(0.0);
	        }
	        else {
		        forkLiftMotor.set(speed);
	        }
        }
	}
	// Solenoid off? (need to keep brake state?)
}
