package com.frigvid.rspa.ui;

import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.ShapeHandler;
import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.history.HistoryStack;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.Arrays;
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
	 * @param scene The scene used to set up listeners for closing the context menu.
	 * @return The context menu.
	 */
	public ContextMenu createShapeContext(Shape shape, Pane canvas, Scene scene)
	{
		ContextMenu shapeContext = new ContextMenu();
		
		/* Define shared menu items. */
		MenuItem strokeColor = new MenuItem("Change Stroke Color");
		MenuItem fillColor = new MenuItem("Change Fill Color");
		MenuItem changeOpacity = new MenuItem("Change Opacity");
		MenuItem moveToFront = new MenuItem("Bring to Front");
		MenuItem moveToBack = new MenuItem("Send to Back");
		MenuItem moveForwardByOne = new MenuItem("Bring Forward by One");
		MenuItem moveBackwardByOne = new MenuItem("Send Backward by One");
		MenuItem delete = new MenuItem("Delete");
		
		// TODO: Implement strokeColor.setOnAction(event -> {});
		// TODO: Implement fillColor.setOnAction(event -> {});
		// TODO: Implement moveToFront.setOnAction(event -> {});
		//moveToFront.setOnAction(event -> moveFigureToFront(shape, canvas));
		// TODO: Implement moveToBack.setOnAction(event -> {});
		// TODO: Implement moveForwardByOne.setOnAction(event -> {});
		// TODO: Implement moveBackwardByOne.setOnAction(event -> {});
		// TODO: Implement delete.setOnAction(event -> {});
		// TODO: Check shape type.
		
		strokeColor.setOnAction(event -> shapeHandler.setStrokeColor(shape, newValue.colorPicker((Color) shape.getStroke())));
		fillColor.setOnAction(event -> shapeHandler.setFillColor(shape, newValue.colorPicker((Color) shape.getFill())));
		delete.setOnAction(event -> shapeHandler.deleteShape(shape, canvas));
		
		// NOTE: Alternatively use if (shape instanceof Line) {}.
		switch ((shape.getClass().getSimpleName()).toUpperCase()) {
			case "CIRCLE":
				shapeContext.getItems().add(instanceCircle(shape));
				break;
			case "LINE":
				shapeContext.getItems().add(instanceLine(shape));
				break;
			case "RECTANGLE":
				shapeContext.getItems().addAll(
						instanceRectangleHeight(shape),
						instanceRectangleWidth(shape)
				);
				break;
			case "TEXT":
				shapeContext.getItems().add(instanceText(shape));
				break;
			default:
				break;
		}
		
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
		shapeContext.getItems().addAll(
				new SeparatorMenuItem(),
				strokeColor
		);
		
		/* Only add fill color menu item if shape is not a line.
		 *
		 * I'd prefer to not split up "addAll()" method like this,
		 * however, it's the easiest way to add or remove this
		 * MenuItem and add it to the correct place in the ContextMenu. */
		if (!(shape instanceof Line))
		{
			shapeContext.getItems().add(fillColor);
		}
		
		/* Finish adding remaining MenuItems to the ContextMenu. */
		shapeContext.getItems().addAll(
				changeOpacity,
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
	 * @param scene The scene used to set up listeners for closing the context menu.
	 * @return The context menu.
	 */
	public ContextMenu createCanvasContext(Scene scene)
	{
		ContextMenu canvasContext = new ContextMenu();
		
		/* Define shared menu items. */
		MenuItem shapeCircle = new MenuItem("Draw Circle");
		MenuItem shapeLine = new MenuItem("Draw Line");
		MenuItem shapeRectangle = new MenuItem("Draw Rectangle");
		MenuItem shapeText = new MenuItem("Draw Text");
		
		// TODO: Implement shapeCircle.setOnAction(event -> {});
		// TODO: Implement shapeLine.setOnAction(event -> {});
		// TODO: Implement shapeRectangle.setOnAction(event -> {});
		// TODO: Implement shapeText.setOnAction(event -> {});
		
		canvasContext.getItems().addAll(
				shapeCircle,
				shapeLine,
				shapeRectangle,
				shapeText
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
				if (newText.isBold()) {text.setBold();}
				if (newText.isItalic()) {text.setItalic();}
				if (newText.isUnderlined()) {text.setUnderline();}
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
