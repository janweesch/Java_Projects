package pyd.desktop.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


import java.io.IOException;


public class ToDoList extends Application

{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(ToDoList.class.getResource("ToDoListLayout.fxml"));
        stage.setTitle("Plan Your Day!"); // window name

        Scene scene = new Scene(fxmlLoader.load(), 600, 600);

        stage.setScene(scene);
        stage.show();
    } // start

    public static void main(String[] args)
    {
        launch();
    }
} // ToDoList

