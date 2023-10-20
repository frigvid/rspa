package com.frigvid.rspa.ui;

import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.history.HistoryStack;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.Objects;

public class CreateContext
{
	InputValue newValue = new InputValue();
	
	public ContextMenu createShapeContext(Shape shape, Pane canvas, ListView<HistoryStack.ShapeItem> sidebar)
	{
		ContextMenu shapeContext = new ContextMenu();
		
		/* Define shared menu items. */
		MenuItem strokeColor = new MenuItem("Change Stroke Color");
		MenuItem fillColor = new MenuItem("Change Fill Color");
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
		
		// TODO: Remove context menu if focus is lost.
		
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
		
		// TODO: Split context menu into canvas context menu and shape context menu.
		shapeContext.getItems().addAll(
				new SeparatorMenuItem(),
				strokeColor,
				fillColor,
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
	
	public ContextMenu createCanvasContext(/* Shape shape, Pane canvas, ListView<HistoryStack.ShapeItem> sidebar */)
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
		
		// TODO: Remove context menu if focus is lost.
		
		// TODO: Split context menu into canvas context menu and shape context menu.
		canvasContext.getItems().addAll(
				shapeCircle,
				shapeLine,
				shapeRectangle,
				shapeText
		);
		
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
	private MenuItem instanceText(Shape shape) {
		MenuItem changeText = new MenuItem("Change Text");
		
		changeText.setOnAction(e ->
		{
			Text text = (Text) shape;
			//WindowText textUI = new WindowText(null);
			//textUI.initUI();
			
			String initialText = text.getText();
			//String newText = newValue.textInput("Change Text", "Enter new text:", initialText);
			String newText = newValue.textInput("Change Text", "Enter new text:", initialText);
			if (!Objects.equals(newText, initialText))
			{
				text.setText(newText);
				//ChangeShapeSizeCommand cmd = new ChangeShapeSizeCommand(rectangle, initialWidth, rectangle.getHeight(), 0);
				//cmd.setNewSize(newWidth, rectangle.getHeight(), 0);
				//undoStack.push(cmd);
				//redoStack.clear();
			}
		});
		
		return changeText;
	}
}
