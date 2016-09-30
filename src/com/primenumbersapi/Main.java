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
        // Example code showing how to initialise the database manager.
        //String path = new File("src\\resources\\primes.db").getAbsolutePath();
        //DatabaseManager databaseManager = new DatabaseManager("jdbc:sqlite:" + path, "primes");
        launch(args);
    }
}
