package com.frigvid.rspa.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WindowText extends GUI {
	
	public WindowText(Stage stage) {
		super(stage);
	}
	
	@Override
	public void initUI() {
		AnchorPane textUI = createUI();
		canvas.getChildren().add(textUI);
	}
	
	public AnchorPane createUI() {
		AnchorPane root = new AnchorPane();
		root.setPrefSize(230, 163);
		
		TextArea textArea = new TextArea();
		textArea.setId("textArea");
		textArea.setLayoutX(15);
		textArea.setLayoutY(15);
		textArea.setPrefSize(200, 100);
		textArea.setPromptText("Enter text here");
		textArea.setWrapText(true);
		
		Button textItalic = new Button("I");
		textItalic.setId("textItalic");
		textItalic.setLayoutX(15);
		textItalic.setLayoutY(125);
		textItalic.setMinWidth(23);
		textItalic.setPrefHeight(25);
		textItalic.setFont(Font.font("System", FontPosture.ITALIC, 12));
		textItalic.setOnAction(event -> {
			// Implement the textItalic action here
		});
		
		Button textBold = new Button("B");
		textBold.setId("textBold");
		textBold.setLayoutX(48);
		textBold.setLayoutY(125);
		textBold.setFont(Font.font("System", FontWeight.BOLD, 12));
		textBold.setOnAction(event -> {
			// Implement the textBold action here
		});
		
		Button textUnderline = new Button("U");
		textUnderline.setId("textUnderline");
		textUnderline.setLayoutX(81);
		textUnderline.setLayoutY(125);
		textUnderline.setDisable(true);
		textUnderline.setUnderline(true);
		textUnderline.setOnAction(event -> {
			// Implement the textUnderline action here
		});
		
		Spinner spinner = new Spinner();
		spinner.setEditable(true);
		spinner.setLayoutX(116);
		spinner.setLayoutY(125);
		spinner.setPrefSize(50, 25);
		
		Button textAdd = new Button("Add");
		textAdd.setId("textAdd");
		textAdd.setLayoutX(177);
		textAdd.setLayoutY(125);
		textAdd.setOnAction(event -> {
			// Implement the textAdd action here
		});
		
		root.getChildren().addAll(textArea, textItalic, textBold, textUnderline, spinner, textAdd);
		
		return root;
	}
}
