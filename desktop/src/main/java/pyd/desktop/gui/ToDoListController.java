package pyd.desktop.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ToDoListController
{
    @FXML
    private TextField title;
    @FXML
    private Label enter;

    @FXML
    protected void getText() {
        System.out.println(title.getText());
    }
}