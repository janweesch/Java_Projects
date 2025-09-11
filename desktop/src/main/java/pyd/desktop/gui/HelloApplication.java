package pyd.desktop.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.io.IOException;


public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage.setTitle("Plan Your Day!"); // window name

        TextField textField_title = new TextField(); // create TextField
        Pane r = new Pane(); // create a pane (layout)
        textField_title.setLayoutX(1); // position X
        textField_title.setLayoutY(1); // position Y

        r.getChildren().add(textField_title); // add textField_title node to rootNode

        //Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        Scene scene = new Scene(r, 600, 600);
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args)
    {
        launch();
    }
}