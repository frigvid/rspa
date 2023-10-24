package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.figure.FigureType;
import com.frigvid.rspa.figure.ShapeDragHandler;

/**
 * Wrapper class for the Line shape.
 * <p/>
 * TODO: Implement abstract wrapper class for Shapes and use that instead of extending it directly.
 * <p/>
 * NOTE: Since Shape dragging is being instantiated twice, it *should* be in its own class, but eh, it's not important.
 */
public class Line
		extends javafx.scene.shape.Line
{
	private final static FigureType FIGURE_TYPE = FigureType.LINE;
	
	public Line()
	{
		/* Enable dragging of any Line shape. */
		ShapeDragHandler dragHandler = new ShapeDragHandler(this);
		dragHandler.enableDrag();
	}
	
	public Line(double startX, double startY, double endX, double endY)
	{
		super(startX, startY, endX, endY);
		
		/* Enable dragging of any Line shape. */
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
	public Line getShape()
	{
		return this;
	}
	
	/**
	 * Gets the length of the line.
	 *
	 * @return The length of the line.
	 */
	public double getLength()
	{
		return Math.sqrt(
				Math.pow(
						getEndX() - getStartX(), 2
				) + Math.pow(getEndY() - getStartY(), 2)
		);
	}
	
	/* Setters. */
	/**
	 * Set the x and y coordinates of the line.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Line line = new Line();
	 *     line.setPosition(10, 10);
	 * </pre>
	 *
	 * @param x The x coordinate of the line.
	 */
	public void setX(double x)
	{
		setStartX(x);
		setEndX(x);
	}
	
	/**
	 * Set the x and y coordinates of the line.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Line line = new Line();
	 *     line.setPosition(10, 10);
	 * </pre>
	 *
	 * @param y The y coordinate of the line.
	 */
	public void setY(double y)
	{
		setStartY(y);
		setEndY(y);
	}
	
	/**
	 * Sets the beginning and end position of the line.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Line line = new Line();
	 *     line.setPosition(10, 10, 20, 20);
	 *     // This is the same as:
	 *     line.setStartPosition(10, 10);
	 *     line.setEndPosition(20, 20);
	 * </pre>
	 *
	 * @param startX The x coordinate of the beginning of the line.
	 * @param startY The y coordinate of the beginning of the line.
	 * @param endX The x coordinate of the end of the line.
	 * @param endY The y coordinate of the end of the line.
	 */
	public void setPosition(double startX, double startY, double endX, double endY)
	{
		setStartX(startX);
		setStartY(startY);
		setEndX(endX);
		setEndY(endY);
	}
	
	/**
	 * Sets the beginning position of the line.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Line line = new Line();
	 *     line.setStartPosition(10, 10);
	 * </pre>
	 *
	 * @param x The x coordinate of the beginning of the line.
	 * @param y The y coordinate of the beginning of the line.
	 */
	public void setStartPosition(double x, double y)
	{
		setStartX(x);
		setStartY(y);
	}
	
	/**
	 * Sets the end position of the line.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Line line = new Line();
	 *     line.setEndPosition(20, 20);
	 * </pre>
	 *
	 * @param x The x coordinate of the end of the line.
	 * @param y The y coordinate of the end of the line.
	 */
	public void setEndPosition(double x, double y)
	{
		setEndX(x);
		setEndY(y);
	}
	
	// Giga ultra galaxy brain method coming up. ( •_•)>⌐■-■ (⌐■_■)
	// Note: It has been phased out, but it's cool, so it stays.
	/**
	 * Sets the length of the line by changing the end point.
	 * <p/>
	 * This does mean that since only the end point of the line
	 * is changed, it'll change from the start point. Essentially,
	 * it'll only get shorter or longer in one direction.
	 *
	 * @param length The new length of the line.
	 */
	public void setLength(double length)
	{
		double angle = Math.atan2(getEndY() - getStartY(), getEndX() - getStartX());
		setEndX(getStartX() + length * Math.cos(angle));
		setEndY(getStartY() + length * Math.sin(angle));
	}
	
	/**
	 * Sets the thickness of the line.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Line line = new Line();
	 *     line.setThickness(5);
	 * </pre>
	 *
	 * @param thickness The thickness of the line.
	 */
	public void setThickness(double thickness)
	{
		setStrokeWidth(thickness);
	}
	
	/* TODO: Investigate usability for using this to fix undo-redo length modification changing the angle when it shouldn't.
	public double calculateAngle()
	{
		return Math.atan2(getEndY() - getStartY(), getEndX() - getStartX());
	}
	*/
}
