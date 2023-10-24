package com.frigvid.rspa.history.command;

import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.history.Command;
import javafx.scene.Node;

/**
 * Undo-redo command for resizing changes.
 * <p/>
 * TODO: Fix this terrible abomination.
 */
public class ResizeCommand
		implements Command
{
	private final Object target;
	private final double oldValue;
	private final double newValue;
	private final PropertyType propertyType;
	
	public ResizeCommand(Object target, double oldValue, double newValue, PropertyType propertyType)
	{
		this.target = target;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.propertyType = propertyType;
	}
	
	// Generally, you'd want to keep these in their own file, but this is fine. Not quote-unqoute best practice, but functional all the same.
	public enum PropertyType
	{
		OPACITY,
		RADIUS,
		WIDTH,
		HEIGHT,
		LENGTH,
		THICKNESS,
		FONT_SIZE
	}
	
	@Override
	public void execute()
	{
		applyValue(newValue);
	}
	
	@Override
	public void undo()
	{
		applyValue(oldValue);
	}
	
	private void applyValue(double value)
	{
		switch (propertyType)
		{
			case OPACITY:
				if (target instanceof Node node)
				{
					node.setOpacity(value);
				}
				break;
			case RADIUS:
				if (target instanceof Circle circle)
				{
					circle.setRadius(value);
				}
				break;
			case WIDTH:
				if (target instanceof Rectangle rectangle)
				{
					rectangle.setWidth(value);
				}
				break;
			case HEIGHT:
				if (target instanceof Rectangle rectangle)
				{
					rectangle.setHeight(value);
				}
				break;
			case LENGTH:
				if (target instanceof Line line)
				{
					line.setEndX(line.getStartX() + value);
				}
				break;
			case THICKNESS:
				if (target instanceof Line line) {
					line.setStrokeWidth(value);
				}
				break;
			case FONT_SIZE:
				if (target instanceof Text text)
				{
					text.setSize(value);
				}
				break;
			default:
				throw new IllegalArgumentException("Unsupported property type: " + propertyType);
		}
	}
}