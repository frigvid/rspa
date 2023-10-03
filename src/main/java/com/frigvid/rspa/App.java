package com.frigvid.rspa;

/* Main boot class. */

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.*;

public class App extends Application
{
    private static final String DEFAULT_TITLE = "RSPA";
    private static final String DEFAULT_FILENAME = "Untitled";
    
    @Override
    public void start(Stage stage) throws IOException
	{
        //FXMLLoader fxmlLoader = new FXMLLoader(Boot.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        //stage.show();
    }
    
    public static void main(String[] args)
    {
        launch();
    }
}