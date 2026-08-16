package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.RobotHardware;
import org.firstinspires.ftc.teamcode.utilities.updater;
import org.firstinspires.ftc.teamcode.utilities.drive;

@TeleOp
public class TeleOp1 extends LinearOpMode {

    @Override
    public void runOpMode() {

        RobotHardware robot = new RobotHardware();

        robot.init(hardwareMap);

        drive drive = new drive(robot);

        waitForStart();

        while (opModeIsActive()) {

            double forward = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            drive.drive(forward, strafe, turn);

            updater.update();
        }

        drive.stop();
    }
}