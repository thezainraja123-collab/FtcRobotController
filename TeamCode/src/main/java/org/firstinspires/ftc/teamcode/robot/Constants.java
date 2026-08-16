package org.firstinspires.ftc.teamcode.robot;

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

    public static final double DRIVE_KP = 0.02;
    public static final double TURN_KP = 0.01;

    // Corrects for imperfect mecanum strafing kinematics
    public static final double STRAFE_CORRECTION = 1.1;


    // =========================
    // Drive Tolerances
    // =========================

    public static final double POSITION_TOLERANCE = 1.0; // inches
    public static final double HEADING_TOLERANCE = 5.0;  // degrees

}
