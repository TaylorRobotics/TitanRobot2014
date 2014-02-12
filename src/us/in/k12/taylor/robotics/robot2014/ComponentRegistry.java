package us.in.k12.taylor.robotics.robot2014;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import us.in.k12.taylor.robotics.robot2014.components.DigitalInputSwitch;
import us.in.k12.taylor.robotics.robot2014.components.SimpleRelay;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;
import us.in.k12.taylor.robotics.robot2014.components.MaxSonarDistanceSensor;
import us.in.k12.taylor.robotics.robot2014.components.MaxSonarDistanceSwitch;
import us.in.k12.taylor.robotics.robot2014.components.Potentiometer;
import us.in.k12.taylor.robotics.robot2014.components.PotentiometerLimitSwitch;
import us.in.k12.taylor.robotics.robot2014.components.Switch;
import us.in.k12.taylor.robotics.robot2014.components.TriggerLockedSwitch;
import us.in.k12.taylor.robotics.robot2014.factories.SpeedControllerFactory;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class ComponentRegistry implements RobotParameters {
    private final SpeedController frontLeftDriveMotor;
    private final SpeedController frontRightDriveMotor;
    private final SpeedController rearLeftDriveMotor;
    private final SpeedController rearRightDriveMotor;
    private final RobotDrive robotDrive;
    private final JoystickButton reverseDirectionButton;
    private final JoystickButton lowSpeedButton;
    private final JoystickButton mediumSpeedButton;
    private final JoystickButton highSpeedButton;
    private final JoystickButton speedBoostButton;
    private final JoystickButton speedDragButton;

    private final TitanSpeedController pickupMotor;
    private final Switch pickupStopSwitch;
    private final JoystickButton pickupButton;

    private final Joystick leftDriveJoystick;
    private final Joystick rightDriveJoystick;
    private final Joystick operatorJoystick;

    private final Switch leftAutonomousModeSwitch;
    private final Switch rightAutonomousModeSwitch;

    private final AnalogChannel analogVoltageMeter;
    private final Potentiometer shoulderPotentiometer;
    private final TitanSpeedController shoulderMotor;
    private final PotentiometerLimitSwitch shoulderForwardLimitSwitch;
    private final PotentiometerLimitSwitch shoulderReverseLimitSwitch;
    private final JoystickButton pickupPositionButton;
    private final JoystickButton lowShotPositionButton;
    private final JoystickButton highShotPositionButton;
    private final JoystickButton seekShotButton;
    private final JoystickButton startPositionButton;
    private final JoystickButton manualPositionButton;
    private final MaxSonarDistanceSensor distanceSensor;
    private final MaxSonarDistanceSwitch shootingDistanceSwitch;

    private final TitanSpeedController triggerMotor;
    private final JoystickButton triggerFireButton;
    private final JoystickButton triggerLockButton;
    private final Switch triggerLockedSwitch;
    private final JoystickButton autoShootButton;
    private final JoystickButton forceTriggerFireButton;

    private final SimpleRelay shootDistanceLightRelay;
    private final SimpleRelay triggerLockedLightRelay;

    private final JoystickButton parkRobotButton;

    public ComponentRegistry() {
        /* Instantiate Drive components */
        leftDriveJoystick = new Joystick(LEFT_DRIVE_JOYSTICK);
        rightDriveJoystick = new Joystick(RIGHT_DRIVE_JOYSTICK);
        operatorJoystick = new Joystick(OPERATOR_JOYSTICK);
        SpeedControllerFactory speedControllerFactory = new SpeedControllerFactory();
        frontLeftDriveMotor = speedControllerFactory.create(FRONT_LEFT_DRIVE_MOTOR_PORT, FRONT_LEFT_DRIVE_SPEED_CONTROLLER, (FRONT_LEFT_DRIVE_MOTOR_DIRECTION==REVERSE));
        frontRightDriveMotor = speedControllerFactory.create(FRONT_RIGHT_DRIVE_MOTOR_PORT, FRONT_RIGHT_DRIVE_SPEED_CONTROLLER, (FRONT_RIGHT_DRIVE_MOTOR_DIRECTION==REVERSE));
        rearLeftDriveMotor = speedControllerFactory.create(REAR_LEFT_DRIVE_MOTOR_PORT, REAR_LEFT_DRIVE_SPEED_CONTROLLER, (REAR_LEFT_DRIVE_MOTOR_DIRECTION==REVERSE));
        rearRightDriveMotor = speedControllerFactory.create(REAR_RIGHT_DRIVE_MOTOR_PORT, REAR_RIGHT_DRIVE_SPEED_CONTROLLER, (REAR_RIGHT_DRIVE_MOTOR_DIRECTION==REVERSE));
        robotDrive = new RobotDrive(frontLeftDriveMotor, rearLeftDriveMotor, frontRightDriveMotor, rearRightDriveMotor);
        reverseDirectionButton = new JoystickButton(leftDriveJoystick, REVERSE_DRIVE_DIRECTION_BUTTON, false);
        speedDragButton = new JoystickButton(leftDriveJoystick, SPEED_DRAG_BUTTON, false);
        speedBoostButton = new JoystickButton(rightDriveJoystick, SPEED_BOOST_BUTTON, false);
        lowSpeedButton = new JoystickButton(rightDriveJoystick, LOW_SPEED_BUTTON, false);
        mediumSpeedButton = new JoystickButton(rightDriveJoystick, MEDIUM_SPEED_BUTTON, true);
        highSpeedButton = new JoystickButton(rightDriveJoystick, HIGH_SPEED_BUTTON, false);

        /* Pickup components */
        pickupStopSwitch = new DigitalInputSwitch(1, NORMALLY_CLOSED);
        pickupMotor = speedControllerFactory.create(PICKUP_MOTOR_PORT, PICKUP_SPEED_CONTROLLER, pickupStopSwitch, null, PICKUP_MOTOR_DIRECTION==REVERSE);
        pickupButton = new JoystickButton(operatorJoystick, PICKUP_BUTTON, false);

        /* Instantiate Switch components */
        leftAutonomousModeSwitch = new DigitalInputSwitch(LEFT_AUTONOMOUS_MODE_CHANNEL, NORMALLY_OPEN);
        rightAutonomousModeSwitch = new DigitalInputSwitch(RIGHT_AUTONOMOUS_MODE_CHANNEL, NORMALLY_OPEN);

        /* Shoulder components */
        analogVoltageMeter = new AnalogChannel(ANALOG_SUPPLY_CHANNEL);
        shoulderPotentiometer = new Potentiometer(ARM_POTENTIOMETER_CHANNEL, analogVoltageMeter, SHOULDER_POTENTIOMETER_DIRECTION==REVERSE, SHOULDER_POTENTIOMETER_SCALE, SHOULDER_POTENTIOMETER_MINIMUM_EDGE, SHOULDER_POTENTIOMETER_MAXIMUM_EDGE);
        shoulderForwardLimitSwitch = new PotentiometerLimitSwitch(shoulderPotentiometer, false, 900.0, false);
        shoulderReverseLimitSwitch = new PotentiometerLimitSwitch(shoulderPotentiometer, true, 100.0, false);
// Replace with correct limit switches
        shoulderMotor = speedControllerFactory.create(SHOULDER_MOTOR_PORT, PICKUP_SPEED_CONTROLLER, shoulderReverseLimitSwitch, shoulderForwardLimitSwitch, SHOULDER_MOTOR_DIRECTION==REVERSE);
        pickupPositionButton = new JoystickButton(operatorJoystick, SHOULDER_PICKUP_POSITION_BUTTON, false);
        lowShotPositionButton = new JoystickButton(operatorJoystick, SHOULDER_LOW_SHOT_POSITION_BUTTON, false);
        highShotPositionButton = new JoystickButton(operatorJoystick, SHOULDER_HIGH_SHOT_POSITION_BUTTON, false);
        seekShotButton = new JoystickButton(operatorJoystick, SHOULDER_SEEK_SHOT_BUTTON, false);
        startPositionButton = new JoystickButton(operatorJoystick, SHOULDER_START_POSITION_BUTTON, false);
        manualPositionButton = new JoystickButton(operatorJoystick, SHOULDER_MANUAL_MODE_BUTTON, false);
        distanceSensor = new MaxSonarDistanceSensor(DISTANCE_SENSOR_CHANNEL, analogVoltageMeter);
        shootingDistanceSwitch = new MaxSonarDistanceSwitch(distanceSensor, AUTO_SHOOT_DISTANCE, AUTO_SHOOT_DISTANCE_TOLERANCE, false);

        /* Trigger components */
        triggerMotor = speedControllerFactory.create(TRIGGER_MOTOR_PORT, TRIGGER_SPEED_CONTROLLER, TRIGGER_MOTOR_DIRECTION==REVERSE);
        triggerFireButton = new JoystickButton(operatorJoystick, TRIGGER_FIRE_BUTTON, false);
        triggerLockButton = new JoystickButton(operatorJoystick, TRIGGER_LOCK_BUTTON, false);
        triggerLockedSwitch = new TriggerLockedSwitch(); // Pass any component switches needed
        autoShootButton = new JoystickButton(operatorJoystick, AUTO_SHOOT_BUTTON, false);
        forceTriggerFireButton = new JoystickButton(operatorJoystick, FORCE_TRIGGER_FIRE_BUTTON, false);

        /* Indicator light components */
        Relay indicatorLightsSpikeRelay = new Relay(INDICATOR_LIGHTS_CHANNEL);
        shootDistanceLightRelay = new SimpleRelay(indicatorLightsSpikeRelay, SHOOT_DISTANCE_LIGHT_RELAY);
        triggerLockedLightRelay = new SimpleRelay(indicatorLightsSpikeRelay, TRIGGER_LOCKED_LIGHT_RELAY);

        parkRobotButton = new JoystickButton(rightDriveJoystick, PARK_ROBOT_BUTTON, false);
    }

    public Switch getPickupStopSwitch() {
        return pickupStopSwitch;
    }

    public JoystickButton getPickupButton() {
        return pickupButton;
    }

    public TitanSpeedController getPickupMotor() {
        return pickupMotor;
    }

    public RobotDrive getRobotDrive() {
        return robotDrive;
    }

    public SpeedController getLeftDriveMotor() {
        return frontLeftDriveMotor;
    }

    public SpeedController getRightDriveMotor() {
        return frontRightDriveMotor;
    }

    public JoystickButton getReverseDirectionButton() {
        return reverseDirectionButton;
    }

    public JoystickButton getLowSpeedButton() {
        return lowSpeedButton;
    }

    public JoystickButton getMediumSpeedButton() {
        return mediumSpeedButton;
    }

    public JoystickButton getHighSpeedButton() {
        return highSpeedButton;
    }

    public JoystickButton getSpeedBoostButton() {
        return speedBoostButton;
    }

    public JoystickButton getSpeedDragButton() {
        return speedDragButton;
    }

    public Joystick getLeftDriveJoystick() {
        return leftDriveJoystick;
    }

    public Joystick getRightDriveJoystick() {
        return rightDriveJoystick;
    }

    public Joystick getOperatorJoystick() {
        return operatorJoystick;
    }

    public Switch getLeftAutonomousModeSwitch() {
        return leftAutonomousModeSwitch;
    }

    public Switch getRightAutonomousModeSwitch() {
        return rightAutonomousModeSwitch;
    }

    public AnalogChannel getAnalogVoltageMeter() {
        return analogVoltageMeter;
    }

    public Potentiometer getShoulderPotentiometer() {
        return shoulderPotentiometer;
    }

    public TitanSpeedController getShoulderMotor() {
        return shoulderMotor;
    }

    public PotentiometerLimitSwitch getShoulderForwardLimitSwitch() {
        return shoulderForwardLimitSwitch;
    }

    public JoystickButton getPickupPositionButton() {
        return pickupPositionButton;
    }

    public JoystickButton getLowShotPositionButton() {
        return lowShotPositionButton;
    }

    public JoystickButton getHighShotPositionButton() {
        return highShotPositionButton;
    }

    public JoystickButton getSeekShotButton() {
        return seekShotButton;
    }

    public JoystickButton getStartPositionButton() {
        return startPositionButton;
    }

    public JoystickButton getManualPositionButton() {
        return manualPositionButton;
    }

    public TitanSpeedController getTriggerMotor() {
        return triggerMotor;
    }

    public JoystickButton getTriggerFireButton() {
        return triggerFireButton;
    }

    public JoystickButton getAutoShootButton() {
        return autoShootButton;
    }

    public JoystickButton getTriggerLockButton() {
        return triggerLockButton;
    }

    public SimpleRelay getShootDistanceLightRelay() {
        return shootDistanceLightRelay;
    }

    public SimpleRelay getTriggerLockedLightRelay() {
        return triggerLockedLightRelay;
    }

    public Switch getTriggerLockedSwitch() {
        return triggerLockedSwitch;
    }

    public JoystickButton getForceTriggerFireButton() {
        return forceTriggerFireButton;
    }

    public MaxSonarDistanceSensor getDistanceSensor() {
        return distanceSensor;
    }

    public MaxSonarDistanceSwitch getShootingDistanceSwitch() {
        return shootingDistanceSwitch;
    }

    public JoystickButton getParkRobotButton() {
        return parkRobotButton;
    }

}