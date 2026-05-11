package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class OpretDestillatPane extends BorderPane {
    private final Controller controller;

    public OpretDestillatPane(Controller controller) {
        this.controller = controller;
        initContent();
    }

    private void initContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(30, 60, 30, 60));

        Label title = new Label("Opret destillat");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subtitle = new Label();

        root.getChildren().addAll(title, subtitle);
        this.setCenter(root);
    }
}
