package com.frigvid.rspa.history.command;

import com.frigvid.rspa.history.Command;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class MoveLayerCommand
		implements Command
{
	private final Node shape;
	private final MoveAction action;
	private final ObservableList<Node> canvasChildren;
	private final int oldPosition;
	
	public MoveLayerCommand(Node shape, MoveAction action, ObservableList<Node> canvasChildren) {
		this.shape = shape;
		this.action = action;
		this.canvasChildren = canvasChildren;
		this.oldPosition = canvasChildren.indexOf(shape);
	}
	
	// Generally, you'd want to keep these in their own file, but this is fine. Not quote-unqoute best practice, but functional all the same.
	public enum MoveAction
	{
		TO_FRONT,
		TO_BACK,
		FORWARD_BY_ONE,
		BACKWARD_BY_ONE
	}
	
	@Override
	public void execute()
	{
		switch (action)
		{
			case TO_FRONT:
				shape.toFront();
				break;
			case TO_BACK:
				shape.toBack();
				break;
			case FORWARD_BY_ONE:
				int forwardIndex = canvasChildren.indexOf(shape);
				if (forwardIndex < canvasChildren.size() - 1)
				{
					canvasChildren.remove(shape);
					canvasChildren.add(forwardIndex + 1, shape);
				}
				break;
			case BACKWARD_BY_ONE:
				int backwardIndex = canvasChildren.indexOf(shape);
				if (backwardIndex > 0)
				{
					canvasChildren.remove(shape);
					canvasChildren.add(backwardIndex - 1, shape);
				}
				break;
		}
	}
	
	@Override
	public void undo()
	{
		canvasChildren.remove(shape);
		canvasChildren.add(oldPosition, shape);
	}
}
