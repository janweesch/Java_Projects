package pyd.desktop.gui;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class ToDoList extends Application

{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(ToDoList.class.getResource("ToDoListLayout.fxml"));
        stage.setTitle("Plan Your Day!"); // window name

        Scene scene = new Scene(fxmlLoader.load(), 3000, 1000);

       ToDoListController controller = fxmlLoader.getController();
       stage.setOnCloseRequest (event-> {
            try
            {
                controller.shutdown(event);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            } });


        stage.setScene(scene);
        stage.show();
    } // start

    public static void main(String[] args)
    {
        launch();
    }
} // ToDoList

