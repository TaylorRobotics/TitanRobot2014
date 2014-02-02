package us.in.k12.taylor.robotics.robot2014;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

/**
 * @author Taylor Robotics 2014
 */
public interface RobotParameters {
    /* Set to true to enable watchdog, false to disable watchdog */
    boolean WATCHDOG_ENABLE = true;

    /* Switch constants */
    int NORMALLY_OPEN = 0;
    int NORMALLY_CLOSED = 1;

    /* InfraredSwitch Constants */
    int ON_HIGH = 0;
    int ON_LOW = 1;

    /* Autonomous mode toggle switches */
    int LEFT_AUTONOMOUS_MODE_CHANNEL = 9;
    int RIGHT_AUTONOMOUS_MODE_CHANNEL = 10;

    /*MaxSonarDistanceSensor constants*/
    double MAXSONAR_INCH_CONSTANT = 512.0;

    /* Delay for teleoperated and autonomous loops */
    double  TELEOPERATED_LOOP_DELAY = 0.005;
    double  AUTONOMOUS_LOOP_DELAY = 0.005;

    /* Drive Motor Direction Constants */
    int FORWARD = 1;
    int REVERSE = 2;
    int DEFAULT_DRIVE_DIRECTION = FORWARD;

    /* Speed Controller Class Mapping */
    Class LEFT_DRIVE_SPEED_CONTROLLER = Talon.class;
    Class RIGHT_DRIVE_SPEED_CONTROLLER = Talon.class;
    Class PICKUP_SPEED_CONTROLLER = Victor.class;

    /* Motor direction - Change motor direction if wired backwards */
    int LEFT_DRIVE_MOTOR_DIRECTION = FORWARD;
    int RIGHT_DRIVE_MOTOR_DIRECTION = FORWARD;
    int PICKUP_MOTOR_DIRECTION = FORWARD;

    /* SpeedController port mapping */
    int LEFT_DRIVE_MOTOR_PORT = 1;
    int RIGHT_DRIVE_MOTOR_PORT = 2;
    int PICKUP_MOTOR_PORT = 3;

    /* Joystick mapping */
    int LEFT_DRIVE_JOYSTICK = 1;
    int RIGHT_DRIVE_JOYSTICK = 2;
    int OPERATOR_JOYSTICK = 3;

    /* Left drive joystick button mapping */
    int SPEED_DRAG_BUTTON = 1;
    int REVERSE_DRIVE_DIRECTION_BUTTON = 3;

    /* Right drive joystick button mapping */
    int SPEED_BOOST_BUTTON = 1;
    int LOW_SPEED_BUTTON = 4;
    int MEDIUM_SPEED_BUTTON = 2;
    int HIGH_SPEED_BUTTON = 5;

    /* Operation Joystick button mapping */
    int PICKUP_BUTTON = 2;

    /* Pickup Motor constants */
    double PICKUP_MOTOR_SPEED = 0.5;
    long PICKUP_TRY_TIME = 1500;

    /* Speed Mode Constants (ENUM) */
    int LOW_SPEED = 1;
    int MEDIUM_SPEED = 2;
    int HIGH_SPEED = 3;

    /* Max speed for speed modes */
    double MAX_LOW_SPEED = 0.4;
    double MAX_MEDIUM_SPEED = 0.75;
    double MAX_HIGH_SPEED = 1.0;

    /* Boost and drag for drive short speed increase and decrease */
    int SPEED_BOOST_DRAG_OFF = 0;
    int SPEED_BOOST_ON = 1;
    int SPEED_DRAG_ON = 2;
    double BOOST_SPEED = 0.5;
    double DRAG_SPEED = -0.25;
}
