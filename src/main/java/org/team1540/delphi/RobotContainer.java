package org.team1540.delphi;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import org.team1540.delphi.commands.drivetrain.Drivetrain;
import org.team1540.delphi.commands.drivetrain.PathPlannerDriveCommand;
import org.team1540.delphi.commands.drivetrain.SwerveDriveCommand;
import org.team1540.delphi.commands.elevator.Elevator;
import org.team1540.delphi.commands.elevator.ElevatorMoveCommand;
import org.team1540.delphi.commands.intake.Intake;
import org.team1540.delphi.commands.intake.IntakeCommand;

public class RobotContainer {
    // Hardware

    // Subsystems
    
    Drivetrain drivetrain = new Drivetrain();
    Elevator elevator = new Elevator();
    Intake intake = new Intake();
    // Controllers
    XboxController driver = new XboxController(0);
    XboxController copilot = new XboxController(1);
    // Commands

    public RobotContainer() {
        initSmartDashboard();
        configureButtonBindings();
        DriverStation.silenceJoystickConnectionWarning(true);
    }

    private void configureButtonBindings() {
        // Driver


        new Trigger(copilot::getLeftBumper).whileActiveOnce(new IntakeCommand(intake,0.5)); //coop:button(LBumper,[HOLD] Intake,pilot)
        new Trigger(driver::getAButton).whenActive(drivetrain::zeroGyroscope);
        // Copilot

        // SmartDashboard Commands
        
    }

    public void setTeleopDefaultCommands() {
        elevator.setDefaultCommand(new ElevatorMoveCommand(copilot, elevator)); //coop:button(LTrigger,[HOLD] Elevator Down,pilot) coop:button(RTrigger,[HOLD] Elevator Up,pilot)
        drivetrain.setDefaultCommand(new SwerveDriveCommand(drivetrain, driver));
        new Trigger(copilot::getLeftBumper).whileActiveOnce(new IntakeCommand(intake, 1));
        new Trigger(copilot::getRightBumper).whileActiveOnce(new IntakeCommand(intake, -1));
    }

    private void initSmartDashboard() {
        
    }

    public CommandBase getAutonomousCommand() {
        return new PathPlannerDriveCommand(drivetrain);
    }
}
