package org.usfirst.frc.team1760.robot.operations;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.JoystickButton;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class ToteLiftOperator {
	private TitanRobot robot;
    private JoystickButton raiseToteButton;
    private JoystickButton lowerToteButton;
    private DoubleSolenoid toteLiftSolenoid;

	public ToteLiftOperator(TitanRobot pRobot) {
		robot = pRobot;
		raiseToteButton = robot.getJoystickStore().getRaiseToteButton();
		lowerToteButton = robot.getJoystickStore().getLowerToteButton();
		toteLiftSolenoid = robot.getSolenoidStore().getToteLiftSolenoid();
	}

	public void periodic() {
        if (raiseToteButton.isSwitchOn()) {
        	toteLiftSolenoid.set(DoubleSolenoid.Value.kForward);
        }
        else if (lowerToteButton.isSwitchOn()) {
        	toteLiftSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
        else {
        	toteLiftSolenoid.set(DoubleSolenoid.Value.kOff);
        }
	}
}
