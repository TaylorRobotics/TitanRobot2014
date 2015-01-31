package org.usfirst.frc.team1760.robot.autonomous;

import org.usfirst.frc.team1760.robot.TitanRobot;
import org.usfirst.frc.team1760.robot.components.Switch;

/**
 * @author Robo-Titans Team 1790 Taylor High School 2015
 */
public class AutonomousModeFactory {
	private TitanRobot robot;
	private Switch autonomousSwitch1;
	private Switch autonomousSwitch2;
	private Switch autonomousSwitch3;

	public AutonomousModeFactory(TitanRobot pRobot) {
		robot = pRobot;
		autonomousSwitch1 = robot.getSwitchStore().getAutonomousSwitch1();
		autonomousSwitch2 = robot.getSwitchStore().getAutonomousSwitch2();
		autonomousSwitch3 = robot.getSwitchStore().getAutonomousSwitch3();
	}

	public AutonomousMode create() {
		AutonomousMode autonomousMode;
		switch(getMode()) {
		case 1:
			autonomousMode = new AutonomousMode1(robot);
			break;
		case 2:
			autonomousMode = new AutonomousMode2(robot);
			break;
		case 3:
			autonomousMode = new AutonomousMode3(robot);
			break;
		case 4:
			autonomousMode = new AutonomousMode4(robot);
			break;
		case 5:
			autonomousMode = new AutonomousMode5(robot);
			break;
		case 6:
			autonomousMode = new AutonomousMode6(robot);
			break;
		case 7:
			autonomousMode = new AutonomousMode7(robot);
			break;
		default:
			autonomousMode = new AutonomousMode0(robot);
		}
		
		return autonomousMode;
	}

	private int getMode() {
        int autonomousMode = 0;
        if (autonomousSwitch3.isSwitchOn()) {
            autonomousMode |= 0x1;
        }
        if (autonomousSwitch2.isSwitchOn()) {
            autonomousMode |= 0x2;
        }
        if (autonomousSwitch1.isSwitchOn()) {
            autonomousMode |= 0x4;
        }

		return autonomousMode;
	}
}
