package com.frigvid.rspa.test;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public abstract class Shape extends javafx.scene.shape.Shape
{
	protected double x;
	protected double y;
	protected double opacity = 1.0;
	protected Color borderColor = Color.BLACK;
	protected Color fillColor = Color.WHITE;
	protected Rotate rotation = new Rotate();
	protected int layer = 0;
	
	public Shape(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/* Getters. */
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	//public double getOpacity()
	//{
	//	return this.opacity;
	//}
	
	public Color getBorderColor()
	{
		return this.borderColor;
	}
	
	public Color getFillColor()
	{
		return this.fillColor;
	}
	
	public Rotate getRotation()
	{
		return this.rotation;
	}
	
	public int getLayer()
	{
		return this.layer;
	}
	
	
	/* Setters. */
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	//public void setOpacity(double opacity)
	//{
	//	this.opacity = opacity;
	//}
	
	public void setBorderColor(Color borderColor)
	{
		this.borderColor = borderColor;
	}
	
	public void setFillColor(Color fillColor)
	{
		this.fillColor = fillColor;
	}
	
	public void setRotation(Rotate rotation)
	{
		this.rotation = rotation;
	}
	
	public void setLayer(int layer)
	{
		this.layer = layer;
	}
	
	public abstract void addToPane(Pane pane);
}