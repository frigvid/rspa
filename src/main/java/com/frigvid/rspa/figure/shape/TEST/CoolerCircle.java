package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.IFigure;
import com.frigvid.rspa.figure.shape.TEST.Type;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class CoolerCircle
		extends Figure
		implements IFigure
{
	//public CoolerCircle() {}
	
	public CoolerCircle(double centerX, double centerY, double radius)
	{
		super(Type.CIRCLE);
		this.node = new Circle(centerX, centerY, radius);
	}
	
	public Type getType()
	{
		return Type.CIRCLE;
	}
	
	public void setStroke(Paint value)
	{
		((Circle) node).setStroke(value);
	}
	
	public void setFill(Paint value)
	{
		((Circle) node).setFill(value);
	}
	
	public void setOpacity(double value)
	{
		((Circle) node).setOpacity(value);
	}
}
