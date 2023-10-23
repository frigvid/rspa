package com.frigvid.rspa.figure.shape;

import com.frigvid.rspa.figure.FigureType;
import com.frigvid.rspa.figure.IFigure;
import com.frigvid.rspa.figure.ShapeDragHandler;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

// TODO: Implement abstract wrapper class for Shapes and use that instead of extending it directly.
// TODO: Investigate issue of style not being applied properly.
public class Text
		extends javafx.scene.text.Text
		implements IFigure
{
	private final static FigureType FIGURE_TYPE = FigureType.TEXT;
	private boolean isItalic = false;
	private boolean isBold = false;
	private boolean isUnderline = false;
	
	public Text()
	{
		/* Enable dragging of any Line shape. */
		ShapeDragHandler dragHandler = new ShapeDragHandler(this);
		dragHandler.enableDrag();
	}
	
	public Text(double x, double y, String text)
	{
		super(x, y, text);
		
		/* Enable dragging of any Text shape. */
		ShapeDragHandler dragHandler = new ShapeDragHandler(this);
		dragHandler.enableDrag();
	}
	
	public Text(double x, double y, String text, double fontSize)
	{
		super(x, y, text);
		this.setFont(new Font(fontSize));
		
		/* Enable dragging of any Text shape. */
		ShapeDragHandler dragHandler = new ShapeDragHandler(this);
		dragHandler.enableDrag();
	}
	
	public FigureType getType()
	{
		return FIGURE_TYPE;
	}
	
	public Text getShape()
	{
		return this;
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
	public void setStyle()
	{
		String style = "";
		style += isBold ? "-fx-font-weight: bold;" : "";
		style += isItalic ? "-fx-font-style: italic;" : "";
		style += isUnderline ? "-fx-underline: true;" : "";
		this.setStyle(style);
	}
	
	/**
	 * Set the font size.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Text text = new Text();
	 *     text.setSize(12);
	 * </pre>
	 *
	 * @param size The font size.
	 * @see #getSize
	 */
	public void setSize(double size)
	{
		this.setFont(new Font(size));
	}
	
	public void setPosition(double x, double y)
	{
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * Set the text to be italic.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Text text = new Text();
	 *     text.setItalic();
	 * </pre>
	 *
	 * @see #isItalic
	 */
	public void setItalic()
	{
		isItalic = !isItalic;
		setStyle();
	}
	
	/**
	 * Set the text to be bold.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Text text = new Text();
	 *     text.setBold();
	 * </pre>
	 *
	 * @see #isBold
	 */
	public void setBold()
	{
		isBold = !isBold;
		setStyle();
	}
	
	/**
	 * Set the text to be underlined.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Text text = new Text();
	 *     text.setUnderline();
	 * </pre>
	 *
	 * @see #isUnderlined
	 */
	public void setUnderline()
	{
		isUnderline = !isUnderline;
		setStyle();
	}
	
	/**
	 * Get if the text is italic.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Text text = new Text();
	 *     boolean isItalic = text.isItalic();
	 * </pre>
	 *
	 * @return Whether the text is italic.
	 * @see #setItalic
	 */
	public boolean isItalic()
	{
		return isItalic;
	}
	
	/**
	 * Get if the text is bold.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Text text = new Text();
	 *     boolean isBold = text.isBold();
	 * </pre>
	 *
	 * @return Whether the text is bold.
	 * @see #setBold
	 */
	public boolean isBold()
	{
		return isBold;
	}
	
	/**
	 * Get if the text is underlined.
	 * <p/>
	 * Note: If the name having a "d" at the end annoys you,
	 * 		 go complain to JavaFX for setting isUnderline
	 * 		 to final when the others aren't.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Text text = new Text();
	 *     boolean isUnderlined = text.isUnderlined();
	 * </pre>
	 *
	 * @return Whether the text is underlined.
	 * @see #setUnderline
	 */
	public boolean isUnderlined()
	{
		return isUnderline;
	}
	
	/**
	 * Get the font size.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Text text = new Text();
	 *     double fontSize = text.getSize();
	 * </pre>
	 *
	 * @return The font size.
	 * @see #setSize
	 */
	public double getSize()
	{
		return this.getFont().getSize();
	}
	
	// TEMPORARY?
	public void getTextWithStyle(TextArea textArea)
	{
		String text = textArea.getText();
		//Text drawableText = new Text(text);
		
		if (isBold)
		{
			text = "<b>" + text + "</b>";
			//drawableText.setFontWeight(FontWeight.BOLD);
		}
		
		if (isItalic)
		{
			text = "<i>" + text + "</i>";
			//drawableText.setFontStyle(FontPosture.ITALIC);
		}
	}
}
