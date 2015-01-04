package org.accela.threebody;

public class Viewport
{
	// 显示的时候，定义物理距离meter向计算机显示的
	// 长度单位pixel的转换比例：meter/pixel
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
