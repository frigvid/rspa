package com.frigvid.rspa.ui;

import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import com.frigvid.rspa.history.MoveShape;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App
		extends GUI
{
	CreateContext context = new CreateContext();
	private MoveShape currentMoveCommand;
	/* Used to check if user is trying to drag a shape,
	 * as opposed to interact with it. */
	boolean isDragging = false;
	private boolean altPressed = false;
	private boolean mousePressed = false;
	private double xOffset = 0;
	private double yOffset = 0;
	//private Type currentShapeType;
	
	public App(Stage stage)
	{
		super(stage);
	}
	
	/**
	 * Initialize the UI. Add any code here that
	 * needs to be run when the Primary UI is created.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     App app = new App(stage);
	 *     app.initialize();
	 * </pre>
	 */
	@Override
	public void initialize()
	{
		// TODO: Implement holding CTRL for changing rotation on shape under cursor.
		// TODO: Implement holding ALT for moving shape under cursor.
		root = new BorderPane();
		Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		root.setCenter(createCanvas(scene));
		root.setRight(createSidebar());
		
		stage.setTitle(DEFAULT_TITLE);
		stage.setMinWidth(MINIMUM_WIDTH);
		stage.setMinHeight(MINIMUM_HEIGHT);
		stage.getIcons().add(new Image(DEFAULT_ICON));
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Create the canvas Pane.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     BorderPane root = new BorderPane();
	 *     root.setCenter(canvas());
	 * </pre>
	 *
	 * @return The canvas Pane.
	 */
	private Pane createCanvas(Scene scene)
	{
		canvas = new Pane();
		
		canvas.setStyle("-fx-background-color: #7e1e1e;");
		
		/* Mouse event handler. */
		// TODO: Implement mouse event handler.
		canvas.setOnMouseClicked(event ->
		{
			//Node node = (Node) event.getTarget();
			//if (node instanceof Shape) {
			//	mouseEventHandler(event, (Shape) node);
			//}
			//mouseEventHandler(event);
			if (isDragging)
			{
				isDragging = false;
				event.consume();
				return;
			}
			
			if (event.getButton() == MouseButton.PRIMARY && !(event.getTarget() instanceof Shape))
			{
				System.out.println("Clicked on canvas.");
			}
			else if (event.getButton() == MouseButton.PRIMARY && (event.getTarget() instanceof Shape shape))
			{
				//Shape shape = getShape((Shape) event.getTarget());
				
				if (shape != null)
				{
					//shape.setOnMousePressed(this::handleShapePress);
					
					//initCursorHover(scene, shape);
					System.out.println(shape);
				}
			}
		});
		
		canvas.setOnContextMenuRequested(event ->
		{
			if (altPressed)
			{
				event.consume();
				return;
			}
			
			if (event.getTarget() instanceof Shape shape)
			{
				ContextMenu shapeContext = context.createShapeContext(shape, canvas, root.getScene());
				shapeContext.show(canvas, event.getScreenX(), event.getScreenY());
			}
			else
			{
				ContextMenu canvasContext = context.createCanvasContext(root.getScene(), canvas);
				canvasContext.show(canvas, event.getScreenX(), event.getScreenY());
			}
			
			event.consume();
		});
		
		return canvas;
	}
	
	private Pane createSidebar()
	{
		sidebar = new StackPane();
		VBox vbox = new VBox();
		
		Label selectedShape = new Label("Selected Shape:");
		TextField selectedShapeField = new TextField("None"); // TODO: Set the shape's type here (e.g. CIRCLE).
		selectedShapeField.setDisable(true);
		
		Label strokeLabel = new Label("Stroke Color:");
		ColorPicker strokeColorPicker = new ColorPicker();
		strokeColorPicker.setValue(Color.BLACK);  // Default color
		
		Label fillLabel = new Label("Fill Color:");
		ColorPicker fillColorPicker = new ColorPicker();
		fillColorPicker.setValue(Color.WHITE);  // Default color
		
		Label widthLabel = new Label("Width:");
		Spinner<Integer> widthSpinner = new Spinner<>(1, 500, 100); // min, max, default values
		
		Label heightLabel = new Label("Height:");
		Spinner<Integer> heightSpinner = new Spinner<>(1, 500, 100);  // min, max, default values
		
		Label fontSizeLabel = new Label("Font Size:");
		Spinner<Integer> fontSizeSpinner = new Spinner<>(1, 100, 12);  // min, max, default values
		
		Label textLabel = new Label("Text:");
		TextArea textField = new TextArea();
		textField.setPromptText("Enter text here.");
		
		Button textItalic = new Button("I");
		textItalic.setId("textItalic");
		//textItalic.setLayoutX(15);
		//textItalic.setLayoutY(BUTTON_Y); // 125
		textItalic.setMinWidth(25);
		textItalic.setPrefHeight(25);
		textItalic.setFont(Font.font("System", FontPosture.ITALIC, 12));
		textItalic.setOnAction(event ->
		{
		});
		textItalic.setDisable(true);
		
		Button textBold = new Button("B");
		textBold.setId("textBold");
		//textBold.setLayoutX(48);
		//textBold.setLayoutY(BUTTON_Y);
		textBold.setMinWidth(25);
		textBold.setPrefHeight(25);
		textBold.setFont(Font.font("System", FontWeight.BOLD, 12));
		textBold.setOnAction(event ->
		{
		});
		textBold.setDisable(true);
		
		Button textUnderline = new Button("U");
		textUnderline.setId("textUnderline");
		//textUnderline.setLayoutX(81);
		//textUnderline.setLayoutY(BUTTON_Y);
		textUnderline.setMinWidth(25);
		textUnderline.setPrefHeight(25);
		//textUnderline.setDisable(true);
		textUnderline.setUnderline(true);
		textUnderline.setOnAction(event ->
		{
		});
		textUnderline.setDisable(true);
		
		Button textAdd = new Button("Add");
		textAdd.setId("textAdd");
		//textAdd.setLayoutX(BUTTON_ADD_X);
		//textAdd.setLayoutY(BUTTON_Y);
		textAdd.setMinWidth(25 * 2);
		textAdd.setPrefHeight(25);
		textAdd.setOnAction(event ->
		{
		});
		
		// Great Scott, it's a *SpaceBar*! ( •_•)>⌐■-■ (⌐■_■) (Ref: https://youtu.be/QJwIsBoe3Lg?si=iP0Z4MVlwexq6IAl&t=88)
		Region spaceBar = new Region();
		HBox.setHgrow(spaceBar, Priority.ALWAYS);
		
		HBox hBox = new HBox(10);
		hBox.setPadding(new Insets(10, 0, 0, 0));
		hBox.getChildren().addAll(
				textItalic,
				textBold,
				textUnderline,
				spaceBar,
				textAdd
		);
		
		// Add all controls to the VBox
		vbox.getChildren().addAll(
				selectedShape,
				selectedShapeField,
				strokeLabel,
				strokeColorPicker,
				fillLabel,
				fillColorPicker,
				widthLabel,
				widthSpinner,
				heightLabel,
				heightSpinner,
				fontSizeLabel,
				fontSizeSpinner,
				textLabel,
				textField,
				hBox
		);
		
		sidebar.getChildren().add(vbox);
		sidebar.setStyle("-fx-background-color: #15e18c;");
		sidebar.setPrefWidth(200);
		sidebar.setPadding(new Insets(15, 15, 15, 15));
		
		return sidebar;
	}
	
	
	
	
	
	
	
	private Shape getShape(Shape shape)
	{
		if (shape instanceof Circle circle)
		{
			return new Circle(circle.getCenterX(), circle.getCenterY(), circle.getRadius());
		}
		else if (shape instanceof Line line)
		{
			return new Line(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
		}
		else if (shape instanceof Rectangle rectangle)
		{
			return new Rectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
		}
		else if (shape instanceof Text text)
		{
			return new Text(text.getX(), text.getY(), text.getText());
		}
		
		return null;
	}
	
	
	
	
	
	private void initCursorHover(Scene scene, Shape shape)
	{
		// Detect ALT key pressed and released
		scene.setOnKeyPressed(event ->
		{
			if (event.isAltDown())
			{
				altPressed = true;
				if (!mousePressed)
				{
					scene.setCursor(Cursor.OPEN_HAND);
				}
			}
		});
		
		scene.setOnKeyReleased(event ->
		{
			if (event.getCode().toString().equals("ALT"))
			{
				altPressed = false;
				scene.setCursor(Cursor.DEFAULT);
			}
		});
		
		// Detect mouse pressed and released with ALT key
		scene.setOnMousePressed(event ->
		{
			if (altPressed && event.isPrimaryButtonDown())
			{
				mousePressed = true;
				scene.setCursor(Cursor.CLOSED_HAND);
				shape.setOnMousePressed(this::handleShapePress);
			}
		});
		
		scene.setOnMouseReleased(event ->
		{
			if (altPressed)
			{
				mousePressed = false;
				scene.setCursor(Cursor.OPEN_HAND);
			}
		});
	}
	
	
	
	
	private void handleShapePress(MouseEvent event)
	{
		Shape shape = (Shape) event.getSource();
		
		if (shape instanceof Circle circle)
		{
			xOffset = event.getSceneX() - circle.getCenterX();
			yOffset = event.getSceneY() - circle.getCenterY();
			currentMoveCommand = new MoveShape(circle, circle.getCenterX(), circle.getCenterY());
		}
		else if (shape instanceof Rectangle rectangle)
		{
			xOffset = event.getSceneX() - rectangle.getX();
			yOffset = event.getSceneY() - rectangle.getY();
			currentMoveCommand = new MoveShape(rectangle, rectangle.getX(), rectangle.getY());
		}
		
		event.consume();
	}
	
	
	
	
	
	
	// Temporarily commented out.
	//private ListView<> createSidebar()
	//{
	//	ListView<> sidebar = new ListView<>();
	//
	//	//sidebar.setStyle("-fx-background-color: #15e18c;");
	//
	//	return sidebar;
	//}
	
	/**
	 * Work in progress handler method to deal with the different
	 * type of interactions a user may have with a shape.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     App app = new App(stage);
	 *     app.mouseEventHandler(event, shape);
	 * </pre>
	 *
	 * @param event The mouse event.
	 * @param shape The shape.
	 */
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
