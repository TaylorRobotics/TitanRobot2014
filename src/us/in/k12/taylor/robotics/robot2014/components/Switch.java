package us.in.k12.taylor.robotics.robot2014.components;

/**
 * @author Taylor Robotics 2014
 */
public abstract class Switch {
    protected boolean currentState;
    protected boolean stateChange;

    public abstract boolean getSwitchState();

    public Switch(boolean pForceStateChange) {
        /* If pForceStateChange is true, the next call to getStateChange() will return true */
        setForceStateChange(pForceStateChange);
    }

    private void setForceStateChange(boolean pForceStateChange) {
        if (pForceStateChange) {
            currentState = !getSwitchState();
        }
        else {
            currentState = getSwitchState();
        }
    }

    public boolean isSwitchOn() {
        boolean newState = getSwitchState();
        stateChange = (currentState != newState);
        currentState = newState;
        return currentState;
    }

    /**
     * Gets whether the last call to isSwitchOn resulted in a button state change.
     * It is important that isSwitchOn is called before a call is made to this method.
     * @return A boolean of true if the last call to isSwitchOn() resulted in a state chagne
     */
    public boolean getStateChange() {
        return stateChange;
    }
}
