package org.firstinspires.ftc.teamcode.utilities;


/*
 UPDATER UTILITY CLASS
 This class includes everything that needs to be refreshed every time a loop is run. Things that can
 go inside it are things like pinpoint.update(); and things like that.

 LOG ALL UPDATES/ADDITIONS HERE:
 8-3-26: Class created, added pinpoint updater
 8-15-26: Pinpoint works, is successfully updated and works when called within a loop.

*/

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

public class updater {

    static GoBildaPinpointDriver pinpoint;

    public static void update() {

        pinpoint.update();

    }
}
