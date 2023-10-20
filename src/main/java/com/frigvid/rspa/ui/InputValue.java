package com.frigvid.rspa.ui;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Optional;

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
				event.consume(); // Omnom.
			}
		});
		
		// Close dialog when focus is lost.
		return ifFocusLost(initialValue, dialog);
	}
	
	/**
	 * Show dialog for input of String value.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     InputValue newValue = new InputValue();
	 *     String value = newValue.textInput("Change Name", "Enter new name:", initialName);
	 * </pre>
	 *
	 * @param title Title of dialog.
	 * @param header Header of dialog.
	 * @param initialString Initial value.
	 * @return String.
	 */
	public String textInput(String title, String header, String initialString)
	{
		TextInputDialog dialog = new TextInputDialog(String.valueOf(initialString));
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText("Value:");
		
		// Close dialog when focus is lost.
		return ifFocusLost(initialString, dialog);
	}
	
	/**
	 * Show dialog for input of String value.
	 * <p/>
	 * Example usage:
	 * <pre>
	 *     InputValue newValue = new InputValue();
	 *     String value = newValue.textAltInput(initialString);
	 * </pre>
	 * NOTE: Set "initialString" to "null" if you want an empty dialog.
	 * NOTE: This is a work in progress.
	 * <p/>
	 * TODO: Consider extracting UI code and using FXML instead.
	 * 		 There's already an example of this in
	 * 		 resources/com/frigvid/rspa/ui/text.fxml.
	 *
	 * @param initialString Initial string.
	 * @return String.
	 */
	public String textInputAlt(String initialString)
	{
		Stage stage = new Stage();
		AnchorPane root = new AnchorPane();
		
		final String[] result = {initialString}; // Lamda final requirement workaround.
		
		/* Set dialogue window size. */
		root.setPrefSize(230, 163);
		
		TextArea textArea = new TextArea();
		textArea.setId("textArea");
		textArea.setLayoutX(15);
		textArea.setLayoutY(15);
		textArea.setPrefSize(200, 100);
		textArea.setText(initialString);
		textArea.setPromptText("Enter text here");
		textArea.setWrapText(true);
		
		Button textItalic = new Button("I");
		textItalic.setId("textItalic");
		textItalic.setLayoutX(15);
		textItalic.setLayoutY(125);
		textItalic.setMinWidth(23);
		textItalic.setPrefHeight(25);
		textItalic.setFont(Font.font("System", FontPosture.ITALIC, 12));
		textItalic.setOnAction(event ->
		{
			// TODO: Call function that sets textArea to Italic.
		});
		
		Button textBold = new Button("B");
		textBold.setId("textBold");
		textBold.setLayoutX(48);
		textBold.setLayoutY(125);
		textBold.setFont(Font.font("System", FontWeight.BOLD, 12));
		textBold.setOnAction(event ->
		{
			// TODO: Call function that sets textArea to Bold.
		});
		
		Button textUnderline = new Button("U");
		textUnderline.setId("textUnderline");
		textUnderline.setLayoutX(81);
		textUnderline.setLayoutY(125);
		textUnderline.setDisable(true);
		textUnderline.setUnderline(true);
		textUnderline.setOnAction(event ->
		{
			// TODO: Call function that sets textArea to Underline.
		});
		
		Spinner spinner = new Spinner();
		spinner.setEditable(true);
		spinner.setLayoutX(116);
		spinner.setLayoutY(125);
		spinner.setPrefSize(50, 25);
		
		Button textAdd = new Button("Add");
		textAdd.setId("textAdd");
		textAdd.setLayoutX(177);
		textAdd.setLayoutY(125);
		textAdd.setOnAction(event ->
		{
			result[0] = textArea.getText();
			stage.close();
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
				result[0] = initialString;
				stage.close();
			}
			else if (new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN).match(keyEvent))
			{
				result[0] = textArea.getText();
				stage.close();
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
		/* Modal requires window to be closed to continue. Closing on focus lost might be better.
		 * stage.initModality(Modality.APPLICATION_MODAL); */
		stage.showAndWait();
		
		/* Return either the initialString, or the new string, if it has been updated. */
		return result[0]; // Previously: textArea.getText();
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
	private Color colorPicker(Color initialColor)
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
				return (T) (Double) result.map(Double::parseDouble).orElse((Double) initialValue);
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
