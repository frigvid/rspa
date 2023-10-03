package com.frigvid.rspa.geometry.shapes;

import com.frigvid.rspa.geometry.Shape;

public class Circle extends Shape
{
	private double radius;
	
	public Circle (double radius)
	{
		super();
		this.radius = radius;
	}
	
	/* Getters. */
	public double getRadius()
	{
		return radius;
	}
	
	/* Setters. */
	public void setRadius(double radius)
	{
		this.radius = radius;
	}
	
	/* To string. */
	@Override
	public String toString()
	{
		return "Circle{" +
				"radius=" + radius +
				", rotation=" + rotation +
				", opacity=" + opacity +
				", colorBorder=" + colorBorder +
				", isColorFill=" + isColorFill +
				", positionX=" + positionX +
				", positionY=" + positionY +
				", objectLayer=" + objectLayer +
				'}';
	}
}
