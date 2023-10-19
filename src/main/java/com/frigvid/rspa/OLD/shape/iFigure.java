package com.frigvid.rspa.shape;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import java.util.Map;

public interface iFigure
{
	void setXPosition(double x);
	void setYPosition(double y);
	double getXPosition();
	double getYPosition();
	
	void setStrokeColor(Color color);
	Color getStrokeColor();
	void setFillColor(Color color);
	Color getFillColor();
	
	void draw(Pane pane);
	
	
	
	
	
	
	
	
	
	
	/* Shape position. */
	void setPosition(Point2D position);
	Point2D getPosition();
	
	/* Shape properties.
	 *
	 * Currently a bit of a black box because it was
	 * made while dead drunk and I can't remember shit
	 * anymore. I *think* from reading the code, that
	 * the idea is storing the shape properties in a
	 * Map to let them be easily modified. */
	void setProp(Map<String, Double> properties);
	Map<String, Double> getProp();
}
