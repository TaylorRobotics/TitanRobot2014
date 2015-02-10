package org.usfirst.frc.team1760.robot.teleop;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.JoystickButton;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * This class handles the operation of the tote lift.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class TailLiftOperator {
	public static final long SOLENOID_ON_LAG = 3000;  // 3 turn off solenoid

	private TitanRobot robot;
    private JoystickButton raiseTailLiftButton;
    private JoystickButton lowerTailLiftButton;
    private DoubleSolenoid tailLiftSolenoid;
    private TimeLimit timeLimit;

    public TailLiftOperator(TitanRobot pRobot) {
		robot = pRobot;
		raiseTailLiftButton = robot.getJoystickStore().getRaiseTailLiftButton();
		lowerTailLiftButton = robot.getJoystickStore().getLowerTailLiftButton();
		tailLiftSolenoid = robot.getSolenoidStore().getTailLiftSolenoid();
		timeLimit = new TimeLimit();
	}


	public void periodic() {
        if (raiseTailLiftButton.isSwitchOn()) {
        	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
        	timeLimit.setTimeLimit(SOLENOID_ON_LAG);
        }
        else if (lowerTailLiftButton.isSwitchOn()) {
        	tailLiftSolenoid.set(DoubleSolenoid.Value.kReverse);
        	timeLimit.setTimeLimit(SOLENOID_ON_LAG);
        }
        else if (timeLimit.isTimeLimitReached()) {
        	tailLiftSolenoid.set(DoubleSolenoid.Value.kOff);
        }
	}

}
