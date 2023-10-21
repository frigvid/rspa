package com.frigvid.rspa.figure;

import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.history.MoveShape;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This handles dragging of shapes.
 * <p/>
 * Example usage (in a constructor):
 * <pre>
 *     ShapeDragHandler shapeDragHandler = new ShapeDragHandler(shape);
 *     shapeDragHandler.enableDrag();
 * </pre>
 * TODO: This should probably be moved into an abstract wrapper class for Shapes instead of being its own thing.
 * NOTE: However, this can be considered as mostly complete for now.
 */
public class ShapeDragHandler
{
	private final Node shape;
	
	public ShapeDragHandler(Node shape)
	{
		this.shape = shape;
	}
	
	public void enableDrag()
	{
		MoveShape moveShape = new MoveShape((Shape) shape, getShapeX(), getShapeY());
		AtomicReference<Double> xOffset = new AtomicReference<>((double) 0);
		AtomicReference<Double> yOffset = new AtomicReference<>((double) 0);
		
		/* Set cursor to hand when hovering Shapes to signify stuff.
		 *
		 * TODO: Figure out a way to immediately change the cursor to an
		 * 		 OPEN_HAND while ALT is pressed. A simple if-else branch
		 * 		 doesn't function properly, as the cursor only changes once
		 * 		 the mouse is moved.
		 */
		shape.setOnMouseMoved(event ->
		{
			if (event.isAltDown())
			{
				shape.setCursor(Cursor.OPEN_HAND);
			}
			else
			{
				shape.setCursor(Cursor.HAND);
			}
		});
		
		/* Set cursor to move, when pressing and holding Cursor Primary Key. */
		shape.setOnMousePressed(event ->
		{
			if (event.isAltDown() && event.isPrimaryButtonDown())
			{
				shape.setCursor(Cursor.MOVE);
				xOffset.set(event.getSceneX() - getShapeX());
				yOffset.set(event.getSceneY() - getShapeY());
				event.consume();
			}
			else if (!event.isAltDown())
			{
				shape.setCursor(Cursor.HAND);
			}
		});
		
		/* Handle actual movement of Shapes while ALT is pressed. */
		shape.setOnMouseDragged(event ->
		{
			if (event.isAltDown())
			{
				double newX = event.getSceneX() - xOffset.get();
				double newY = event.getSceneY() - yOffset.get();
				
				Pane canvas = (Pane) shape.getParent();
				double canvasWidth = canvas.getWidth();
				double canvasHeight = canvas.getHeight();
				
				double shapeWidth = getShapeWidth();
				double shapeHeight = getShapeHeight();
				
				newX = Math.max(0, newX);
				newY = Math.max(0, newY);
				
				if (newX + shapeWidth > canvasWidth)
				{
					newX = canvasWidth - shapeWidth;
				}
				if (newY + shapeHeight > canvasHeight)
				{
					newY = canvasHeight - shapeHeight;
				}
				
				setShapePosition(newX, newY);
			}
		});
		
		shape.setOnMouseReleased(event ->
		{
			if(event.isAltDown())
			{
				shape.setCursor(Cursor.OPEN_HAND);
			}
			else
			{
				shape.setCursor(Cursor.HAND);
			}
			
			moveShape.setNewPosition(getShapeX(), getShapeY());
		});
		
		/* Reset cursor when mouse exits the shape. */
		shape.setOnMouseExited(event -> shape.setCursor(Cursor.DEFAULT));
	}
	
	/**
	 * Wrapper method for X-coordinate of shape.
	 * <p/>
	 * Example usage (assuming you've initialized):
	 * <pre>
	 *     Shape shape = new Shape();
	 *     double x = getShapeX(shape);
	 * </pre>
	 *
	 * @return The X-coordinate of the shape.
	 */
	private double getShapeX()
	{
		if (shape instanceof Circle circle)
		{
			return circle.getCenterX();
		}
		
		if (shape instanceof Line line)
		{
			return line.getStartX();
		}
		
		if (shape instanceof Rectangle rectangle)
		{
			return rectangle.getX();
		}
		
		if (shape instanceof Text text)
		{
			return text.getX();
		}
		
		return 0;
	}
	
	/**
	 * Wrapper method for Y-coordinate of shape.
	 * <p/>
	 * Example usage (assuming you've initialized):
	 * <pre>
	 *     Shape shape = new Shape();
	 *     double y = getShapeY(shape);
	 * </pre>
	 *
	 * @return The Y-coordinate of the shape.
	 */
	private double getShapeY()
	{
		if (shape instanceof Circle circle)
		{
			return circle.getCenterY();
		}
		
		if (shape instanceof Line line)
		{
			return line.getStartY();
		}
		
		if (shape instanceof Rectangle rectangle)
		{
			return rectangle.getY();
		}
		
		if (shape instanceof Text text)
		{
			return text.getY();
		}
		
		return 0;
	}
	
	/**
	 * Wrapper method for width (or radius) of shape.
	 * <p/>
	 * Example usage (assuming you've initialized):
	 * <pre>
	 *     Shape shape = new Shape();
	 *     double width = getShapeWidth(shape);
	 * </pre>
	 *
	 * @return The width (or radius) of the shape.
	 */
	private double getShapeWidth()
	{
		if (shape instanceof Circle circle)
		{
			return 2 * circle.getRadius();
		}
		
		if (shape instanceof Line line)
		{
			return Math.abs(line.getEndX() - line.getStartX());
		}
		
		if (shape instanceof Rectangle rectangle)
		{
			return rectangle.getWidth();
		}
		
		if (shape instanceof Text text)
		{
			return text.getLayoutBounds().getWidth();
		}
		
		return 0;
	}
	
	/**
	 * Wrapper method for height (or radius) of shape.
	 * <p/>
	 * Example usage (assuming you've initialized):
	 * <pre>
	 *     Shape shape = new Shape();
	 *     double height = getShapeHeight(shape);
	 * </pre>
	 *
	 * @return The height (or radius) of the shape.
	 */
	private double getShapeHeight()
	{
		if (shape instanceof Circle circle)
		{
			return 2 * circle.getRadius();
		}
		
		if (shape instanceof Line line)
		{
			return Math.abs(line.getEndY() - line.getStartY());
		}
		
		if (shape instanceof Rectangle rectangle)
		{
			return rectangle.getHeight();
		}
		
		if (shape instanceof Text text)
		{
			return text.getLayoutBounds().getHeight();
		}
		
		return 0;
	}
	
	/**
	 * Wrapper method for setting the position of the shape.
	 * <p/>
	 * Example usage (assuming you've initialized):
	 * <pre>
	 *     Shape shape = new Shape();
	 *     setShapePosition(shape, 10, 10);
	 * </pre>
	 *
	 * @param x The new X-coordinate of the shape.
	 * @param y The new Y-coordinate of the shape.
	 */
	private void setShapePosition(double x, double y)
	{
		if (shape instanceof Circle circle)
		{
			circle.setCenterX(x);
			circle.setCenterY(y);
		}
		else if (shape instanceof Line line)
		{
			double deltaX = x - getShapeX();
			double deltaY = y - getShapeY();
			line.setStartX(line.getStartX() + deltaX);
			line.setStartY(line.getStartY() + deltaY);
			line.setEndX(line.getEndX() + deltaX);
			line.setEndY(line.getEndY() + deltaY);
		}
		if (shape instanceof Rectangle rectangle)
		{
			rectangle.setX(x);
			rectangle.setY(y);
		}
		else if (shape instanceof Text text)
		{
			text.setX(x);
			text.setY(y);
		}
	}
}
