package com.frigvid.rspa.figure.TEMP;

import com.frigvid.rspa.IFigure;
import javafx.scene.shape.Shape;

public class Figure
		//extends Group
		implements IFigure
{
	private final Shape shape;
	
	public Figure(Type type, double... args)
	{
		this.shape = FigureFactory.Figure(type, args);
		// NOTE: This lets you add shapes with `canvas.getChildren().add(circle);`
		//this.getChildren().add(shape);
	}
	
	// Now you can add your custom methods or properties
	// And also expose any specific properties or methods from the contained Shape
	
	/**
	 * This method lets you get the underlying JavaFX Node.
	 * When trying to add a shape to a Pane, for example with
	 * `canvas.getChildren().add(circle);`, it wouldn't work,
	 * and you can instead call this method to get it to draw
	 * to the Pane by doing:
	 * <p/>
	 * `canvas.getChildren().add(circle.getNode());`
	 *
	 * @return shape - JavaFX Node
	 */
	public Shape getNode()
	{
		return shape;
	}
	
	// ... other custom methods and properties ...
}
