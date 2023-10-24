package com.frigvid.rspa.history.command;

import com.frigvid.rspa.history.Command;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class DeleteShapeCommand
		implements Command
{
	private final Pane canvas;
	private final Node shape;
	
	public DeleteShapeCommand(Pane canvas, Node shape)
	{
		this.canvas = canvas;
		this.shape = shape;
	}
	
	@Override
	public void execute()
	{
		canvas.getChildren().remove(shape);
	}
	
	@Override
	public void undo()
	{
		canvas.getChildren().add(shape);
		
	}
}