## TeamCode Module

Welcome!

This module, TeamCode, is the place where you will write/paste the code for your team's
robot controller App.

## Folder Structure

```
teamcode/
  robot/
    Robot.java              <- wires up every subsystem, single init()/update()
    Constants.java           <- hardware config names + tuning values
    subsystems/
      Drivetrain.java         <- mecanum motors, drive(y, x, rx)
      Odometry.java            <- wraps the Pinpoint
  opmodes/
    common/
      BaseTeleOp.java          <- shared driving + telemetry for every TeleOp
    teleop/
      MecanumTeleOp.java       <- the real competition/practice TeleOp
    test/
      PinpointTest.java        <- odometry sanity check
      HardwareTest.java        <- keyboard motor jog tool
  utilities/
    TelemetryUtil.java        <- generic, hardware-agnostic helpers (telemetry formatting, etc)
```

The rule of thumb: **`robot/` is "what the robot has and how it moves", `opmodes/` is "what the
driver picks on the Driver Station", `utilities/` is generic helpers that don't know or care what
robot they're running on.**

### Adding your own TeleOp

Every driver-controlled OpMode should extend `BaseTeleOp` (in `opmodes/common/`) instead of
starting from scratch. It already handles robot init, driving (left stick = move, right stick =
turn), odometry updates, and telemetry every loop — so your driving feel is guaranteed to match
every other TeleOp on the team.

```java
package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.opmodes.common.BaseTeleOp;

@TeleOp(name = "My Experiment")
public class MyExperiment extends BaseTeleOp {

    @Override
    protected void driverControls() {
        // Called once per loop, after driving is already handled.
        // robot is available here — try things out!
    }
}
```

Put it in `opmodes/teleop/` if it's meant to actually be driven at a match or practice, or
`opmodes/test/` if it's just for poking at hardware. You can't accidentally break driving here —
`driverControls()` runs after `BaseTeleOp` has already applied stick input to the drivetrain.

### Adding a new mechanism (arm, intake, etc)

1. Add its hardware config name to `robot/Constants.java`.
2. Create a class for it under `robot/subsystems/`, following `Drivetrain.java`/`Odometry.java` as
   a template — it owns its own motor/servo and exposes simple methods (`in()`, `out()`, `stop()`,
   etc), not raw hardware objects.
3. Add one field + one line to `robot/Robot.java`'s `init()` to wire it up.
4. Call it from `driverControls()` in whichever TeleOp(s) should use it.

That's the whole process — no other file needs to change.

## Creating your own OpModes

The easiest way to create your own OpMode is to copy a Sample OpMode and make it your own.

Sample opmodes exist in the FtcRobotController module.
To locate these samples, find the FtcRobotController module in the "Project/Android" tab.

Expand the following tree elements:
 FtcRobotController/java/org.firstinspires.ftc.robotcontroller/external/samples

### Naming of Samples

To gain a better understanding of how the samples are organized, and how to interpret the
naming system, it will help to understand the conventions that were used during their creation.

These conventions are described (in detail) in the sample_conventions.md file in this folder.

To summarize: A range of different samples classes will reside in the java/external/samples.
The class names will follow a naming convention which indicates the purpose of each class.
The prefix of the name will be one of the following:

Basic:  	This is a minimally functional OpMode used to illustrate the skeleton/structure
            of a particular style of OpMode.  These are bare bones examples.

Sensor:    	This is a Sample OpMode that shows how to use a specific sensor.
            It is not intended to drive a functioning robot, it is simply showing the minimal code
            required to read and display the sensor values.

Robot:	    This is a Sample OpMode that assumes a simple two-motor (differential) drive base.
            It may be used to provide a common baseline driving OpMode, or
            to demonstrate how a particular sensor or concept can be used to navigate.

Concept:	This is a sample OpMode that illustrates performing a specific function or concept.
            These may be complex, but their operation should be explained clearly in the comments,
            or the comments should reference an external doc, guide or tutorial.
            Each OpMode should try to only demonstrate a single concept so they are easy to
            locate based on their name.  These OpModes may not produce a drivable robot.

After the prefix, other conventions will apply:

* Sensor class names are constructed as:    Sensor - Company - Type
* Robot class names are constructed as:     Robot - Mode - Action - OpModetype
* Concept class names are constructed as:   Concept - Topic - OpModetype

Once you are familiar with the range of samples available, you can choose one to be the
basis for your own robot.  In all cases, the desired sample(s) needs to be copied into
your TeamCode module to be used.

This is done inside Android Studio directly, using the following steps:

 1) Locate the desired sample class in the Project/Android tree.

 2) Right click on the sample class and select "Copy"

 3) Expand the TeamCode/java folder

 4) Right click on the folder that matches the sample's purpose and select "Paste":
      - A driver-controlled sample (drives the robot)?  Consider rewriting it to extend
        `BaseTeleOp` instead (see "Adding your own TeleOp" above) so it shares driving code
        with the rest of the team. Otherwise paste into `opmodes/teleop/`.
      - A sensor/concept demo you're just experimenting with, not driving a robot?
        Paste into `opmodes/test/`.
      - Not an OpMode at all (a helper class)?  It probably belongs in `utilities/` if it's
        generic, or `robot/subsystems/` if it wraps a piece of hardware.

 5) You will be prompted for a class name for the copy.
    Choose something meaningful based on the purpose of this class.
    Start with a capital letter, and remember that there may be more similar classes later.

Once your copy has been created, you should prepare it for use on your robot.
This is done by adjusting the OpMode's name, and enabling it to be displayed on the
Driver Station's OpMode list.

Each OpMode sample class begins with several lines of code like the ones shown below:

```
 @TeleOp(name="Template: Linear OpMode", group="Linear Opmode")
 @Disabled
```

The name that will appear on the driver station's "opmode list" is defined by the code:
 ``name="Template: Linear OpMode"``
You can change what appears between the quotes to better describe your opmode.
The "group=" portion of the code can be used to help organize your list of OpModes.

As shown, the current OpMode will NOT appear on the driver station's OpMode list because of the
  ``@Disabled`` annotation which has been included.
This line can simply be deleted , or commented out, to make the OpMode visible.



## ADVANCED Multi-Team App management:  Cloning the TeamCode Module

In some situations, you have multiple teams in your club and you want them to all share
a common code organization, with each being able to *see* the others code but each having
their own team module with their own code that they maintain themselves.

In this situation, you might wish to clone the TeamCode module, once for each of these teams.
Each of the clones would then appear along side each other in the Android Studio module list,
together with the FtcRobotController module (and the original TeamCode module).

Selective Team phones can then be programmed by selecting the desired Module from the pulldown list
prior to clicking to the green Run arrow.

Warning:  This is not for the inexperienced Software developer.
You will need to be comfortable with File manipulations and managing Android Studio Modules.
These changes are performed OUTSIDE of Android Studios, so close Android Studios before you do this.
 
Also.. Make a full project backup before you start this :)

To clone TeamCode, do the following:

Note: Some names start with "Team" and others start with "team".  This is intentional.

1)  Using your operating system file management tools, copy the whole "TeamCode"
    folder to a sibling folder with a corresponding new name, eg: "Team0417".

2)  In the new Team0417 folder, delete the TeamCode.iml file.

3)  the new Team0417 folder, rename the "src/main/java/org/firstinspires/ftc/teamcode" folder
    to a matching name with a lowercase 'team' eg:  "team0417".

4)  In the new Team0417/src/main folder, edit the "AndroidManifest.xml" file, change the line that contains
         package="org.firstinspires.ftc.teamcode"
    to be
         package="org.firstinspires.ftc.team0417"

5)  Add:    include ':Team0417' to the "/settings.gradle" file.
    
6)  Open up Android Studios and clean out any old files by using the menu to "Build/Clean Project""