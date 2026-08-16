package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.Constants;

/**
 * Mecanum drivetrain. Every TeleOp should drive through drive() so driving
 * feel stays identical everywhere it's used.
 */
public class Drivetrain {

    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;

    public Drivetrain(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.dcMotor.get(Constants.DRIVE_FRONT_LEFT);
        frontRight = hardwareMap.dcMotor.get(Constants.DRIVE_FRONT_RIGHT);
        backLeft = hardwareMap.dcMotor.get(Constants.DRIVE_BACK_LEFT);
        backRight = hardwareMap.dcMotor.get(Constants.DRIVE_BACK_RIGHT);

        // Reverse left side so positive power always drives forward
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // Brake when no power, instead of coasting
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /**
     * Standard field-relative-free mecanum mixing.
     *
     * @param y  forward/back, positive = forward
     * @param x  strafe, positive = right
     * @param rx rotation, positive = clockwise
     */
    public void drive(double y, double x, double rx) {
        // Correct for imperfect strafing kinematics
        x *= Constants.STRAFE_CORRECTION;

        double frontLeftPower = y + x + rx;
        double backLeftPower = y - x + rx;
        double frontRightPower = y - x - rx;
        double backRightPower = y + x - rx;

        double max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 1.0) {
            frontLeftPower /= max;
            backLeftPower /= max;
            frontRightPower /= max;
            backRightPower /= max;
        }

        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
    }

    public void stop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
