package org.firstinspires.ftc.teamcode.utilities;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class RobotHardware {


    // =========================
    // Drive Motors
    // =========================

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;


    // =========================
    // Sensors
    // =========================

    public GoBildaPinpointDriver pinpoint;



    // =========================
    // Initialize Robot
    // =========================

    public void init(HardwareMap hardwareMap) {


        // Map motors

        frontLeft = hardwareMap.dcMotor.get(Constants.DRIVE_FRONT_LEFT);

        frontRight = hardwareMap.dcMotor.get(Constants.DRIVE_FRONT_RIGHT);

        backLeft = hardwareMap.dcMotor.get(Constants.DRIVE_BACK_LEFT);

        backRight = hardwareMap.dcMotor.get(Constants.DRIVE_BACK_RIGHT);


        // Map Pinpoint

        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, Constants.PINPOINT);



        // =========================
        // Configure motors
        // =========================

        // Reverse left side
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        // Brake when no power
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        // Reset odometry
        pinpoint.resetPosAndIMU();

    }


    // =========================
    // Stop all drive motors
    // =========================

    public void stopDrive() {

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }

}