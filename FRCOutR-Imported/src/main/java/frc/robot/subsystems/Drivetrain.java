// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.DriveCommand;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private XboxController controller;
  private CANSparkMax LDrive1 = new CANSparkMax(1, MotorType.kBrushless);
  private CANSparkMax LDrive2 = new CANSparkMax(2, MotorType.kBrushless);
  private CANSparkMax RDrive1 = new CANSparkMax(3, MotorType.kBrushless);
  private CANSparkMax RDrive2 = new CANSparkMax(4, MotorType.kBrushless);

  private PWM servoTest = new PWM(0);
  private PWM servoTest2 = new PWM(2);

  private static Spark m_blinkin = new Spark(1);

  private CANSparkMax arm = new CANSparkMax(5, MotorType.kBrushless);
  private CANSparkMax arm2 = new CANSparkMax(6, MotorType.kBrushless);

  private Compressor _comp = new Compressor(24, PneumaticsModuleType.REVPH);
  private Solenoid _Solenoid = new Solenoid(24, PneumaticsModuleType.REVPH, 0);

  public Drivetrain(XboxController controller) {
    this.controller = controller;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new DriveCommand(controller, this));
  }
  public void setLeft(double power){
    LDrive1.set(-power);
    LDrive2.set(-power);
  }
  public void setDriveCoast(){
    LDrive2.setIdleMode(IdleMode.kBrake);
    LDrive1.setIdleMode(IdleMode.kBrake);
    RDrive2.setIdleMode(IdleMode.kBrake);
    RDrive1.setIdleMode(IdleMode.kBrake);
    arm.setIdleMode(IdleMode.kBrake);
  }
  public void setRight(double power){
    RDrive1.set(power);
    RDrive2.set(power);
  }
  public void setArm(double power){
    arm.set(power);
    arm2.set(-power);

  }
  public void setPWM(double pos){
    servoTest.setPosition(pos);
  }

  public void setPWM2(double pos){
   servoTest2.setPosition(pos);
  }
  public void setLED(double val){
    m_blinkin.set(val);
  }
  public void setSolenoid(boolean var){
    _Solenoid.set(var);
  }

  public void compStart(){
    _comp.enableAnalog(60, 70);
  }

  public void compStop(){
    _comp.disable();
  }
}
