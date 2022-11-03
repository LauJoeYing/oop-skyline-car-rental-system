package oodj.assignment.oopskylinecarrentalsystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class viewSwitcher {


    private static Scene scene;

    public static void setScene(Scene scene) {
        viewSwitcher.scene = scene;
    }

    public static void switchTo(View view) {
        if (scene==null) {
            System.out.println("No ");
            return;
        }

        try {
            Parent root = FXMLLoader.load(
                    viewSwitcher.class.getResource(view.getFileName())
            );

            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

