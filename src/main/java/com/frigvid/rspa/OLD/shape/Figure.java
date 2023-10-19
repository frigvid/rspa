package com.frigvid.rspa.shape;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Map;

public abstract class Figure
				extends Pane
				implements iFigure
{
	private Point2D startX;
	private Point2D startY;
	private Point2D topLeft;
	private Color strokeColor;
	private Color fillColor;
	private boolean isFill;
	private Map<String, Double> properties = new HashMap<>();
	
	public Figure() {}
	
	public Figure(Point2D startX, Point2D startY)
	{
		this.startX = startX;
		this.startY = startY;
		this.fillColor = Color.TRANSPARENT;
		this.topLeft = calcTopLeft();
	}
	
	public Figure(Point2D startX, Point2D startY, Color strokeColor)
	{
		this.startX = startX;
		this.startY = startY;
		this.strokeColor = strokeColor;
		this.fillColor = Color.TRANSPARENT;
		this.topLeft = calcTopLeft();
	}
	
	public Figure(Point2D startX, Point2D startY, Color strokeColor, Color fillColor, boolean isFill)
	{
		this.startX = startX;
		this.startY = startY;
		this.strokeColor = strokeColor;
		this.fillColor = fillColor;
		this.isFill = isFill;
		this.topLeft = calcTopLeft();
	}
	
	/* Shape position */
	@Override
	public Point2D getPosition()
	{
		return this.startX;
	}
	
	public Point2D getEndPosition()
	{
		return this.startY;
	}
	
	public Point2D getTopLeft()
	{
		return this.topLeft;
	}
	
	@Override
	public void setPosition(Point2D position)
	{
		this.startX = position;
	}
	
	public void setEndPosition(Point2D position)
	{
		this.startY = position;
	}
	
	public void setTopLeft(Point2D position)
	{
		this.startX = position;
	}
	
	public Point2D calcTopLeft()
	{
		double x = Math.min(this.getPosition().getX(), this.getEndPosition().getX());
		double y = Math.min(this.getPosition().getY(), this.getEndPosition().getY());
		return new Point2D(x, y);
	}
	
	/* Generic getters. */
	public Color getStrokeColor()
	{
		return this.strokeColor;
	}
	
	public Color getFillColor()
	{
		return this.fillColor;
	}
	
	public boolean getIsFill()
	{
		return this.isFill;
	}
	
	/* Generic setters. */
	public void setStrokeColor(Color color)
	{
		this.strokeColor = color;
	}
	
	public void setFillColor(Color fillColor)
	{
		this.fillColor = fillColor;
	}
	
	public void setIsFill(boolean isFill)
	{
		this.isFill = isFill;
	}
	
	
	
	
	
	
	
	
	
	
	
	/* Shape properties.
	 *
	 * Kind of a black box written while being dead drunk.
	 * Not sure what my idea was anymore, so Imma just keep
	 * going and cry meanwhile.
	 *
	 * Like, seriously, what does numbers mean Jason!?
	 */
	
	/**
	 * Sets the properties map.
	 * @param properties The properties map.
	 */
	public void setProp(Map<String, Double> properties)
	{
		this.properties = properties;
		setPropMap();
	}
	
	/**
	 * Returns the properties map.
	 * @return The properties map.
	 */
	public Map<String, Double> getProp()
	{
		getPropMap();
		return this.properties;
	}
	
	/**
	 * Adds a property to the properties map.
	 * @param key The key of the property.
	 * @param value The value of the property.
	 */
	public void addPropToMap(String key, Double value)
	{
		properties.put(key, value);
	}
	
	protected double getPropFromMap(String s)
	{
		try
		{
			return (double)properties.get(s);
		}
		catch (Exception e)
		{
			System.out.println("Error, can't find this property.");
		}
		
		return Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Sets the properties of the shape based on the values in the properties map.
	 * <p>
	 * The properties map should contain the following keys:
	 * - StartX: the x-coordinate of the starting point of the shape,
	 * - StartY: the y-coordinate of the starting point of the shape,
	 * - EndX: the x-coordinate of the ending point of the shape,
	 * - EndY: the y-coordinate of the ending point of the shape,
	 * - StrokeR: the red component of the stroke color,
	 * - StrokeG: the green component of the stroke color,
	 * - StrokeB: the blue component of the stroke color,
	 * - FillR: the red component of the fill color,
	 * - FillG: the green component of the fill color,
	 * - FillB: the blue component of the fill color.
	 * <p>
	 * The method retrieves the values from the map and
	 * creates a Point2D object for the start and end positions,
	 * and a Color object for the stroke and fill colors.
	 */
	protected void setPropMap()
	{
		double startX;
		double startY;
		double endX;
		double endY;
		
		startX = (double) properties.get("StartX");
		startY = (double) properties.get("StartY");
		endX = (double) properties.get("EndX");
		endY = (double) properties.get("EndY");
		
		Point2D startPosition = new Point2D(startX, startY);
		Point2D endPosition = new Point2D(endX, endY);
		
		double strokeR;
		double strokeG;
		double strokeB;
		double fillR;
		double fillG;
		double fillB;
		
		strokeR = (double) properties.get("StrokeR");
		strokeG = (double) properties.get("StrokeG");
		strokeB = (double) properties.get("StrokeB");
		fillR = (double) properties.get("FillR");
		fillG = (double) properties.get("FillG");
		fillB = (double) properties.get("FillB");
		
		Color strokeColor = Color.color(strokeR, strokeG, strokeB);
		Color fillColor = Color.color(fillR, fillG, fillB);
	}
	
	/* TODO: Fix values. */
	/**
	 * Populates the properties map with the current values of the Shape object.
	 * <p>
	 * The properties map contains the following keys:
	 * - StartX: the x-coordinate of the starting point of the shape,
	 * - StartY: the y-coordinate of the starting point of the shape,
	 * - EndX: the x-coordinate of the ending point of the shape,
	 * - EndY: the y-coordinate of the ending point of the shape,
	 * - StrokeR: the red component of the stroke color,
	 * - StrokeG: the green component of the stroke color,
	 * - StrokeB: the blue component of the stroke color,
	 * - FillR: the red component of the fill color,
	 * - FillG: the green component of the fill color,
	 * - FillB: the blue component of the fill color.
	 */
	protected void getPropMap()
	{
		properties.put("StartX", this.startX.getX());
		properties.put("StartY", this.startX.getY());
		properties.put("EndX", this.startY.getX());
		properties.put("EndY", this.startY.getY());
		
		properties.put("StrokeR", this.strokeColor.getRed());
		properties.put("StrokeG", this.strokeColor.getGreen());
		properties.put("StrokeB", this.strokeColor.getBlue());
		properties.put("FillR", this.fillColor.getRed());
		properties.put("FillG", this.fillColor.getGreen());
		properties.put("FillB", this.fillColor.getBlue());
	}
}
