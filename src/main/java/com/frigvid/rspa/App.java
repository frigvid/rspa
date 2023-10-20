package com.frigvid.rspa;

import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.ui.WindowMain;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class App extends Application
{
	@Override
	public void start(Stage stage)
	{
		WindowMain mainUI = new WindowMain(stage);
		mainUI.initUI();
		
		// TEST: Adding shapes to canvas.
		Pane canvas = mainUI.getCanvas();
		
		Circle circTest = new Circle(100, 100, 25);
		circTest.setFill(Paint.valueOf("red"));
		circTest.setStroke(Paint.valueOf("green"));
		circTest.setOpacity(0.5);
		canvas.getChildren().add(circTest);
		
		Rectangle rectTest = new Rectangle(200, 200, 50, 50);
		rectTest.setFill(Paint.valueOf("yellow"));
		rectTest.setStroke(Paint.valueOf("blue"));
		rectTest.setOpacity(0.5);
		rectTest.setRotate(45);
		canvas.getChildren().add(rectTest);
		
		Text textTest = new Text(200, 100, "Hello World!");
		textTest.setSize(30);
		textTest.setOpacity(0.8);
		textTest.setBold();
		textTest.setItalic();
		textTest.setUnderline();
		textTest.setRotate(45);
		canvas.getChildren().add(textTest);
		
		Line lineTest = new Line(100, 150, 200, 100);
		lineTest.setStroke(Paint.valueOf("blue"));
		lineTest.setStrokeWidth(5);
		canvas.getChildren().add(lineTest);
	}

	public static void main(String[] args)
	{
		launch();
	}
}