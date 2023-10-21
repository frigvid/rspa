package com.frigvid.rspa.figure;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * The ShapeHandler is used as a discount way
 * of changing an unknown Shape's properties
 * without trying to cast it to a specific Shape.
 * <p/>
 * This class is used in the {@link com.frigvid.rspa.ui.CreateContext}
 * class to change the properties of a Shape.
 * <p/>
 * Example usage:
 * <pre>
 *     ShapeHandler shapeHandler = new ShapeHandler();
 *     shapeHandler.setStrokeColor(shape, Color.RED);
 *     shapeHandler.setFillColor(shape, Color.BLUE);
 *     shapeHandler.setOpacity(shape, 0.5);
 *     shapeHandler.deleteShape(shape, canvas);
 * </pre>
 */
public class ShapeHandler
{
	/* TODO: Implement shared classes to both get various properties
	 * 		 of a shape and set various properties of a shape.
	 *
	 * As an example of what properties need to be accessible, see
	 * the list below:
	 * - Stroke color (set/get)
	 * - Fill color (set/get)
	 * - Opacity (set/get)
	 * - Delete
	 */
	
	public void setStrokeColor(Shape shape, Color color)
	{
		shape.setStroke(color);
	}
	
	public void setFillColor(Shape shape, Color color)
	{
		shape.setFill(color);
	}
	
	public void setOpacity(Shape shape, double opacity)
	{
		shape.setOpacity(opacity);
	}
	
	public void deleteShape(Shape shape, Pane canvas)
	{
		canvas.getChildren().remove(shape);
	}
}
