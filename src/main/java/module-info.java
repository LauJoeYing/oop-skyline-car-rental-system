module oodj.assignment.oopskylinecarrentalsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens oodj.assignment.oopskylinecarrentalsystem to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem;
}