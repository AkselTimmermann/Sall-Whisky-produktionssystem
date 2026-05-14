package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.FadIndhold;
import model.ProduktRegistrering;

import java.time.LocalDate;
import java.util.ArrayList;

public class OpretWhiskyProduktPane extends BorderPane {

    private final Controller controller;

    private TextField txfProduktNr;
    private DatePicker dpDato;
    private TextField txfFortynding;
    private TextArea txaBeskrivelse;

    private ComboBox<FadIndhold> cmbFadIndhold;
    private TextField txfAntalLiter;
    private TextArea txaFadIndholdInfo;

    private ListView<String> lvwValgteFadIndhold;
    private Label lblSamletLiter;

    private ArrayList<FadIndhold> valgteFadIndhold = new ArrayList<>();
    private ArrayList<Double> valgteLiter = new ArrayList<>();

    private TextField txfNavn;
    private TextField txfAlkoholProcent;

    private ListView<ProduktRegistrering> lvwProduktRegistreringer;

    private final ArrayList<ProduktRegistrering> produktRegistreringer = new ArrayList<>();


    public OpretWhiskyProduktPane (Controller controller) {
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
        Label title = new Label("Opret whiskyprodukt");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subtitle = new Label("Opret et nyt whiskyprodukt fra en eller flere fadindhold.");
        subtitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subtitle);
    }

    private GridPane createFormat() {
        // Standard pane-opsætning
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30));
        pane.setVgap(15);
        pane.setHgap(15);
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        // Opretter indhold
        txfProduktNr = new TextField();
        txfProduktNr.setPrefWidth(350);
        txfProduktNr.setPromptText("Fx 1001");

        dpDato = new DatePicker();
        dpDato.setPrefWidth(350);
        dpDato.setValue(LocalDate.now());

        txfFortynding = new TextField();
        txfFortynding.setPrefWidth(350);
        txfFortynding.setPromptText("Liter vand tilsat. Brug 0 ved cask strength");



        txfNavn = new TextField();
        txfNavn.setPrefWidth(350);

        txfAlkoholProcent = new TextField();
        txfAlkoholProcent.setPrefWidth(350);

        txaBeskrivelse = new TextArea();
        txaBeskrivelse.setPrefWidth(350);
        txaBeskrivelse.setPrefRowCount(4);
        txaBeskrivelse.setWrapText(true);

        cmbFadIndhold = new ComboBox<>();
        cmbFadIndhold.setPrefWidth(350);
        cmbFadIndhold.setOnAction(event -> updateFadIndholdInfo());

        txfAntalLiter = new TextField();
        txfAntalLiter.setPrefWidth(350);
        txfAntalLiter.setPromptText("Antal liter fra valgt fadindhold");

        txaFadIndholdInfo = new TextArea("Vælg et fadindhold for at se info.");
        txaFadIndholdInfo.setEditable(false);
        txaFadIndholdInfo.setWrapText(true);
        txaFadIndholdInfo.setPrefRowCount(5);
        txaFadIndholdInfo.setPrefWidth(500);

        lvwProduktRegistreringer = new ListView<>();
        lvwProduktRegistreringer.setPrefSize(500, 180);

        lblSamletLiter = new Label();

        // Placerer indhold
        Label lblProduktInfo = new Label("Whiskyprodukt");
        lblProduktInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblProduktInfo, 0, 0, 2, 1);

        pane.add(new Label("Produkt nr.:"), 0, 1);
        pane.add(txfProduktNr, 1, 1);

        pane.add(new Label("Navn:"), 0, 2);
        pane.add(txfNavn, 1, 2);


        return pane;
    }

    private void updateFadIndholdInfo() {
    }



    private void rydFelter() {
        txfProduktNr.clear();


    }

    private void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText(null);
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
