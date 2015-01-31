package org.usfirst.frc.team1760.robot.operations;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.Switch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class ForkLiftOperator {
	private TitanRobot robot;
	private SpeedController forkLiftMotor;
	private Joystick operatorJoystick;
	private Switch forkLiftUpperLimitSwitch;
	private Switch forkLiftLowerLimitSwitch;
	private DoubleSolenoid forkLiftBrakeSolenoid;

	public ForkLiftOperator(TitanRobot pRobot) {
		robot = pRobot;
		forkLiftMotor = robot.getMotorStore().getForkLiftMotor();
		operatorJoystick = robot.getJoystickStore().getOperatorJoystick();
		forkLiftUpperLimitSwitch = robot.getSwitchStore().getForkLiftUpperLimitSwitch();
		forkLiftLowerLimitSwitch = robot.getSwitchStore().getForkListLowerLimitSwitch();
		forkLiftBrakeSolenoid = robot.getSolenoidStore().getForkLiftBrakeSolenoid();
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

	    if (speed == 0.0) {
	    	System.out.println("STOP");
	    	forkLiftBrakeSolenoid.set(DoubleSolenoid.Value.kForward);
        	forkLiftMotor.set(0.0);
	    }
	    else {
	    	forkLiftBrakeSolenoid.set(DoubleSolenoid.Value.kReverse);
        	forkLiftMotor.set(speed);
	    }
//		forkLiftBrakeSolenoid.set(DoubleSolenoid.Value.kOff);
	}
}
