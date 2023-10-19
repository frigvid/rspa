package com.frigvid.rspa.shape;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Map;

public class Circle
		extends javafx.scene.shape.Circle
		//implements iFigure
{
	private double x;
	private double y;
	
	/**
	 * Creates an empty instance of Circle.
	 */
	public Circle() {}
	
	public Circle(double radius)
	{
		super(radius);
	}
	
	public Circle(double x, double y, double radius)
	{
		super(radius);
		this.x = (x);
		this.y = (y);
	}
	
	//@Override
	public void draw(Pane pane)
	{
		pane.getChildren().add(this);
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
}
