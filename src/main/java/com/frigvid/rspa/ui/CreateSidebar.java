package com.frigvid.rspa.ui;

import com.frigvid.rspa.figure.FigureType;
import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.function.Consumer;

public class CreateSidebar
		extends GUI
{
	/* Sidebar elements. */
	StackPane sidebar = new StackPane();
	HBox hbox;
	Label selectedShape;
	TextField selectedShapeField;
	Label strokeLabel;
	ColorPicker strokeColorPicker;
	Label fillLabel;
	ColorPicker fillColorPicker;
	Label opacityLabel;
	Spinner<Double> opacitySpinner;
	Label rotationLabel;
	Spinner<Double> rotationSpinner;
	Label radiusLabel;
	Spinner<Double> radiusSpinner;
	Label lengthLabel;
	Spinner<Double> lengthSpinner;
	Label thicknessLabel;
	Spinner<Double> thicknessSpinner;
	Label widthLabel;
	Spinner<Double> widthSpinner;
	Label heightLabel;
	Spinner<Double> heightSpinner;
	Label fontSizeLabel;
	Spinner<Double> fontSizeSpinner;
	Label textLabel;
	TextArea textField;
	Button textItalic;
	Button textBold;
	Button textUnderline;
	Button textAdd;
	
	/* Default values. */
	final static int SPINNER_MIN = 1;
	final static int SPINNER_MAX = 999;
	FigureType figureType = FigureType.NONE;
	Color strokeColor = Color.BLACK;
	Color fillColor = Color.WHITE;
	double nodeOpacity = 1.0;
	double nodeRotation = 0.0;
	double circleRadius = 25.0;
	double lineLength = 50.0;
	double lineThickness = 2.5;
	double rectangleWidth = 100.0;
	double rectangleHeight = 50.0;
	double textFontSize = 12.0;
	String textString = "Default text.";
	Node shape = null;
	
	public CreateSidebar() {}
	
	public CreateSidebar(FigureType figureType)
	{
		this.figureType = figureType;
		initialize();
	}
	
	public void initialize()
	{
		purge(); // Empty the sidebar first, whenever it's called.
		createSidebar();
		sidebar.setStyle("-fx-background-color: #15e18c;");
		sidebar.setPrefWidth(200);
		sidebar.setPadding(new Insets(15, 15, 15, 15));
	}
	
	/* Sidebar components. */
	/**
	 * This method creates the sidebar.
	 * <p/>
	 * It makes use of the variable figureType to determine which
	 * components to add to the sidebar.
	 * <p/>
	 * Note that if this method isn't initialized, it will call
	 * initialize() first, which will empty the sidebar.
	 */
	private void createSidebar()
	{
		VBox vbox = new VBox(10);
		
		// Discount way to deal with the sidebar not being instantiated properly.
		if (sidebar == null)
		{
			initialize();
		}
		
		if (figureType != FigureType.NONE)
		{
			vbox.getChildren().addAll(
					addSelectedShape(),
					addStrokeColor(),
					addFillColor(),
					addOpacity(),
					addRotation()
			);
		}
		
		// Would've been nicer to use ternary operators instead, but due to the spacing, it causes large gaps.
		switch (figureType)
		{
			case CIRCLE:
				vbox.getChildren().add(addRadius());
				vbox.getChildren().remove(4); // Circles don't need rotation.
				break;
			case LINE:
				vbox.getChildren().addAll(
						addLength(),
						addThickness()
				);
				vbox.getChildren().remove(2); // Lines don't have a fill color.
				break;
			case RECTANGLE:
				vbox.getChildren().addAll(
						addWidth(),
						addHeight()
				);
				break;
			case TEXT:
				vbox.getChildren().addAll(
						addFontSize(),
						addText()
				);
				break;
			default:
				break;
		}
		
		sidebar.getChildren().add(vbox);
	}
	
	/**
	 * This component is just to display the shape type,
	 * just in case you click on the wrong thing.
	 * <p/>
	 * Note that this makes no distinction between a
	 * Circle and another Circle. They'll both appear
	 * as "CIRCLE" in the sidebar. However, they have
	 * different values (often), so you can still
	 * differentiate between them like that.
	 *
	 * @return The sidebar selected shape component.
	 */
	private VBox addSelectedShape()
	{
		VBox sidebarItem = new VBox();
		
		selectedShape = new Label("Selected Shape:");
		selectedShapeField = new TextField(figureType.toString());
		selectedShapeField.setDisable(true);
		sidebarItem.getChildren().addAll(
				selectedShape,
				selectedShapeField
		);
		
		return sidebarItem;
	}
	
	/**
	 * This contains the stroke color component for all shapes.
	 *
	 * @return The sidebar stroke color component.
	 */
	private VBox addStrokeColor()
	{
		VBox sidebarItem = new VBox();
		
		strokeLabel = new Label("Stroke Color:");
		strokeColorPicker = new ColorPicker();
		strokeColorPicker.setValue(strokeColor);
		sidebarItem.getChildren().addAll(
				strokeLabel,
				strokeColorPicker
		);
		
		strokeColorPicker.valueProperty().addListener(colorChangeListener(true));
		
		return sidebarItem;
	}
	
	/**
	 * This contains the fill color component for all shapes except Line.
	 *
	 * @return The sidebar fill color component.
	 */
	private VBox addFillColor()
	{
		VBox sidebarItem = new VBox();
		
		fillLabel = new Label("Fill Color:");
		fillColorPicker = new ColorPicker();
		fillColorPicker.setValue(fillColor);
		sidebarItem.getChildren().addAll(
				fillLabel,
				fillColorPicker
		);
		
		fillColorPicker.valueProperty().addListener(colorChangeListener(false));
		
		return sidebarItem;
	}
	
	/**
	 * This contains the opacity component for all shapes.
	 *
	 * @return The sidebar opacity component.
	 */
	private VBox addOpacity()
	{
		double min = 0.02; // Barely visible like this.
		double max = 1.0;
		double increment = 0.01;
		
		VBox sidebarItem = new VBox();
		DoubleSpinnerValueFactory opacitySpinnerFactory = new DoubleSpinnerValueFactory(min, max, nodeOpacity, increment);
		
		opacityLabel = new Label("Opacity:");
		opacitySpinner = new Spinner<>();
		opacitySpinner.setValueFactory(opacitySpinnerFactory);
		sidebarItem.getChildren().addAll(
				opacityLabel,
				opacitySpinner
		);
		
		opacitySpinner.valueProperty().addListener(spinnerChangeListener(opacity ->
				{
					nodeOpacity = opacity;
					shape.setOpacity(opacity);
				}));
		
		return sidebarItem;
	}
	
	/**
	 * This contains the rotation component for all shapes.
	 *
	 * @return The sidebar rotation component.
	 */
	private VBox addRotation()
	{
		double min = -360.0;
		double max = 360.0;
		double increment = 1.0;
		
		VBox sidebarItem = new VBox();
		DoubleSpinnerValueFactory rotationSpinnerFactory = new DoubleSpinnerValueFactory(min, max, nodeRotation, increment);
		
		rotationLabel = new Label("Rotation:");
		rotationSpinner = new Spinner<>();
		rotationSpinner.setValueFactory(rotationSpinnerFactory);
		sidebarItem.getChildren().addAll(
				rotationLabel,
				rotationSpinner
		);
		
		rotationSpinner.valueProperty().addListener(spinnerChangeListener(rotation ->
				{
					nodeRotation = rotation;
					shape.setRotate(rotation);
				}));
		
		return sidebarItem;
	}
	
	/**
	 * This contains the radius component for the Circle shape.
	 *
	 * @requires Import of com.frigvid.rspa.figure.shape.Circle.
	 * @return The sidebar radius component.
	 */
	private VBox addRadius()
	{
		VBox sidebarItem = new VBox();
		
		radiusLabel = new Label("Radius:");
		radiusSpinner = new Spinner<>(SPINNER_MIN, SPINNER_MAX, circleRadius);
		sidebarItem.getChildren().addAll(
				radiusLabel,
				radiusSpinner
		);
		
		radiusSpinner.valueProperty().addListener(spinnerChangeListener(radius ->
				{
					circleRadius = radius;
					((Circle) shape).setRadius(radius);
				}));
		
		return sidebarItem;
	}
	
	/**
	 * This contains the length component for the Line shape.
	 *
	 * @requires Import of com.frigvid.rspa.figure.shape.Line.
	 * @return The sidebar length component.
	 */
	private VBox addLength()
	{
		VBox sidebarItem = new VBox();
		
		lengthLabel = new Label("Length:");
		lengthSpinner = new Spinner<>(SPINNER_MIN, SPINNER_MAX, lineLength);
		sidebarItem.getChildren().addAll(
				lengthLabel,
				lengthSpinner
		);
		
		lengthSpinner.valueProperty().addListener(spinnerChangeListener(length ->
				{
					lineLength = length;
					((Line) shape).setLength(length);
				}));
		
		return sidebarItem;
	}
	
	/**
	 * This contains the thickness component for the Line shape.
	 *
	 * @requires Import of com.frigvid.rspa.figure.shape.Line.
	 * @return The sidebar thickness component.
	 */
	private VBox addThickness()
	{
		VBox sidebarItem = new VBox();
		
		thicknessLabel = new Label("Thickness:");
		thicknessSpinner = new Spinner<>(SPINNER_MIN, SPINNER_MAX, lineThickness);
		sidebarItem.getChildren().addAll(
				thicknessLabel,
				thicknessSpinner
		);
		
		thicknessSpinner.valueProperty().addListener(spinnerChangeListener(thickness ->
				{
					lineThickness = thickness;
					((Line) shape).setThickness(thickness);
				}));
		
		return sidebarItem;
	}
	
	/**
	 * This contains the width component for the Rectangle shape.
	 *
	 * @requires Import of com.frigvid.rspa.figure.shape.Rectangle.
	 * @return The sidebar width component.
	 */
	private VBox addWidth()
	{
		VBox sidebarItem = new VBox();
		
		widthLabel = new Label("Width:");
		widthSpinner = new Spinner<>(SPINNER_MIN, SPINNER_MAX, rectangleWidth);
		sidebarItem.getChildren().addAll(
				widthLabel,
				widthSpinner
		);
		
		widthSpinner.valueProperty().addListener(spinnerChangeListener(width ->
				{
					rectangleWidth = width;
					((Rectangle) shape).setWidth(width);
				}));
		
		return sidebarItem;
	}
	
	/**
	 * This contains the height component for the Rectangle shape.
	 *
	 * @requires Import of com.frigvid.rspa.figure.shape.Rectangle.
	 * @return The sidebar height component.
	 */
	private VBox addHeight()
	{
		VBox sidebarItem = new VBox();
		
		heightLabel = new Label("Height:");
		heightSpinner = new Spinner<>(SPINNER_MIN, SPINNER_MAX, rectangleHeight);
		sidebarItem.getChildren().addAll(
				heightLabel,
				heightSpinner
		);
		
		heightSpinner.valueProperty().addListener(spinnerChangeListener(height ->
				{
					rectangleHeight = height;
					((Rectangle) shape).setHeight(height);
				}));
		
		return sidebarItem;
	}
	
	/**
	 * This contains the font size component for the Text shape.
	 *
	 * @requires Import of com.frigvid.rspa.figure.shape.Text.
	 * @return The sidebar font size component.
	 */
	private VBox addFontSize()
	{
		VBox sidebarItem = new VBox();
		
		fontSizeLabel = new Label("Font Size:");
		fontSizeSpinner = new Spinner<>(SPINNER_MIN, SPINNER_MAX, textFontSize);
		sidebarItem.getChildren().addAll(
				fontSizeLabel,
				fontSizeSpinner
		);
		
		fontSizeSpinner.valueProperty().addListener(fontSizeListener());
		
		return sidebarItem;
	}
	
	/**
	 * This contains the text component for the Text shape.
	 *
	 * @requires Import of com.frigvid.rspa.figure.shape.Text.
	 * @return The sidebar text component.
	 */
	private VBox addText()
	{
		final int BUTTON_SIZE = 25;
		final int BUTTON_FONT_SIZE = 12;
		VBox sidebarItem = new VBox();
		
		textLabel = new Label("Text:");
		
		textField = new TextArea();
		textField.setText(textString);
		textField.setPromptText("Write here will ya?");
		textField.setFont(Font.font("System", 12));
		
		textItalic = new Button("I");
		textItalic.setPrefWidth(BUTTON_SIZE);
		textItalic.setPrefHeight(BUTTON_SIZE);
		textItalic.setFont(Font.font("System", FontPosture.ITALIC, BUTTON_FONT_SIZE));
		//textItalic.setOnAction(event -> {});
		
		textBold = new Button("B");
		textBold.setPrefWidth(BUTTON_SIZE);
		textBold.setPrefHeight(BUTTON_SIZE);
		textBold.setFont(Font.font("System", FontWeight.BOLD, BUTTON_FONT_SIZE));
		//textBold.setOnAction(event -> {});
		
		textUnderline = new Button("U");
		textUnderline.setPrefWidth(BUTTON_SIZE);
		textUnderline.setPrefHeight(BUTTON_SIZE);
		textUnderline.setUnderline(true);
		//textUnderline.setOnAction(event -> {});
		
		// These buttons are disabled for now due to unstable behavior.
		textItalic.setDisable(true);
		textBold.setDisable(true);
		textUnderline.setDisable(true);
		
		textAdd = new Button("Change");
		textAdd.setPrefWidth(BUTTON_SIZE * 2.25); // More than this will cause the textUnderline button to not display its text properly.
		textAdd.setPrefHeight(BUTTON_SIZE);
		textAdd.setOnAction(event ->
				{
					textString = textField.getText();
					((Text) shape).setText(textField.getText());
				});
		
		// Great Scott, it's a *SpaceBar*! ( •_•)>⌐■-■ (⌐■_■) (Ref: https://youtu.be/QJwIsBoe3Lg?si=iP0Z4MVlwexq6IAl&t=88)
		Region spaceBar = new Region();
		HBox.setHgrow(spaceBar, Priority.ALWAYS);
		
		hbox = new HBox(10);
		hbox.setPadding(new Insets(10, 0, 0, 0));
		hbox.getChildren().addAll(
				textItalic,
				textBold,
				textUnderline,
				spaceBar,
				textAdd
		);
		
		sidebarItem.getChildren().addAll(
				textLabel,
				textField,
				hbox
		);
		
		return sidebarItem;
	}
	
	/* Getters. */
	/**
	 * Get the actual sidebar Pane.
	 * <p/>
	 * Yes, I realize this is a bit of a weird design, putting it lightly.
	 * However, it works and I don't really care for now. Don't at me.
	 *
	 * @return The sidebar Pane.
	 */
	public Pane getSidebar()
	{
		return sidebar;
	}
	
	public Node getShape()
	{
		return shape;
	}
	
	public Color getStrokeColor()
	{
		return strokeColor;
	}
	
	public Color getFillColor()
	{
		return fillColor;
	}
	
	public double getNodeOpacity()
	{
		return nodeOpacity;
	}
	
	public double getNodeRotation()
	{
		return nodeRotation;
	}
	
	public double getCircleRadius()
	{
		return circleRadius;
	}
	
	public double getLineLength()
	{
		return lineLength;
	}
	
	public double getLineThickness()
	{
		return lineThickness;
	}
	
	public double getRectangleWidth()
	{
		return rectangleWidth;
	}
	
	public double getRectangleHeight()
	{
		return rectangleHeight;
	}
	
	public String getText()
	{
		return textField.getText();
	}
	
	public double getTextFontSize()
	{
		return textFontSize;
	}
	
	/* Setters. */
	public void setFigureType(FigureType type)
	{
		figureType = type;
	}
	
	public void setStrokeColor(Color color)
	{
		strokeColor = color;
	}
	
	public void setFillColor(Color color)
	{
		fillColor = color;
	}
	
	public void setNodeOpacity(double opacity)
	{
		nodeOpacity = opacity;
	}
	
	public void setNodeRotation(double rotation)
	{
		nodeRotation = rotation;
	}
	
	public void setCircleRadius(double radius)
	{
		circleRadius = radius;
	}
	
	public void setLineLength(double length)
	{
		lineLength = length;
	}
	
	public void setLineThickness(double thickness)
	{
		lineThickness = thickness;
	}
	
	public void setRectangleWidth(double width)
	{
		rectangleWidth = width;
	}
	
	public void setRectangleHeight(double height)
	{
		rectangleHeight = height;
	}
	
	public void setTextFontSize(double fontSize)
	{
		textFontSize = fontSize;
	}
	
	public void setTextString(String text)
	{
		textString = text;
	}
	
	/**
	 * This passes the node that is going to be modified to this class,
	 * and allows for listeners and such to be set up with a more
	 * "streamlined" design than what would otherwise be implemented.
	 * <p/>
	 * Do note that this is not a setter for the shape itself, but rather
	 * a setter for an internal variable that saves the Shape or Node
	 * object to a Node variable.
	 *
	 * @param node The node that is going to be modified.
	 */
	public void setNode(Node node)
	{
		shape = node;
	}
	
	/* Miscellaneous. */
	/**
	 * This method is used to empty the sidebar of all its children.
	 */
	public void purge()
	{
		sidebar.getChildren().clear();
	}
	
	/**
	 * This method is used to disable all the text buttons.
	 * <p/>
	 * Call this when you're creating a new Text object,
	 * and not modifying an existing one.
	 */
	public void disableTextButtons()
	{
		textItalic.setDisable(true);
		textBold.setDisable(true);
		textUnderline.setDisable(true);
		textAdd.setDisable(true);
	}
	
	/**
	 * This method disables some buttons for the Line component.
	 * <p/>
	 * Call this when you're creating a new Line object, and not
	 * modifying an existing one.
	 */
	public void disableLineButtons()
	{
		opacitySpinner.setDisable(true);
		lengthSpinner.setDisable(true);
	}
	
	/* Listeners. */
	/**
	 * This is a generic listener for the ColorPicker components.
	 * It's a bit ugly and could do to be less verbose, but it works.
	 *
	 * @param isStroke Whether the ColorPicker is for the stroke or fill.
	 * @return The ChangeListener for the ColorPicker.
	 */
	private ChangeListener<Color> colorChangeListener(boolean isStroke)
	{
		return (observable, oldValue, newValue) ->
		{
			if (shape instanceof Shape)
			{
				if (isStroke)
				{
					strokeColor = newValue;
					((Shape) shape).setStroke(newValue);
				}
				else
				{
					fillColor = newValue;
					((Shape) shape).setFill(newValue);
				}
			}
			else if (shape instanceof Text) // This complains it'll always be false. It won't, just ignore it.
			{
				if (isStroke)
				{
					strokeColor = newValue;
					((Text) shape).setStroke(newValue);
				}
				else
				{
					fillColor = newValue;
					((Text) shape).setFill(newValue);
				}
			}
		};
	}
	
	/**
	 * This is a generic listener for the Spinner components.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     Spinner<double> spinner = new Spinner(1, 999, 25);
	 *     spinner.valueProperty().addListener(spinnerChangeListener(radius -> ((Circle) shape).setRadius(radius)));
	 * </pre>
	 *
	 * @param action The action to perform when the Spinner value changes.
	 * @return The ChangeListener for the Spinner.
	 * @param <T> The type of the Spinner.
	 */
	private <T> ChangeListener<T> spinnerChangeListener(Consumer<T> action)
	{
		return (observable, oldValue, newValue) ->
		{
			action.accept(newValue);
		};
	}
	
	/**
	 * This is a listener for the font size Spinner component.
	 * <p/>
	 * Note that if you don't update the textFontSize variable
	 * through this listener, creating new Text through the
	 * sidebar will instead cause it to use the default or the
	 * value it was set to when the sidebar was initialized.
	 * It will update existing Text objects though, because of
	 * how it works, but just remember not to remove that variable.
	 *
	 * @return The ChangeListener for the font size Spinner.
	 */
	private ChangeListener<Double> fontSizeListener()
	{
		return (observable, oldValue, newValue) ->
		{
			textFontSize = newValue;
			((Text) shape).setSize(newValue);
			// I think it's actually better UX to not modify the size of the text field. I'll leave it here for reference.
			//textField.setFont(Font.font(textField.getFont().getFamily(), newValue));
		};
	}
}
