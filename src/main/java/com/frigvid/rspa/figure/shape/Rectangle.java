package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.figure.FigureType;
import com.frigvid.rspa.figure.ShapeDragHandler;

/**
 * Wrapper class for the Rectangle shape.
 * <p/>
 * TODO: Implement abstract wrapper class for Shapes and use that instead of extending it directly.
 * <p/>
 * NOTE: Since Shape dragging is being instantiated twice, it *should* be in its own class, but eh, it's not important.
 */
public class Rectangle
		extends javafx.scene.shape.Rectangle
{
	private final static FigureType FIGURE_TYPE = FigureType.RECTANGLE;
	
	public Rectangle()
	{
		/* Enable dragging of any Line shape. */
		ShapeDragHandler dragHandler = new ShapeDragHandler(this);
		dragHandler.enableDrag();
	}
	
	public Rectangle(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		
		/* Enable dragging of any Rectangle shape. */
		ShapeDragHandler dragHandler = new ShapeDragHandler(this);
		dragHandler.enableDrag();
	}
	
	/* Getters. */
	/**
	 * Gets the type of the figure.
	 *
	 * @return The type of the figure.
	 */
	public FigureType getType()
	{
		return FIGURE_TYPE;
	}
	
	/**
	 * Gets the shape.
	 *
	 * @return The shape.
	 */
	public Rectangle getShape()
	{
		return this;
	}
	
	/* Setters. */
	/**
	 * Set the width and height of the rectangle.
	 * <p/>
	 * @param width The width of the rectangle.
	 * @param height The height of the rectangle.
	 */
	public void setSize(double width, double height)
	{
		this.setWidth(width);
		this.setHeight(height);
	}
	
	/**
	 * Set the x and y coordinates of the rectangle.
	 * <p/>
	 * @param x The x coordinate of the rectangle.
	 * @param y The y coordinate of the rectangle.
	 */
	public void setPosition(double x, double y)
	{
		this.setX(x);
		this.setY(y);
	}
}
