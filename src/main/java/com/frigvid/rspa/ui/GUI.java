package com.frigvid.rspa.ui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Shape;

public abstract class GUI
{
	protected static final String DEFAULT_TITLE = "RSPA";
	protected static final String DEFAULT_ICON = String.valueOf(GUI.class.getResource("/com/frigvid/rspa/icon/logo.png"));
	protected static final double DEFAULT_WIDTH = 1000.0;
	protected static final double DEFAULT_HEIGHT = 500.0;
	protected static final double MINIMUM_WIDTH = 500.0;
	protected static final double MINIMUM_HEIGHT = 300.0;
	
	protected Stage stage;
	protected BorderPane root;
	protected Pane canvas;
	
	public GUI(Stage stage)
	{
		this.stage = stage;
	}
	
	public abstract void initUI();
	
	public Stage getStage()
	{
		return stage;
	}
	
	public BorderPane getRoot()
	{
		return root;
	}
	
	public Pane getCanvas()
	{
		return canvas;
	}
	
	protected void moveFigureToFront(Shape shape, Pane root)
	{
		//shape.toFront();
		System.out.println(root.getChildren().indexOf(shape));
		//root.getChildren().remove(shape);
		//root.getChildren().add(shape);
	}
	
	protected void moveFigureToBack(Shape shape, Pane root)
	{
		root.getChildren().remove(shape);
		root.getChildren().add(0, shape);
	}
	
	protected void moveFigureForwardByOne(Shape shape, Pane root)
	{
		int idx = root.getChildren().indexOf(shape);
		if (idx < root.getChildren().size() - 1) {
			root.getChildren().remove(shape);
			root.getChildren().add(idx + 1, shape);
		}
	}
	
	protected void moveFigureBackwardByOne(Shape shape, Pane root)
	{
		int idx = root.getChildren().indexOf(shape);
		if (idx > 0) {
			root.getChildren().remove(shape);
			root.getChildren().add(idx - 1, shape);
		}
	}
}
