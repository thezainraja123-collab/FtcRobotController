package org.firstinspires.ftc.teamcode.opmodes.common;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Robot;

/**
 * Base class for all Autonomous OpModes.
 *
 * Handles robot init and telemetry, then hands off to runRoutine() once the
 * match starts. Unlike BaseTeleOp there's no per-loop hook — autonomous is a
 * one-shot sequence, so just write your steps top to bottom in runRoutine().
 */
public abstract class BaseAutonomous extends LinearOpMode {

    protected Robot robot;

    @Override
    public void runOpMode() {
        // Sends every telemetry.addData()/update() call to BOTH the Driver
        // Station and FTC Dashboard, instead of just the Driver Station.
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot = new Robot();
        robot.init(hardwareMap);

        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            runRoutine();
        }
    }

    /** Write your autonomous sequence here. robot is available. */
    protected abstract void runRoutine();
}
