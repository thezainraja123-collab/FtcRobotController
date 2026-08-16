package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Keyboard Motor Test", group = "Test Code Programs")
public class hardwareTest extends LinearOpMode {
    @Override
    public void runOpMode() {

        String motorName = "";

        double turnSpeed = 0.5;
        boolean shift = false;

        boolean numberMode = false;

        int selected = 0;

        char[] lowercase = {
                'a','b','c','d','e','f','g',
                'h','i','j','k','l','m','n',
                'o','p','q','r','s','t','u',
                'v','w','x','y','z','_'
        };

        char[] uppercase = {
                'A','B','C','D','E','F','G',
                'H','I','J','K','L','M','N',
                'O','P','Q','R','S','T','U',
                'V','W','X','Y','Z','_'
        };

        char[] numbers = {
                '0','1','2','3','4',
                '5','6','7','8','9',
                '-','_','.'
        };

        char[] keyboard;

        DcMotor motor = null;

        boolean lastA = false;
        boolean lastB = false;
        boolean lastX = false;
        boolean lastY = false;
        boolean lastRB = false;

        waitForStart();

        while(opModeIsActive()) {

            if(gamepad1.y && !lastY) {
                shift = !shift;
            }

            if(gamepad1.right_bumper && !lastRB) {
                numberMode = !numberMode;
            }


            if(numberMode) {
                keyboard = numbers;
            }
            else if(shift) {
                keyboard = uppercase;
            }
            else {
                keyboard = lowercase;
            }


            if(gamepad1.dpad_right) {
                selected++;

                if(selected >= keyboard.length) {
                    selected = 0;
                }
            }

            if(gamepad1.dpad_left) {
                selected--;

                if(selected < 0) {
                    selected = keyboard.length - 1;
                }
            }


            if(gamepad1.dpad_down) {
                selected += 7;

                if(selected >= keyboard.length) {
                    selected = 0;
                }
            }

            if(gamepad1.dpad_up) {
                selected -= 7;

                if(selected < 0) {
                    selected = keyboard.length - 1;
                }
            }


            if(gamepad1.a && !lastA) {
                motorName += keyboard[selected];
            }


            if(gamepad1.b && !lastB) {

                if(!motorName.isEmpty()) {
                    motorName =
                            motorName.substring(0, motorName.length()-1);
                }
            }


            if(gamepad1.x && !lastX) {

                try {
                    motor = hardwareMap.get(DcMotor.class, motorName);
                }

                catch(Exception e) {
                    motor = null;
                }
            }


            if(gamepad1.left_bumper) {
                turnSpeed = 0.25;
            }

            if(gamepad1.right_bumper) {
                turnSpeed = 1;
            }


            if(motor != null) {

                if(gamepad1.left_stick_y < -0.5) {
                    motor.setPower(turnSpeed);
                }

                else if(gamepad1.left_stick_y > 0.5) {
                    motor.setPower(-turnSpeed);
                }

                else {
                    motor.setPower(0);
                }
            }


            telemetry.addLine("Keyboard Motor Test");

            telemetry.addData("Motor Name", motorName);
            telemetry.addData("Selected Key", keyboard[selected]);

            if(numberMode) {
                telemetry.addLine("Mode: Numbers");
            }

            else if(shift) {
                telemetry.addLine("Mode: Uppercase");
            }

            else {
                telemetry.addLine("Mode: Lowercase");
            }


            if(motor != null) {
                telemetry.addLine("Motor Connected");
                telemetry.addData("Encoder", motor.getCurrentPosition());
            }

            else {
                telemetry.addLine("No Motor Connected");
            }


            telemetry.addLine("");
            telemetry.addLine("Dpad = Move");
            telemetry.addLine("A = Add");
            telemetry.addLine("B = Delete");
            telemetry.addLine("X = Connect");
            telemetry.addLine("Y = Shift");
            telemetry.addLine("RB = Numbers");

            telemetry.update();

            lastA = gamepad1.a;
            lastB = gamepad1.b;
            lastX = gamepad1.x;
            lastY = gamepad1.y;
            lastRB = gamepad1.right_bumper;

            idle();
        }
    }
}