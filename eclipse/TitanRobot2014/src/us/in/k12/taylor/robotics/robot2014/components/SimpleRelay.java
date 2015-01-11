package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.Relay;

/**
 * This class treats a Spike type Relay as two independent relays, a left and right relay.
 * One SimpleRelay instance controls either the left, or the right relay on a Spike.
 * @author Taylor Robotics 2014
 */
public class SimpleRelay {
    public static final int LEFT_RELAY = 0;
    public static final int RIGHT_RELAY = 1;
    private final Relay relay;
    private final int relaySide;

    public SimpleRelay(Relay pRelay, int pRelaySide) {
        relay = pRelay;
        relaySide = pRelaySide;
    }

    public void set(boolean pRelayState) {
        boolean leftRelayState;
        boolean rightRelayState;
        Relay.Value currentValue = relay.get();

        /* Get left and right relay states */
        if (relaySide == LEFT_RELAY) {
            leftRelayState = pRelayState;
            rightRelayState = (currentValue.equals(Relay.Value.kOn) || currentValue.equals(Relay.Value.kReverse));
        }
        else {
            leftRelayState = (currentValue.equals(Relay.Value.kOn) || currentValue.equals(Relay.Value.kForward));
            rightRelayState = pRelayState;
        }

        /* Set relay to left and right relay states */
        if (leftRelayState) {
            if (rightRelayState) {
                relay.set(Relay.Value.kOn);
            }
            else {
                relay.set(Relay.Value.kForward);
            }
        }
        else if (rightRelayState) {
            relay.set(Relay.Value.kReverse);
        }
        else {
            relay.set(Relay.Value.kOff);
        }
    }
}
