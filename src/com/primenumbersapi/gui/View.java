package com.primenumbersapi.gui;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

import static java.awt.SystemColor.menu;

@SuppressWarnings("WeakerAccess")
public class View {

    public void createView(Stage primaryStage) throws Exception{

        VBox root = new VBox();

        MenuBar menuBar = new MenuBar();
        createMenuBar(menuBar);

        root.getChildren().addAll(menuBar);
        primaryStage.setTitle("Prime Numbers");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }

    private void createMenuBar(MenuBar menuBar){

        // --- Menu File
        Menu menuFile = new Menu("File");

        MenuItem New = new MenuItem("New");
        menuFile.getItems().addAll(New);

        // --- Menu Edit
        Menu menuEdit = new Menu("Edit");

        // --- Menu View
        Menu menuView = new Menu("View");

        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);

    }

}
