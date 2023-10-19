package com.frigvid.rspa.OLD.object;

import javafx.scene.paint.Color;

public class Line extends Shape
{
	private double startX;
	private double startY;
	private double endX;
	private double endY;

	public Line(double startX, double startY, double endX, double endY, double rotation, double opacity, Color borderColor, Color fillColor, double positionX, double positionY, int layer)
	{
		super(rotation, opacity, borderColor, fillColor, positionX, positionY, layer);
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
}
