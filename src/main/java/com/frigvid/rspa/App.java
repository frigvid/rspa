package com.frigvid.rspa;

import com.frigvid.rspa.figure.Figure;
import com.frigvid.rspa.figure.Type;
import com.frigvid.rspa.figure.shape.Circle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
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
		BorderPane root = new BorderPane();
		Pane canvas = new Pane();
		//ListView<> sidebar = new ListView<>();
		
		root.setCenter(canvas);
		//root.setRight(sidebar);
		
		Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		stage.setTitle(DEFAULT_TITLE);
		stage.setMinHeight(MINIMUM_HEIGHT);
		stage.setMinWidth(MINIMUM_WIDTH);
		stage.getIcons().add(new Image(DEFAULT_ICON));
		stage.setScene(scene);
		stage.show();
		
		Figure circle = new Figure(Type.CIRCLE, 100, 50, 50);
		canvas.getChildren().add(circle);
		//circle.draw(canvas, circle);
		
		//Circle test = new Circle(100, 50, 50);
		//canvas.getChildren().add(test);
		
		
		// TODO: Extract UI code into its own class and call that to set up the UI instead of FXML.
		// Reason: I'm not familiar enough with FXML to take the time to test and fix the issues I'm having,
		// considering the deadline.
		//FXMLLoader fxml = new FXMLLoader(App.class.getResource("ui/app.fxml"));
		//Scene scene = new Scene(fxml.load(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//stage.setTitle(DEFAULT_TITLE);
		//stage.setMinHeight(MINIMUM_HEIGHT);
		//stage.setMinWidth(MINIMUM_WIDTH);
		//stage.getIcons().add(new Image(DEFAULT_ICON));
		//stage.setScene(scene);
		//stage.show();
	}

	public static void main(String[] args)
	{
		launch();
	}
}