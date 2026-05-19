package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import storage.Storage;
import storage.StorageInterface;

public class App extends Application{

    public void start(Stage stage) {
        StorageInterface storage = new Storage();
        Controller controller = new Controller(storage);

        InitialData.initData(controller);

        StartVindue root = new StartVindue(controller);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        double width = screenBounds.getWidth() * 0.9;
        double height = screenBounds.getHeight() * 0.9;


        Scene scene = new Scene(root, width, height);

        stage.setTitle("Sall Whisky Distillery");
        stage.setScene(scene);

        stage.setMinWidth(1000);
        stage.setMinHeight(700);
        stage.show();

    }


    public static void main(String[] args) {


        launch(args);
    }


}
