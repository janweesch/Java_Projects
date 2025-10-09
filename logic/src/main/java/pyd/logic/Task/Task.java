package pyd.logic.Task;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import pyd.logic.UsefulMethods.TimeMethods;

import java.time.LocalDate;
import java.util.ArrayList;


public class Task
{
    private String title; // title of to do list

    private LocalDate startDate; // start of the task

    private LocalDate endDate; // end of the task

    private BooleanProperty done = new SimpleBooleanProperty(false);

    private String[] description; // description of the task

    private String[] notes; // memory to save notes

    private ArrayList<String> steps; // divide tasks in steps

    private boolean important; // define if Task is important

    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     *
     * ATTRIBUTES and METHODS TO ADD:
     * reminder
     * files
     * repeat
     * --> Check the data type and idea how to implement
     *
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */

    public Task (String task)
    {
        title = task;

    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = TimeMethods.setDate(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth());
    }

    public LocalDate getStartDate()
    {
        return this.startDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = TimeMethods.setDate(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth());
    }

    public LocalDate getEndDate()
    {
        return this.endDate;
    }

    public void setDone (boolean status)
    {
        this.done.set(status);
    }

    public BooleanProperty getDone ()
    {
        return this.done;
    }

    public void setDescription(String[] description)
    {
        this.description = description;
    }

    public String[] getDescription()
    {
        return description;
    }

    public void setNotes(String[] notes)
    {
        this.notes = notes;
    }

    public String[] getNotes()
    {
        return notes;
    }

    public void setSteps(ArrayList<String> steps)
    {
        this.steps = steps;
    }

    public ArrayList<String> getSteps()
    {
        return steps;
    }

    public void setImportant(boolean important)
    {
        this.important = important;
    }

    public boolean getImportant()
    {
        return important;
    }

    public String toString()
    {
        return title;
    }

}
