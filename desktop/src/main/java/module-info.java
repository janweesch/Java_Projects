module pyd.desktop
{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires pyd.logic;

    opens pyd.desktop.gui to javafx.fxml;
    exports pyd.desktop.gui;

}