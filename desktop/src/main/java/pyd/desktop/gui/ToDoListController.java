package pyd.desktop.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


// ToDoList import
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import pyd.logic.Lists.ToDoList;
import pyd.logic.Task.Task;

import java.net.URL;
import java.util.ResourceBundle;


public class ToDoListController implements Initializable
{
    //@FXML
    public VBox vbox;
    @FXML
    private TextField title;
    @FXML
    private ListView<Task> list;

    // ToDoList
    ToDoList toDoList;


    @FXML
    protected void setTitle()
    {
        //toDoList = new ToDoList(title.getText());
        toDoList.setTitle(title.getText());
    }


    @FXML
    protected void sort()
    {

    }

    @FXML
    protected void sortTasks()
    {

    }

    @FXML
    protected void addTask()
    {
         // Text field
        TextField textField = new TextField(); // create Text field
        textField.setPromptText("Put your Task here...");
        vbox.getChildren().add(2, textField); // show Text field
        textField.requestFocus(); // request Focus
        textField.setOnAction(e ->
        {
            Task task = new Task(textField.getText()); // create new Task
            toDoList.add(task); // add -> toDoList
            textField.clear(); // clear Text Field
            vbox.getChildren().remove(2);
        }); // Action Handler

    } // addTask

    public void deleteTask()
    {



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        toDoList = new ToDoList();
        list.setItems(toDoList.getList());
        list.setCellFactory(CheckBoxListCell.forListView(Task::getDone));
    }
}