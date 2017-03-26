package org.usfirst.frc.team5865.robot;

public class Utils {

	public static double Limit (double num) {
		return Limit(num, -1, 1);
	}
	public static double Limit(double num, double min, double max) {
		if (max < min)
			throw new IllegalArgumentException("Le minimum doit etre plus petit que le maximum");

		if (num > max)
			num = max;

		if (num < min)
			num = min;

		return num;
	}
	
	public static double distAngle(double angle, double radius) {
		return (radius * angle * Math.PI) / 180;
	}
	
	public static double distanceToRotation(double distance, double wheelsDiameter) {
		return distance / (wheelsDiameter * Math.PI);
	}
	
	public static double rotationsToMeters(double rotations, double wheelsDiameter) {
		return rotations * (wheelsDiameter * Math.PI);
	}
	
	public static double inchesToMeters(double inches) {
		return inches * 0.0254;
	}
}
