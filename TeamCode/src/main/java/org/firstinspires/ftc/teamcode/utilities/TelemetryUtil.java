package org.firstinspires.ftc.teamcode.utilities;

/*
 Shortcuts for commonly used telemetry strings. To add, follow the same
 structure as used below and RECORD ALL CHANGES IN THIS COMMENT.

 8-3-26  - Zain added spacer, bigSpacer, checkmark, error, warning, and newHeading
 8-16-26 - Renamed from telem -> TelemetryUtil (was clashing in spirit with the
           SDK's own Telemetry type) and moved into utilities/.
*/

public class TelemetryUtil {

    public static final String spacer = "----------------";
    public static final String bigSpacer = "================";

    public static final String checkmark = "✔";
    public static final String error = "ERROR";
    public static final String warning = "!!!";

    public static String newHeading(String name) {
        return "---> " + name + " <---";
    }
}
