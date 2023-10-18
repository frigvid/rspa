package com.frigvid.rspa.object;

import javafx.scene.paint.Color;

public class Rectangle extends Shape
{
	private double width;
	private double height;

	public Rectangle(double width, double height, double rotation, double opacity, Color borderColor, Color fillColor, double positionX, double positionY, int layer)
	{
		super(rotation, opacity, borderColor, fillColor, positionX, positionY, layer);
		this.width = width;
		this.height = height;
	}
}
