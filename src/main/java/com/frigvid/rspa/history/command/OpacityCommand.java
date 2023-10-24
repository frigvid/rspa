package com.frigvid.rspa.history.command;

import com.frigvid.rspa.history.Command;
import javafx.scene.Node;

/**
 * Undo-redo command for opacity changes.
 * <p/>
 * TODO: Investigate why undoing this resets the opacity to 1.0 instead of the old value.
 */
public class OpacityCommand
		implements Command
{
	private final Node node;
	private final double oldValue;
	private final double newValue;
	
	public OpacityCommand(Node node, double oldValue, double newValue)
	{
		this.node = node;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	@Override
	public void execute()
	{
		node.setOpacity(newValue);
	}
	
	@Override
	public void undo()
	{
		node.setOpacity(oldValue);
	}
}