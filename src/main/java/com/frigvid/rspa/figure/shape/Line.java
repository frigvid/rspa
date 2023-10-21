package com.frigvid.rspa.figure.shape;

import javafx.scene.Cursor;

// TODO: Implement abstract wrapper class for Shapes and use that instead of extending it directly.
public class Line
		extends javafx.scene.shape.Line
		implements IFigure
{
	public Line() {}
	
	public Line(double startX, double startY, double endX, double endY)
	{
		super(startX, startY, endX, endY);
		changeCursorOnHover();
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
	
	// Giga ultra galaxy brain method coming up. ( •_•)>⌐■-■ (⌐■_■)
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
	 * Change the cursor to a hand when hovering over the text.
	 * <p/>
	 * Use it in the constructor.
	 * <p/>
	 * TODO: Move this to an abstract superclass, or less
	 * 		 optimally, a utility class.
	 */
	private void changeCursorOnHover()
	{
		this.setOnMouseEntered(event -> this.setCursor(Cursor.HAND));
		this.setOnMouseExited(event -> this.setCursor(Cursor.DEFAULT));
	}
	
	/* Mathematical function. Might be useful for something like setLength().
	public double calculateAngle()
	{
		return Math.atan2(getEndY() - getStartY(), getEndX() - getStartX());
	}
	*/
}
