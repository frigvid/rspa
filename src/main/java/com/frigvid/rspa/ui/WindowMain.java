package com.frigvid.rspa.ui;

import com.frigvid.rspa.figure.shape.Circle;
import com.frigvid.rspa.figure.shape.Line;
import com.frigvid.rspa.figure.shape.Rectangle;
import com.frigvid.rspa.figure.shape.Text;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class WindowMain extends GUI {
	
	public WindowMain(Stage stage) {
		super(stage);
	}
	
	@Override
	public void initUI() {
		windowMain();
	}
	
	private void windowMain()
	{
		root = new BorderPane();
		canvas = new Pane();
		//ListView<> sidebar = new ListView<>();
		
		root.setCenter(canvas);
		//root.setRight(sidebar);
		
		// Setting background color to differentiate which is which.
		canvas.setStyle("-fx-background-color: #7e1e1e;");
		//sidebar.setStyle("-fx-background-color: #15e18c;");
		
		Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		stage.setTitle(DEFAULT_TITLE);
		stage.setMinHeight(MINIMUM_HEIGHT);
		stage.setMinWidth(MINIMUM_WIDTH);
		stage.getIcons().add(new Image(DEFAULT_ICON));
		stage.setScene(scene);
		stage.show();
	}
	
	private void canvas(BorderPane borderPane, Pane canvas)
	{
	
	}
	
	private void sidebar()
	{
	
	}
}
