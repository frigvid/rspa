package com.frigvid.rspa.history.command;

import com.frigvid.rspa.history.Command;
import javafx.scene.Node;

public class RotateCommand
		implements Command
{
	private final Node node;
	private final double oldValue;
	private final double newValue;
	
	public RotateCommand(Node node, double oldValue, double newValue)
	{
		this.node = node;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	@Override
	public void execute()
	{
		node.setRotate(newValue);
	}
	
	@Override
	public void undo()
	{
		node.setRotate(oldValue);
	}
}
