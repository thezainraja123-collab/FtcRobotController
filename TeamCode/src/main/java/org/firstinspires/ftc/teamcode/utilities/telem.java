package org.firstinspires.ftc.teamcode.utilities;



/* CREATED BY ZAIN R.

 This file includes shortcuts for accessing commonly used telemetry. To add, follow the same
 structure as used below and RECORD ALL CHANGES IN THIS COMMENT

 8-3-26 -  Zain added spacer, bigSpacer, checkmark, error, warning, and newHeading


*/


import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

public class telem {
    GoBildaPinpointDriver pinpoint;


    public static final String spacer = "----------------";
    public static final String bigSpacer = "================";

    public static final String checkmark = "✔";
    public static final String error = "ERROR";
    public static final String warning = "!!!";

    public static final String newHeading(String name) {
        return "---> " + name + " <---";
    }



}
