package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ForsidePane extends BorderPane {

    private final Controller controller;

    public ForsidePane(Controller controller) {
        this.controller = controller;
        initContent();
    }

    private void initContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(30, 60, 30, 60));

        Label title = new Label("Forside");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subTitle = new Label("Underoverskrift om hvad der vises");
        subTitle.setStyle("-fx-font-size: 16px;");

        root.getChildren().addAll(title, subTitle);

        this.setCenter(root);
    }
}
