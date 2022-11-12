package org.team1540.delphi;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import org.team1540.delphi.commands.drivetrain.Drivetrain;
import org.team1540.delphi.commands.intake.Intake;
import org.team1540.delphi.commands.intake.IntakeCommand;

public class RobotContainer {
    // Hardware

    // Subsystems
    Drivetrain drivetrain = new Drivetrain();
    Intake intake = new Intake();
    // Controllers
    XboxController driver = new XboxController(0);
    // Commands

    public RobotContainer() {
        initSmartDashboard();
        configureButtonBindings();
        DriverStation.silenceJoystickConnectionWarning(true);
    }

    private void configureButtonBindings() {
        // Driver


        new Trigger(driver::getLeftBumper).whileActiveOnce(new IntakeCommand(intake)); //coop:button(LBumper,[HOLD] Intake,pilot)

        // Copilot


        // Robot hardware button


        // SmartDashboard Commands
        
    }


    private void initSmartDashboard() {
        
    }

    public CommandBase getAutonomousCommand() {
        return new InstantCommand();
    }
}
