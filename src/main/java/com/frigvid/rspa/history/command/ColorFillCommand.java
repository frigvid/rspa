package com.frigvid.rspa.history.command;

import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.history.Command;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * Undo-redo command for changing the fill color of a shape.
 * <p/>
 * TODO: Text should not have fill changes added to stack.
 * TODO: Implement an unsupported type exception. Not really necessary, but it would be nice.
 */
public class ColorFillCommand
		implements Command
{
	private final Object node;
	private final Color oldValue;
	private final Color newValue;
	
	public ColorFillCommand(Object node, Color oldValue, Color newValue)
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
			shape.setFill(newValue);
		}
		else if (node instanceof Text text) // It's not always false, the caller may pass an unsupported type. Ignore it.
		{
			text.setFill(newValue);
		}
	}
	
	@Override
	public void undo()
	{
		if (node instanceof Shape shape)
		{
			shape.setFill(oldValue);
		}
		else if (node instanceof Text text) // It's not always false, the caller may pass an unsupported type. Ignore it.
		{
			text.setFill(oldValue);
		}
	}
}
