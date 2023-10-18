package com.frigvid.rspa.object;

import javafx.scene.paint.Color;

public class Text extends Shape
{
	private String text;

	public Text(String text, double rotation, double opacity, Color borderColor, Color fillColor, double positionX, double positionY, int layer)
	{
		super(rotation, opacity, borderColor, fillColor, positionX, positionY, layer);
		this.text = text;
	}
}