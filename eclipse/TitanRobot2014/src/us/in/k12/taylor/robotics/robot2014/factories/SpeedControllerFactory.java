package us.in.k12.taylor.robotics.robot2014.factories;

import us.in.k12.taylor.robotics.robot2014.components.Switch;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

/**
 * @author Taylor Robotics 2014
 */
public class SpeedControllerFactory  {
    public TitanSpeedController create(int pChannel, Class<?> pClass, boolean pInvertDirection) {
        return create(pChannel, pClass, null, null, pInvertDirection);
    }

    public TitanSpeedController create(int pChannel, Class<?> pClass, Switch pForwardLimitSwitch,
            Switch pReverseLimitSwitch, boolean pInvertDirection) {
        TitanSpeedController controller;
        if (pClass.equals(Talon.class)) {
            controller = new TitanSpeedController(new Talon(pChannel), pForwardLimitSwitch, pReverseLimitSwitch, pInvertDirection);
        }
        else if (pClass.equals(Victor.class)) {
            controller = new TitanSpeedController(new Victor(pChannel), pForwardLimitSwitch, pReverseLimitSwitch, pInvertDirection);
        }
        else {
            controller = new TitanSpeedController(new Jaguar(pChannel), pForwardLimitSwitch, pReverseLimitSwitch, pInvertDirection);
        }

        return controller;
    }
}
