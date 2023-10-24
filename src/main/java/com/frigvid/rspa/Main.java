package com.frigvid.rspa;

import com.frigvid.rspa.ui.App;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main
		extends Application
{
	@Override
	public void start(Stage stage)
	{
		// Instantiate and create the UI.
		App ui = new App(stage);
		ui.initialize();
	}

	public static void main(String[] args)
	{
		launch();
	}
}