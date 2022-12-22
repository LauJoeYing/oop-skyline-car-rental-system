package oodj.assignment.oopskylinecarrentalsystem.controller.shared;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oodj.assignment.oopskylinecarrentalsystem.model.Admin;
import oodj.assignment.oopskylinecarrentalsystem.model.User;

import java.io.IOException;

public class CommonViewController {
    private User user;
    private Object object;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void switchLabelledUserSceneWithObject(Event event, String fxmlFile, Object object) throws IOException {
        LabelledViewController labelledController = (LabelledViewController) switchUserScene(event, fxmlFile);
        labelledController.setObject(object);
        labelledController.setLabelData();
    }

    public void switchLabelledUserScene(Event event, String fxmlFile) throws IOException {
        LabelledViewController labelledController = (LabelledViewController) switchUserScene(event, fxmlFile);
        labelledController.setLabelData();
    }

    public CommonViewController switchUserScene(Event event, String fxmlFile) throws IOException {
        CommonViewController newSceneController = switchScene(event, String.format("%s/%s", getDirectoryPrefix(user), fxmlFile)).getController();
        newSceneController.setUser(user);
        return newSceneController;
    }

    public void switchSharedScene(Event event, String fxmlFile) throws IOException {
        switchScene(event, String.format("shared/%s", fxmlFile));
    }

    private FXMLLoader switchScene(Event event, String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                CommonViewController
                        .class
                        .getResource(String.format("/oodj/assignment/oopskylinecarrentalsystem/view/%s.fxml", fxmlFile))
        );

        Scene newScene = new Scene(fxmlLoader.load());
        Stage newStage = new Stage();
        newStage.setTitle("Skyline Car Rental");
        newStage.setScene(newScene);

        Node node = (Node) event.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();

        newStage.show();

        return fxmlLoader;
    }

    private String getDirectoryPrefix(User user) {
        if (user instanceof Admin) {
            return "admin";
        } else {
            return "customer";
        }
    }
}
