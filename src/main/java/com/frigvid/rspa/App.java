package com.frigvid.rspa;

import com.frigvid.rspa.ui.UI;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
	private UI ui;
	
	@Override
	public void start(Stage stage)
	{
		// Instantiate UI.
		ui = new UI(stage);
		ui.initUI();
	}

	public static void main(String[] args)
	{
		launch();
	}
}