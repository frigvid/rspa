package com.frigvid.rspa.history.command;

import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.history.Command;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

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
		else if (node instanceof Text text)
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
		else if (node instanceof Text text)
		{
			text.setStroke(oldValue);
		}
	}
}
