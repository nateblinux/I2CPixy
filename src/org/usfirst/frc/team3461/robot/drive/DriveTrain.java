package org.usfirst.frc.team3461.robot.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrain {
	WPI_TalonSRX rdt1 = new WPI_TalonSRX(1);
	//WPI_TalonSRX rdt2 = new WPI_TalonSRX(2);
	WPI_TalonSRX ldt1 = new WPI_TalonSRX(2);
	//WPI_TalonSRX ldt2 = new WPI_TalonSRX(4);
	
	public void setRSpeed(double speed){
		rdt1.set(speed);
		//rdt2.set(speed);
	}
	
	public void setLSpeed(double speed){
		ldt1.set(speed);
		//ldt2.set(speed);
	}
}
