package com.frigvid.rspa.ui;

import com.frigvid.rspa.figure.shape.Text;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This class handles input of values.
 * <p/>
 * Example usage:
 * <pre>
 *     InputValue newValue = new InputValue();
 *     double value = newValue.numberInput("Change Radius", "Enter new radius:", initialRadius);
 * </pre>
 * TODO: Fix discrepancy between the textInputAlt TextArea and actual Text Shape.
 * 		 Currently there's some weird behavior going on that's causing the style
 * 		 to not be set properly.
 */
public class InputValue
{
	/**
	 * Show dialog for input of double value.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     InputValue newValue = new InputValue();
	 *     double value = newValue.numberInput("Change Radius", "Enter new radius:", initialRadius);
	 * </pre>
	 *
	 * @param title Title of dialog.
	 * @param header Header of dialog.
	 * @param initialValue Initial value.
	 * @return double.
	 */
	public double numberInput(String title, String header, double initialValue)
	{
		TextInputDialog dialog = new TextInputDialog(String.valueOf(initialValue));
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText("Value:");
		
		// Filter input.
		TextField inputField = dialog.getEditor();
		inputField.addEventFilter(KeyEvent.KEY_TYPED, event ->
		{
			if (!event.getCharacter().matches("[0-9.]"))
			{
				event.consume();
			}
		});
		
		// Close dialog when focus is lost.
		return ifFocusLost(initialValue, dialog);
	}
	
	public Text textInputAlt(Text initialText)
	{
		Stage stage = new Stage();
		AnchorPane root = new AnchorPane();
		
		/* Setting some default UI values.
		 *
		 * Also doing some discount calculations to make resizing easier.
		 */
		final int ROOT_WIDTH = 400;
		final int ROOT_HEIGHT = 200;
		final int DEFAULT_MARGIN = 15; // Used for adding margin between the window and elements.
		final int DEFAULT_PADDING = 10; // Used for adding padding between elements.
		final int DEFAULT_BUTTON_SIZE = 25;
		final int TEXTAREA_WIDTH = ROOT_WIDTH - (DEFAULT_MARGIN * 2);
		final int TEXTAREA_HEIGHT = (int) (ROOT_HEIGHT / 1.5);
		final int BUTTON_Y = ROOT_HEIGHT - (DEFAULT_MARGIN * 2) - DEFAULT_PADDING;
		final int BUTTON_ADD_X = ROOT_WIDTH - (DEFAULT_BUTTON_SIZE * 2) - DEFAULT_MARGIN;
		
		/* Initialize UI. */
		TextArea textArea = new TextArea();
		Button textItalic = new Button("I");
		Button textBold = new Button("B");
		Button textUnderline = new Button("U");
		Spinner<Double> spinner = new Spinner<>(10, 100, initialText.getSize()); // Min, max, initial value.
		Button textAdd = new Button("Add");
		
		/* Initialize values. */
		AtomicBoolean italic = new AtomicBoolean(initialText.isItalic());
		AtomicBoolean bold = new AtomicBoolean(initialText.isBold());
		AtomicBoolean underline = new AtomicBoolean(initialText.isUnderlined());
		AtomicReference<Double> size = new AtomicReference<>(initialText.getSize());
		
		/* Set dialogue window size. */
		root.setPrefSize(ROOT_WIDTH, ROOT_HEIGHT);
		
		/* Customize textArea. */
		textArea.setId("textArea");
		textArea.setLayoutX(DEFAULT_MARGIN);
		textArea.setLayoutY(DEFAULT_MARGIN);
		textArea.setPrefSize(TEXTAREA_WIDTH, TEXTAREA_HEIGHT);
		textArea.setMaxHeight(TEXTAREA_HEIGHT);
		textArea.setMaxWidth(TEXTAREA_WIDTH);
		textArea.setText(initialText.getText());
		textArea.setPromptText("Enter text here");
		textArea.setWrapText(true);
		
		/* Do an initial update so the TextArea reflects the Text object being modified. */
		updateFont(textArea, bold, italic, underline, spinner.getValue());
		
		/* Customize textItalic. */
		textItalic.setId("textItalic");
		textItalic.setLayoutX(15);
		textItalic.setLayoutY(BUTTON_Y); // 125
		textItalic.setMinWidth(DEFAULT_BUTTON_SIZE);
		textItalic.setPrefHeight(DEFAULT_BUTTON_SIZE);
		textItalic.setFont(Font.font("System", FontPosture.ITALIC, 12));
		textItalic.setOnAction(event ->
		{
			italic.set(!italic.get());
			updateFont(textArea, bold, italic, underline, spinner.getValue());
		});
		
		/* Customize textBold. */
		textBold.setId("textBold");
		textBold.setLayoutX(48);
		textBold.setLayoutY(BUTTON_Y);
		textBold.setMinWidth(DEFAULT_BUTTON_SIZE);
		textBold.setPrefHeight(DEFAULT_BUTTON_SIZE);
		textBold.setFont(Font.font("System", FontWeight.BOLD, 12));
		textBold.setOnAction(event ->
		{
			bold.set(!bold.get());
			updateFont(textArea, bold, italic, underline, spinner.getValue());
		});
		
		/* Customize textUnderline. */
		textUnderline.setId("textUnderline");
		textUnderline.setLayoutX(81);
		textUnderline.setLayoutY(BUTTON_Y);
		textUnderline.setMinWidth(DEFAULT_BUTTON_SIZE);
		textUnderline.setPrefHeight(DEFAULT_BUTTON_SIZE);
		//textUnderline.setDisable(true);
		textUnderline.setUnderline(true);
		textUnderline.setOnAction(event ->
		{
			underline.set(!underline.get());
			updateFont(textArea, bold, italic, underline, spinner.getValue());
		});
		
		/* Customize spinner. */
		spinner.setEditable(true);
		spinner.setLayoutX(116);
		spinner.setLayoutY(BUTTON_Y);
		spinner.setPrefSize(100, DEFAULT_BUTTON_SIZE);
		spinner.valueProperty().addListener((obs, oldValue, newValue) ->
		{
			size.set(newValue);
			updateFont(textArea, bold, italic, underline, newValue);
		});
		
		/* Customize textAdd. */
		textAdd.setId("textAdd");
		textAdd.setLayoutX(BUTTON_ADD_X);
		textAdd.setLayoutY(BUTTON_Y);
		textAdd.setMinWidth(DEFAULT_BUTTON_SIZE * 2);
		textAdd.setPrefHeight(DEFAULT_BUTTON_SIZE);
		textAdd.setOnAction(event ->
		{
			updateTextShape(initialText, stage, textArea, italic, bold, underline);
		});
		
		root.getChildren().addAll(textArea, textItalic, textBold, textUnderline, spinner, textAdd);
		
		Scene scene = new Scene(root);
		
		/* Either cancel (close) dialog when escape is pressed or returns new string when control+enter is pressed.
		 * <p/>
		 * Technically, this causes a redraw, but it's the initial String, so the user doesn't notice.
		 * However, this will need to be fixed if a sidebar with all shapes visible are to be implemented.
		 * If it isn't, it'll probably cause errors like the shape appearing in the list multiple times or
		 * being moved to the top of the stack when it shouldn't be. A potential fix might just be to let
		 * the caller deal with it, but that's not very user friendly. In other words:
		 *
		 * TODO: Fix this abomination, you smooth brain.
		 */
		scene.setOnKeyPressed(keyEvent ->
		{
			if (keyEvent.getCode() == KeyCode.ESCAPE)
			{
				initialText.setText(initialText.getText());
				stage.close();
			}
			else if (new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN).match(keyEvent))
			{
				updateTextShape(initialText, stage, textArea, italic, bold, underline);
			}
		});
		
		/* Closes the dialogue window if focus is lost. */
		stage.focusedProperty().addListener((observable, oldValue, newValue) ->
		{
			if (!newValue) {
				stage.close();
			}
		});
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.showAndWait();
		
		return initialText;
	}
	
	private void updateTextShape(Text initialText, Stage stage, TextArea textArea, AtomicBoolean italic, AtomicBoolean bold, AtomicBoolean underline)
	{
		initialText.setText(textArea.getText());
		
		if (italic.get())
		{
			initialText.setItalic();
		}
		
		if (bold.get())
		{
			initialText.setBold();
		}
		
		if (underline.get())
		{
			initialText.setUnderline();
		}
		
		initialText.setStyle();
		stage.close();
	}
	
	private void updateFont(TextArea textArea, AtomicBoolean isBold, AtomicBoolean isItalic, AtomicBoolean isUnderlined, double fontSize)
	{
		FontWeight fontWeight = isBold.get() ? FontWeight.BOLD : FontWeight.NORMAL;
		FontPosture fontPosture = isItalic.get() ? FontPosture.ITALIC : FontPosture.REGULAR;
		textArea.setFont(Font.font("System", fontWeight, fontPosture, fontSize));
		//textArea.setUnderline(isUnderlined.get());
		textArea.getStyleClass().add("-fx-underline: " + isUnderlined.get() + ";");
	}
	
	/**
	 * Show dialog for input of Color value.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     InputValue newValue = new InputValue();
	 *     Color value = newValue.colorInput("Change Color", "Enter new color:", initialColor);
	 * </pre>
	 * <p/>
	 * TODO: Implement close on lost focus.
	 *
	 * @param initialColor Initial value.
	 * @return Color.
	 */
	public Color colorPicker(Color initialColor)
	{
		Dialog<Color> dialog = new Dialog<>();
		ColorPicker colorPicker = new ColorPicker(initialColor);
		
		dialog.setTitle("Pick a Color");
		dialog.getDialogPane().setContent(colorPicker);
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		
		dialog.setResultConverter(dialogButton ->
		{
			if (dialogButton == ButtonType.OK)
			{
				return colorPicker.getValue();
			}
			return null;
		});
		
		return dialog.showAndWait().orElse(null);
	}
	
	/**
	 * Close dialog when focus is lost.
	 * <p/>
	 * Shared method for inputValue methods.
	 * <p/>
	 * Note: This method suppresses "unchecked" warnings.
	 * 		 I could leave it to the caller, and it's generally
	 * 		 not recommended to do it this way, but eh, it's fine
	 * 		 for something so small and one-off like this.
	 *
	 * @param initialValue Initial value.
	 * @param dialog TextInputDialog.
	 * @return Initial value, either double or String.
	 * @param <T> Either double or String.
	 */
	@SuppressWarnings("unchecked")
	private <T> T ifFocusLost(T initialValue, TextInputDialog dialog)
	{
		Window dialogWindow = dialog.getDialogPane().getScene().getWindow();
		dialogWindow.focusedProperty().addListener((obs, wasFocused, isNowFocused) ->
		{
			if (!isNowFocused)
			{
				dialog.close();
			}
		});
		
		Optional<String> result = dialog.showAndWait();
		
		if (initialValue instanceof Double)
		{
			try
			{
				return (T) result.map(Double::parseDouble).orElse((Double) initialValue);
			}
			catch (NumberFormatException e)
			{
				return initialValue;
			}
		}
		else if (initialValue instanceof String)
		{
			return (T) result.orElse((String) initialValue);
		}
		else
		{
			throw new IllegalArgumentException("Unsupported type for initialValue");
		}
	}
}
