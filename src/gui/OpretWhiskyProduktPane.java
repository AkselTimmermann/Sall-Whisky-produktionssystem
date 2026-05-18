package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.FadIndhold;
import model.ProduktRegistrering;
import model.WhiskyProdukt;

import java.time.LocalDate;
import java.util.ArrayList;

public class OpretWhiskyProduktPane extends BorderPane {

    private final Controller controller;

    private TextField txfNavn;
    private TextField txfProduktNr;
    private DatePicker dpDato;
    private TextField txfFortynding;
    private TextArea txaBeskrivelse;

    private ComboBox<FadIndhold> cmbFadIndhold;
    private TextField txfAntalLiter;
    private TextArea txaFadIndholdInfo;

    private ListView<String> lvwValgteFadIndhold;
    private Label lblSamletLiter;
    private Label lblSamletAlkohol;

    private ArrayList<FadIndhold> valgteFadIndhold = new ArrayList<>();
    private ArrayList<Double> valgteLiter = new ArrayList<>();


    public OpretWhiskyProduktPane (Controller controller) {
        this.controller = controller;
        initContent();
        updateFadIndhold();
        updateOpsummering();
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
        txfNavn = new TextField();
        txfNavn.setPrefWidth(350);
        txfNavn.setPromptText("");

        txfProduktNr = new TextField();
        txfProduktNr.setPrefWidth(350);
        txfProduktNr.setPromptText("F.eks. 1001");

        dpDato = new DatePicker();
        dpDato.setPrefWidth(350);
        dpDato.setValue(LocalDate.now());

        txfFortynding = new TextField();
        txfFortynding.setPrefWidth(350);
        txfFortynding.setPromptText("Liter vand tilsat. Brug 0 ved cask strength");

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

        lvwValgteFadIndhold = new ListView<>();
        lvwValgteFadIndhold.setPrefSize(500, 180);

        lblSamletLiter = new Label();
        lblSamletAlkohol = new Label("Beregnet alkoholprocent: 0.00%");


        // Placerer indhold
        Label lblProduktInfo = new Label("Whiskyprodukt");
        lblProduktInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblProduktInfo, 0, 0, 2, 1);

        pane.add(new Label("Produktnavn"), 0, 1);
        pane.add(txfNavn, 1, 1);

        pane.add(new Label("Produkt nr.:"), 0, 2);
        pane.add(txfProduktNr, 1, 2);


        pane.add(new Label("Dato:"), 0, 3);
        pane.add(dpDato, 1, 3);

        pane.add(new Label("Fortyndeing:"), 0, 4);
        pane.add(txfFortynding, 1, 4);

        pane.add(new Label("Beskrivelse"), 0, 5);
        pane.add(txaBeskrivelse, 1, 5);


        Label lblFadIndhold = new Label("Tilføj fadindhold");
        lblFadIndhold.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblFadIndhold, 0, 7, 2, 1);

        pane.add(new Label("Fadindhold:"), 0, 8);
        pane.add(cmbFadIndhold, 1, 8);

        pane.add(new Label("Fadindhold info:"), 0, 9);
        pane.add(txaFadIndholdInfo, 1, 9);

        pane.add(new Label("Antal liter:"), 0, 10);
        pane.add(txfAntalLiter, 1, 10);

        Button btnTilfoej = new Button("Tilføj fadindhold");
        btnTilfoej.setOnAction(event -> tilfoejFadIndholdAction());
        pane.add(btnTilfoej, 1, 11);


        Label lblValgte = new Label("Valgte fadindhold:");
        lblValgte.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblValgte, 0, 13, 2, 1);

        pane.add(lvwValgteFadIndhold, 1, 14);

        Button btnFjern = new Button("Fjern valgt fadindhold");
        btnFjern.setOnAction(event -> fjernFadIndholdAction());
        pane.add(btnFjern, 1, 15);

        pane.add(lblSamletLiter, 1, 16);
        pane.add(lblSamletAlkohol, 1, 17);

        Button btnOpret = new Button("Opret whiskyprodukt");
        Button btnRyd = new Button("Ryd felter");

        btnOpret.setOnAction(event -> opretWhiskyProduktAction());
        btnRyd.setOnAction(event -> rydFelter());

        HBox buttons = new HBox(15, btnOpret, btnRyd);
        pane.add(buttons, 1, 19);


        return pane;
    }

    private void updateFadIndhold() {
        cmbFadIndhold.getItems().clear();
        cmbFadIndhold.getItems().addAll(controller.getFadIndhold());
    }

    private void fjernFadIndholdAction() {
        int index = lvwValgteFadIndhold.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            visFejl("Vælg et fadindhold der skal fjernes");
            return;
        }

        valgteFadIndhold.remove(index);
        valgteLiter.remove(index);

        updateValgteFadIndholdListe();
        updateOpsummering();
    }

    private void tilfoejFadIndholdAction() {
        FadIndhold fadIndhold = cmbFadIndhold.getValue();

        if (fadIndhold == null) {
            visFejl("Vælg et fadindhold");
            return;
        }
        if (valgteFadIndhold.contains(fadIndhold)) {
            visFejl("Det valgte fadindhold er allerede tilføjet");
            return;
        }

        double antalLiter = 0;
        try {
            antalLiter = Double.parseDouble(txfAntalLiter.getText().trim());
        } catch (IllegalArgumentException e) {
            visFejl(e.getMessage());
            return;
        }

        if (antalLiter > fadIndhold.getResterendeLiter()) {
            visFejl("Der er ikke nok resterede liter på fadet");
            return;
        }

        LocalDate produktDato = dpDato.getValue();
        if (produktDato == null) {
            visFejl("Vælg produktdato før du fortsætter");
            return;
        }

        if (!fadIndhold.isLagretMinimum3Aar(produktDato)) {
            visFejl("Fadinhodlet har ikke lagret i minimum 3 år.");
            return;
        }


        valgteFadIndhold.add(fadIndhold);
        valgteLiter.add(antalLiter);

        updateValgteFadIndholdListe();
        updateOpsummering();

        cmbFadIndhold.getSelectionModel().clearSelection();
        txfAntalLiter.clear();
        updateFadIndholdInfo();
    }


    private void opretWhiskyProduktAction() {
        String navn = txfNavn.getText().trim();
        String beskrivelse = txaBeskrivelse.getText().trim();

        if (navn.isEmpty()) {
            visFejl("Navn skal udfyldes");
            return;
        }

        int produktNr;
        try {
            produktNr = Integer.parseInt(txfProduktNr.getText().trim());
        } catch (NumberFormatException e) {
            visFejl("Produkt nr. skal være et heltal.");
            return;
        }

        LocalDate dato = dpDato.getValue();
        if (dato == null) {
            visFejl("Dato skal vælges.");
            return;
        }

        double fortynding;
        try {
            fortynding = Double.parseDouble(txfFortynding.getText().trim());
        } catch (IllegalArgumentException e) {
            visFejl(e.getMessage());
            return;
        }


        if (beskrivelse.isEmpty()) {
            visFejl("Beskrivelse skal udfyldes");
            return;
        }

        if (valgteFadIndhold.isEmpty()) {
            visFejl("Der skal vælges mindst ét fadindhold.");
            return;
        }

        try {
            WhiskyProdukt whiskyProdukt = controller.createWhiskyProdukt(
                    navn,
                    produktNr,
                    beskrivelse,
                    dato,
                    fortynding,
                    new ArrayList<>(valgteFadIndhold),
                    new ArrayList<>(valgteLiter)
            );

            visInfo("Whiskyprodukt oprettet: " + whiskyProdukt);

            rydFelter();
            updateFadIndhold();

        } catch (Exception e) {
            visFejl(e.getMessage());
        }

    }

    private void updateValgteFadIndholdListe() {
        lvwValgteFadIndhold.getItems().clear();

        for (int i = 0; i < valgteFadIndhold.size(); i++) {
            FadIndhold fadIndhold = valgteFadIndhold.get(i);
            double liter = valgteLiter.get(i);

            String tekst = String.format("%.1f liter fra fad %s", liter, fadIndhold.getFad());

            lvwValgteFadIndhold.getItems().add(tekst);
        }
    }

    private void updateOpsummering() {
        double samletLiter = 0;
        double samletAlkohol = 0;

        for (double liter : valgteLiter) {
            samletLiter += liter;
        }

        lblSamletLiter.setText("Samlet antal liter i whiskyprodukt: "
                + String.format("%.1f", samletLiter));
    }

    private void updateFadIndholdInfo() {
        FadIndhold fadIndhold = cmbFadIndhold.getValue();

        if (fadIndhold == null) {
            txaFadIndholdInfo.setText("Vælg et fadindhold for at se info.");
            return;
        }
        txaFadIndholdInfo.setText("");

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
