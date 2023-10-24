package com.frigvid.rspa.figure;

import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.history.InvokeCommand;
import com.frigvid.rspa.history.command.MoveShapeCommand;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.concurrent.atomic.AtomicReference;

/**
 * This handles dragging of shapes.
 * <p/>
 * Example usage (in a constructor):
 * <pre>
 *     ShapeDragHandler shapeDragHandler = new ShapeDragHandler(shape);
 *     shapeDragHandler.enableDrag();
 * </pre>
 * NOTE: This should probably be moved into an abstract wrapper class for Shapes instead of being its own thing.
 */
public class ShapeDragHandler
{
	private final InvokeCommand invokeCommand = new InvokeCommand(); // For undo-redo.
	private MoveShapeCommand moveShapeCmd;
	private final Node shape;
	private double oldX;
	private double oldY;
	private double newX;
	private double newY;
	
	public ShapeDragHandler(Node shape)
	{
		this.shape = shape;
	}
	
	public void enableDrag()
	{
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
					// Save old position of shape on click.
					oldX = getShapeX();
					oldY = getShapeY();
					
					// Construct new command using the minimal constructor.
					moveShapeCmd = new MoveShapeCommand(shape, getShapeX(), getShapeY());
					
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
						// Update position of shape while dragging.
						newX = event.getSceneX() - xOffset.get();
						newY = event.getSceneY() - yOffset.get();
						
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
						
						// Use command method to update position of shape while in movement.
						moveShapeCmd.setShapePosition(shape, newX, newY);
					}
				});
		
		shape.setOnMouseReleased(event ->
				{
					newX = getShapeX();
					newY = getShapeY();
					
					if(event.isAltDown())
					{
						shape.setCursor(Cursor.OPEN_HAND);
					}
					else
					{
						shape.setCursor(Cursor.HAND);
					}
					
					// Add command to stack. Reason for making a new command is due to position offset issues occur otherwise.
					moveShapeCmd = new MoveShapeCommand(shape, oldX, oldY, newX, newY);
					invokeCommand.execute(moveShapeCmd);
				});
		
		/* Reset cursor when mouse exits the shape. */
		shape.setOnMouseExited(event -> shape.setCursor(Cursor.DEFAULT));
	}
	
	/**
	 * Wrapper method for X-coordinate of a shape.
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
	 * Wrapper method for Y-coordinate of a shape.
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
	 * Wrapper method for width (or radius) of a shape.
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
	 * Wrapper method for height (or radius) of a shape.
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
}
