package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.IFigure;

public class Circle
		extends javafx.scene.shape.Circle
		implements IFigure
{
	public Circle() {}
	
	public Circle(double centerX, double centerY, double radius)
	{
		super(centerX, centerY, radius);
	}
}
