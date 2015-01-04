package org.accela.threebody;

import com.jme.app.SimpleGame;
import com.jme.bounding.BoundingBox;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.shape.Sphere;
import com.jme.scene.state.MaterialState;

public class Display extends SimpleGame
{
	private System system = null;

	private Viewport viewport = null;

	private Sphere spheres[] = null;

	public Display(System system, Viewport viewport)
	{
		this.system = system;
		this.viewport = viewport;
	}

	public System getSystem()
	{
		return system;
	}

	public Viewport getViewport()
	{
		return viewport;
	}

	private Vector3f calSphereCenterByBall(Ball ball)
	{
		Vector3f center = new Vector3f((float) ball.getX(),
				(float) ball.getY(), (float) ball.getZ());
		center = center.divide((float) viewport.getScale());
		return center;
	}

	private Sphere createSphereByBall(int num)
	{
		Ball ball = system.getBalls()[num];
		Sphere sphere = new Sphere("sphere" + num, 30, 30, (float) (ball
				.getRadius() / viewport.getScale()));
		ColorRGBA color = new ColorRGBA(
				(float) (ball.getColor().getRed() * 1.0 / 255), (float) (ball
						.getColor().getGreen() * 1.0 / 255), (float) (ball
						.getColor().getBlue() * 1.0 / 255), 1.0f);
		sphere.setModelBound(new BoundingBox());
		sphere.updateModelBound();

		MaterialState ms = display.getRenderer().createMaterialState();
		ms.setAmbient(color);
		ms.setDiffuse(color);
		ms.setSpecular(ColorRGBA.white);
		ms.setShininess(40f);
		ms.setEmissive(ColorRGBA.black);
		ms.setEnabled(true);
		sphere.setRenderState(ms);

		return sphere;
	}

	private void moveSphereByBall(int num)
	{
		system.update();
		Ball ball = system.getBalls()[num];
		Sphere sphere = spheres[num];
		sphere.setLocalTranslation(calSphereCenterByBall(ball));
	}

	@Override
	protected void simpleInitGame()
	{
		spheres = new Sphere[system.getBalls().length];
		for (int i = 0; i < system.getBalls().length; i++)
		{
			spheres[i] = createSphereByBall(i);
			rootNode.attachChild(spheres[i]);
		}
	}

	@Override
	protected void simpleUpdate()
	{
		for (int i = 0; i < system.getBalls().length; i++)
		{
			moveSphereByBall(i);
		}
	}
}
