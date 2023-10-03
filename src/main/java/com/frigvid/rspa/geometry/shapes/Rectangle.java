package com.frigvid.rspa.geometry.shapes;

import com.frigvid.rspa.geometry.Shape;

public class Rectangle extends Shape
{
	private double width;
	private double height;
	
	public Rectangle (double width, double height)
	{
		super();
		this.width = width;
		this.height = height;
	}
	
	/* Getters. */
	public double getWidth()
	{
		return width;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	/* Setters. */
	public void setWidth(double width)
	{
		this.width = width;
	}
	
	public void setHeight(double height)
	{
		this.height = height;
	}
	
	/* To string. */
	@Override
	public String toString()
	{
		return "Rectangle{" +
				"width=" + width +
				", height=" + height +
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
