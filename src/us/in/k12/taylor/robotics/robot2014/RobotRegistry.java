package us.in.k12.taylor.robotics.robot2014;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import us.in.k12.taylor.robotics.robot2014.components.DigitalInputSwitch;
import us.in.k12.taylor.robotics.robot2014.components.JoystickButton;
import us.in.k12.taylor.robotics.robot2014.components.Switch;
import us.in.k12.taylor.robotics.robot2014.factories.SpeedControllerFactory;
import us.in.k12.taylor.robotics.robot2014.factories.TitanSpeedController;

/**
 * @author Taylor Robotics 2014
 */
public class RobotRegistry implements RobotParameters {
    private final SpeedController leftDriveMotor;
    private final SpeedController rightDriveMotor;
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
    
    public RobotRegistry() {
        /* Instantiate Drive components */
        leftDriveJoystick = new Joystick(LEFT_DRIVE_JOYSTICK);
        rightDriveJoystick = new Joystick(RIGHT_DRIVE_JOYSTICK);
        operatorJoystick = new Joystick(OPERATOR_JOYSTICK);
        SpeedControllerFactory speedControllerFactory = new SpeedControllerFactory();
        leftDriveMotor = speedControllerFactory.create(LEFT_DRIVE_MOTOR_PORT, LEFT_DRIVE_SPEED_CONTROLLER, (LEFT_DRIVE_MOTOR_DIRECTION==REVERSE));
        rightDriveMotor = speedControllerFactory.create(RIGHT_DRIVE_MOTOR_PORT, RIGHT_DRIVE_SPEED_CONTROLLER, (RIGHT_DRIVE_MOTOR_DIRECTION==REVERSE));
        robotDrive = new RobotDrive(leftDriveMotor, rightDriveMotor);
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
        return leftDriveMotor;
    }

    public SpeedController getRightDriveMotor() {
        return rightDriveMotor;
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
}