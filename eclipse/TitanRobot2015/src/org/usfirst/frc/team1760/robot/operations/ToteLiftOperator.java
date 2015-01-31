package org.usfirst.frc.team1760.robot.operations;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.JoystickButton;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * @author Robo-Titans Team 1790 Taylor High School 2015
 */
public class ToteLiftOperator {
	public static final long SOLENOID_ON_LAG = 3000;  // 3 turn off solenoid
	private TitanRobot robot;
    private JoystickButton raiseToteButton;
    private JoystickButton lowerToteButton;
    private DoubleSolenoid toteLiftSolenoid;
    private TimeLimit timeLimit;

	public ToteLiftOperator(TitanRobot pRobot) {
		robot = pRobot;
		raiseToteButton = robot.getJoystickStore().getRaiseToteButton();
		lowerToteButton = robot.getJoystickStore().getLowerToteButton();
		toteLiftSolenoid = robot.getSolenoidStore().getToteLiftSolenoid();
		timeLimit = new TimeLimit();
	}

	public void periodic() {
        if (raiseToteButton.isSwitchOn()) {
        	toteLiftSolenoid.set(DoubleSolenoid.Value.kForward);
        	timeLimit.setTimeLimit(SOLENOID_ON_LAG);
        }
        else if (lowerToteButton.isSwitchOn()) {
        	toteLiftSolenoid.set(DoubleSolenoid.Value.kReverse);
        	timeLimit.setTimeLimit(SOLENOID_ON_LAG);
        }
        else if (timeLimit.isTimeLimitReached()) {
        	toteLiftSolenoid.set(DoubleSolenoid.Value.kOff);
        }
	}
}
