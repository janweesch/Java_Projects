package pyd.logic.Lists;

import javafx.collections.ObservableList;
import java.util.NoSuchElementException;

/**
 * Interface for modifying Lists.
 */

public interface ListModifier <T>
{
    /**
     * Creates a new List.
     *
     * @return an {@link javafx.collections.ObservableList} containing elements of type {@code T}
     */

    ObservableList<T> createList(); // create List

    /**
     * Sets the Title of the {@code ToDo List}.
     *
     * @param title The Title of the {@code ToDo List}.
     */
    void setTitle(String title); // set List Title

    /**
     * Gets the Title of the {@code ToDo List}.
     *
     * @return Title of the {@code ToDo List}.
     */
    String getTitle();

    /**
     * Gets the {@code ToDo List}.
     *
     * @throws NullPointerException if {@code ToDo List} is not available
     * @return Returns the {@code ToDo List}.
     */

    ObservableList<T> getList() throws NullPointerException; // get List

    /**
     * Adds a {@code Task} to the {@code ToDo List}.
     *
     * @param task{@code Task} to add to the {@code ToDo List}.
     */
    void add (T... task); // add Task

    /**
     * Removes a {@code Task} from {@code ToDo List}.
     *
     * @param task {@code Task} to remove from {@code ToDo List}.
     * @throws NoSuchElementException if the task is not in {@code ToDo List}
     */
    void remove (T task) throws NoSuchElementException; // remove Task

    /**
     * Swaps two Tasks (could be of importance).
     *
     * @param task1 One {@code Task} to swap
     * @param task2 Second {@code Task} to swap with first {@code Task}.
     * @throws NullPointerException if one of the tasks is null
     * @throws NoSuchElementException if one of the tasks is not in {@code ToDo List}
     */
    void swap (T task1, T task2) throws NullPointerException, NoSuchElementException; // swap to Tasks

    /**
     * Deletes first all Tasks from the List, then the {@code ToDo List}.
     * @throws NullPointerException if {@code ToDo List} is not available
     */
    void deleteList() throws NullPointerException; // delete List
}
