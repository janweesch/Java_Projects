package pyd.logic.Lists;

import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import pyd.logic.Task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.*;

import javafx.collections.FXCollections;


/**
 * A {@code ToDo List} for modifying different tasks.
 * <p>
 * Implements the {@link ListModifier} interface.
 */
public class ToDoList implements ListModifier<Task> {
    private String title; // Title of the ToDO List

    private ObservableList<Task> ToDO; // TODo List

    /**
     * Creates a new ToDo List
     *
     * @param title Title of the pyd.logic.Lists.ToDoList.
     */
    public ToDoList(String title) {
        setTitle(title);
        this.ToDO = createList();
    }

    public ToDoList() {
        this.ToDO = createList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableList<Task> createList() {
        //return new ArrayList<>(); // creates new empty ArrayList
        return FXCollections.observableArrayList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTitle(String title) {
        this.title = title; // changes the Title
    }

    /**
     * {@inheritDoc}
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableList<Task> getList() throws NullPointerException {
        if (this.ToDO == null) {
            throw new NullPointerException("To Do list is not available");
        }
        return this.ToDO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Task... tasks) {
        // adds a ToDo.ToDo.Task.Task
        this.ToDO.addAll(Arrays.asList(tasks));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Task task) throws NoSuchElementException {
        if (!this.ToDO.remove(task)) {
            throw new NoSuchElementException("Task not in To Do List!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void swap(Task task1, Task task2) throws NullPointerException, NoSuchElementException {
        if (task1 == null || task2 == null) {
            throw new NullPointerException("'Tasks must not be null.");
        }
        int temp_1_index = this.ToDO.indexOf(task1); // save index of Task1
        int temp_2_index = this.ToDO.indexOf(task2); // save index of Task2

        if (temp_1_index == -1) {
            throw new NoSuchElementException("com/Task " + task1 + " not in To Do list");
        }

        if (temp_2_index == -1) {
            throw new NoSuchElementException("com/Task " + task2 + " not in To Do list");
        }

        this.ToDO.set(temp_2_index, task1); // insert Task1 at position of Task2
        this.ToDO.set(temp_1_index, task2); // insert Task2 at position of Task1

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteList() throws NullPointerException {
        if (this.ToDO == null) {
            throw new NullPointerException("To Do list is not available");
        }
        this.ToDO.clear(); // removes all Objects from the List
        this.ToDO = null; // deletes the List
    }

    /**
     * JSON-File
     */

    public void WriteJson() throws IOException
    {
        FileWriter fileWriter = new FileWriter(this.title + ".json"); // create FileWriter and specify file name

        // activate PRETTY PRINTING
        Map<String, Boolean> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, false);

        JsonWriterFactory jf = Json.createWriterFactory(properties);
        JsonWriter jsonObjectWriter = jf.createWriter(fileWriter); // create writer for json Object

        JsonObjectBuilder jsonObjectBUilder = Json.createObjectBuilder().add("title", this.title); // Create json ObjectBuilder and add ToDo List title
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder(); // Create json ArrayBuilder
        for (Task task : ToDO) // iterate over ToDO List
        {
            JsonObject jsonObject1 = Json.createObjectBuilder().add("task", task.getTitle()).add("status", task.getDone().get()).build(); // add tasks
            jsonArrayBuilder.add(jsonObject1);
        }

        // write to JSON File
        jsonObjectBUilder.add("Tasks", jsonArrayBuilder);
        jsonObjectWriter.writeObject(jsonObjectBUilder.build());
        jsonObjectWriter.close();
    }


    /**
     * Class to Save and Load {@code ToDoList} as JSON File.
     */

    public static class SaveAndLoadFiles
    {

        public record TitleAndToDoList(String title, ToDoList toDoList) {}

        /**
         * Writes {@code ToDo List} in a JSON File.
         *
         * @param toDoList with {@code Task} defined in a  {@code ListView} for example.
         * @throws IOException if it is not possible to write the JSON File
         */
        public void writeToJson(ToDoList toDoList) throws IOException {

            FileWriter fileWriter = new FileWriter(toDoList.getTitle() + ".json"); // create FileWriter and specify file name

            // activate PRETTY PRINTING
            Map<String, Boolean> properties = new HashMap<>(1);
            properties.put(JsonGenerator.PRETTY_PRINTING, false);

            JsonWriterFactory jf = Json.createWriterFactory(properties);
            JsonWriter jsonObjectWriter = jf.createWriter(fileWriter); // create writer for json Object

            JsonObjectBuilder jsonObjectBUilder = Json.createObjectBuilder().add("title", toDoList.getTitle()); // Create json ObjectBuilder and add ToDo List title
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder(); // Create json ArrayBuilder
            for (Task task : toDoList.getList()) // iterate over ToDO List
            {
                JsonObject jsonObject1 = Json.createObjectBuilder().add("task", task.getTitle()).add("status", task.getDone().get()).build(); // add tasks
                jsonArrayBuilder.add(jsonObject1);
            }

            // write to JSON File
            jsonObjectBUilder.add("Tasks", jsonArrayBuilder);
            jsonObjectWriter.writeObject(jsonObjectBUilder.build());
            jsonObjectWriter.close();
        }


        /**
         * Opens the Explorer to select a JSON File.
         *
         * @return the JSON File
         */
        public File LoadJson()
        {
            FileChooser fileChooser = new FileChooser(); // open Explorer
            fileChooser.setTitle("Select ToDO List File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
            return fileChooser.showOpenDialog(Window.getWindows().getFirst());
        }

        public TitleAndToDoList readFromJson(File file, ToDoList toDoList) throws IOException
        {
            String content = Files.readString(file.toPath());
            JsonReader reader = Json.createReader(new StringReader(content));
            JsonObject obj = reader.readObject();

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

            return new TitleAndToDoList(obj.getString("title"), toDoList );
        }
    }
}
