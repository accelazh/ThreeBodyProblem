package org.accela.threebody;

public class Viewport
{
	// ��ʾ��ʱ�򣬶����������meter��������ʾ��
	// ���ȵ�λpixel��ת��������meter/pixel
	private double scale = 0;

	public Viewport(double scale)
	{
		super();
		this.scale = scale;
	}

	public double getScale()
	{
		return scale;
	}

	@Override
	public String toString()
	{
		return "Viewport [scale=" + scale + "]";
	}

}
