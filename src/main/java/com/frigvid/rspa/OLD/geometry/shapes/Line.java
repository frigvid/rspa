package com.frigvid.rspa.OLD.geometry.shapes;

import com.frigvid.rspa.OLD.geometry.Shape;

public class Line extends Shape
{
	private double length;
	
	public Line (double length)
	{
		super();
		this.length = length;
	}
	
	/* Getters. */
	public double getLength()
	{
		return length;
	}
	
	/* Setters. */
	public void setLength(double length)
	{
		this.length = length;
	}
	
	/* To string. */
	@Override
	public String toString()
	{
		return "Line{" +
				"length=" + length +
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
