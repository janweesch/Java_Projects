package pyd.desktop.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


// ToDoList import
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import pyd.logic.Lists.ToDoList;
import pyd.logic.Task.Task;


public class ToDoListController
{
    public VBox vbox;
    // GUI
    @FXML
    private TextField title;
    @FXML
    private ListView<Task> list;



    // ToDoList
    ToDoList toDoList;

    @FXML
    protected void setTitle()
    {
        toDoList = new ToDoList(title.getText());
        Label label = new Label(title.getText());
        label.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20));
        vbox.getChildren().remove(title);
        vbox.getChildren().addFirst(label);
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
            list.getItems().add(task);
            list.setCellFactory(CheckBoxListCell.forListView(Task::getDone));
            textField.clear(); // clear Text Field
        }); // Action Handler
    } // addTask

    public void deleteTask()
    {

    }
}