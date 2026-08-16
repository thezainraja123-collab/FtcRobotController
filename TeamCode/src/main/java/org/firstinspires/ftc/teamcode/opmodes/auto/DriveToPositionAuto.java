package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.common.BaseAutonomous;

/**
 * Exercises Robot's closed-loop movement: drive to a point 24" forward,
 * then turn to face 90 degrees.
 */
@Autonomous(name = "Drive To Position")
public class DriveToPositionAuto extends BaseAutonomous {

    @Override
    protected void runRoutine() {
        robot.driveToPosition(this, 0, 24, 0.5);
        robot.turnToHeading(this, 90, 0.5);
    }
}
