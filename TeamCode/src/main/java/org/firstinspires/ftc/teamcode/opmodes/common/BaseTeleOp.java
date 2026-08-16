package org.firstinspires.ftc.teamcode.opmodes.common;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Robot;

/**
 * Base class for all driver-controlled OpModes.
 *
 * Handles robot init, driving (left stick = translate, right stick = rotate),
 * odometry updates, and telemetry every loop. Extend this and override
 * driverControls() to add your own controls (intake, arm, whatever you're
 * experimenting with) without touching driving code.
 */
public abstract class BaseTeleOp extends LinearOpMode {

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

        while (opModeIsActive()) {
            robot.update();

            double y = -gamepad1.left_stick_y; // stick is reversed by default
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            robot.drivetrain.drive(y, x, rx);

            driverControls();

            telemetry.addData("X position", robot.odometry.getX());
            telemetry.addData("Y position", robot.odometry.getY());
            telemetry.addData("Heading", robot.odometry.getHeading());
            telemetry.update();
        }
    }

    /**
     * Override to add your own controls. Called once per loop, after driving
     * has already been applied. robot is available here.
     */
    protected void driverControls() {
    }
}
