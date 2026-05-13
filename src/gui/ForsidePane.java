package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Lager;

public class ForsidePane extends BorderPane {

    private final Controller controller;

    ListView<String> lvwLagre;
    ListView<String> lvwTommeFade;
    ListView<String> lvwFyldteFade;

    public ForsidePane(Controller controller) {
        this.controller = controller;
        initContent();
    }

    private void initContent() {
        VBox root = new VBox(25);
        root.setPadding(new Insets(30, 60, 30, 60));

        Label title = new Label("Forside");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subTitle = new Label("Midlertidigt overblik");
        subTitle.setStyle("-fx-font-size: 16px;");

        VBox header = new VBox(5, title, subTitle);

        GridPane pane = new GridPane();
        pane.setHgap(25);
        pane.setVgap(15);

        lvwLagre = new ListView<>();
        lvwTommeFade = new ListView<>();
        lvwFyldteFade = new ListView<>();



        Button btnOpdater = new Button("Opdater lister");
        btnOpdater.setOnAction(event -> updateLists());


        root.getChildren().addAll(header, pane, btnOpdater);

        this.setCenter(root);
    }


    private void updateLists() {
        lvwLagre.getItems().clear();
        lvwTommeFade.getItems().clear();
        lvwFyldteFade.getItems().clear();
    }
}
