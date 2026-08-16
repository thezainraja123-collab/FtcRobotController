package org.firstinspires.ftc.teamcode.robot;

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
     * Runs one step of proportional control driving toward (targetX, targetY).
     * Call this repeatedly from your own loop until it returns false. Assumes
     * the robot is roughly facing the field's X/Y axes; it does not correct
     * for heading.
     *
     * @param targetX target X position, in Constants.ODOMETRY_UNIT
     * @param targetY target Y position, in Constants.ODOMETRY_UNIT
     * @param power   max drive power to apply, 0.0-1.0
     * @return true if still moving toward the target; false once within
     *         Constants.POSITION_TOLERANCE (drivetrain has already been stopped)
     */
    public boolean driveToPositionStep(double targetX, double targetY, double power) {
        update();

        double errorX = targetX - odometry.getX();
        double errorY = targetY - odometry.getY();

        if (Math.hypot(errorX, errorY) < Constants.POSITION_TOLERANCE) {
            drivetrain.stop();
            return false;
        }

        double y = clamp(errorY * Constants.DRIVE_KP, -power, power);
        double x = clamp(errorX * Constants.DRIVE_KP, -power, power);
        drivetrain.drive(y, x, 0);
        return true;
    }

    /**
     * Runs one step of proportional control turning to face targetHeading.
     * Call this repeatedly from your own loop until it returns false.
     *
     * @param targetHeading target heading, in degrees
     * @param power         max turn power to apply, 0.0-1.0
     * @return true if still turning toward the target; false once within
     *         Constants.HEADING_TOLERANCE (drivetrain has already been stopped)
     */
    public boolean turnToHeadingStep(double targetHeading, double power) {
        update();

        double error = targetHeading - odometry.getHeading();
        while (error > 180) error -= 360;
        while (error < -180) error += 360;

        if (Math.abs(error) < Constants.HEADING_TOLERANCE) {
            drivetrain.stop();
            return false;
        }

        double rx = clamp(error * Constants.TURN_KP, -power, power);
        drivetrain.drive(0, 0, rx);
        return true;
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
