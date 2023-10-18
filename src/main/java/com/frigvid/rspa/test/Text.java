package com.frigvid.rspa.test;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Pane;

public class Text extends Shape
{
	private Text text;
	//private Text text;
	private FontWeight fontWeight = FontWeight.NORMAL;
	private FontPosture fontStyle = FontPosture.REGULAR;
	
	public Text(String text)
	{
		this.text = text;
	}
	
	public Text(double x, double y, String text)
	{
		super(x, y);
		this.text = text;
		text = new javafx.scene.text.Text(x, y, text);
	}
	
	@Override
	public void addToPane(Pane pane) {
		if ("canvas".equals(pane.getId())) {
			pane.getChildren().add(text);
			
			text.setFill(fillColor);
			text.setStroke(borderColor);
			text.setOpacity(opacity);
			text.getTransforms().add(rotation);
		}
	}
	
	public javafx.scene.text.Text getText() {
		return this.text;
	}
	
	private Font getFont()
	{
		return this.text.getFont();
	}
	
	public void setFontWeight(FontWeight fontWeight)
	{
		this.fontWeight = fontWeight;
		updateFont();
	}
	
	public void setFontStyle(FontPosture fontStyle)
	{
		this.fontStyle = fontStyle;
		updateFont();
	}
	
	private void setFont(Font font)
	{
		this.text.setFont(font);
	}
	
	private void updateFont()
	{
		this.setFont(
			Font.font(
				this.getFont().getFamily(),
				fontWeight,
				fontStyle,
				this.getFont().getSize()
			)
		);
	}
}
