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
        while (opModeIsActive() && robot.driveToPositionStep(0, 24, 0.5)) {
            telemetry.addData("X", robot.odometry.getX());
            telemetry.addData("Y", robot.odometry.getY());
            telemetry.update();
        }

        while (opModeIsActive() && robot.turnToHeadingStep(90, 0.5)) {
            telemetry.addData("Heading", robot.odometry.getHeading());
            telemetry.update();
        }
    }
}
