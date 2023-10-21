package com.frigvid.rspa.figure.shape;

import javafx.scene.Cursor;

// TODO: Implement abstract wrapper class for Shapes and use that instead of extending it directly.
public class Circle
		extends javafx.scene.shape.Circle
		implements IFigure
{
	public Circle() {}
	
	public Circle(double centerX, double centerY, double radius)
	{
		super(centerX, centerY, radius);
		changeCursorOnHover();
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
	
	// TESTING
	//public Type getType()
	//{
	//	return Type.CIRCLE;
	//}
	
	/* Mathematical functions. Very pointless though, so commented out until further notice.
	 * Might be useful for something like what's done in the Line class though.
	public double calculateArea() {
		return Math.PI * Math.pow(this.getRadius(), 2);
	}
	
	public double calculatePerimeter() {
		return 2 * Math.PI * this.getRadius();
	}
	
	public double calculateDiameter() {
		return 2 * this.getRadius();
	}
	
	public double calculateCircumference() {
		return 2 * Math.PI * this.getRadius();
	}
	*/
}
