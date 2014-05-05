package us.in.k12.taylor.robotics.robot2014;

import edu.wpi.first.wpilibj.Talon;
import us.in.k12.taylor.robotics.robot2014.components.SimpleRelay;

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

    /* Digital Input Mapping */
    int ARM_UP_LIMIT_SWITCH_CHANNEL = 1;
    int ARM_DOWN_LIMIT_SWITCH_CHANNEL = 2;
    int BALL_STOP_SWITCH_CHANNEL = 3;
    int HAMMER_LATCHED_SWITCH_CHANNEL = 4;
    int HAMMER_LOCKED_SWITCH_CHANNEL = 5;
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
    Class FRONT_LEFT_DRIVE_SPEED_CONTROLLER = Talon.class;
    Class FRONT_RIGHT_DRIVE_SPEED_CONTROLLER = Talon.class;
    Class REAR_LEFT_DRIVE_SPEED_CONTROLLER = Talon.class;
    Class REAR_RIGHT_DRIVE_SPEED_CONTROLLER = Talon.class;
    Class PICKUP_SPEED_CONTROLLER = Talon.class;
    Class SHOULDER_SPEED_CONTROLLER = Talon.class;
    Class LATCH_SPEED_CONTROLLER = Talon.class;

    /* Motor direction - Change motor direction if wired backwards */
    int FRONT_LEFT_DRIVE_MOTOR_DIRECTION = REVERSE;
    int FRONT_RIGHT_DRIVE_MOTOR_DIRECTION = REVERSE;
    int REAR_LEFT_DRIVE_MOTOR_DIRECTION = REVERSE;
    int REAR_RIGHT_DRIVE_MOTOR_DIRECTION = REVERSE;
    int PICKUP_MOTOR_DIRECTION = FORWARD;
    int SHOULDER_MOTOR_DIRECTION = FORWARD;
    int LATCH_MOTOR_DIRECTION = FORWARD;

    /* SpeedController port mapping */
    int SHOULDER_MOTOR_PORT = 1;
    int LATCH_MOTOR_PORT = 2;
    int PICKUP_MOTOR_PORT = 8;
    int FRONT_RIGHT_DRIVE_MOTOR_PORT = 4;
    int REAR_RIGHT_DRIVE_MOTOR_PORT = 5;
    int FRONT_LEFT_DRIVE_MOTOR_PORT = 6;
    int REAR_LEFT_DRIVE_MOTOR_PORT = 7;

    /* Joystick mapping */
    int LEFT_DRIVE_JOYSTICK = 1;
    int RIGHT_DRIVE_JOYSTICK = 2;
    int OPERATOR_JOYSTICK = 3;

    /* Left drive joystick button mapping */
    int SPEED_BOOST_BUTTON = 1;
    int REVERSE_DRIVE_DIRECTION_BUTTON = 2;
    int LOW_SPEED_BUTTON = 3;
    int MEDIUM_SPEED_BUTTON = 4;
    int HIGH_SPEED_BUTTON = 5;

    /* Right drive joystick button mapping */
    int SPEED_DRAG_BUTTON = 1;
    int PARK_ROBOT_BUTTON = 11;

    /* Operator Joystick button mapping */
    int HAMMER_FIRE_BUTTON = 1;
    int PICKUP_BUTTON = 2;
    int SHOULDER_MANUAL_MODE_BUTTON = 3;
    int LATCH_LOCK_BUTTON = 4;
    int REVERSE_PICKUP_BUTTON = 5;
    int AUTO_SHOOT_BUTTON = 6;
    int SHOULDER_PICKUP_POSITION_BUTTON = 7;
    int SHOULDER_LOW_SHOT_POSITION_BUTTON = 8;
    int SHOULDER_HIGH_SHOT_POSITION_BUTTON = 9;
    int SHOULDER_START_POSITION_BUTTON = 10;
    int SHOULDER_SEEK_SHOT_BUTTON = 11;

    /* Pickup Motor constants */
    double PICKUP_MOTOR_SPEED = 1.0;
    double PICKUP_MOTOR_KEEP_BALL_SPEED = 0.3;
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

    /* Analog Card constants */
    int ANALOG_SUPPLY_CHANNEL = 1;
    int ARM_POTENTIOMETER_CHANNEL = 2;
    int DISTANCE_SENSOR_CHANNEL = 3;

    /* Shooter Hammer Motor Speeds */
    double HAMMER_FIRE_SPEED = 0.75;
    double HAMMER_LATCH_SPEED = -0.75;
    long PICKUP_MOTOR_FIRE_TIME = 2000;
    double PICKUP_MOTOR_FIRE_SPEED = -1.0;
    double HAMMER_FIRE_LIMIT = 400.0;

    /* Auto Shoot mode constants */
    // was 43.0, 6.0
    double AUTO_SHOOT_DISTANCE = 125.0;
    double AUTO_SHOOT_DISTANCE_TOLERANCE = 40.0;

    /* Shoulder motor constants */
    double SHOULDER_POSITION_TOLERANCE = 10.0;
    double SHOULDER_SPEED_INCREMENT = 0.05; // Increment speed by this amount
    long SHOULDER_SPEED_INTERVAL = 100; // Check speed every 100 ms
    long SHOULDER_SAFETY_TIME_LIMIT = 6000; // Cancel servo if cannot reach target in 6000 ms
    double MINIMUM_SHOULDER_SPEED = 0.1;
    double TARGET_ARM_SPEED = 15;
    double MAXIMUM_SHOULDER_SPEED = 0.50;

    /* Digital Output Channel Mapping */
    int INDICATOR_LIGHTS_CHANNEL = 4;

    /* Indicator light relays */
    int SHOOT_DISTANCE_LIGHT_RELAY = SimpleRelay.LEFT_RELAY;
    int LATCH_LOCKED_LIGHT_RELAY = SimpleRelay.RIGHT_RELAY;

    int SHOULDER_JOYSTICK_MODE = 0;
    int SHOULDER_SERVO_MODE = 1;
    int SHOULDER_SEEK_MODE = 2;

    /* Shoulder potentiometer constants */
    int SHOULDER_POTENTIOMETER_DIRECTION = FORWARD;
    double SHOULDER_POTENTIOMETER_MINIMUM_EDGE = 0.015;
    double SHOULDER_POTENTIOMETER_MAXIMUM_EDGE = 0.985;
    double SHOULDER_POTENTIOMETER_SCALE = 1000.0;

    /* Shoulder position constants */
    double SHOULDER_PICKUP_POSITION = 200.0;
    double SHOULDER_LOW_SHOT_POSITION = 300.0;
    double SHOULDER_HIGH_SHOT_POSITION = 980.0;
    double SHOULDER_START_POSITION = 980.0;
}
