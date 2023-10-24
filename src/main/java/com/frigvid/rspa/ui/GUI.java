package com.frigvid.rspa.ui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public abstract class GUI
{
	protected static final String DEFAULT_TITLE = "RSPA";
	protected static final String DEFAULT_ICON = String.valueOf(GUI.class.getResource("/com/frigvid/rspa/icon/logo.png"));
	protected static final double DEFAULT_WIDTH = 1000.0;
	protected static final double DEFAULT_HEIGHT = 580.0;
	protected static final double MINIMUM_WIDTH = 500.0;
	protected static final double MINIMUM_HEIGHT = 500.0;
	
	protected Stage stage;
	protected BorderPane root;
	protected Pane canvas;
	protected StackPane sidebar;
	
	public GUI() {}
	
	public GUI(Stage stage)
	{
		this.stage = stage;
	}
	
	/**
	 * Get the Scene. Useful if you for need to access the Scene,
	 * but for some reason don't have access to the Scene itself,
	 * but do have access to something like the root BorderPane.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     App app = new App(stage);
	 *     BorderPane root = app.getRoot();
	 *     Scene scene = app.getScene();
	 * </pre>
	 *
	 * @return The Scene.
	 * @see CreateContext CreateContext for an example in the createShapeContext and createCanvasContext methods.
	 */
	public Scene getScene()
	{
		return stage.getScene();
	}
	
	/**
	 * Get the root BorderPane. Useful if you want to add a Node to the root,
	 * but you don't have access to the root BorderPane for some reason.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     App app = new App(stage);
	 *     BorderPane root = app.getRoot();
	 * </pre>
	 *
	 * @return The root BorderPane.
	 */
	public BorderPane getRoot()
	{
		return root;
	}
	
	/**
	 * Get the canvas Pane. Useful if you want to add a Shape to the canvas,
	 * but you don't have access to the canvas Pane through inheritance.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     App app = new App(stage);
	 *     Pane canvas = app.getCanvas();
	 * </pre>
	 *
	 * @return The canvas Pane.
	 */
	public Pane getCanvas()
	{
		return canvas;
	}
}
