package pyd.desktop.gui;

import jakarta.json.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

import javafx.stage.WindowEvent;
import pyd.logic.Lists.ToDoList;
import pyd.logic.Task.Task;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ToDoListController implements Initializable {
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

    // Dragged Task
    Task draggedTask;

    // JSON Handle
    ToDoList.SaveAndLoadFiles handle = new ToDoList.SaveAndLoadFiles();

    @FXML
    protected void setTitle() {

        toDoList.setTitle(title.getText());
        task.requestFocus();
    }

    @FXML
    protected void addTask() {
        if (task.getText().isEmpty()) {
            // set a promptText for explanation
            this.task.setPromptText("Put your Task you want to add here!");
        } else {
            // get Task title and add it to toDoList
            Task task = new Task(this.task.getText());
            toDoList.add(task);
            this.task.clear();
        }
    } // addTask

    @FXML
    protected void setTask() {
        // create new Task
        Task task = new Task(this.task.getText());
        toDoList.add(task);
        this.task.clear();
    }

    public void deleteTask() {
        if (list.getSelectionModel().getSelectedItems().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Select the Tasks you want to delete!");
            alert.showAndWait();

        }
        else {
            ArrayList<Task> tasks_to_delete = new ArrayList<>(list.getSelectionModel().getSelectedItems()); // safe Items as Array List
            for (Task del_task : tasks_to_delete) // iterate and delete
            {
                toDoList.remove(del_task);
            }
            list.getSelectionModel().clearSelection(); // clear Selections, so that you can't spam the delet button!
        }
    }

    @FXML
    protected void loadToDoList() throws IOException
    {
        File file = handle.LoadJson(); // choose JSON File from Explorer

        ToDoList.SaveAndLoadFiles.TitleAndToDoList result = handle.readFromJson(file, toDoList); // read JSON File to ToDo List

        title.setText(result.title()); // extract Title

        toDoList = result.toDoList(); // set ToDO list
    }

    @FXML
    protected void newToDoList()
    {
        // clear all Inputs and Tasks from ToDO List
        title.clear();
        task.clear();
        list.getItems().clear();
    }

    @FXML
    public void saveToDoList() throws IOException {
        if (title.getText().isEmpty() && !list.getItems().isEmpty())
        {
            // show an Alert message if ToDo List can not be saved
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please give your ToDoList a name!");
            alert.showAndWait(); // wait until click Button OK on Window
        }
        else if (!title.getText().isEmpty())
        {
            toDoList.setTitle(title.getText());
            handle.writeToJson(toDoList);
        }
    }

    public void saveToDoListOnShutdown(WindowEvent event) throws IOException {
        if (title.getText().isEmpty() && !list.getItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please give your ToDoList a name!");
            if (alert.showAndWait().get() == ButtonType.OK);
            {
                event.consume();
            }
        }
        else if (!title.getText().isEmpty())
        {
            toDoList.setTitle(title.getText());
            handle.writeToJson(toDoList);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toDoList = new ToDoList();
        list.setItems(toDoList.getList());
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); // enable multi selections

        list.setCellFactory(list -> new CheckBoxListCell<Task>( item -> {

            item.getDone().addListener((obs, oldVal, newVal) -> {Platform.runLater(list::refresh);});
            return item.getDone();})
            {
                {
                    setOnDragDetected(event -> // starts if Drag is detected
                    { // check if selected Item is null
                        if (getItem() == null)
                        {
                            return;
                        };

                    ObservableList<Task> items = getListView().getItems();

                    Dragboard dragboard = startDragAndDrop(TransferMode.MOVE); // specific Clipboard
                    ClipboardContent content = new ClipboardContent();

                    draggedTask = getItem(); // save dragged Task

                    content.putString(getItem().getTitle()); // put String on ClipboardConten
                    dragboard.setContent(content); // store Data Format
                    event.consume();
                    } );


                    setOnDragOver(event -> { // continue moving the Node
                        if (event.getGestureSource() != this && event.getDragboard().hasString())
                        {
                            event.acceptTransferModes(TransferMode.MOVE);
                        }
                        event.consume();
                    });

                    setOnDragEntered(event -> { // make the Node transparent,  if dragged Node enters
                        if (event.getGestureSource() != this && event.getDragboard().hasString())
                        {
                            setOpacity(0.3);
                        }
                    });

                    setOnDragExited(event -> { // delete transparency, if dragged Node leaves
                        if (event.getGestureSource() != this && event.getDragboard().hasString())
                        {
                            setOpacity(1);
                        }
                    });

                    // drop the DraggedItem
                    setOnDragDropped(event -> {
                        if (getItem() == null)
                        {
                            return;
                        }

                        Dragboard db = event.getDragboard(); // get the Dragboard
                        boolean success = false;

                        if (db.hasString())
                        {
                            ObservableList<Task> items = getListView().getItems();
                            List<String> itemsString =items.stream().map(Task::getTitle).toList(); // convert ObservableList<Task> to String

                            int draggedIdx = itemsString.indexOf(db.getString()); // get Index of dragged Object
                            int thisIdx = items.indexOf(getItem()); // get Index of current selected Item

                            if (draggedTask.getDone().get() != getItem().getDone().get() )
                            {
                                this.setStyle(""); // set default style of ListCell
                                Platform.runLater(list::refresh); // refresh the ListCell
                                list.getSelectionModel().clearSelection(); // clear the Selections

                            }

                            // swap the two Items
                            items.set(draggedIdx, getItem());
                            items.set(thisIdx, draggedTask);

                            success = true;
                        }
                        event.setDropCompleted(success);

                        event.consume();
                    });
                    setStyle("");
                    setOnDragDone(DragEvent::consume);

                }


                public void updateItem(Task item, boolean empty)
                {
                    super.updateItem(item, empty);
                    if (item == null)
                    {
                        setStyle(""); // set Default style
                    }
                    else
                    {
                        if (item.getDone().get())
                        {
                            setStyle("-fx-background-color: green; -fx-text-fill: black"); // Checked list Cells are Green!
                        }
                    }
                }
            }
        );

    }

    public void shutdown(WindowEvent event) throws IOException
    {
        saveToDoListOnShutdown(event);
    }
}

