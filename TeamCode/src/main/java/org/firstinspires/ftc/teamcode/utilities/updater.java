package org.firstinspires.ftc.teamcode.utilities;


/*
 UPDATER UTILITY CLASS
 This class includes everything that needs to be refreshed every time a loop is run. Things that can
 go inside it are things like pinpoint.update(); and things like that.

 LOG ALL UPDATES/ADDITIONS HERE:
 8-3-26: Class created, added pinpoint updater

*/

import org.firstinspires.ftc.teamcode.utilities.RobotHardware;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

public class updater {

    GoBildaPinpointDriver pinpoint;

    public void update() {
        
        pinpoint.update();
    }
}
