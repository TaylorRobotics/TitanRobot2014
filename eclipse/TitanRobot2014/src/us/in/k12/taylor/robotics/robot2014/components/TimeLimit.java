package us.in.k12.taylor.robotics.robot2014.components;

/**
 * @author Taylor Robotics 2014
 */
public class TimeLimit {
    private long finishTime;

    public TimeLimit() {
        this(0);
    }

    public TimeLimit(long pTimeLimit) {
        setTimeLimit(pTimeLimit);
    }

    public void setTimeLimit(long pTimeLimit) {
        finishTime = System.currentTimeMillis() + pTimeLimit;
    }

    public boolean isTimeLimitReached() {
        return (System.currentTimeMillis() >= finishTime);
    }
}
