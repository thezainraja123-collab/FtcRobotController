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
}
