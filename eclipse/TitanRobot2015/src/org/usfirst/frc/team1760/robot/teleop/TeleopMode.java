package org.usfirst.frc.team1760.robot.teleop;

import org.usfirst.frc.team1760.robot.TitanRobot;

/**
 * This class handles Telop operations.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 *
 */
public class TeleopMode {
	/**
	 * The TitanRobot.
	 */
	private TitanRobot robot;

	/**
	 * The class instance to handle TankDrive operations.
	 */
	private TankDriveOperator tankDriveOperator;

	/**
	 * The class instance to handle tote lift operations.
	 */
	private ToteLiftOperator toteLiftOperator;

	/**
	 * The class instance to handle tail lift operations.
	 */
	private TailLiftOperator tailLiftOperator;

	/**
	 * The class instance to handle dragon tail operations.
	 */
	private DragonTailOperator dragonTailOperator;

	/**
	 * The class instance to handle fork lift operations.
	 */
	private ForkLiftOperator forkLiftOperator;

	/**
	 * Creates a TelopMode instance for controlling the Robot in Teleop.
	 * @param pRobot The TitanRobot instance
	 */
	public TeleopMode(TitanRobot pRobot) {
		robot = pRobot;
    	tankDriveOperator = new TankDriveOperator(robot);
    	toteLiftOperator = new ToteLiftOperator(robot);
    	tailLiftOperator = new TailLiftOperator(robot);
    	dragonTailOperator = new DragonTailOperator(robot);
    	forkLiftOperator = new ForkLiftOperator(robot);
	}

    /**
     * This function is called periodically during teleop
     */
    public  void teleopPeriodic() {
    	tankDriveOperator.periodic();
    	toteLiftOperator.periodic();
    	tailLiftOperator.periodic();
    	forkLiftOperator.periodic();
    	dragonTailOperator.periodic();
    }
}
