// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.ControlAffinePlantInversionFeedforward;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveCommand extends CommandBase {
  /** Creates a new DriveCommand. */
  Drivetrain drive;
  XboxController controller;
  double max;
  double left;
  double right;
  double drives;
  double turn;
  boolean compState = true;
  public DriveCommand(XboxController controller, Drivetrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    this.drive = drive;
    this.controller = controller;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.setDriveCoast();
    drive.setLED(-0.29);
    drive.compStart();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drives = controller.getLeftY() * 0.2;
    turn = controller.getRightX() * 0.25;

    left = drives - turn;
    right = drives + turn;

    max = Math.max(Math.abs(left),Math.abs(right));

    if( max > 1.0){
      left /= max;
      right /= max;
    }

    drive.setLeft(left);
    drive.setRight(right);
    if(controller.getLeftBumper()){
      drive.setPWM(0);
    }
    else{
      drive.setPWM(1);
    }
    if(controller.getAButton()){
      drive.setArm(0.1);
    }
    else if(controller.getYButton()){
      drive.setArm(-0.1);
    }
    else{
      drive.setArm(0);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.compStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
