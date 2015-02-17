package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.TimeLimit;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * This class handles Autonomous mode 3 operations.
 * Autonomous Mode 3 is a dead mode.  Simply drop tail lift.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class AutonomousMode3 extends AutonomousMode {
	private static final int DROPPING_TAIL_LIFT = 0;
	private static final int COMPLETE = 44;

	private static final long WAIT_TIME_LIMIT = 200;
	
	private TimeLimit waitTimeLimit;

	private DoubleSolenoid tailLiftSolenoid;
	private int mode;


	public AutonomousMode3(TitanRobot pRobot) {
		super(pRobot);
	    tailLiftSolenoid = robot.getSolenoidStore().getTailLiftSolenoid();

	    /* Starting drive mode */
	    mode = DROPPING_TAIL_LIFT;
		waitTimeLimit = new TimeLimit(WAIT_TIME_LIMIT);
    	tailLiftSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	/* (non-Javadoc)
	 * @see org.usfirst.frc.team1760.robot.autonomous.AutonomousMode#periodic()
	 */
	@Override
	public void periodic() {
		if (mode == DROPPING_TAIL_LIFT) {
			if (waitTimeLimit.isTimeLimitReached()) {
				mode = COMPLETE;
		    	tailLiftSolenoid.set(DoubleSolenoid.Value.kOff);
			}
		}
	}

}
