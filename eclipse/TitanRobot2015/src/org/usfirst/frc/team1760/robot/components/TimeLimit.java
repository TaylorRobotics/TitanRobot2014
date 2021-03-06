package org.usfirst.frc.team1760.robot.components;

/**
 * This class indicates if the amount of time since the limit was set has been reached.
 * 
 * @author Robo-Titans Team 1760 Taylor High School 2015
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
