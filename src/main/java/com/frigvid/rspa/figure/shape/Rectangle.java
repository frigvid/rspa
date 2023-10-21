package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.figure.IFigure;
import com.frigvid.rspa.figure.ShapeDragHandler;
import com.frigvid.rspa.history.MoveShape;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;

import java.util.concurrent.atomic.AtomicReference;

// TODO: Implement abstract wrapper class for Shapes and use that instead of extending it directly.
public class Rectangle
		extends javafx.scene.shape.Rectangle
		implements IFigure
{
	public Rectangle() {}
	
	public Rectangle(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		
		/* Enable dragging of any Rectangle shape. */
		ShapeDragHandler dragHandler = new ShapeDragHandler(this);
		dragHandler.enableDrag();
	}
	
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
	
	/* Mathematical functions. Very pointless though, so commented out until further notice.
	 * Might be useful for something like what's done in the Line class though.
	public double calculateArea()
	{
		return this.getWidth() * this.getHeight();
	}
	
	public double calculatePerimeter()
	{
		return 2 * (this.getWidth() + this.getHeight());
	}
	
	public double calculateSurfaceArea()
	{
		return 2 * (calculateArea() + this.getWidth() * this.getHeight() + this.getHeight() * this.getWidth());
	}
	*/
	
	/* NOTE: Not sure if these will be useful yet,
	 * 		 but I'm keeping them here for now.
	 *       I may end up removing them later.
	public double getCenterX()
	{
		return this.getX() + this.getWidth() / 2;
	}
	public double getCenterY()
	{
		return this.getY() + this.getHeight() / 2;
	}
	public void setCenterX(double x)
	{
		this.setX(x - this.getWidth() / 2);
	}
	public void setCenterY(double y)
	{
		this.setY(y - this.getHeight() / 2);
	}
	*/
}
