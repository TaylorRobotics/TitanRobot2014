package org.usfirst.frc.team1760.robot.components;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SolenoidStore {
	public static final int LIFT_SOLENOID_FORWARD_CHANNEL = 0;
	public static final int LIFT_SOLENOID_REVERSE_CHANNEL = 1;
	private DoubleSolenoid liftSolenoid = null;

	public synchronized DoubleSolenoid getLiftSolenoid() {
		if (liftSolenoid == null) {
			liftSolenoid = new DoubleSolenoid(LIFT_SOLENOID_FORWARD_CHANNEL, LIFT_SOLENOID_REVERSE_CHANNEL);
		}
		return liftSolenoid;
	}

}
