package org.accela.threebody;

import java.io.IOException;

import com.jme.app.AbstractGame.ConfigShowMode;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		ConfigLoader configLoader = new ConfigLoader();
		System system = configLoader.loadSystem();
		java.lang.System.out.println("====System====\n"
				+ system.toString()
				+ "\n");
		Viewport viewport = configLoader.loadViewport();
		java.lang.System.out.println("====Viewport====\n"
				+ viewport.toString()
				+ "\n");
		Display display = new Display(system, viewport);
		display.setConfigShowMode(ConfigShowMode.AlwaysShow);
		display.start();
	}
}
