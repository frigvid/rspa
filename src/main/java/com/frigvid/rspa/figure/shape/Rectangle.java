package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.IFigure;

public class Rectangle
		extends javafx.scene.shape.Rectangle
		implements IFigure
{
	public Rectangle() {}
	
	public Rectangle(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}
}
