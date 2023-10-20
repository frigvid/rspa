package com.frigvid.rspa.ui;

import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class WindowMain extends GUI {
	
	public WindowMain(Stage stage) {
		super(stage);
	}
	
	@Override
	public void initUI() {
		windowMain();
		
		// TEST: Adding shapes to canvas.
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
		
		Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		stage.setTitle(DEFAULT_TITLE);
		stage.setMinHeight(MINIMUM_HEIGHT);
		stage.setMinWidth(MINIMUM_WIDTH);
		stage.getIcons().add(new Image(DEFAULT_ICON));
		stage.setScene(scene);
		stage.show();
	}
}
