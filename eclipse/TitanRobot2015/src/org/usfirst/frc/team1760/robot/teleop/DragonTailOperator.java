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
public class DragonTailOperator {
	public static final long SOLENOID_ON_LAG = 3000;  // 3 turn off solenoid

	private TitanRobot robot;
    private JoystickButton raiseDragonTailButton;
    private JoystickButton lowerDragonTailButton;
    private DoubleSolenoid dragonTailSolenoid;
    private TimeLimit timeLimit;

    public DragonTailOperator(TitanRobot pRobot) {
		robot = pRobot;
		raiseDragonTailButton = robot.getJoystickStore().getRaiseDragonTailButton();
		lowerDragonTailButton = robot.getJoystickStore().getLowerDragonTailButton();
		dragonTailSolenoid = robot.getSolenoidStore().getDragonTailSolenoid();
		timeLimit = new TimeLimit();
	}

	public void periodic() {
        if (raiseDragonTailButton.isSwitchOn()) {
        	dragonTailSolenoid.set(DoubleSolenoid.Value.kReverse);
        	timeLimit.setTimeLimit(SOLENOID_ON_LAG);
        }
        else if (lowerDragonTailButton.isSwitchOn()) {
        	dragonTailSolenoid.set(DoubleSolenoid.Value.kForward);
        	timeLimit.setTimeLimit(SOLENOID_ON_LAG);
        }
        else if (timeLimit.isTimeLimitReached()) {
        	dragonTailSolenoid.set(DoubleSolenoid.Value.kOff);
        }
	}

}
