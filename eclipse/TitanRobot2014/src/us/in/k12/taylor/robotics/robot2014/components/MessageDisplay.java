package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Taylor Robotics 2014
 */
public class MessageDisplay {
    private final String[] displayLines;

    public MessageDisplay() {
        displayLines = new String[10];
        for (int i = 0; i < displayLines.length; i++) {
        	displayLines[i] = "DB/String " + i;
        }
    }

    public void setLine(int pLineNumber, String pText) {
        if ((pLineNumber >= 0) && (pLineNumber < displayLines.length)) {
        	SmartDashboard.putString(displayLines[pLineNumber], pText);
        }
    }
}
