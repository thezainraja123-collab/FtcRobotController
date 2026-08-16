package org.firstinspires.ftc.teamcode.robot;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

// @Config makes every public static (non-final) field below live-editable
// from FTC Dashboard's browser UI while the robot is running. Edits do NOT
// persist across an app restart -- once you find a good value on the
// Dashboard, copy it back into this file.
@Config
public class Constants {


    // =========================
    // Hardware Names
    // =========================

    public static final String DRIVE_FRONT_LEFT = "frontLeft";
    public static final String DRIVE_FRONT_RIGHT = "frontRight";
    public static final String DRIVE_BACK_LEFT = "backLeft";
    public static final String DRIVE_BACK_RIGHT = "backRight";

    public static final String PINPOINT = "pinpoint";



    // =========================
    // Drive Tuning
    // =========================

    public static double DRIVE_KP = 0.02;
    public static double TURN_KP = 0.01;

    // Corrects for imperfect mecanum strafing kinematics
    public static double STRAFE_CORRECTION = 1.1;


    // =========================
    // Drive Tolerances
    // =========================

    public static double POSITION_TOLERANCE = 1.0;
    public static double HEADING_TOLERANCE = 5.0;  // degrees


    // =========================
    // Units
    // =========================

    // Odometry position (Odometry.getX()/getY(), Robot.driveToPositionStep(),
    // POSITION_TOLERANCE) is always reported/measured in this unit. Change
    // this single line to change it everywhere.
    public static final DistanceUnit ODOMETRY_UNIT = DistanceUnit.INCH;

}
