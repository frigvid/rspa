package com.frigvid.rspa.ui;

import com.frigvid.rspa.App;
import com.frigvid.rspa.test.Text;
import com.frigvid.rspa.ui.fragment.TextController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import java.io.IOException;

public class AppController
{
	@FXML private Pane canvas;
	@FXML private TabPane tabPane;
	
	@FXML
	public void initialize()
	{
		// Dynamic canvas size.
		Pane parentCanvas = (Pane) canvas.getParent();
		canvas.prefWidthProperty().bind(parentCanvas.widthProperty());
		canvas.prefHeightProperty().bind(parentCanvas.heightProperty());
		
		// Dynamic tabPane size.
		Pane parentTabPane = (Pane) tabPane.getParent();
		parentTabPane.heightProperty().addListener((obs, oldVal, newVal) -> tabPane.setPrefHeight(newVal.doubleValue()));
		parentTabPane.widthProperty().addListener((obs, oldVal, newVal) -> tabPane.setPrefWidth(newVal.doubleValue()));
	}
	
	/**
	 * Temporary method to verify functionality of buttons.
	 */
	@FXML
	protected void tempTool(ActionEvent event)
	{
		Button btn = (Button) event.getSource();
		String id = btn.getId();
		
		System.out.println("Button String: " + btn);
		System.out.println("Button Id: " + id);
	}
	
	public void addText(Text drawableText)
	{
		canvas.getChildren().add(drawableText.getText());
	}
	
	/**
	 * Creates a new window to display text options.
	 */
	@FXML
	protected void objectText() throws IOException
	{
		FXMLLoader fxml = new FXMLLoader(App.class.getResource("ui/text.fxml"));
		Scene scene = new Scene(fxml.load());
		TextController textController = fxml.getController();
		textController.setAppController(this);
		Stage stage = new Stage();
		stage.setTitle("Input text");
		stage.setScene(scene);
		stage.show();
	}
}

