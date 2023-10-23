package com.frigvid.rspa.ui;

import com.frigvid.rspa.figure.FigureType;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.ShapeHandler;
import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.figure.shape.Circle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.Objects;

public class CreateContext
{
	InputValue newValue = new InputValue();
	ShapeHandler shapeHandler = new ShapeHandler();
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
		//MenuItem strokeColor = new MenuItem("Change Stroke Color");
		//MenuItem fillColor = new MenuItem("Change Fill Color");
		//MenuItem changeOpacity = new MenuItem("Change Opacity");
		MenuItem moveToFront = new MenuItem("Bring to Front");
		MenuItem moveToBack = new MenuItem("Send to Back");
		MenuItem moveForwardByOne = new MenuItem("Bring Forward by One");
		MenuItem moveBackwardByOne = new MenuItem("Send Backward by One");
		MenuItem delete = new MenuItem("Delete");
		
		//strokeColor.setOnAction(event -> shapeHandler.setStrokeColor(shape, newValue.colorPicker((Color) shape.getStroke())));
		//fillColor.setOnAction(event -> shapeHandler.setFillColor(shape, newValue.colorPicker((Color) shape.getFill())));
		moveToFront.setOnAction(event -> shape.toFront());
		moveToBack.setOnAction(event -> shape.toBack());
		moveForwardByOne.setOnAction(event ->
		{
			int index = canvas.getChildren().indexOf(shape);
			if (index < canvas.getChildren().size() - 1)
			{
				canvas.getChildren().remove(shape);
				canvas.getChildren().add(index + 1, shape);
			}
		});
		
		moveBackwardByOne.setOnAction(event ->
		{
			int index = canvas.getChildren().indexOf(shape);
			if (index > 0)
			{
				canvas.getChildren().remove(shape);
				canvas.getChildren().add(index - 1, shape);
			}
		});
		
		delete.setOnAction(event -> shapeHandler.deleteShape(shape, canvas));
		
		// NOTE: Alternatively use if (shape instanceof Line) {}.
		//switch ((shape.getClass().getSimpleName()).toUpperCase()) {
		//	case "CIRCLE":
		//		shapeContext.getItems().add(instanceCircle(shape));
		//		break;
		//	case "LINE":
		//		shapeContext.getItems().add(instanceLine(shape));
		//		break;
		//	case "RECTANGLE":
		//		shapeContext.getItems().addAll(
		//				instanceRectangleHeight(shape),
		//				instanceRectangleWidth(shape)
		//		);
		//		break;
		//	case "TEXT":
		//		shapeContext.getItems().add(instanceText(shape));
		//		break;
		//	default:
		//		break;
		//}
		
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
		
		/* Start adding remaining MenuItems to the ContextMenu. */
		//shapeContext.getItems().addAll(
		//		new SeparatorMenuItem(),
		//		strokeColor
		//);
		
		/* Only add fill color menu item if shape is not a line.
		 *
		 * I'd prefer to not split up "addAll()" method like this,
		 * however, it's the easiest way to add or remove this
		 * MenuItem and add it to the correct place in the ContextMenu. */
		//if (!(shape instanceof Line))
		//{
		//	shapeContext.getItems().add(fillColor);
		//}
		
		/* Finish adding remaining MenuItems to the ContextMenu. */
		shapeContext.getItems().addAll(
				//changeOpacity,
				//new SeparatorMenuItem(),
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
		
		/* Define shapes out of setOnAction scope. */
		
		// TODO: Integrate with sidebar selection.
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
						
						// Add text to canvas and close sidebar.
						canvas.getChildren().add(circle);
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
						
						// Add text to canvas and close sidebar.
						canvas.getChildren().add(line);
						canvas.setOnMouseClicked(null);
						root.setRight(null);
						
						/* Reset start position values. */
						startX[0] = null;
						startY[0] = null;
					}
				}
			});
		});
		
		// TODO: Enable user to preset width and height through the sidebar.
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
					
					// Add text to canvas and close sidebar.
					canvas.getChildren().add(rectangle);
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
					
					// Add text to canvas and close sidebar.
					canvas.getChildren().add(text);
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
	 * Instance menu item for changing circle radius.
	 * <p/>
	 * TODO: Fix position transform.
	 * TODO: Implement missing features.
	 *
	 * @param shape The Circle to change.
	 * @return The menu item.
	 */
	private MenuItem instanceCircle(Shape shape)
	{
		MenuItem changeRadius = new MenuItem("Change Radius");
		
		changeRadius.setOnAction(e ->
		{
			Circle circle = (Circle) shape;
			double initialRadius = circle.getRadius();
			double newRadius = newValue.numberInput("Change Radius", "Enter new radius:", initialRadius);
			if (newRadius != initialRadius)
			{
				//ChangeShapeSizeCommand cmd = new ChangeShapeSizeCommand(circle, 0, 0, initialRadius);
				//cmd.setNewSize(0, 0, newRadius);
				circle.setRadius(newRadius);
				//undoStack.push(cmd);
				//redoStack.clear();
			}
		});
		
		return changeRadius;
	}
	
	/**
	 * Instance menu item for changing line length.
	 *
	 * @param shape The Line to change.
	 * @return The menu item.
	 */
	private MenuItem instanceLine(Shape shape)
	{
		MenuItem changeLength = new MenuItem("Change Length");
		
		changeLength.setOnAction(e ->
		{
			Line line = (Line) shape;
			double initialLength = line.getLength();
			double newLength = newValue.numberInput("Change Length", "Enter new length:", initialLength);
			if (newLength != initialLength)
			{
				//ChangeShapeSizeCommand cmd = new ChangeShapeSizeCommand(circle, 0, 0, initialRadius);
				//cmd.setNewSize(0, 0, newRadius);
				line.setLength(newLength);
				//undoStack.push(cmd);
				//redoStack.clear();
			}
		});
		
		return changeLength;
	}
	
	/**
	 * Instance menu item for changing rectangle height.
	 * <p/>
	 * TODO: Fix position transform.
	 * TODO: Implement missing features.
	 *
	 * @see #instanceRectangleWidth(Shape)
	 * @param shape The Rectangle height to change.
	 * @return The menu item.
	 */
	private MenuItem instanceRectangleHeight(Shape shape)
	{
		MenuItem changeHeight = new MenuItem("Change Height");
		
		changeHeight.setOnAction(e ->
		{
			Rectangle rectangle = (Rectangle) shape;
			double initialHeight = rectangle.getHeight();
			double newHeight = newValue.numberInput("Change Width", "Enter new width:", initialHeight);
			if (newHeight != initialHeight)
			{
				rectangle.setHeight(newHeight);
				//ChangeShapeSizeCommand cmd = new ChangeShapeSizeCommand(rectangle, initialWidth, rectangle.getHeight(), 0);
				//cmd.setNewSize(newWidth, rectangle.getHeight(), 0);
				//undoStack.push(cmd);
				//redoStack.clear();
			}
		});
		
		return changeHeight;
	}
	
	/**
	 * Instance menu item for changing rectangle width.
	 * <p/>
	 * TODO: Fix position transform.
	 * TODO: Implement missing features.
	 *
	 * @see #instanceRectangleHeight(Shape)
	 * @param shape The Rectangle width to change.
	 * @return The menu item.
	 */
	private MenuItem instanceRectangleWidth(Shape shape)
	{
		MenuItem changeWidth = new MenuItem("Change Width");
		
		changeWidth.setOnAction(e ->
		{
			Rectangle rectangle = (Rectangle) shape;
			double initialWidth = rectangle.getWidth();
			double newWidth = newValue.numberInput("Change Width", "Enter new width:", initialWidth);
			if (newWidth != initialWidth)
			{
				rectangle.setWidth(newWidth);
				//ChangeShapeSizeCommand cmd = new ChangeShapeSizeCommand(rectangle, initialWidth, rectangle.getHeight(), 0);
				//cmd.setNewSize(newWidth, rectangle.getHeight(), 0);
				//undoStack.push(cmd);
				//redoStack.clear();
			}
		});
		
		return changeWidth;
	}
	
	// TODO: Clean up method.
	/**
	 * Instance menu item for changing text.
	 * <p/>
	 * TODO: Fix position transform.
	 * TODO: Implement missing features.
	 *
	 * @param shape The Text to change.
	 * @return The menu item.
	 */
	private MenuItem instanceText(Shape shape)
	{
		MenuItem changeText = new MenuItem("Change Text");
		
		changeText.setOnAction(e ->
		{
			Text text = (Text) shape;
			//String initialText = text.getText();
			//String newText = newValue.textInput("Change Text", "Enter new text:", initialText);
			Text newText = newValue.textInputAlt(text);
			if (!Objects.equals(newText, text))
			{
				text.setText(newText.getText());
				text.setSize(newText.getSize());
				
				if (newText.isBold())
				{
					text.setBold();
				}
				
				if (newText.isItalic())
				{
					text.setItalic();
				}
				
				if (newText.isUnderlined())
				{
					text.setUnderline();
				}
				
				//ChangeShapeSizeCommand cmd = new ChangeShapeSizeCommand(rectangle, initialWidth, rectangle.getHeight(), 0);
				//cmd.setNewSize(newWidth, rectangle.getHeight(), 0);
				//undoStack.push(cmd);
				//redoStack.clear();
			}
		});
		
		return changeText;
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
