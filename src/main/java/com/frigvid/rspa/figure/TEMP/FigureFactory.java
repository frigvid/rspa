package com.frigvid.rspa.figure.TEMP;

import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Rectangle;
import javafx.scene.shape.Shape;

public class FigureFactory
{
	public static Shape Figure(Type type, double... args)
	{
		return switch (type)
		{
			case CIRCLE -> new Circle(args[0], args[1], args[2]);
			//case ELLIPSE -> new Ellipse(args[0], args[1], args[2], args[3]);
			case LINE -> null;
			//case POLYGON -> new Polygon(args[0], args[1], args[2], args[3]);
			case RECTANGLE -> new Rectangle(args[0], args[1], args[2], args[3]);
			//case TEXT -> null;
			default -> null;
		};
	}
}
