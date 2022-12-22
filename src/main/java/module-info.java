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
    requires java.desktop;

    exports oodj.assignment.oopskylinecarrentalsystem.model;
    opens oodj.assignment.oopskylinecarrentalsystem.model to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem.util;
    opens oodj.assignment.oopskylinecarrentalsystem.util to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem.app;
    opens oodj.assignment.oopskylinecarrentalsystem.app to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem.controller.shared;
    opens oodj.assignment.oopskylinecarrentalsystem.controller.shared to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem.controller.customer;
    opens oodj.assignment.oopskylinecarrentalsystem.controller.customer to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem.controller.admin;
    opens oodj.assignment.oopskylinecarrentalsystem.controller.admin to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem.constant;
    opens oodj.assignment.oopskylinecarrentalsystem.constant to javafx.fxml;
    exports oodj.assignment.oopskylinecarrentalsystem.interfaces;
    opens oodj.assignment.oopskylinecarrentalsystem.interfaces to javafx.fxml;
}