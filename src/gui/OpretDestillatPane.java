package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Destillering;

import java.util.ArrayList;

public class OpretDestillatPane extends BorderPane {
    private final Controller controller;

    private TextField txfDestillatNr;
    private ComboBox<Destillering> cmbDestillering;
    private TextField txfAntalLiter;

    private ListView<String> lvwValgteDestilleringer;
    private Label lblSamletLiter;
    private Label lblGennemsnitAlk;

    private final ArrayList<Destillering> valgteDestilleringer = new ArrayList<>();
    private final ArrayList<Integer> valgteLiter = new ArrayList<>();

    public OpretDestillatPane(Controller controller) {
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
        Label title = new Label("Opret destillat");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subtitle = new Label("Sammensæt et destillat ud fra " +
                "én eller flere destilleringer");
        subtitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subtitle);
    }

    private GridPane createFormat() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30));
        pane.setVgap(15);
        pane.setHgap(15);
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        txfDestillatNr = new TextField();
        txfDestillatNr.setPrefWidth(350);

        cmbDestillering = new ComboBox<>();
        cmbDestillering.setPrefWidth(350);

        txfAntalLiter = new TextField();
        txfAntalLiter.setPrefWidth(350);
        txfAntalLiter.setPromptText("Antal liter fra valgt destillering");

        lvwValgteDestilleringer = new ListView<>();
        lvwValgteDestilleringer.setPrefSize(500, 180);

        lblSamletLiter = new Label("Samlet antal liter:");
        lblGennemsnitAlk = new Label("Gennemsnitlig alkoholprocent:");

        Label lblInfo = new Label("Destillatoplysninger");
        lblInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblInfo, 0, 0, 2, 1);

        pane.add(new Label("Destillat nr.:"), 0, 1);
        pane.add(txfDestillatNr, 1, 1);

        Label lblDestillering = new Label("Tilføje destillering");
        lblDestillering.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblDestillering, 0, 3, 2, 1);

        pane.add(new Label("Destillering:"), 0, 4);
        pane.add(cmbDestillering, 1, 4);

        pane.add(new Label("Antal liter:"), 0, 5);
        pane.add(txfAntalLiter, 1, 5);

        Button btnTilfoejDestillering = new Button("Tilføj destillering");
        btnTilfoejDestillering.setOnAction(event -> tilfoejDestilleringAction());
        pane.add(btnTilfoejDestillering, 1, 6);

        Label lblValgte = new Label("Valgte destilleringer");
        lblValgte.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblValgte, 0, 7, 2, 1);

        pane.add(lvwValgteDestilleringer,1, 8);

        Button btnFjernValgt = new Button("Fjern valgt destillering");
        btnFjernValgt.setOnAction(event -> fjernValgtDestilleringAction());
        pane.add(btnFjernValgt, 1, 9);

        pane.add(lblSamletLiter, 1, 10);
        pane.add(lblGennemsnitAlk, 1, 11);

        Button btnOpretDestillat = new Button("Opret destillat");
        Button btnRyd = new Button("Ryd felter");

        btnOpretDestillat.setOnAction(event -> opretDestillatAction());
        btnRyd.setOnAction(event -> rydFelter());

        HBox buttons = new HBox(15, btnOpretDestillat, btnRyd);
        pane.add(buttons, 1, 12);

        return pane;
    }

    private void updateDestilleringer() {
        cmbDestillering.getItems().clear();
        cmbDestillering.getItems().addAll();

    }


    private void rydFelter() {
        txfDestillatNr.clear();
        cmbDestillering.getSelectionModel().clearSelection();
        txfAntalLiter.clear();

        valgteDestilleringer.clear();
        valgteLiter.clear();

    }

    private void opretDestillatAction() {
    }

    private void fjernValgtDestilleringAction() {
    }

    private void tilfoejDestilleringAction() {
    }

    private  void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText("Destillat kunne ikke oprettes");
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
