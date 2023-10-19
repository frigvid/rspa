package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.IFigure;
import javafx.scene.text.Font;

public class Text
		extends javafx.scene.text.Text
		implements IFigure
{
	private boolean isItalic = false;
	private boolean isBold = false;
	private boolean isUnderlined = false;
	
	public Text() {}
	
	public Text(double x, double y, String text)
	{
		super(x, y, text);
	}
	
	/**
	 * This method enables the use of multiple types of font
	 * styles and lets you mix and match them in any order.
	 * <p/>
	 * Set the font styles using the following methods:
	 * <ul>
	 *     <li>{@link #setItalic()}</li>
	 *     <li>{@link #setBold()}</li>
	 *     <li>{@link #setUnderline()}</li>
	 * </ul>
	 */
	private void setStyle()
	{
		String style = "";
		style += isBold ? "-fx-font-weight: bold;" : "";
		style += isItalic ? "-fx-font-style: italic;" : "";
		style += isUnderlined ? "-fx-underline: true;" : "";
		this.setStyle(style);
	}
	
	/**
	 * Set the font size.
	 * <p/>
	 * @param size The font size.
	 */
	public void setSize(double size)
	{
		this.setFont(new Font(size));
	}
	
	public void setItalic()
	{
		isItalic = !isItalic;
		setStyle();
	}
	
	public void setBold()
	{
		isBold = !isBold;
		setStyle();
	}
	
	public void setUnderline()
	{
		isUnderlined = !isUnderlined;
		setStyle();
	}
}
