package com.frigvid.rspa.history.command;

import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.history.Command;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Undo-redo command for changing the stroke color of a shape or text.
 * <p/>
 * TODO: Implement an unsupported type exception. Not really necessary, but it would be nice.
 */
public class ColorStrokeCommand
		implements Command
{
	private final Object node;
	private final Color oldValue;
	private final Color newValue;
	
	public ColorStrokeCommand(Object node, Color oldValue, Color newValue)
	{
		this.node = node;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	@Override
	public void execute()
	{
		if (node instanceof Shape shape)
		{
			shape.setStroke(newValue);
		}
		else if (node instanceof Text text) // It's not always false, the caller may pass an unsupported type. Ignore it.
		{
			text.setStroke(newValue);
		}
	}
	
	@Override
	public void undo()
	{
		if (node instanceof Shape shape)
		{
			shape.setStroke(oldValue);
		}
		else if (node instanceof Text text) // It's not always false, the caller may pass an unsupported type. Ignore it.
		{
			text.setStroke(oldValue);
		}
	}
}
