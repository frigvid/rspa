package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.figure.FigureType;
import com.frigvid.rspa.figure.IFigure;
import com.frigvid.rspa.figure.ShapeDragHandler;

// TODO: Implement abstract wrapper class for Shapes and use that instead of extending it directly.
public class Circle
		extends javafx.scene.shape.Circle
		implements IFigure
{
	private final static FigureType FIGURE_TYPE = FigureType.CIRCLE;
	
	public Circle()
	{
		/* Enable dragging of any Line shape. */
		ShapeDragHandler dragHandler = new ShapeDragHandler(this);
		dragHandler.enableDrag();
	}
	
	public Circle(double centerX, double centerY, double radius)
	{
		super(centerX, centerY, radius);
		
		/* Enable dragging of any Circle shape. */
		ShapeDragHandler dragHandler = new ShapeDragHandler(this);
		dragHandler.enableDrag();
	}
	
	public FigureType getType()
	{
		return FIGURE_TYPE;
	}
	
	public Circle getShape()
	{
		return this;
	}
	
	public void setPosition(double x, double y)
	{
		this.setCenterX(x);
		this.setCenterY(y);
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
