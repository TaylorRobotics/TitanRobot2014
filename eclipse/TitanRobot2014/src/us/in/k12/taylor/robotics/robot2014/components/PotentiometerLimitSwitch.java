package us.in.k12.taylor.robotics.robot2014.components;

/**
 * @author Taylor Robotics 2014
 */
public class PotentiometerLimitSwitch extends Switch {
    private final Potentiometer potentiometer;
    private final boolean lowLimit;
    private final double limitValue;

    public PotentiometerLimitSwitch(Potentiometer pPotentiometer, boolean pLowLimit, double pLimitValue, boolean pForceStateChange) {
        potentiometer = pPotentiometer;
        lowLimit = pLowLimit;
        limitValue = pLimitValue;
        setForceStateChange(pForceStateChange);
    }
    
    public boolean getSwitchState() {
        boolean switchOn;
        double value = potentiometer.getValue();
        if (lowLimit) {
            switchOn = (value <= limitValue);
        }
        else {
            switchOn = (value >= limitValue);
        }
        return switchOn;
    }
}
