package org.usfirst.frc.team1760.robot.operations;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.JoystickButton;
import org.usfirst.frc.team1760.robot.components.Switch;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class ElevatorOperator {
	public static final double AUTO_UP_SPEED = 0.75;
	public static final double PARKING_SPEED = -0.25;

	private TitanRobot robot;
	private SpeedController elevatorMotor;
	private Joystick operatorJoystick;
	private JoystickButton holdElevatorHighButton;
	private JoystickButton parkElevatorButton;
	private JoystickButton cancelElevatorHoldButton;
	private Switch elevatorUpperLimitSwitch;
	private Switch elevatorLowerLimitSwitch;

	private boolean holdHigh;
	private boolean parking;

	public ElevatorOperator(TitanRobot pRobot) {
		robot = pRobot;
		elevatorMotor = robot.getMotorStore().getElevatorMotor();
		operatorJoystick = robot.getJoystickStore().getOperatorJoystick();
		holdElevatorHighButton = robot.getJoystickStore().getHoldElevatorHighButton();
		parkElevatorButton = robot.getJoystickStore().getParkElevatorButton();
		cancelElevatorHoldButton = robot.getJoystickStore().getCancelElevatorHoldButton();
		elevatorUpperLimitSwitch = robot.getSwitchStore().getElevatorUpperLimitSwitch();
		elevatorLowerLimitSwitch = robot.getSwitchStore().getElevatorLowerLimitSwitch();
		holdHigh = false;
		parking = false;
	}

	public void periodic() {
        if (holdElevatorHighButton.isSwitchOn()) {
        	holdHigh = true;
        	parking = false;
        }
        else if (parkElevatorButton.isSwitchOn()) {
        	parking = true;
        	holdHigh = false;
        }
        else if (cancelElevatorHoldButton.isSwitchOn()) {
        	parking = false;
        	holdHigh = false;
        }

        double speed = 0.0;

        if (parking) {
            if (elevatorLowerLimitSwitch.isSwitchOn()) {
            	parking = false;
            }
            else {
            	speed = PARKING_SPEED;
            }
        }

        if (holdHigh) {
        	speed = AUTO_UP_SPEED;
        }
        else {
            double y = operatorJoystick.getY();
            speed = y * y;
        }

        if ((speed > 0.0) && elevatorUpperLimitSwitch.isSwitchOn()) {
        	speed = 0.0;
        }
        else if ((speed < 0.0) && elevatorLowerLimitSwitch.isSwitchOn()) {
        	speed = 0.0;
        }

        elevatorMotor.set(speed);
	}
}
