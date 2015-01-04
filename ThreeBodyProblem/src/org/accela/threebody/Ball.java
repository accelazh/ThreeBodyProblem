package org.accela.threebody;

import java.awt.Color;

public class Ball
{
	private double mass = 0;

	private double x = 0;

	private double y = 0;

	private double z = 0;

	private double vx = 0;

	private double vy = 0;

	private double vz = 0;

	private Color color = null;

	private double radius = 0;

	public Ball(double mass,
			double x,
			double y,
			double z,
			double vx,
			double vy,
			double vz,
			Color color,
			double radius)
	{
		this.mass = mass;
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		this.color = color;
		this.radius = radius;
	}

	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public double getVx()
	{
		return vx;
	}

	public void setVx(double vx)
	{
		this.vx = vx;
	}

	public double getVy()
	{
		return vy;
	}

	public void setVy(double vy)
	{
		this.vy = vy;
	}

	public double getMass()
	{
		return mass;
	}

	public Color getColor()
	{
		return color;
	}

	public double getRadius()
	{
		return radius;
	}

	public double getZ()
	{
		return z;
	}

	public void setZ(double z)
	{
		this.z = z;
	}

	public double getVz()
	{
		return vz;
	}

	public void setVz(double vz)
	{
		this.vz = vz;
	}

	@Override
	public String toString()
	{
		return "Ball [color="
				+ color
				+ ", mass="
				+ mass
				+ ", radius="
				+ radius
				+ ", vx="
				+ vx
				+ ", vy="
				+ vy
				+ ", vz="
				+ vz
				+ ", x="
				+ x
				+ ", y="
				+ y
				+ ", z="
				+ z
				+ "]";
	}

}
