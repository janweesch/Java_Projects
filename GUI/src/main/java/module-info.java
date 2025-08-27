module com.example.gui
{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    // ToDoList-Module
    requires ToDo;

    opens com.example.gui to javafx.fxml;
    exports com.example.gui;
}