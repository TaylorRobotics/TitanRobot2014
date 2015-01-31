package org.usfirst.frc.team1760.robot.teleop;

import org.usfirst.frc.team1760.robot.TitanRobot;

public class TeleopMode {
	protected TitanRobot robot;

	/**
	 * The class instance to handle TankDrive operations.
	 */
	private TankDriveOperator tankDriveOperator = null;
	/**
	 * The class instance to handle tote lift operations.
	 */
	private ToteLiftOperator toteLiftOperator = null;
	/**
	 * The class instance to handle fork lift operations.
	 */
	private ForkLiftOperator forkLiftOperator = null;

	public TeleopMode(TitanRobot pRobot) {
		robot = pRobot;
	}

	/**
     * This function is run once each time the robot enters teleop mode
     */
    public void teleopInit() {
    	tankDriveOperator = new TankDriveOperator(robot);
    	toteLiftOperator = new ToteLiftOperator(robot);
    	forkLiftOperator = new ForkLiftOperator(robot);
    }

    /**
     * This function is called periodically during teleop
     */
    public  void teleopPeriodic() {
    	tankDriveOperator.periodic();
    	toteLiftOperator.periodic();
    	forkLiftOperator.periodic();
    }
}
