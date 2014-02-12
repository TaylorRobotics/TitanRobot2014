package us.in.k12.taylor.robotics.robot2014.components;

/**
 * @author Taylor Robotics 2014
 */
public class HammerLatchedSwitch extends Switch {
    public HammerLatchedSwitch() {
        // Get the limit switches need to determine locked
    }

    public boolean getSwitchState() {
        // return value of limit switches states that indicate locked
        return false;
    }
}
