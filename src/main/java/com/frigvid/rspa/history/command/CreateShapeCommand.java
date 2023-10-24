package com.frigvid.rspa.history.command;

import com.frigvid.rspa.history.Command;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Undo-redo command for creating a shape.
 */
public class CreateShapeCommand
		implements Command
{
	private final Pane canvas;
	private final Node shape;
	
	public CreateShapeCommand(Pane canvas, Node shape)
	{
		this.canvas = canvas;
		this.shape = shape;
	}
	
	@Override
	public void execute()
	{
		canvas.getChildren().add(shape);
	}
	
	@Override
	public void undo()
	{
		canvas.getChildren().remove(shape);
	}
}