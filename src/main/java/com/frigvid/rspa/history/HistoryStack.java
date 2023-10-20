package com.frigvid.rspa.history;

import javafx.scene.shape.Shape;

import java.util.Stack;

public abstract class HistoryStack
		implements ICommand
{
	private final Stack<ICommand> undoStack = new Stack<>();
	private final Stack<ICommand> redoStack = new Stack<>();
	
	public void addToHistory()
	{
	
	}
	
	public void removeFromHistory()
	{
	
	}
	
	public class ShapeItem
	{
		private String name;
		private Shape shape;
		
		public ShapeItem(String name, Shape shape)
		{
			this.name = name;
			this.shape = shape;
		}
		
		public String getName()
		{
			return name;
		}
		
		public Shape getShape()
		{
			return shape;
		}
		
		@Override
		public String toString()
		{
			return super.toString();
		}
	}
	
	
	@Override
	public void undo()
	{
	
	}
	
	@Override
	public void redo()
	{
	
	}
	
	/* TODO: Implement AddShapeCommand
	@Override
	public void undo() {
		pane.getChildren().remove(shape);
		sidebar.getItems().remove(shapeListItem);
	}
	@Override
	public void redo() {
		pane.getChildren().add(shape);
		sidebar.getItems().add(shapeListItem);
	}
	*/
	
	/* TODO: Implement MoveShapeCommand
	@Override
	public void undo() {
		if (shape instanceof Circle) {
			Circle circle = (Circle) shape;
			circle.setCenterX(oldX);
			circle.setCenterY(oldY);
		} else if (shape instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) shape;
			rectangle.setX(oldX);
			rectangle.setY(oldY);
		}
		shape.setTranslateX(0);
		shape.setTranslateY(0);
	}
	
	@Override
	public void redo() {
		if (shape instanceof Circle) {
			Circle circle = (Circle) shape;
			circle.setCenterX(newX);
			circle.setCenterY(newY);
		} else if (shape instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) shape;
			rectangle.setX(newX);
			rectangle.setY(newY);
		}
		shape.setTranslateX(0);
		shape.setTranslateY(0);
	}
	*/
	
	/* TODO: Implement ChangeShapeCommand
	@Override
	public void undo() {
		if (shape instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) shape;
			rectangle.setWidth(oldWidth);
			rectangle.setHeight(oldHeight);
		} else if (shape instanceof Circle) {
			Circle circle = (Circle) shape;
			circle.setRadius(oldRadius);
		}
	}
	
	@Override
	public void redo() {
		if (shape instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) shape;
			rectangle.setWidth(newWidth);
			rectangle.setHeight(newHeight);
		} else if (shape instanceof Circle) {
			Circle circle = (Circle) shape;
			circle.setRadius(newRadius);
		}
	}
	*/
	
	/* TODO: Implement ChangeShapeColorCommand
	@Override
	public void undo() {
		if(isStroke) {
			shape.setStroke(oldColor);
		} else {
			shape.setFill(oldColor);
		}
	}
	
	@Override
	public void redo() {
		if(isStroke) {
			shape.setStroke(newColor);
		} else {
			shape.setFill(newColor);
		}
	}
	*/
	
	/* TODO: Implement DeleteShapeCommand
	@Override
	public void undo() {
		drawingPane.getChildren().add(shape);
		sidebar.getItems().add(shapeListItem);
	}
	
	@Override
	public void redo() {
		execute();
	}
	*/
}
