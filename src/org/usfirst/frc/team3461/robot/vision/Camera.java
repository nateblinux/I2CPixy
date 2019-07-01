package org.usfirst.frc.team3461.robot.vision;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.*;

public class Camera {
	I2C i2c;
	byte[] toSend = new byte[1];
	byte[] toGet = new byte[1];
	public Camera(){
		i2c = new I2C(Port.kMXP, 0xA0);
		toGet[0] = 11;
	}
	
	public int getDistance(){
		int get;
		toSend[0] = 1;
		toGet[0] = 111;
		System.out.println("Before: " + toGet[0]);
		
		i2c.transaction(toSend, 1, toGet, 1);
		get = toGet[0];
		if(get == 1){
			toSend[0] = 11;
			i2c.transaction(toSend, 1, toGet, 1);
			return toGet[0];
		}
		if(get == 2){
			toSend[0] = 120;
			i2c.transaction(toSend,1, toGet, 1);
			get = toGet[0] * 100;
			toSend[0] = 121;
			i2c.transaction(toSend,1, toGet, 1);
			get +=toGet[0];
		}
		
		
		return get;
	}
	
	public int getAngleError(){
		int get;
		toSend[0] = 2;
		toGet[0] = 111;
		System.out.println("Before: " + toGet[0]);
		
		i2c.transaction(toSend, 1, toGet, 1);
		get = toGet[0];
		if(get == 1){
			toSend[0] = 21;
			i2c.transaction(toSend, 1, toGet, 1);
			return toGet[0];
		}
		if(get == 2){
			toSend[0] = 122;
			i2c.transaction(toSend,1, toGet, 1);
			get = toGet[0] * 100;
			toSend[0] = 123;
			i2c.transaction(toSend,1, toGet, 1);
			get +=toGet[0];
		}
		
		
		return get;
	}
}
