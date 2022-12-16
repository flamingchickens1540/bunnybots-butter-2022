package org.team1540.delphi.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCommand extends CommandBase {
    private final Intake intake;
    private final double speed;

    public IntakeCommand(Intake intake, double speed) {
        this.intake = intake;
        this.speed = speed;
    }

    @Override
    public void initialize() {
        intake.setPercent(speed);
    }

    @Override
    public void end(boolean isInterrupted) {
        intake.setPercent(0);
    }


}
