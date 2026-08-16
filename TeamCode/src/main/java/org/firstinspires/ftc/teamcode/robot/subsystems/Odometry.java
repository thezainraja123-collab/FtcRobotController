package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Constants;

/**
 * Wraps the goBILDA Pinpoint odometry computer.
 */
public class Odometry {

    private final GoBildaPinpointDriver pinpoint;

    public Odometry(HardwareMap hardwareMap) {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, Constants.PINPOINT);
        pinpoint.resetPosAndIMU();
    }

    /** Call once per loop to refresh position/heading. */
    public void update() {
        pinpoint.update();
    }

    public void resetPosition() {
        pinpoint.resetPosAndIMU();
    }

    /** @return X position, in Constants.ODOMETRY_UNIT */
    public double getX() {
        return pinpoint.getPosX(Constants.ODOMETRY_UNIT);
    }

    /** @return Y position, in Constants.ODOMETRY_UNIT */
    public double getY() {
        return pinpoint.getPosY(Constants.ODOMETRY_UNIT);
    }

    public double getHeading() {
        return pinpoint.getPosition().getHeading(AngleUnit.DEGREES);
    }
}
