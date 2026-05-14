package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import storage.Storage;
import storage.StorageInterface;

public class App extends Application{

    public void start(Stage stage) {
        StorageInterface storage = new Storage();
        Controller controller = new Controller(storage);

        InitialData.initData(controller);


        StartVindue root = new StartVindue(controller);

        Scene scene = new Scene(root, 1200, 900);
        stage.setTitle("Sall Whisky Distillery");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {


        launch(args);
    }


}
