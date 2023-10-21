package com.frigvid.rspa.history;

import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import javafx.scene.shape.Shape;

public class MoveShape
		implements ICommand
{
	private final Shape shape;
	private final double oldX;
	private final double oldY;
	private double newX;
	private double newY;
	
	public MoveShape(Shape shape, double oldX, double oldY)
	{
		this.shape = shape;
		this.oldX = oldX;
		this.oldY = oldY;
	}
	
	public void setNewPosition(double newX, double newY)
	{
		this.newX = newX;
		this.newY = newY;
	}
	
	@Override
	public void undo()
	{
		//if (shape instanceof Circle)
		//{
		//	Circle circle = (Circle) shape;
		//	circle.setCenterX(oldX);
		//	circle.setCenterY(oldY);
		//}
		//else if (shape instanceof Rectangle)
		//{
		//	Rectangle rectangle = (Rectangle) shape;
		//	rectangle.setX(oldX);
		//	rectangle.setY(oldY);
		//}
		//
		//shape.setTranslateX(0);
		//shape.setTranslateY(0);
		getShape(shape, true);
	}
	
	@Override
	public void redo() {
		//if (shape instanceof Circle) {
		//	Circle circle = (Circle) shape;
		//	circle.setCenterX(newX);
		//	circle.setCenterY(newY);
		//} else if (shape instanceof Rectangle) {
		//	Rectangle rectangle = (Rectangle) shape;
		//	rectangle.setX(newX);
		//	rectangle.setY(newY);
		//}
		//shape.setTranslateX(0);
		//shape.setTranslateY(0);
		getShape(shape, false);
	}
	
	private void getShape(Shape shape, boolean isUndo)
	{
		if (shape instanceof Circle)
		{
			Circle circle = (Circle) shape;
			if (isUndo)
			{
				circle.setCenterX(oldX);
				circle.setCenterY(oldY);
			}
			else
			{
				circle.setCenterX(newX);
				circle.setCenterY(newY);
			}
			//return new Circle(circle.getX(), circle.getY(), circle.getRadius());
		}
		else if (shape instanceof Rectangle)
		{
			Rectangle rectangle = (Rectangle) shape;
			if (isUndo)
			{
				rectangle.setX(oldX);
				rectangle.setY(oldY);
			}
			else
			{
				rectangle.setX(newX);
				rectangle.setY(newY);
			}
			//return new Rectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
		}
		else if (shape instanceof Line)
		{
			Line line = (Line) shape;
			if (isUndo)
			{
				line.setX(oldX);
				line.setY(oldY);
			}
			else
			{
				line.setX(newX);
				line.setY(newY);
			}
			//return new Line(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
		}
		else if (shape instanceof Text)
		{
			Text text = (Text) shape;
			if (isUndo)
			{
				text.setX(oldX);
				text.setY(oldY);
			}
			else
			{
				text.setX(newX);
				text.setY(newY);
			}
			//return new Text(text.getX(), text.getY(), text.getText());
		}
		
		shape.setTranslateX(0);
		shape.setTranslateY(0);
		
		//return null; // Or throw an exception if unsupported shape
		//throw new UnsupportedOperationException();
	}
}
