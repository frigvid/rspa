package com.frigvid.rspa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application
{
	private static final String DEFAULT_TITLE = "RSPA";
	private static final String DEFAULT_ICON = String.valueOf(App.class.getResource("icon/logo.png"));
	// private static final String DEFAULT_FILENAME = "Untitled";
	private static final double DEFAULT_WIDTH = 1000.0;
	private static final double DEFAULT_HEIGHT = 500.0;
	private static final double MINIMUM_WIDTH = 500.0;
	private static final double MINIMUM_HEIGHT = 300.0;

	@Override
	public void start(Stage stage) throws IOException
	{
		FXMLLoader fxml = new FXMLLoader(App.class.getResource("ui/app.fxml"));
		Scene scene = new Scene(fxml.load(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
		stage.setTitle(DEFAULT_TITLE);
		stage.setMinHeight(MINIMUM_HEIGHT);
		stage.setMinWidth(MINIMUM_WIDTH);
		stage.getIcons().add(new Image(DEFAULT_ICON));
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args)
	{
		launch();
	}
}