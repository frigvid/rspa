package com.frigvid.rspa.ui;

import com.frigvid.rspa.history.InvokeCommand;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class App
		extends GUI
{
	private final InvokeCommand invokeCommand = new InvokeCommand();
	CreateContext context = new CreateContext();
	private boolean altPressed = false;
	private boolean mousePressed = false;
	private double xOffset = 0;
	private double yOffset = 0;
	
	public App(Stage stage)
	{
		super(stage);
	}
	
	/**
	 * Initialize the UI. Add any code here that
	 * needs to be run when the Primary UI is created.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     App app = new App(stage);
	 *     app.initialize();
	 * </pre>
	 */
	//@Override
	public void initialize()
	{
		// TODO: Implement holding CTRL for changing rotation on shape under cursor.
		root = new BorderPane();
		Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		root.setCenter(createCanvas(scene));
		//root.setRight(sidebar.getSidebar());
		
		/* Undo-redo key-combinations. */
		KeyCombination cZ = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
		KeyCombination cSZ = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
		
		scene.setOnKeyPressed(event ->
		{
			if (cZ.match(event))
			{
				invokeCommand.undo();
			}
			else if (cSZ.match(event))
			{
				invokeCommand.redo();
			}
		});
		
		/* Display application. */
		stage.setTitle(DEFAULT_TITLE);
		stage.setMinWidth(MINIMUM_WIDTH);
		stage.setMinHeight(MINIMUM_HEIGHT);
		stage.getIcons().add(new Image(DEFAULT_ICON));
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Create the canvas Pane.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     BorderPane root = new BorderPane();
	 *     root.setCenter(canvas());
	 * </pre>
	 *
	 * @return The canvas Pane.
	 */
	private Pane createCanvas(Scene scene)
	{
		canvas = new Pane();
		
		canvas.setStyle("-fx-background-color: #7e1e1e;");
		
		canvas.setOnContextMenuRequested(event ->
		{
			if (altPressed)
			{
				event.consume();
				return;
			}
			
			if (event.getTarget() instanceof Shape shape)
			{
				ContextMenu shapeContext = context.createShapeContext(shape, canvas, scene);
				shapeContext.show(canvas, event.getScreenX(), event.getScreenY());
			}
			else
			{
				ContextMenu canvasContext = context.createCanvasContext(root, canvas);
				canvasContext.show(canvas, event.getScreenX(), event.getScreenY());
			}
			
			event.consume();
		});
		
		return canvas;
	}
	
	// TODO: This is left as reference, for some future fixes for various x-y position translation offset issues.
	//private void handleShapePress(MouseEvent event)
	//{
	//	Shape shape = (Shape) event.getSource();
	//
	//	if (shape instanceof Circle circle)
	//	{
	//		xOffset = event.getSceneX() - circle.getCenterX();
	//		yOffset = event.getSceneY() - circle.getCenterY();
	//		currentMoveCommand = new MoveCommand(circle, circle.getCenterX(), circle.getCenterY());
	//	}
	//	else if (shape instanceof Rectangle rectangle)
	//	{
	//		xOffset = event.getSceneX() - rectangle.getX();
	//		yOffset = event.getSceneY() - rectangle.getY();
	//		currentMoveCommand = new MoveCommand(rectangle, rectangle.getX(), rectangle.getY());
	//	}
	//
	//	event.consume();
	//}
}
