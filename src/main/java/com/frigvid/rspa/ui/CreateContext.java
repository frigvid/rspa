package com.frigvid.rspa.ui;

import com.frigvid.rspa.figure.FigureType;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.history.InvokeCommand;
import com.frigvid.rspa.history.command.CreateShapeCommand;
import com.frigvid.rspa.history.command.DeleteShapeCommand;
import com.frigvid.rspa.history.command.MoveLayerCommand;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class CreateContext
{
	private final InvokeCommand invokeCommand = new InvokeCommand(); // For undo-redo.
	private ContextMenu openContext;
	
	/**
	 * Create context menu for a shape.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     CreateContext context = new CreateContext();
	 *     ContextMenu shapeContext = context.createShapeContext(shape, canvas, sidebar);
	 * </pre>
	 *
	 * @param shape The shape to create context menu for.
	 * @param canvas The canvas to draw on.
	 * @param scene The Scene used to set up listeners for closing the context menu.
	 * @return The context menu.
	 */
	public ContextMenu createShapeContext(Shape shape, Pane canvas, Scene scene)
	{
		ContextMenu shapeContext = new ContextMenu();
		
		/* Define shared menu items. */
		MenuItem selectShapeImmediate = new MenuItem("Select this drawing");
		MenuItem moveToFront = new MenuItem("Bring to Front");
		MenuItem moveToBack = new MenuItem("Send to Back");
		MenuItem moveForwardByOne = new MenuItem("Bring Forward by One");
		MenuItem moveBackwardByOne = new MenuItem("Send Backward by One");
		MenuItem delete = new MenuItem("Delete");
		
		selectShapeImmediate.setOnAction(event -> createShapeSidebar(shape, (BorderPane) canvas.getParent()));
		
		moveToFront.setOnAction(event ->
				{
					MoveLayerCommand layerCmd = new MoveLayerCommand(shape, MoveLayerCommand.MoveAction.TO_FRONT, canvas.getChildren());
					layerCmd.execute();
					invokeCommand.execute(layerCmd);
				});
		
		moveToBack.setOnAction(event ->
				{
					MoveLayerCommand layerCmd = new MoveLayerCommand(shape, MoveLayerCommand.MoveAction.TO_BACK, canvas.getChildren());
					layerCmd.execute();
					invokeCommand.execute(layerCmd);
				});
		
		moveForwardByOne.setOnAction(event ->
				{
					MoveLayerCommand layerCmd = new MoveLayerCommand(shape, MoveLayerCommand.MoveAction.FORWARD_BY_ONE, canvas.getChildren());
					layerCmd.execute();
					invokeCommand.execute(layerCmd);
				});
		
		moveBackwardByOne.setOnAction(event ->
				{
					MoveLayerCommand layerCmd = new MoveLayerCommand(shape, MoveLayerCommand.MoveAction.BACKWARD_BY_ONE, canvas.getChildren());
					layerCmd.execute();
					invokeCommand.execute(layerCmd);
				});
		
		delete.setOnAction(event ->
				{
					DeleteShapeCommand deleteCmd = new DeleteShapeCommand(canvas, shape);
					deleteCmd.execute();
					invokeCommand.execute(deleteCmd);
				});
		
		/* Closes the shape context menu if
		 * another context menu of the same type is opened.
		 *
		 * Together with the similar event in createCanvasContext,
		 * it essentially allows for only one context menu to be
		 * open at any time. */
		shapeContext.setOnShowing(event ->
				{
					ifContextExternalClick(scene);
					closeContextIfOpen();
					openContext = shapeContext;
				});
		
		/* Finish adding remaining MenuItems to the ContextMenu. */
		shapeContext.getItems().addAll(
				selectShapeImmediate,
				new SeparatorMenuItem(),
				moveToFront,
				moveToBack,
				moveForwardByOne,
				moveBackwardByOne,
				new SeparatorMenuItem(),
				delete
		);
		
		return shapeContext;
	}
	
	/**
	 * Create context menu for the canvas.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     CreateContext context = new CreateContext();
	 *     ContextMenu canvasContext = context.createCanvasContext();
	 * </pre>
	 *
	 * @param root The root pane of the scene. The Scene is gotten from this.
	 * @param canvas The canvas to draw on.
	 * @return The context menu.
	 */
	public ContextMenu createCanvasContext(BorderPane root, Pane canvas)
	{
		Scene scene = root.getScene();
		ContextMenu canvasContext = new ContextMenu();
		
		/* Define shared menu items. */
		MenuItem shapeSelect = new MenuItem("Select drawing...");
		MenuItem shapeCircle = new MenuItem("Draw Circle");
		MenuItem shapeLine = new MenuItem("Draw Line");
		MenuItem shapeRectangle = new MenuItem("Draw Rectangle");
		MenuItem shapeText = new MenuItem("Draw Text");
		MenuItem shapeDeselect = new MenuItem("Deselect shape");
		MenuItem resetCanvas = new MenuItem("Reset canvas");
		
		shapeSelect.setOnAction(event ->
		{
			canvas.setOnMouseClicked(eventSelect ->
			{
				if (eventSelect.getButton() == MouseButton.PRIMARY && !eventSelect.isAltDown())
				{
					Node node = eventSelect.getPickResult().getIntersectedNode();
					createShapeSidebar(node, root);
				}
			});
		});
		
		shapeCircle.setOnAction(event ->
		{
			Circle circle = new Circle();
			CreateSidebar sidebar = new CreateSidebar(FigureType.CIRCLE);
			sidebar.setNode(circle);
			root.setRight(sidebar.getSidebar());
			
			canvas.setOnMouseClicked(eventCircle ->
				{
					if (eventCircle.getButton() == MouseButton.PRIMARY && !eventCircle.isAltDown())
					{
						circle.setPosition(eventCircle.getX(), eventCircle.getY());
						circle.setRadius(sidebar.getCircleRadius());
						
						// Add shape to canvas and add action to history.
						CreateShapeCommand command = new CreateShapeCommand(canvas, circle);
						invokeCommand.execute(command);
						
						// Remove listeners and close sidebar.
						canvas.setOnMouseClicked(null);
						root.setRight(null);
					}
				});
		});
		
		shapeLine.setOnAction(event ->
		{
			Line line = new Line();
			CreateSidebar sidebar = new CreateSidebar(FigureType.LINE);
			sidebar.setNode(line);
			sidebar.disableLineButtons();
			root.setRight(sidebar.getSidebar());
			
			final Double[] startX = {null};
			final Double[] startY = {null};
			
			canvas.setOnMouseClicked(eventLine ->
			{
				if (eventLine.getButton() == MouseButton.PRIMARY && !eventLine.isAltDown())
				{
					if (startX[0] == null)
					{
						startX[0] = eventLine.getX();
						startY[0] = eventLine.getY();
					} else
					{
						line.setPosition(startX[0], startY[0], eventLine.getX(), eventLine.getY());
						line.setThickness(sidebar.getLineThickness());
						
						// Add shape to canvas and add action to history.
						CreateShapeCommand command = new CreateShapeCommand(canvas, line);
						invokeCommand.execute(command);
						
						// Remove listeners and close sidebar.
						canvas.setOnMouseClicked(null);
						root.setRight(null);
						
						/* Reset start position values. */
						startX[0] = null;
						startY[0] = null;
					}
				}
			});
		});
		
		shapeRectangle.setOnAction(event ->
		{
			Rectangle rectangle = new Rectangle();
			CreateSidebar sidebar = new CreateSidebar(FigureType.RECTANGLE);
			sidebar.setNode(rectangle);
			root.setRight(sidebar.getSidebar());
			
			canvas.setOnMouseClicked(eventRectangle ->
			{
				if (eventRectangle.getButton() == MouseButton.PRIMARY && !eventRectangle.isAltDown())
				{
					rectangle.setPosition(eventRectangle.getX(), eventRectangle.getY());
					rectangle.setSize(sidebar.getRectangleWidth(), sidebar.getRectangleHeight());
					
					// Add shape to canvas and add action to history.
					CreateShapeCommand command = new CreateShapeCommand(canvas, rectangle);
					invokeCommand.execute(command);
					
					// Remove listeners and close sidebar.
					canvas.setOnMouseClicked(null);
					root.setRight(null);
				}
			});
		});
		
		shapeText.setOnAction(event ->
		{
			Text text = new Text();
			CreateSidebar sidebar = new CreateSidebar(FigureType.TEXT);
			sidebar.setNode(text);
			sidebar.disableTextButtons();
			root.setRight(sidebar.getSidebar());
			
			canvas.setOnMouseClicked(eventText ->
			{
				if (eventText.getButton() == MouseButton.PRIMARY && !eventText.isAltDown())
				{
					text.setPosition(eventText.getX(), eventText.getY());
					text.setText(sidebar.getText());
					text.setSize(sidebar.getTextFontSize());
					
					// Add shape to canvas and add action to history.
					CreateShapeCommand command = new CreateShapeCommand(canvas, text);
					invokeCommand.execute(command);
					
					// Remove listeners and close sidebar.
					canvas.setOnMouseClicked(null);
					root.setRight(null);
				}
			});
		});
		
		/* Leave this empty to "deselect" shapes. */
		shapeDeselect.setOnAction(event ->
				{
					root.setRight(null);
					canvas.setOnMouseClicked(eventDeselect -> {});
				});
		
		/* Reset canvas to default state. */
		resetCanvas.setOnAction(event ->
				{
					root.setRight(null);
					canvas.getChildren().clear();
				});
		
		canvasContext.getItems().addAll(
				shapeSelect,
				new SeparatorMenuItem(),
				shapeCircle,
				shapeLine,
				shapeRectangle,
				shapeText,
				shapeDeselect,
				new SeparatorMenuItem(),
				resetCanvas
		);
		
		/* Closes the canvas context menu if
		 * another context menu of the same type is opened.
		 *
		 * Together with the similar event in createShapeContext,
		 * it essentially allows for only one context menu to be
		 * open at any time. */
		canvasContext.setOnShowing(event ->
				{
					ifContextExternalClick(scene);
					closeContextIfOpen();
					openContext = canvasContext;
				});
		
		return canvasContext;
	}
	
	/**
	 * This adds the various sidebar components to the sidebar.
	 * It's kind of ugly, but it works well enough.
	 *
	 * @param node The node to create the sidebar for.
	 * @param root The root pane of the scene. The sidebar position is set with this.
	 */
	private void createShapeSidebar(Node node, BorderPane root)
	{
		FigureType type;
		CreateSidebar sidebar = null;
		
		switch (node) {
			case Circle circle:
				type = circle.getType();
				sidebar = new CreateSidebar();
				
				sidebar.setStrokeColor((Color) circle.getStroke());
				sidebar.setFillColor((Color) circle.getFill());
				sidebar.setNodeOpacity(circle.getOpacity());
				sidebar.setNodeRotation(circle.getRotate());
				sidebar.setCircleRadius(circle.getRadius());
				sidebar.setNode(circle);
				
				break;
			case Line line:
				type = line.getType();
				sidebar = new CreateSidebar(type);
				
				sidebar.setStrokeColor((Color) line.getStroke());
				sidebar.setNodeOpacity(line.getOpacity());
				sidebar.setNodeRotation(line.getRotate());
				sidebar.setLineLength(line.getLength());
				sidebar.setLineThickness(line.getStrokeWidth());
				sidebar.setNode(line);
				
				break;
			case Rectangle rectangle:
				type = rectangle.getType();
				sidebar = new CreateSidebar(type);
				
				sidebar.setStrokeColor((Color) rectangle.getStroke());
				sidebar.setFillColor((Color) rectangle.getFill());
				sidebar.setNodeOpacity(rectangle.getOpacity());
				sidebar.setNodeRotation(rectangle.getRotate());
				sidebar.setRectangleWidth(rectangle.getWidth());
				sidebar.setRectangleHeight(rectangle.getHeight());
				sidebar.setNode(rectangle);
				
				break;
			case Text text:
				type = text.getType();
				sidebar = new CreateSidebar(type);
				
				sidebar.setStrokeColor((Color) text.getStroke());
				sidebar.setFillColor((Color) text.getFill());
				sidebar.setNodeOpacity(text.getOpacity());
				sidebar.setNodeRotation(text.getRotate());
				sidebar.setTextFontSize(text.getSize());
				sidebar.setTextString(text.getText());
				sidebar.setNode(text);
				
				break;
			default:
				type = FigureType.NONE;
				break; // Yes, I know it's unnecessary. Sue me.
		}
		
		if (sidebar == null)
		{
			root.setRight(null);
			return;
		}
		
		//assert sidebar != null;
		sidebar.setFigureType(type);
		sidebar.initialize();
		root.setRight(sidebar.getSidebar());
	}
	
	/**
	 * Shared method closes the context menu saved
	 * to the openContext variable.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     ContextMenu canvasContext = new ContextMenu();
	 *     canvasContext.setOnShowing(event ->
	 *     {
	 *          closeContext();
	 *          openContext = canvasContext;
	 *     });
	 * </pre>
	 *
	 * @see #createShapeContext
	 * @see #createCanvasContext
	 */
	private void closeContextIfOpen()
	{
		if (openContext != null && openContext.isShowing())
		{
			openContext.hide();
		}
	}
	
	/**
	 * Close the current context menu if user clicks outside of it.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     CreateContext context = new CreateContext();
	 *     context.setupGlobalContextMenuHandler(scene);
	 * </pre>
	 *
	 * @param scene The scene to close the context menu in.
	 */
	public void ifContextExternalClick(Scene scene)
	{
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> closeContextIfOpen());
	}
}
