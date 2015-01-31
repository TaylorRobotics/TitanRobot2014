package org.usfirst.frc.team1760.robot.stores;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * This class holds pneumatic solenoid components defined for robot operation.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public class SolenoidStore {
	public static final int TOTE_LIFT_SOLENOID_FORWARD_CHANNEL = 1;
	public static final int TOTE_LIFT_SOLENOID_REVERSE_CHANNEL = 0;
	public static final int FORK_LIFT_BRAKE_ON_CHANNEL = 2;
	public static final int FORK_LIFT_BRAKE_OFF_CHANNEL = 3;

	private DoubleSolenoid toteLiftSolenoid = null;
	private DoubleSolenoid forkLiftBrakeSolenoid = null;

	/**
	 * Gets the DoubleSolenoid for the tote lift.
	 * @return The DoubleSolenoid for the tote lift.
	 */
	public synchronized DoubleSolenoid getToteLiftSolenoid() {
		if (toteLiftSolenoid == null) {
			toteLiftSolenoid = new DoubleSolenoid(TOTE_LIFT_SOLENOID_FORWARD_CHANNEL, TOTE_LIFT_SOLENOID_REVERSE_CHANNEL);
		}
		return toteLiftSolenoid;
	}

	/**
	 * Gets the DoubleSolenoid for the fork lift.
	 * @return The DoubleSolenoid for the fork lift brak.
	 */
	public synchronized DoubleSolenoid getForkLiftBrakeSolenoid() {
		if (forkLiftBrakeSolenoid == null) {
			forkLiftBrakeSolenoid = new DoubleSolenoid(FORK_LIFT_BRAKE_ON_CHANNEL, FORK_LIFT_BRAKE_OFF_CHANNEL);
		}
		return forkLiftBrakeSolenoid;
	}
	
}
