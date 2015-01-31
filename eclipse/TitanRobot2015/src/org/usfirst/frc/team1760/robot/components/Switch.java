package org.usfirst.frc.team1760.robot.components;

/**
 * This class defines a Switch which may be designated as NORMALLY_OPEN or NORMALLY_CLOSED,
 * and provides a method to indicate when a switch is on or off.  It also provides a method
 * which identifies if the last check for the switch state resulted in a change in switch state.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
 */
public abstract class Switch {
	public static final int NORMALLY_OPEN = 0;
	public static final int NORMALLY_CLOSED = 1;

    protected boolean currentState;
    protected boolean stateChange;

    public abstract boolean getSwitchState();

    protected void setForceStateChange(boolean pForceStateChange) {
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
