package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Mecanum TeleOp", group = "Linear OpMode")
public class MecanumTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare hardware motor objects
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");

        // Reverse the right side motors because their axles point in the opposite direction
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // POV Mode: Left stick controls translation, right stick controls rotation
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed by default
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing kinematics
            double rx = gamepad1.right_stick_x;

            // Mecanum Kinematics mixing formula
            double frontLeftPower = y + x + rx;
            double backLeftPower = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower = y + x - rx;

            // Normalize powers if any exceed 1.0 to maintain proportional control
            double max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backRightPower));

            if (max > 1.0) {
                frontLeftPower /= max;
                backLeftPower /= max;
                frontRightPower /= max;
                backRightPower /= max;
            }

            // Apply the calculated power levels to the hardware motors
            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            // Left bumper = turn 90 degrees left
            //if (gamepad1.left_bumper && !lastLeftBumper) {
               // targetHeading = heading + 90;
           // }

// Right bumper = turn 90 degrees right
            //if (gamepad1.right_bumper && !lastRightBumper) {
            //    targetHeading = heading - 90;
            //}

// Remember bumper state
            lastLeftBumper = gamepad1.left_bumper;
            lastRightBumper = gamepad1.right_bumper;

            //double error = targetHeading - heading;

// Fix wrap-around (359° -> 0° problem)
            //if (error > 180) error -= 360;
          //  if (error < -180) error += 360;

          //  double turnPower = error * kP;

// Limit power
          //  turnPower = Math.max(-0.5, Math.min(0.5, turnPower));


            // Output data to the Driver Station screen for telemetry monitoring
            telemetry.addData("X position", x_pos);
            telemetry.addData("Y position", y_pos);
            telemetry.addData("Heading", heading);
            telemetry.addLine("");
            telemetry.addLine("-----------------------");
            telemetry.addLine("");
            telemetry.addData("Stick Y (Drive)", y);
            telemetry.addData("Stick X (Strafe)", x);
            telemetry.addData("Stick RX (Rotate)", rx);
            telemetry.update();
        }
    }
}
