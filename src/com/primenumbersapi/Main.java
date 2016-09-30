package com.primenumbersapi;

import com.primenumbersapi.gui.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        View thisView = new View();
        thisView.createView(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
