package com.frigvid.rspa.ui;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class WindowMain
		extends GUI
{
	CreateContext context = new CreateContext();
	/* Used to check if user is trying to drag a shape,
	 * as opposed to interact with it. */
	boolean isDragging = false;
	private boolean altPressed = false;
	private boolean mousePressed = false;
	
	public WindowMain(Stage stage)
	{
		super(stage);
	}
	
	/**
	 * Initialize the UI. Add any code here that
	 * needs to be run when the Primary UI is created.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     WindowMain windowMain = new WindowMain(stage);
	 *     windowMain.initialize();
	 * </pre>
	 */
	@Override
	public void initialize()
	{
		// TODO: Implement holding CTRL for changing rotation on shape under cursor.
		// TODO: Implement holding ALT for moving shape under cursor.
		root = new BorderPane();
		
		root.setCenter(createCanvas());
		root.setRight(createSidebar());
		
		Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		scene.setOnKeyPressed(event -> {
			if (event.isAltDown()) {
				altPressed = true;
				if (!mousePressed) {
					scene.setCursor(Cursor.OPEN_HAND);
				}
			}
		});
		scene.setOnKeyReleased(event -> {
			if (event.getCode().toString().equals("ALT")) {
				altPressed = false;
				scene.setCursor(Cursor.DEFAULT);
			}
		});
		
		// Detect mouse pressed and released with ALT key
		scene.setOnMousePressed(event -> {
			if (altPressed && event.isPrimaryButtonDown()) {
				mousePressed = true;
				scene.setCursor(Cursor.CLOSED_HAND);
			}
		});
		scene.setOnMouseReleased(event -> {
			if (altPressed) {
				mousePressed = false;
				scene.setCursor(Cursor.OPEN_HAND);
			}
		});
		
		stage.setTitle(DEFAULT_TITLE);
		stage.setMinHeight(MINIMUM_HEIGHT);
		stage.setMinWidth(MINIMUM_WIDTH);
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
	private Pane createCanvas()
	{
		canvas = new Pane();
		
		canvas.setStyle("-fx-background-color: #7e1e1e;");
		
		/* Mouse event handler. */
		// TODO: Implement mouse event handler.
		//canvas.setOnMouseClicked(event -> {
		//	Node node = (Node) event.getTarget();
		//	if (node instanceof Shape) {
		//		mouseEventHandler(event, (Shape) node);
		//	}
		//	//mouseEventHandler(event);
		//});
		
		// Temporary code for testing.
		canvas.setOnContextMenuRequested(event ->
		{
			if (altPressed)
			{
				event.consume();
				return;
			}
			
			// TODO: Fix import of JavaFX shapes and RSPA shapes.
			if (event.getTarget() instanceof Shape)
			{
				ContextMenu shapeContext = context.createShapeContext((Shape) event.getTarget(), canvas, root.getScene());
				shapeContext.show(canvas, event.getScreenX(), event.getScreenY());
			}
			else
			{
				ContextMenu canvasContext = context.createCanvasContext(root.getScene());
				canvasContext.show(canvas, event.getScreenX(), event.getScreenY());
			}
			
			event.consume();
		});
		
		return canvas;
	}
	
	private Pane createSidebar()
	{
		Pane sidebar = new Pane();
		
		sidebar.setStyle("-fx-background-color: #15e18c;");
		sidebar.setPrefWidth(200);
		
		return sidebar;
	}
	
	// Temporarily commented out.
	//private ListView<> createSidebar()
	//{
	//	ListView<> sidebar = new ListView<>();
	//
	//	//sidebar.setStyle("-fx-background-color: #15e18c;");
	//
	//	return sidebar;
	//}
	
	/**
	 * Work in progress handler method to deal with the different
	 * type of interactions a user may have with a shape.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     WindowMain windowMain = new WindowMain(stage);
	 *     windowMain.mouseEventHandler(event, shape);
	 * </pre>
	 *
	 * @param event The mouse event.
	 * @param shape The shape.
	 */
	private void mouseEventHandler(MouseEvent event, Shape shape)
	{
		/*
		switch (e.getEventType().getName())
		{
			case "MOUSE_CLICKED":
				break;
			case "MOUSE_DRAGGED":
				break;
			case "MOUSE_ENTERED":
				break;
			case "MOUSE_ENTERED_TARGET":
				break;
			case "MOUSE_EXITED":
				break;
			case "MOUSE_EXITED_TARGET":
				break;
			case "MOUSE_MOVED":
				break;
			case "MOUSE_PRESSED":
				break;
			case "MOUSE_RELEASED":
				break;
			default:
				break;
		}
		*/
		
		if (isDragging)
		{
			isDragging = false;
			event.consume();
			return;
		}
		
		//if (event.getButton() == MouseButton.PRIMARY && !(event.getTarget() instanceof Figure))
		//{
		//	//Shape shape = null;
		//	String shapeName = null;
		//
		//	if (shape.getType() == Type.CIRCLE)
		//	{
		//
		//	}
		//}
	}
}
