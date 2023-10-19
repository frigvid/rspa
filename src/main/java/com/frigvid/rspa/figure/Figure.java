package com.frigvid.rspa.figure;

import com.frigvid.rspa.IFigure;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Figure
		extends Group
		implements IFigure
{
	private final Shape shape;
	
	public Figure(Type type, double... args)
	{
		this.shape = FigureFactory.Figure(type, args);
		// NOTE: This lets you add shapes with `canvas.getChildren().add(circle);`
		this.getChildren().add(shape);
	}
	
	// Now you can add your custom methods or properties
	// And also expose any specific properties or methods from the contained Shape
	
	public Shape getUnderlyingShape()
	{
		return shape;
	}
	
	//public void draw(Pane pane, Figure figure)
	//{
	//	pane.getChildren().add(figure.getUnderlyingShape());
	//}
	
	// ... other custom methods and properties ...
}
