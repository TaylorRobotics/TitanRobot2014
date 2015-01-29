package org.usfirst.frc.team1760.robot.components;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SolenoidStore {
	public static final int TOTE_LIFT_SOLENOID_FORWARD_CHANNEL = 0;
	public static final int TOTE_LIFT_SOLENOID_REVERSE_CHANNEL = 1;
	private DoubleSolenoid toteLiftSolenoid = null;

	public synchronized DoubleSolenoid getToteLiftSolenoid() {
		if (toteLiftSolenoid == null) {
			toteLiftSolenoid = new DoubleSolenoid(TOTE_LIFT_SOLENOID_FORWARD_CHANNEL, TOTE_LIFT_SOLENOID_REVERSE_CHANNEL);
		}
		return toteLiftSolenoid;
	}

}
