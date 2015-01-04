package org.accela.threebody;

import java.util.Arrays;

public class System
{
	private double timeResolution = 0;

	private double G = 0;

	private double minDistance = 0;

	private Ball[] balls;

	public System(double timeResolution,
			double G,
			double minDistance,
			Ball[] balls)
	{
		this.G = G;
		this.timeResolution = timeResolution;
		this.minDistance = minDistance;
		this.balls = balls;
	}

	public double getTimeResolution()
	{
		return timeResolution;
	}

	public double getG()
	{
		return G;
	}

	public double getMinDistance()
	{
		return minDistance;
	}

	public Ball[] getBalls()
	{
		return balls;
	}

	public void update()
	{
		for (Ball target : balls)
		{
			for (Ball other : balls)
			{
				if (other == target)
				{
					continue;
				}

				this.updateVel(target, other);
			}
		}
		
		for (Ball target : balls)
		{
			this.updateS(target);
		}
	}

	private void updateVel(Ball target, Ball other)
	{
		double distance = Common.distance(target.getX(), target.getY(), target
				.getZ(), other.getX(), other.getY(), other.getZ());
		distance = Math.max(distance, minDistance);
		double F = G
				* target.getMass()
				* other.getMass()
				/ Math.pow(distance, 2);
		double Fx = F * (other.getX() - target.getX()) / distance;
		double Fy = F * (other.getY() - target.getY()) / distance;
		double Fz = F * (other.getZ() - target.getZ()) / distance;

		double dertVx = Fx / target.getMass() * timeResolution;
		double dertVy = Fy / target.getMass() * timeResolution;
		double dertVz = Fz / target.getMass() * timeResolution;

		target.setVx(target.getVx() + dertVx);
		target.setVy(target.getVy() + dertVy);
		target.setVz(target.getVz() + dertVz);
	}

	private void updateS(Ball target)
	{
		target.setX(target.getX() + target.getVx() * timeResolution);
		target.setY(target.getY() + target.getVy() * timeResolution);
		target.setZ(target.getZ() + target.getVz() * timeResolution);
	}

	@Override
	public String toString()
	{
		return "System [G="
				+ G
				+ ", balls="
				+ Arrays.toString(balls)
				+ ", minDistance="
				+ minDistance
				+ ", timeResolution="
				+ timeResolution
				+ "]";
	}

}
