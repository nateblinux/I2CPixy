/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3461.robot;

import org.usfirst.frc.team3461.robot.drive.DriveTrain;
import org.usfirst.frc.team3461.robot.vision.Camera;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("deprecation")
public class Robot extends SampleRobot {
	
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	public Joystick joy1 = new Joystick(2);
	public Joystick joy2 = new Joystick(1);
	public DriveTrain drive = new DriveTrain();
	public Camera camera = new Camera();
	public static final int DISTANCE = 35;
	public static final int XVAL = 145;
	public Robot() {
		
	}

	@Override
	public void robotInit() {
		SmartDashboard.putData("Auto modes", m_chooser);
		SmartDashboard.putNumber("Distance", camera.getDistance());
		SmartDashboard.putString("Blocks", "X/nX/n");
	}

	@Override
	public void autonomous() {
		while(isAutonomous()){
			SmartDashboard.putNumber("Distance", camera.getDistance());
			SmartDashboard.putNumber("Y Error", camera.getAngleError());
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	@Override
	public void operatorControl() {
		double kI = 0;
		double kIAngle = 0;
		while(isOperatorControl()){
			int distance = camera.getDistance();
			SmartDashboard.putNumber("Distance:", distance);
			SmartDashboard.putNumber("X Angle", camera.getAngleError());
			double speed = 0;
			int error = DISTANCE - distance;
			double kP = error * .06;
			kI += error * .0005;
			speed = (error * error * error);
			speed = speed / Math.abs(error);
			speed = speed * .0016;
			speed += kP;
			speed += kI;
			if(Math.abs(error) < 2){
				speed = 0;
				System.out.println("Not Enough Movement");
			}
			if(speed > .3){
				speed = .3;
			}
			if(speed < -.3){
				speed = -.3;
			}
			double xError = XVAL - camera.getAngleError();
			double adjust = xError * .001;
			kIAngle = xError * .001;
			//adjust += kIAngle;
			if(distance < 10 || distance > 200){
				speed = 0;
				adjust = 0;
				System.out.println("Out of camera range");
			}
			double leftSpeed = speed + adjust;
			double rightSpeed = speed - adjust;
			drive.setRSpeed(-leftSpeed);
			drive.setLSpeed(rightSpeed);
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void test() {
	}
}
