package com.frigvid.rspa.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.TabPane;

public class Controller
{
	/* Yes, best practice of being consistent with syntax styles (like at annotation). But,
	 * this saves space, and the compiler doesn't care either way, so it's fine. */
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
		
		/* Debug:
		import javafx.application.Platform;
		System.out.println("dCanvas: " + dCanvas);
		System.out.println("canvasParent: " + canvasParent);
		canvasParent.widthProperty().addListener((obs, oldVal, newVal) -> System.out.println("canvasParent width: " + newVal));
		canvasParent.heightProperty().addListener((obs, oldVal, newVal) -> System.out.println("canvasParent height: " + newVal));
		properties.heightProperty().addListener((obs, oldVal, newVal) -> System.out.println("properties height: " + newVal));
		tabPane.heightProperty().addListener((obs, oldVal, newVal) -> System.out.println("tabPane height: " + newVal));
		*/
	}
	
	@FXML
	protected void tempTool(ActionEvent event)  {
		Button btn = (Button) event.getSource();
		String id = btn.getId();
		
		System.out.println("Button String: " + btn);
		System.out.println("Button Id: " + id);
		
		//btn.setText("hello");
	}
}
