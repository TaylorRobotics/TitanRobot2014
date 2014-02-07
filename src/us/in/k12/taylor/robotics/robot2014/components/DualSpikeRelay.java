package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.Relay;

/**
 * @author Taylor Robotics 2014
 */
public class DualSpikeRelay extends Relay {
    private boolean firstRelayState;
    private boolean secondRelayState;

    public DualSpikeRelay(int pChannel) {
        super(pChannel);
        firstRelayState = false;
        secondRelayState = false;
    }

    public void setFirstRelay(boolean pFirstRelayOn) {
        firstRelayState = pFirstRelayOn;
        setRelayStates();
    }

    public void setSecondRelay(boolean pSecondRelayOn) {
        secondRelayState = pSecondRelayOn;
        setRelayStates();
    }

    private void setRelayStates() {
        if (firstRelayState) {
            if (secondRelayState) {
                set(Relay.Value.kOn);
            }
            else {
                set(Relay.Value.kForward);
            }
        }
        else if (secondRelayState) {
            set(Relay.Value.kReverse);
        }
        else {
            set(Relay.Value.kOff);
        }
    }
}
