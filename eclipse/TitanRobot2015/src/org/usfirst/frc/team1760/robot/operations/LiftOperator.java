package org.usfirst.frc.team1760.robot.operations;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.JoystickButton;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class LiftOperator {
	private TitanRobot robot;
    private JoystickButton liftDownButton;
    private JoystickButton liftUpButton;
    private DoubleSolenoid liftSolenoid;

	public LiftOperator(TitanRobot pRobot) {
		robot = pRobot;
		liftDownButton = robot.getJoystickStore().getLiftDownButton();
		liftUpButton = robot.getJoystickStore().getLiftUpButton();
		liftSolenoid = robot.getSolenoidStore().getLiftSolenoid();
	}

	public void periodic() {
        if (liftDownButton.isSwitchOn()) {
        	liftSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
        else if (liftUpButton.isSwitchOn()) {
        	liftSolenoid.set(DoubleSolenoid.Value.kForward);
        }
        else {
        	liftSolenoid.set(DoubleSolenoid.Value.kOff);
        }

	}
}
