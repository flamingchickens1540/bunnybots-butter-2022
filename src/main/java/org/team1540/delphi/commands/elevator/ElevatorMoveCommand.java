package org.team1540.delphi.commands.elevator;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static org.team1540.delphi.Constants.Elevator.HOLD_SPEED;

public class ElevatorMoveCommand extends CommandBase {
    private final XboxController controller;
    private final Elevator elevator;

    public ElevatorMoveCommand(XboxController controller, Elevator elevator) {
        this.controller = controller;
        this.elevator = elevator;
        addRequirements(elevator);
        SmartDashboard.setDefaultNumber("Elevator/holdSpeed", HOLD_SPEED);
    }
    @Override
    public void execute() {
        // TODO: Keep elevator stable with no input
        elevator.setPercent((controller.getLeftTriggerAxis()-controller.getRightTriggerAxis())/1.5+SmartDashboard.getNumber("Elevator/holdSpeed", HOLD_SPEED));
        System.out.println(controller.getLeftTriggerAxis());
    }

    @Override
    public void end(boolean isInterrupted) {
        elevator.setPercent(0);
    }
}
