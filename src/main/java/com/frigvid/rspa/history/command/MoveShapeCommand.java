package com.frigvid.rspa.history.command;

import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.history.Command;
import javafx.scene.Node;

/**
 * Undo-redo command for moving a shape.
 */
public class MoveShapeCommand
		implements Command
{
	private final Node shape;
	private final double oldX;
	private final double oldY;
	private double newX;
	private double newY;
	
	public MoveShapeCommand(Node shape, double oldX, double oldY)
	{
		this.shape = shape;
		this.oldX = oldX;
		this.oldY = oldY;
	}
	
	public MoveShapeCommand(Node shape, double oldX, double oldY, double newX, double newY)
	{
		this.shape = shape;
		this.oldX = oldX;
		this.oldY = oldY;
		this.newX = newX;
		this.newY = newY;
	}
	
	@Override
	public void execute() {
		setShapePosition(shape, newX, newY);
	}
	
	@Override
	public void undo() {
		setShapePosition(shape, oldX, oldY);
	}
	
	/**
	 * Sets the position of an unknown node type.
	 * <p>
	 * Example usage:
	 * <pre>
	 *     moveShapeCmd = new MoveShapeCommand(shape, getShapeX(), getShapeY());
	 *     moveShapeCmd.setShapePosition(shape, 100, 200);
	 * </pre>
	 *
	 * @param shape The Shape or Text to move.
	 * @param x The new x position.
	 * @param y The new y position.
	 */
	public void setShapePosition(Node shape, double x, double y)
	{
		if (shape instanceof Circle circle)
		{
			circle.setCenterX(x);
			circle.setCenterY(y);
		}
		else if (shape instanceof Line line)
		{
			double deltaX = x - line.getStartX();
			double deltaY = y - line.getStartY();
			
			line.setStartX(line.getStartX() + deltaX);
			line.setStartY(line.getStartY() + deltaY);
			line.setEndX(line.getEndX() + deltaX);
			line.setEndY(line.getEndY() + deltaY);
		}
		else if (shape instanceof Rectangle rectangle)
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
