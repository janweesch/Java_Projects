package ToDo;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Interface for modifying Lists.
 */

public interface ListModifier <T>
{
    /**
     * Creates a new List of Type ToDo.ToDo.Task.Task.
     *
     * @return List of Type ToDo.ToDo.Task.Task
     */
    ArrayList<T> createList(); // create List

    /**
     * Sets the Title of the ToDo.ToDoList.
     * @param title The Title of the ToDo.ToDoList
     */
    void setTitle(String title); // set List Title

    /**
     * Gets the ToDo.ToDoList.
     *
     * @throws NullPointerException if ToDo.ToDoList is not available
     * @return Returns the ToDo.ToDoList.
     */
    ArrayList<T> getList() throws NullPointerException; // get List

    /**
     * Adds a ToDo.ToDo.Task.Task to the ToDo.ToDoList.
     * @param task ToDo.ToDo.Task.Task to add to the ToDo.ToDoList.
     */
    void add (T task); // add ToDo.ToDo.Task.Task

    /**
     * Removes a ToDo.ToDo.Task.Task from ToDo.ToDoList.
     * @param task ToDo.ToDo.Task.Task to remove from ToDo.ToDoList.
     * @throws NoSuchElementException if the task is not in ToDo.ToDoList
     */
    void remove (T task) throws NoSuchElementException; // remove ToDo.ToDo.Task.Task

    /**
     * Swaps two Tasks (could be of importance).
     * @param task1 One ToDo.ToDo.Task.Task to swap
     * @param task2 Second ToDo.ToDo.Task.Task to swap with first ToDo.ToDo.Task.Task
     * @throws NullPointerException if one of the tasks is null
     * @throws NoSuchElementException if one of the tasks is not in ToDo.ToDoList
     */
    void swap (T task1, T task2) throws NullPointerException, NoSuchElementException; // swap to Tasks

    /**
     * Deletes first all Tasks from the List, then the ToDo.ToDoList.
     * @throws NullPointerException if ToDo.ToDoList is not available
     */
    void deleteList() throws NullPointerException; // delete List
}
