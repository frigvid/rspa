package com.frigvid.rspa.ui;

import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class WindowMain
		extends GUI
{
	boolean isDragging = false;
	CreateContext context = new CreateContext();
	
	public WindowMain(Stage stage)
	{
		super(stage);
	}
	
	@Override
	public void initUI()
	{
		windowMain();
		// TODO: Move canvas code into canvas() method.
		// TODO: Move sidebar code into sidebar() method.
		// TODO: Remove windowMain() method.
		// TODO: Call canvas() and sidebar() methods from initUI() method.
	}
	
	private void windowMain()
	{
		root = new BorderPane();
		canvas = new Pane();
		//ListView<> sidebar = new ListView<>();
		
		root.setCenter(canvas);
		//root.setRight(sidebar);
		
		// Setting background color to differentiate which is which.
		canvas.setStyle("-fx-background-color: #7e1e1e;");
		//sidebar.setStyle("-fx-background-color: #15e18c;");
		
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
			// TODO: Fix import of JavaFX shapes and RSPA shapes.
			
			if (event.getTarget() instanceof Shape)
			{
				ContextMenu shapeContext = context.createShapeContext((Shape) event.getTarget(), canvas, null);
				shapeContext.show(canvas, event.getScreenX(), event.getScreenY());
			}
			else
			{
				ContextMenu canvasContext = context.createCanvasContext();
				canvasContext.show(canvas, event.getScreenX(), event.getScreenY());
				System.out.println("HAHAHAHAHAHHAHAHHAHAHAHAHAHAHAHAHAHAHAHAHAHHAHAHAHA");
			}
			
			event.consume();
		});
		
		Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		stage.setTitle(DEFAULT_TITLE);
		stage.setMinHeight(MINIMUM_HEIGHT);
		stage.setMinWidth(MINIMUM_WIDTH);
		stage.getIcons().add(new Image(DEFAULT_ICON));
		stage.setScene(scene);
		stage.show();
	}
	
	private void canvas(BorderPane borderPane, Pane canvas)
	{
	}
	
	private void sidebar()
	{
	}
	
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
