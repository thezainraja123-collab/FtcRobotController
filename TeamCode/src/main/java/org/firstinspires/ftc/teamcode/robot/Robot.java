package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.robot.subsystems.Odometry;

/**
 * Single point of hardware setup. Add a new subsystem field + one line in
 * init() to bring in a new mechanism (arm, intake, etc).
 */
public class Robot {

    public Drivetrain drivetrain;
    public Odometry odometry;

    public void init(HardwareMap hardwareMap) {
        drivetrain = new Drivetrain(hardwareMap);
        odometry = new Odometry(hardwareMap);
    }

    /** Call once per loop to refresh every sensor-backed subsystem. */
    public void update() {
        odometry.update();
    }

    /**
     * Drives toward (targetX, targetY), in inches, using simple proportional
     * control on the Pinpoint position. Blocks until within
     * Constants.POSITION_TOLERANCE. Assumes the robot is roughly facing the
     * field's X/Y axes; it does not correct for heading.
     */
    public void driveToPosition(LinearOpMode opMode, double targetX, double targetY, double power) {
        while (opMode.opModeIsActive()) {
            update();

            double errorX = targetX - odometry.getX();
            double errorY = targetY - odometry.getY();

            if (Math.hypot(errorX, errorY) < Constants.POSITION_TOLERANCE) {
                break;
            }

            double y = clamp(errorY * Constants.DRIVE_KP, -power, power);
            double x = clamp(errorX * Constants.DRIVE_KP, -power, power);
            drivetrain.drive(y, x, 0);
        }
        drivetrain.stop();
    }

    /**
     * Turns to face targetHeading, in degrees, using simple proportional
     * control. Blocks until within Constants.HEADING_TOLERANCE.
     */
    public void turnToHeading(LinearOpMode opMode, double targetHeading, double power) {
        while (opMode.opModeIsActive()) {
            update();

            double error = targetHeading - odometry.getHeading();
            while (error > 180) error -= 360;
            while (error < -180) error += 360;

            if (Math.abs(error) < Constants.HEADING_TOLERANCE) {
                break;
            }

            double rx = clamp(error * Constants.TURN_KP, -power, power);
            drivetrain.drive(0, 0, rx);
        }
        drivetrain.stop();
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
