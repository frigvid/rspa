package com.frigvid.rspa.ui;

import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
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
