package com.frigvid.rspa.ui;

import javafx.scene.Scene;
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
	
	public abstract void initialize();
	
	/**
	 * Get the Scene. Useful if you for need to access the Scene,
	 * but for some reason don't have access to the Scene itself,
	 * but do have access to something like the root BorderPane.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     WindowMain windowMain = new WindowMain(stage);
	 *     BorderPane root = windowMain.getRoot();
	 *     Scene scene = windowMain.getScene();
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
	 *     WindowMain windowMain = new WindowMain(stage);
	 *     BorderPane root = windowMain.getRoot();
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
	 *     WindowMain windowMain = new WindowMain(stage);
	 *     Pane canvas = windowMain.getCanvas();
	 * </pre>
	 *
	 * @return The canvas Pane.
	 */
	public Pane getCanvas()
	{
		return canvas;
	}
	
	/**
	 * Move figure to front of canvas.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     moveFigureToFront(shape, canvas);
	 * </pre>
	 *
	 * @param shape The Shape to move.
	 * @param root The Pane to move the shape in.
	 */
	protected void moveFigureToFront(Shape shape, Pane root)
	{
		//shape.toFront();
		System.out.println(root.getChildren().indexOf(shape));
		//root.getChildren().remove(shape);
		//root.getChildren().add(shape);
	}
	
	/**
	 * Move figure to back of canvas.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     moveFigureToBack(shape, canvas);
	 * </pre>
	 *
	 * @param shape The Shape to move.
	 * @param root The Pane to move the shape in.
	 */
	protected void moveFigureToBack(Shape shape, Pane root)
	{
		root.getChildren().remove(shape);
		root.getChildren().add(0, shape);
	}
	
	/**
	 * Move figure forward by one.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     moveFigureForwardByOne(shape, canvas);
	 * </pre>
	 *
	 * @param shape The Shape to move.
	 * @param root The Pane to move the shape in.
	 */
	protected void moveFigureForwardByOne(Shape shape, Pane root)
	{
		int idx = root.getChildren().indexOf(shape);
		if (idx < root.getChildren().size() - 1) {
			root.getChildren().remove(shape);
			root.getChildren().add(idx + 1, shape);
		}
	}
	
	/**
	 * Move figure backward by one.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     moveFigureBackwardByOne(shape, canvas);
	 * </pre>
	 *
	 * @param shape The Shape to move.
	 * @param root The Pane to move the shape in.
	 */
	protected void moveFigureBackwardByOne(Shape shape, Pane root)
	{
		int idx = root.getChildren().indexOf(shape);
		if (idx > 0) {
			root.getChildren().remove(shape);
			root.getChildren().add(idx - 1, shape);
		}
	}
}
