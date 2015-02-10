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
public class ToteLiftOperator {
	public static final int NONE = 0;
	public static final int LIFT_STEP_1 = 1;
	public static final int LIFT_STEP_2 = 2;
	public static final int LOWER_STEP_1 = 3;
	public static final int LOWER_STEP_2 = 4;
	public static final long TOTE_LIFT_DELAY = 1500;
	public static final long BULLDOZER_DELAY = 1000;

	private TitanRobot robot;
    private JoystickButton raiseToteButton;
    private JoystickButton lowerToteButton;
    private DoubleSolenoid toteLiftSolenoid;
    private DoubleSolenoid bulldozerSolenoid;
    private TimeLimit timeLimit;
    private int state;

    public ToteLiftOperator(TitanRobot pRobot) {
		robot = pRobot;
		raiseToteButton = robot.getJoystickStore().getRaiseToteButton();
		lowerToteButton = robot.getJoystickStore().getLowerToteButton();
		toteLiftSolenoid = robot.getSolenoidStore().getToteLiftSolenoid();
		bulldozerSolenoid = robot.getSolenoidStore().getBulldozerSolenoid();
		timeLimit = new TimeLimit();
		state = NONE;
	}

	public void periodic() {
		switch(state) {
			case NONE:
			if (raiseToteButton.isSwitchOn() && raiseToteButton.getStateChange()) {
				state = LIFT_STEP_1;
	        	toteLiftSolenoid.set(DoubleSolenoid.Value.kForward);
	        	timeLimit.setTimeLimit(TOTE_LIFT_DELAY);
			}
			else if (lowerToteButton.isSwitchOn() && lowerToteButton.getStateChange()) {
				state = LOWER_STEP_1;
	        	bulldozerSolenoid.set(DoubleSolenoid.Value.kReverse);
	        	timeLimit.setTimeLimit(BULLDOZER_DELAY);
			}
			break;

			case LIFT_STEP_1:
			if (timeLimit.isTimeLimitReached()) {
				state = LIFT_STEP_2;
				bulldozerSolenoid.set(DoubleSolenoid.Value.kForward);
	        	timeLimit.setTimeLimit(BULLDOZER_DELAY);
			}
			break;

			case LOWER_STEP_1:
			if (timeLimit.isTimeLimitReached()) {
				state = LOWER_STEP_2;
				toteLiftSolenoid.set(DoubleSolenoid.Value.kReverse);
	        	timeLimit.setTimeLimit(TOTE_LIFT_DELAY);
			}
			break;

			case LIFT_STEP_2:
			case LOWER_STEP_2:
			if (timeLimit.isTimeLimitReached()) {
				state = NONE;
				toteLiftSolenoid.set(DoubleSolenoid.Value.kOff);
				bulldozerSolenoid.set(DoubleSolenoid.Value.kOff);
			}
			break;
		}
	}

	/* Old periodic code */
	public static final long SOLENOID_ON_LAG = 3000;  // 3 turn off solenoid

	public void periodic_old() {
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
