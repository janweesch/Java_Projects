module pyd.desktop
{
    requires javafx.controls;
    requires javafx.fxml;

    requires jakarta.json;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires pyd.logic;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;

    opens pyd.desktop.gui to javafx.fxml;
    exports pyd.desktop.gui;

}