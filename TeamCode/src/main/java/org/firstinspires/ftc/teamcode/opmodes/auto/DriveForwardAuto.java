package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.common.BaseAutonomous;

/**
 * Simplest possible autonomous: drive forward for one second, then stop.
 */
@Autonomous(name = "Drive Forward")
public class DriveForwardAuto extends BaseAutonomous {

    @Override
    protected void runRoutine() {
        robot.drivetrain.drive(0.5, 0, 0);
        sleep(1000);
        robot.drivetrain.stop();
    }
}
