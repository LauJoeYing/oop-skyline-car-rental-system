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

    requires org.apache.commons.io;
    requires commons.lang;
    requires java.dotenv;
    requires java.mail;

    exports oodj.assignment.oopskylinecarrentalsystem.model;
    opens oodj.assignment.oopskylinecarrentalsystem.model to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem.config;
    opens oodj.assignment.oopskylinecarrentalsystem.config to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem.app;
    opens oodj.assignment.oopskylinecarrentalsystem.app to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem.controller.shared;
    opens oodj.assignment.oopskylinecarrentalsystem.controller.shared to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem.controller.customer;
    opens oodj.assignment.oopskylinecarrentalsystem.controller.customer to javafx.fxml;
}