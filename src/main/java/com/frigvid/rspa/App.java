package com.frigvid.rspa;

import com.frigvid.rspa.figure.shape.*;
import com.frigvid.rspa.ui.WindowMain;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class App
		extends Application
{
	@Override
	public void start(Stage stage)
	{
		WindowMain mainUI = new WindowMain(stage);
		mainUI.initialize();
		
		// TEST: Adding shapes to canvas.
		Pane canvas = mainUI.getCanvas();
		
		Circle circTest = new Circle(100, 100, 25);
		circTest.setFill(Paint.valueOf("red"));
		circTest.setStroke(Paint.valueOf("green"));
		circTest.setOpacity(0.5);
		canvas.getChildren().add(circTest);
		
		Circle circTest2 = new Circle(115, 115, 25);
		circTest2.setFill(Paint.valueOf("magenta"));
		circTest2.setStroke(Paint.valueOf("red"));
		circTest2.setOpacity(0.5);
		canvas.getChildren().add(circTest2);
		
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
		
		/* TEST: CoolerCircle
		CoolerCircle circTest2 = new CoolerCircle(300, 300, 25);
		circTest2.setFill(Paint.valueOf("magenta"));
		circTest2.setStroke(Paint.valueOf("green"));
		circTest2.setOpacity(0.1);
		System.out.println(circTest2.getType());
		canvas.getChildren().add(circTest2.getNode());
		System.out.println(circTest2.getClass().getSimpleName());
		*/
		
		System.out.println(circTest.getClass().getSimpleName());
		System.out.println(rectTest.getClass().getSimpleName());
		System.out.println(textTest.getClass().getSimpleName());
		System.out.println(lineTest.getClass().getSimpleName());
	}

	public static void main(String[] args)
	{
		launch();
	}
}