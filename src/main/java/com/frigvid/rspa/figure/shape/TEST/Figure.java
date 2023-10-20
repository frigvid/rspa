package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.figure.shape.TEST.Type;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

public abstract class Figure
{
	protected Node node;
	protected Shape shape;
	private final Type type;
	
	public Figure(Type type)
	{
		this.type = type;
	}
	
	public Node getNode()
	{
		return node;
	}
	
	public Shape getShape()
	{
		return shape;
	}
	
	public Type getType()
	{
		return type;
	}
}
