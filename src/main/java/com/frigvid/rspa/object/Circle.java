package com.frigvid.rspa.object;

import javafx.scene.paint.Color;

public class Circle extends Shape
{
	private double radius;

	public Circle(double radius, double rotation, double opacity, Color borderColor, Color fillColor, double positionX, double positionY, int layer)
	{
		super(rotation, opacity, borderColor, fillColor, positionX, positionY, layer);
		this.radius = radius;
	}
}
