package pyd.desktop.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


import java.io.IOException;


public class ToDoList extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(ToDoList.class.getResource("ToDoListLayout.fxml"));
        stage.setTitle("Plan Your Day!"); // window name

//        TextField textField_title = new TextField(); // create TextField
//        Pane r = new Pane(); // create a pane (layout)
//        textField_title.setLayoutX(1); // position X
//        textField_title.setLayoutY(1); // position Y
//
//        r.getChildren().add(textField_title); // add textField_title node to rootNode

        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        //Scene scene = new Scene(r, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("HBox Experiment 1");
//
//        TextField textField = new TextField();
//
//        Button button = new Button("Click to get text");
//
//        button.setOnAction(action -> {
//            System.out.println(textField.getText());
//        });
//
//        HBox hbox = new HBox(textField, button);
//
//        Scene scene = new Scene(hbox, 200, 100);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//    }



    public static void main(String[] args)
    {
        launch();
    }
}