package us.in.k12.taylor.robotics.robot2014.components;

import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 * @author Taylor Robotics 2014
 */
public class MessageDisplay {
    private final DriverStationLCD driverStationLCD;
    private final DriverStationLCD.Line[] displayLines;

    public MessageDisplay() {
        driverStationLCD = DriverStationLCD.getInstance();
        displayLines = new DriverStationLCD.Line[6];
        displayLines[0] = DriverStationLCD.Line.kUser1;
        displayLines[1] = DriverStationLCD.Line.kUser2;
        displayLines[2] = DriverStationLCD.Line.kUser3;
        displayLines[3] = DriverStationLCD.Line.kUser4;
        displayLines[4] = DriverStationLCD.Line.kUser5;
        displayLines[5] = DriverStationLCD.Line.kUser6;
    }

    public void update() {
        driverStationLCD.updateLCD();
    }

    public void setLine(int pLineNumber, int pStartingColumn, String pText) {
        if ((pLineNumber >= 0) && (pLineNumber < 6)) {
            driverStationLCD.println(displayLines[pLineNumber], pStartingColumn, pText);
        }
    }

    public void setLine(int pLineNumber, String pText) {
        if ((pLineNumber >= 0) && (pLineNumber < 6)) {
            driverStationLCD.println(displayLines[pLineNumber], 1, pText);
        }
    }
}
