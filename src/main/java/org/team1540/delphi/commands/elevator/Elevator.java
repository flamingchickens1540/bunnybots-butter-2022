package org.team1540.delphi.commands.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.team1540.delphi.Constants;

public class Elevator extends SubsystemBase {
    private final TalonSRX leftMotor = new TalonSRX(Constants.Elevator.leftMotor);
    private final TalonSRX rightMotor = new TalonSRX(Constants.Elevator.rightMotor);

    public Elevator() {
        // TODO: Confirm inversion
        leftMotor.setInverted(false);
        rightMotor.setInverted(false);
        rightMotor.follow(leftMotor);
        leftMotor.setNeutralMode(NeutralMode.Brake);
        rightMotor.setNeutralMode(NeutralMode.Brake);
        leftMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        rightMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    }

    /**
     * Drives the elevator up and down
     * @param percent [-1,1] How fast to move it
     */
    public void setPercent(double percent) {
        leftMotor.set(ControlMode.PercentOutput, percent);
    }
    @Override
    public void periodic() {
        SmartDashboard.putBoolean("elevator/limitForwardRight", rightMotor.isFwdLimitSwitchClosed() == 1);
        SmartDashboard.putBoolean("elevator/limitReverseRight", rightMotor.isRevLimitSwitchClosed() == 1);
        SmartDashboard.putBoolean("elevator/limitForwardLeft", leftMotor.isFwdLimitSwitchClosed() == 1);
        SmartDashboard.putBoolean("elevator/limitReverseLeft", leftMotor.isRevLimitSwitchClosed() == 1);
    }
}
