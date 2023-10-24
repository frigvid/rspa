package com.frigvid.rspa.history.command;

import com.frigvid.rspa.history.Command;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

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
		System.out.println("Adding shape to canvas...");
		canvas.getChildren().add(shape);
	}
	
	@Override
	public void undo()
	{
		System.out.println("Removing shape from canvas...");
		canvas.getChildren().remove(shape);
	}
}