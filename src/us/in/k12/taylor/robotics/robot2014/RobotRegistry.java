package us.in.k12.taylor.robotics.robot2014;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import us.in.k12.taylor.robotics.robot2014.components.DigitalInputSwitch;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;
import us.in.k12.taylor.robotics.robot2014.components.Potentiometer;
import us.in.k12.taylor.robotics.robot2014.components.PotentiometerLimitSwitch;
import us.in.k12.taylor.robotics.robot2014.components.Switch;
import us.in.k12.taylor.robotics.robot2014.factories.SpeedControllerFactory;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class RobotRegistry implements RobotParameters {
    private final SpeedController frontLeftDriveMotor;
    private final SpeedController frontRightDriveMotor;
    private final SpeedController rearLeftDriveMotor;
    private final SpeedController rearRightDriveMotor;
    private final RobotDrive robotDrive;
    private final JoystickButton reverseDirectionButton;
    private int driveDirection;
    private final JoystickButton lowSpeedButton;
    private final JoystickButton mediumSpeedButton;
    private final JoystickButton highSpeedButton;
    private final JoystickButton speedBoostButton;
    private final JoystickButton speedDragButton;

    private int driveSpeedMode; // Modes = LOW_SPEED, MEDIUM_SPEED, HIGH_SPEED (default)
    private int speedBoostDrag; // Modes = SPEED_BOOST_ON, SPEED_DRAG_ON, SPEED_BOOST_DRAG_OFF (default)

    private final TitanSpeedController pickupMotor;
    private final Switch pickupStopSwitch;
    private boolean keepBallMode;
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

    private final TitanSpeedController triggerMotor;
    private final JoystickButton triggerFireButton;
    private final JoystickButton triggerLockButton;
    private boolean fireMode;

    public RobotRegistry() {
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
        driveDirection = DEFAULT_DRIVE_DIRECTION;
        speedDragButton = new JoystickButton(leftDriveJoystick, SPEED_DRAG_BUTTON, false);
        speedBoostButton = new JoystickButton(rightDriveJoystick, SPEED_BOOST_BUTTON, false);
        lowSpeedButton = new JoystickButton(rightDriveJoystick, LOW_SPEED_BUTTON, false);
        mediumSpeedButton = new JoystickButton(rightDriveJoystick, MEDIUM_SPEED_BUTTON, true);
        highSpeedButton = new JoystickButton(rightDriveJoystick, HIGH_SPEED_BUTTON, false);

        driveSpeedMode = HIGH_SPEED;
        speedBoostDrag = SPEED_BOOST_DRAG_OFF;

        /* Pickup components */
        pickupStopSwitch = new DigitalInputSwitch(1, NORMALLY_CLOSED);
        pickupMotor = speedControllerFactory.create(PICKUP_MOTOR_PORT, PICKUP_SPEED_CONTROLLER, pickupStopSwitch, null, PICKUP_MOTOR_DIRECTION==REVERSE);
        keepBallMode = false;
        pickupButton = new JoystickButton(operatorJoystick, PICKUP_BUTTON, false);

        /* Instantiate Switch components */
        leftAutonomousModeSwitch = new DigitalInputSwitch(LEFT_AUTONOMOUS_MODE_CHANNEL, NORMALLY_OPEN);
        rightAutonomousModeSwitch = new DigitalInputSwitch(RIGHT_AUTONOMOUS_MODE_CHANNEL, NORMALLY_OPEN);

        /* Shoulder components */
        analogVoltageMeter = new AnalogChannel(ANALOG_SUPPLY_CHANNEL);
        shoulderPotentiometer = new Potentiometer(ARM_POTENTIOMETER_CHANNEL, analogVoltageMeter, 1000.0, 0.1, 0.9);
        shoulderForwardLimitSwitch = new PotentiometerLimitSwitch(shoulderPotentiometer, false, 900.0, false);
        shoulderMotor = speedControllerFactory.create(SHOULDER_MOTOR_PORT, PICKUP_SPEED_CONTROLLER, shoulderForwardLimitSwitch, null, SHOULDER_MOTOR_DIRECTION==REVERSE);

        /* Trigger components */
        triggerMotor = speedControllerFactory.create(TRIGGER_MOTOR_PORT, TRIGGER_SPEED_CONTROLLER, TRIGGER_MOTOR_DIRECTION==REVERSE);
        triggerFireButton = new JoystickButton(operatorJoystick, TRIGGER_FIRE_BUTTON, false);
        triggerLockButton = new JoystickButton(operatorJoystick, TRIGGER_LOCK_BUTTON, false);
        fireMode = false;

    }

    public Switch getPickupStopSwitch() {
        return pickupStopSwitch;
    }

    public boolean isKeepBallMode() {
        return keepBallMode;
    }

    public void setKeepBallMode(boolean keepBallMode) {
        this.keepBallMode = keepBallMode;
    }

    public boolean isFireMode() {
        return fireMode;
    }

    public void setFireMode(boolean fireMode) {
        this.fireMode = fireMode;
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

    public int getDriveDirection() {
        return driveDirection;
    }

    public void setDriveDirection(int driveDirection) {
        this.driveDirection = driveDirection;
    }

    public int getDriveSpeedMode() {
        return driveSpeedMode;
    }

    public void setDriveSpeedMode(int driveSpeedMode) {
        this.driveSpeedMode = driveSpeedMode;
    }

    public int getSpeedBoostDrag() {
        return speedBoostDrag;
    }

    public void setSpeedBoostDrag(int speedBoostDrag) {
        this.speedBoostDrag = speedBoostDrag;
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

    public TitanSpeedController getTriggerMotor() {
        return triggerMotor;
    }

    public JoystickButton getTriggerFireButton() {
        return triggerFireButton;
    }

    public JoystickButton getTriggerLockButton() {
        return triggerLockButton;
    }
}
