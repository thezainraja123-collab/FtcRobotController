package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Robot;

/**
 * Streams Pinpoint x/y/heading to telemetry while the robot is pushed by
 * hand. Used to verify odometry is tracking correctly.
 */
@TeleOp(name = "TEST - Pinpoint", group = "Testing")
public class PinpointTest extends LinearOpMode {

    Robot robot = new Robot();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        telemetry.addLine("Pinpoint Initialized");
        telemetry.addLine("Press PLAY");
        telemetry.update();

        waitForStart();

        robot.odometry.resetPosition();

        while (opModeIsActive()) {
            robot.odometry.update();

            telemetry.addData("X", robot.odometry.getX());
            telemetry.addData("Y", robot.odometry.getY());
            telemetry.addData("Heading", robot.odometry.getHeading());

            telemetry.addLine("----------------");
            telemetry.addLine("Move robot manually");

            telemetry.update();
        }
    }
}
