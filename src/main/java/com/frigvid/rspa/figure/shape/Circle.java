package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.figure.FigureType;
import com.frigvid.rspa.figure.ShapeDragHandler;

/**
 * Wrapper class for the Circle shape.
 * <p/>
 * TODO: Implement abstract wrapper class for Shapes and use that instead of extending it directly.
 * <p/>
 * NOTE: Since Shape dragging is being instantiated twice, it *should* be in its own class, but eh, it's not important.
 */
public class Circle
		extends javafx.scene.shape.Circle
{
	private final static FigureType FIGURE_TYPE = FigureType.CIRCLE;
	
	public Circle()
	{
		// Enable dragging of any Line shape.
		ShapeDragHandler dragHandler = new ShapeDragHandler(this);
		dragHandler.enableDrag();
	}
	
	public Circle(double centerX, double centerY, double radius)
	{
		super(centerX, centerY, radius);
		
		// Enable dragging of any Circle shape.
		ShapeDragHandler dragHandler = new ShapeDragHandler(this);
		dragHandler.enableDrag();
	}
	
	/* Getters. */
	public FigureType getType()
	{
		return FIGURE_TYPE;
	}
	
	public Circle getShape()
	{
		return this;
	}
	
	/* Setters. */
	public void setPosition(double x, double y)
	{
		this.setCenterX(x);
		this.setCenterY(y);
	}
}
