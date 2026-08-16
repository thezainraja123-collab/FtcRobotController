package org.firstinspires.ftc.teamcode.testcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


import org.firstinspires.ftc.teamcode.utilities.RobotHardware;


@TeleOp(name = "TEST - Pinpoint", group = "Testing")
public class PinpointTest extends LinearOpMode {


    RobotHardware robot = new RobotHardware();


    @Override
    public void runOpMode() {


        robot.init(hardwareMap);


        telemetry.addLine("Pinpoint Initialized");
        telemetry.addLine("Press PLAY");
        telemetry.update();


        waitForStart();


        // Reset starting position
        robot.pinpoint.resetPosAndIMU();


        while(opModeIsActive()) {


            // Update position
            robot.pinpoint.update();


            double x =
                    robot.pinpoint.getPosX(DistanceUnit.INCH);


            double y =
                    robot.pinpoint.getPosY(DistanceUnit.INCH);


            double heading =
                    robot.pinpoint.getPosition()
                            .getHeading(AngleUnit.DEGREES);



            telemetry.addData("X", x);
            telemetry.addData("Y", y);
            telemetry.addData("Heading", heading);


            telemetry.addLine("----------------");
            telemetry.addLine("Move robot manually");


            telemetry.update();

        }
    }
}