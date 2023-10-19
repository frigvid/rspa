package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.IFigure;

public class Line
		extends javafx.scene.shape.Line
		implements IFigure
{
	public Line() {}
	
	public Line(double startX, double startY, double endX, double endY)
	{
		super(startX, startY, endX, endY);
	}
}
