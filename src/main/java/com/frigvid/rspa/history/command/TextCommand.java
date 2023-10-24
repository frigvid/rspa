package com.frigvid.rspa.history.command;

import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.history.Command;

/**
 * Undo-redo command for changing the text of a Text node.
 */
public class TextCommand
		implements Command
{
	private final Object node;
	private final String oldValue;
	private final String newValue;
	
	public TextCommand(Object node, String oldValue, String newValue)
	{
		this.node = node;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	@Override
	public void execute()
	{
		((Text) node).setText(newValue);
	}
	
	@Override
	public void undo()
	{
		((Text) node).setText(oldValue);
	}
}
