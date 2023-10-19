package com.frigvid.rspa.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
//import com.frigvid.rspa.test.Text;
//import com.frigvid.rspa.ui.AppController;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.FontWeight;
import javafx.scene.layout.Pane;

/* TODO: Needs to close/cancel when focus is lost. */

public class TextController
{
	@FXML
	public Button textItalic;
	
	@FXML
	public Button textBold;
	
	@FXML
	public Button textUnderline;
	
	@FXML
	public Button textAdd;
	
	@FXML
	public TextArea textArea;
	
	private boolean isItalic = false;
	private boolean isBold = false;
	private boolean isUnderlined = false;
	
	// Reference for AppController
	private AppController appController;
	public void setAppController(AppController appController)
	{
		this.appController = appController;
	}
	
	// Reference for canvas
	private Pane canvas;
	public void setCanvas(Pane canvas)
	{
		this.canvas = canvas;
	}
	
	private void setStyle()
	{
		String style = "";
		style += isBold ? "-fx-font-weight: bold;" : "";
		style += isItalic ? "-fx-font-style: italic;" : "";
		style += isUnderlined ? "-fx-underline: true;" : "";
		textArea.setStyle(style);
	}
	
	public void textItalic(ActionEvent e)
	{
		System.out.println(temp(e));
		isItalic = !isItalic;
		setStyle();
	}
	
	public void textBold(ActionEvent e)
	{
		System.out.println(temp(e));
		isBold = !isBold;
		setStyle();
	}
	
	public void textUnderline(ActionEvent e)
	{
		System.out.println(temp(e));
		isUnderlined = !isUnderlined;
		setStyle();
	}
	
	public void textAdd()
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
		
		//if(appController != null) {
		//	DrawableText drawableText = new DrawableText(50, 50, text);
		//	appController.addText(drawableText);
		//}
		
		//System.out.println(text);
		//((Stage)textArea.getScene().getWindow()).close();
		
		//canvas.setOnMouseClicked(event ->
		//{
		//	drawableText.setX(event.getX());
		//	drawableText.setY(event.getY());
		//	canvas.getChildren().add(drawableText);
		//	canvas.setOnMouseClicked(null); // Remove the listener to prevent multiple placements
		//});
	}
	
	private String temp(ActionEvent event)
	{
		Button btn = (Button) event.getSource();
		String id = btn.getId();
		return "Button String: " + btn + "\nButton Id: " + id + "\nTextArea font: " + textArea.getFont();
	}
}
