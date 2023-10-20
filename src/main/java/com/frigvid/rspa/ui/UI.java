package com.frigvid.rspa.ui;

import com.frigvid.rspa.App;
import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public abstract class UI
{
	private static final String DEFAULT_TITLE = "RSPA";
	private static final String DEFAULT_ICON = String.valueOf(App.class.getResource("icon/logo.png"));
	// private static final String DEFAULT_FILENAME = "Untitled";
	private static final double DEFAULT_WIDTH = 1000.0;
	private static final double DEFAULT_HEIGHT = 500.0;
	private static final double MINIMUM_WIDTH = 500.0;
	private static final double MINIMUM_HEIGHT = 300.0;
	private Stage stage;
	private BorderPane root;
	private Pane canvas;
	
	public UI(Stage stage)
	{
		this.stage = stage;
	}
	
	public createUI()
	{
		this.stage = stage;
	}
	
	public void initUI()
	{
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
	
	//private void windowText()
	//{
	//	public AnchorPane createUI() {
	//		AnchorPane root = new AnchorPane();
	//		root.setPrefSize(230, 163);
	//
	//		TextArea textArea = new TextArea();
	//		textArea.setId("textArea");
	//		textArea.setLayoutX(15);
	//		textArea.setLayoutY(15);
	//		textArea.setPrefSize(200, 100);
	//		textArea.setPromptText("Enter text here");
	//		textArea.setWrapText(true);
	//
	//		Button textItalic = new Button("I");
	//		textItalic.setId("textItalic");
	//		textItalic.setLayoutX(15);
	//		textItalic.setLayoutY(125);
	//		textItalic.setMinWidth(23);
	//		textItalic.setPrefHeight(25);
	//		textItalic.setFont(Font.font("System", FontPosture.ITALIC, 12));
	//		textItalic.setOnAction(event -> {
	//			// Implement the textItalic action here
	//		});
	//
	//		Button textBold = new Button("B");
	//		textBold.setId("textBold");
	//		textBold.setLayoutX(48);
	//		textBold.setLayoutY(125);
	//		textBold.setFont(Font.font("System", FontWeight.BOLD, 12));
	//		textBold.setOnAction(event -> {
	//			// Implement the textBold action here
	//		});
	//
	//		Button textUnderline = new Button("U");
	//		textUnderline.setId("textUnderline");
	//		textUnderline.setLayoutX(81);
	//		textUnderline.setLayoutY(125);
	//		textUnderline.setDisable(true);
	//		textUnderline.setUnderline(true);
	//		textUnderline.setOnAction(event -> {
	//			// Implement the textUnderline action here
	//		});
	//
	//		Spinner spinner = new Spinner();
	//		spinner.setEditable(true);
	//		spinner.setLayoutX(116);
	//		spinner.setLayoutY(125);
	//		spinner.setPrefSize(50, 25);
	//
	//		Button textAdd = new Button("Add");
	//		textAdd.setId("textAdd");
	//		textAdd.setLayoutX(177);
	//		textAdd.setLayoutY(125);
	//		textAdd.setOnAction(event -> {
	//			// Implement the textAdd action here
	//		});
	//
	//		root.getChildren().addAll(textArea, textItalic, textBold, textUnderline, spinner, textAdd);
	//
	//		return root;
	//	}
	//}
	
	private void moveFigureToFront(Shape shape, Pane root)
	{
		root.getChildren().remove(shape);
		root.getChildren().add(shape);
		//sendToBackItem.setOnAction(event ->
		//{
		//	shape.toBack();
		//});
	}
	
	private void moveFigureToBack(Shape shape, Pane root)
	{
		root.getChildren().remove(shape);
		root.getChildren().add(0, shape);
		//bringToFrontItem.setOnAction(event ->
		//{
		//	shape.toFront();
		//});
	}
	
	private void moveFigureForwardByOne(Shape shape, Pane root)
	{
		int idx = root.getChildren().indexOf(shape);
		if (idx < root.getChildren().size() - 1)
		{
			root.getChildren().remove(shape);
			root.getChildren().add(idx + 1, shape);
		}
	}
	
	private void moveFigureBackwardByOne(Shape shape, Pane root)
	{
		int idx = root.getChildren().indexOf(shape);
		if (idx > 0)
		{
			root.getChildren().remove(shape);
			root.getChildren().add(idx - 1, shape);
		}
	}
}
