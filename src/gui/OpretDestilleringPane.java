package gui;

import com.sun.javafx.scene.control.DoubleField;
import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class OpretDestilleringPane extends BorderPane {

    private final Controller controller;


    private TextField txfNewMakeNr = new TextField();
    private TextField txfKornSort = new TextField();
    private TextField txfRygemateriale = new TextField();
    private TextField txfKommentar = new TextField();

    private DoubleField dfMaengdeVaeske = new DoubleField();
    private DoubleField dfAlkoholProcent = new DoubleField();

    private ComboBox maltBatchCb = new ComboBox();
    private ComboBox medarbejderCb = new ComboBox<>();

    private DatePicker dpStartDato = new DatePicker();
    private DatePicker dpSlutDato = new DatePicker();

    public OpretDestilleringPane(Controller controller) {
        this.controller = controller;
        initContent();
    }

    private void initContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(30, 60, 30, 60));

        VBox header = createHeader();
        GridPane format = createFormat();

        root.getChildren().addAll(header, format);
        this.setCenter(root);
    }

    private VBox createHeader() {

        Label title = new Label("Opret Destillering");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subTitle = new Label("Opret ny Destillering");
        subTitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subTitle);
    }

    private GridPane createFormat() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30));
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        return pane;

    }

    private void opretDestilleringAction() {

    }

    private void rydFelterAction() {

    }
    private void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText("Destillering kunne ikke oprettes");
        alert.setContentText(besked);
        alert.showAndWait();
    }

    private void visInfo(String besked) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Oprettet");
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }
}
