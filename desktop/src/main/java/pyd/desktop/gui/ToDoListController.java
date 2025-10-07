package pyd.desktop.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

// ToDoList import

import javafx.stage.Stage;
import pyd.logic.Lists.ToDoList;
import pyd.logic.Task.Task;



public class ToDoListController implements Initializable
{
    //@FXML
    public VBox vbox;
    @FXML
    private TextField title;
    @FXML
    private TextField task;
    @FXML
    private ListView<Task> list;


    // ToDoList
    ToDoList toDoList;


    @FXML
    protected void setTitle()
    {
        toDoList.setTitle(title.getText());
    }

    @FXML
    protected void addTask()
    {
        if (task.getText().isEmpty())
        {
            // set a promptText for explanation
            this.task.setPromptText("Put your Task you want to add here!");
        }
        else
        {
            // get Task title and add it to toDoList
            Task task = new Task(this.task.getText());
            toDoList.add(task);
            this.task.clear();
        }
    } // addTask

    @FXML
    protected void setTask()
    {
        // create new Task
        Task task = new Task(this.task.getText());
        toDoList.add(task);
        this.task.clear();
    }

    public void deleteTask()
    {
        if (list.getSelectionModel().getSelectedItems().isEmpty())
        {
            Label delete_Label = new Label("Select the Tasks you want to delete!");
            Scene scene2 = new Scene(delete_Label, 600, 600);
            Stage stage = new Stage();
            stage.setScene(scene2);
            stage.show();
            //this.task.setPromptText("Select the Tasks you want to delete!");
        }
        else
        {
            ArrayList<Task> tasks_to_delete =  new ArrayList<>(list.getSelectionModel().getSelectedItems()); // safe Items as Array List
            for(Task del_task : tasks_to_delete ) // iterate and delete
            {
                toDoList.remove(del_task);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        toDoList = new ToDoList();
        list.setItems(toDoList.getList());
        list.setCellFactory(CheckBoxListCell.forListView(Task::getDone));
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}