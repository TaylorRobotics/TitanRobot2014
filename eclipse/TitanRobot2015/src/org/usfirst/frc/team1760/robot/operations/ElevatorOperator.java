package org.usfirst.frc.team1760.robot.operations;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.JoystickButton;
import org.usfirst.frc.team1760.robot.components.Switch;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class ElevatorOperator {
	private TitanRobot robot;
	private SpeedController elevatorMotor;
	private Joystick operatorJoystick;
	private Switch elevatorUpperLimitSwitch;
	private Switch elevatorLowerLimitSwitch;

	public ElevatorOperator(TitanRobot pRobot) {
		robot = pRobot;
		elevatorMotor = robot.getMotorStore().getElevatorMotor();
		operatorJoystick = robot.getJoystickStore().getOperatorJoystick();
		elevatorUpperLimitSwitch = robot.getSwitchStore().getElevatorUpperLimitSwitch();
		elevatorLowerLimitSwitch = robot.getSwitchStore().getElevatorLowerLimitSwitch();
	}

	public void periodic() {
        double speed = 0.0;
    double y = operatorJoystick.getY();
    speed = y * y;
    if ((speed >= -0.05) && (speed <= 0.05)) {
    	elevatorMotor.set(0.0);
    	// Apply Brake
    }
    else {
    	//release brake
        if ((speed > 0.0) && elevatorUpperLimitSwitch.isSwitchOn()) {
            	elevatorMotor.set(0.0);
        }
        else if ((speed < 0.0) && elevatorLowerLimitSwitch.isSwitchOn()) {
            	elevatorMotor.set(0.0);
        }
        else {
	        elevatorMotor.set(speed);
        }
        }
	}
	// Solenoid off? (need to keep brake state?)
}
