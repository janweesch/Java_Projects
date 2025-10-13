package pyd.desktop.gui;

import jakarta.json.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pyd.logic.Lists.ToDoList;
import pyd.logic.Task.Task;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
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

    @FXML
    protected void setTitle() {
        toDoList.setTitle(title.getText());
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
            Label delete_Label = new Label("Select the Tasks you want to delete!");
            delete_Label.setAlignment(Pos.CENTER);
            Scene scene2 = new Scene(delete_Label, 600, 600);
            Stage stage = new Stage();
            stage.setScene(scene2);
            stage.show();
        } else {
            ArrayList<Task> tasks_to_delete = new ArrayList<>(list.getSelectionModel().getSelectedItems()); // safe Items as Array List
            for (Task del_task : tasks_to_delete) // iterate and delete
            {
                toDoList.remove(del_task);
            }
        }
    }

    @FXML
    protected void loadToDoList() throws IOException {

        FileChooser fileChooser = new FileChooser(); // open Explorer
        fileChooser.setTitle("Open ToDO List File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File selectedFile =fileChooser.showOpenDialog(list.getScene().getWindow());

        String content = Files.readString(selectedFile.toPath());
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject obj = reader.readObject();
        title.setText(obj.getString("title"));
        List<String> TaskList =  obj.getJsonArray("Tasks").stream().map(JsonValue::asJsonObject).map(o -> o.getString("task")).toList();
        List<Boolean> StatusList =  obj.getJsonArray("Tasks").stream().map(JsonValue::asJsonObject).map(o -> o.getBoolean("status")).toList();

        int index = 0;

        for (Object task : TaskList)
        {
            Task task1 = new Task(task.toString());
            task1.setDone(StatusList.get(index));
            toDoList.add(task1);

            index++;
        }
        reader.close();
    }

    @FXML
    protected void newToDoList()
    {

    }

    @FXML
    public void saveToDoList()
    {


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toDoList = new ToDoList();
        list.setItems(toDoList.getList());
        list.setCellFactory(list -> new CheckBoxListCell<Task>( item -> {
            item.getDone().addListener((obs, oldVal, newVal) -> {list.requestFocus(); Platform.runLater(list::refresh);});
            return item.getDone();})
            {
                public void updateItem(Task item, boolean empty)
                {
                    super.updateItem(item, empty);
                    if (item == null)
                    {
                       setStyle("-fx-background-color: white; -fx-text-fill: black");
                    }
                    else
                    {
                        if (item.getDone().get())
                        {
                            setStyle("-fx-background-color: green; -fx-text-fill: black");
                        }
                    }
                }
            }
        );
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void shutdown() throws IOException
    {
        this.toDoList.WriteJson();
    }


}

