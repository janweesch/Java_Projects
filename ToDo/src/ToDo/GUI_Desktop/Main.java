package ToDo.GUI_Desktop;

import ToDo.Task.Task;
import ToDo.ToDoList;

import java.util.Optional;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
         // create new ToDOList
        ToDoList toDoList1 = new ToDoList("Friday");

        // Task
        Task Task1 = new Task("Sport");
        Task Task2 = new Task("Study");
        Task Task3 = new Task("Programming");
        Task Task4 = new Task("Relax");

        // Add Tasks to ToDoList
        toDoList1.add(Task1, Task2, Task3, Task4);
//        toDoList1.add(Task2);
//        toDoList1.add(Task3);
//        toDoList1.add(Task4);

        // Show the result
        toDoList1.getList().stream().forEach(Task -> System.out.println(Task.getTitle()));


        // Swap to Tasks
        toDoList1.swap(Task1, Task1);

        // Show the result
        toDoList1.getList().stream().forEach(Task -> System.out.println(Task.getTitle()));

        // remove Task
        toDoList1.remove(Task1);

        // Show the result
        toDoList1.getList().stream().forEach(Task -> System.out.println(Task.getTitle()));

        // delete Task
        toDoList1.deleteList();

        // Show the result
        //toDoList1.getList().stream().forEach(Task -> System.out.println(Task.getTitle()));

        System.out.println(toDoList1.getTitle());


    }
}
