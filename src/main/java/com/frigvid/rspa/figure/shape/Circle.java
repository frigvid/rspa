package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.IFigure;

public class Circle
		extends javafx.scene.shape.Circle
		implements IFigure
{
	public Circle() {}
	
	public Circle(double centerX, double centerY, double radius)
	{
		super(centerX, centerY, radius);
	}
	
	public void test() {
		Circle test = new Circle(100, 100, 25);
		test.getRadius();
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
