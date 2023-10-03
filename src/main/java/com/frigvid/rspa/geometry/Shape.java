package com.frigvid.rspa.geometry;

import javafx.scene.paint.Color;

/**
 * Code declaration for shapes and the like. */

public class Shape
{
	protected double rotation;
	protected int opacity;
	protected Color colorBorder;
	protected boolean isColorFill;
	protected double positionX;
	protected double positionY;
	protected int objectLayer;
	
	/**
	 * Default constructor for shapes.
	 * */
	protected Shape () {}
	
	/**
	 * Primary constructor for shapes.
	 *
	 * @param rotation Percentage.
	 * @param opacity 0-100.
	 * @param colorBorder Takes a color.
	 * @param isColorFill Takes a boolean to specify if shape is filled.
	 *                    Uses color from <code>colorBorder</code>.
	 * @param positionX Takes an int for x-position.
	 * @param positionY Takes an int for y-position.
	 * @param objectLayer Takes an int for which layer the object is on.
	 *                    E.g. if the object is in front of, or behind
	 *                    another object on the canvas.
	 * */
	public Shape (double rotation, int opacity, Color colorBorder, boolean isColorFill, double positionX, double positionY, int objectLayer)
	{
		this.rotation = rotation;
		this.opacity = opacity;
		this.colorBorder = colorBorder;
		this.isColorFill = isColorFill;
		this.positionX = positionX;
		this.positionY = positionY;
		this.objectLayer = objectLayer;
	}
	
	//public Shape (double rotation, int opacity, Color colorBorder, double positionX, double positionY, int objectLayer)
	//{
	//	this.rotation = rotation;
	//	this.opacity = opacity;
	//	this.colorBorder = colorBorder;
	//	this.positionX = positionX;
	//	this.positionY = positionY;
	//	this.objectLayer = objectLayer;
	//}
	
	/* Getters. */
	public double getRotation()
	{
		return rotation;
	}
	
	public int getOpacity()
	{
		return opacity;
	}
	
	public Color getColorBorder()
	{
		return colorBorder;
	}
	
	public boolean getIsColorFill()
	{
		return isColorFill;
	}
	
	public double getPositionX()
	{
		return positionX;
	}
	
	public double getPositionY()
	{
		return positionY;
	}
	
	public int getObjectLayer()
	{
		return objectLayer;
	}
	
	/* Setters. */
	public void setRotation(float rotation)
	{
		this.rotation = rotation;
	}
	
	public void setOpacity(int opacity)
	{
		this.opacity = opacity;
	}
	
	public void setColorBorder(Color colorBorder)
	{
		this.colorBorder = colorBorder;
	}
	
	public void setIsColorFill(boolean isColorFill)
	{
		this.isColorFill = isColorFill;
	}
	
	public void setPositionX(int positionX)
	{
		this.positionX = positionX;
	}
	
	public void setPositionY(int positionY)
	{
		this.positionY = positionY;
	}
	
	public void setObjectLayer(int objectLayer)
	{
		this.objectLayer = objectLayer;
	}
	
	/* To string. */
	@Override
	public String toString()
	{
		return "Shape{" +
				"rotation=" + rotation +
				", opacity=" + opacity +
				", colorBorder=" + colorBorder +
				", isColorFill=" + isColorFill +
				", positionX=" + positionX +
				", positionY=" + positionY +
				", objectLayer=" + objectLayer +
				'}';
	}
}
