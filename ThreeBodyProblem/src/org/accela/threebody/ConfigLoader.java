package org.accela.threebody;

import java.awt.Color;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ConfigLoader
{
	public Ball loadBall(int num) throws IOException
	{
		FileReader in = new FileReader("ball" + num + ".cfg");
		try
		{
			Properties prop = new Properties();
			prop.load(in);
			return new Ball(Double.parseDouble(prop.getProperty("mass")),
					Double.parseDouble(prop.getProperty("x")), Double
							.parseDouble(prop.getProperty("y")), Double
							.parseDouble(prop.getProperty("z")), Double
							.parseDouble(prop.getProperty("vx")), Double
							.parseDouble(prop.getProperty("vy")), Double
							.parseDouble(prop.getProperty("vz")), new Color(
							Integer.parseInt(prop.getProperty("r")), Integer
									.parseInt(prop.getProperty("g")), Integer
									.parseInt(prop.getProperty("b"))), Double
							.parseDouble(prop.getProperty("radius")));
		}
		finally
		{
			in.close();
		}
	}

	public System loadSystem() throws IOException
	{
		// load balls
		List<Ball> balls = new LinkedList<Ball>();
		for (int i = 0; i < Integer.MAX_VALUE; i++)
		{
			try
			{
				balls.add(loadBall(i));
			}
			catch (IOException ex)
			{
				break;
			}
		}

		// load system config
		FileReader in = new FileReader("system.cfg");
		try
		{
			Properties prop = new Properties();
			prop.load(in);
			return new System(Double.parseDouble(prop
					.getProperty("timeResolution")), Double.parseDouble(prop
					.getProperty("G")), Double.parseDouble(prop
					.getProperty("minDistance")), balls.toArray(new Ball[0]));
		}
		finally
		{
			in.close();
		}
	}

	public Viewport loadViewport() throws IOException
	{
		FileReader in = new FileReader("viewport.cfg");
		try
		{
			Properties prop = new Properties();
			prop.load(in);
			return new Viewport(Double.parseDouble(prop.getProperty("scale")));
		}
		finally
		{
			in.close();
		}
	}
}
