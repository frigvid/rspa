package com.frigvid.rspa.object;

import javafx.scene.paint.Color;

public class Shape
{
	protected double rotation;
	protected double opacity;
	protected Color borderColor;
	protected Color fillColor;
	protected double positionX;
	protected double positionY;
	protected int layer;
	
	public Shape(double rotation, double opacity, Color borderColor, Color fillColor, double positionX, double positionY, int layer)
	{
		this.rotation = rotation;
		this.opacity = opacity;
		this.borderColor = borderColor;
		this.fillColor = fillColor;
		this.positionX = positionX;
		this.positionY = positionY;
		this.layer = layer;
	}
}
