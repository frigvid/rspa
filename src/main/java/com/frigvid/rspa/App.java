package com.frigvid.rspa;

import com.frigvid.rspa.ui.WindowMain;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
	private UI ui;
	
	@Override
	public void start(Stage stage)
	{
		// Instantiate UI.
		//ui = new UI(stage);
		//ui.initUI();
		
		WindowMain mainUI = new WindowMain(stage);
		mainUI.initUI();
	}

	public static void main(String[] args)
	{
		launch();
	}
}