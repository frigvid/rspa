package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.IFigure;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Circle
		extends javafx.scene.shape.Circle
		implements IFigure
{
	public Circle() {}
	
	/**
	 *
	 * @param radius
	 * @param centerY
	 * @param centerX
	 */
	public Circle(double radius, double centerY, double centerX)
	{
		super(radius, centerY, centerX);
	}
	
	//@Override
	//public double setStartX(double startX)
	//{
	//	return 0;
	//}
	//
	//@Override
	//public double setStartY(double startY)
	//{
	//	return 0;
	//}
	//
	//@Override
	//public double getStartX()
	//{
	//	return 0;
	//}
	//
	//@Override
	//public double getStartY()
	//{
	//	return 0;
	//}
	//
	//@Override
	//public Color setStrokeColor(Color color)
	//{
	//	return null;
	//}
	//
	//@Override
	//public Color getStrokeColor()
	//{
	//	return null;
	//}
	//
	//@Override
	//public Color setFillColor(Color color)
	//{
	//	return null;
	//}
	//
	//@Override
	//public Color getFillColor()
	//{
	//	return null;
	//}
	//
	//@Override
	//public double setOpacity()
	//{
	//	return 0;
	//}
	//
	//@Override
	//public void draw(Pane pane)
	//{
	//
	//}
}
