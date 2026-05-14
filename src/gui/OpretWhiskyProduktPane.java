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


        // Placerer indhold
        Label lblProduktInfo = new Label("Whiskyprodukt");
        lblProduktInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblProduktInfo, 0, 0, 2, 1);

        pane.add(new Label("Produkt nr.:"), 0, 1);
        pane.add(txfProduktNr, 1, 1);

        pane.add(new Label("Dato:"), 0, 2);
        pane.add(dpDato, 1, 2);

        pane.add(new Label("Fortyndeing:"), 0, 3);
        pane.add(txfFortynding, 1, 3);

        pane.add(new Label("Beskrivelse"), 0, 4);
        pane.add(txaBeskrivelse, 1, 4);

        Label lblFadIndhold = new Label("Tilføj fadindhold");
        lblFadIndhold.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblFadIndhold, 0, 6, 2, 1);

        pane.add(new Label("Fadindhold:"), 0, 7);
        pane.add(cmbFadIndhold, 1, 7);

        pane.add(new Label("Fadindhold info:"), 0, 8);
        pane.add(txaFadIndholdInfo, 1, 8);

        pane.add(new Label("Antal liter:"), 0, 9);
        pane.add(txfAntalLiter, 1, 9);

        Button btnTilfoej = new Button("Tilføj fadindhold");
        btnTilfoej.setOnAction(event -> tilfoejFadIndholdAction());
        pane.add(btnTilfoej, 1, 10);

        Label lblValgte = new Label("Valgte fadindhold:");
        lblValgte.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblValgte, 0, 12, 2, 1);

        pane.add(lvwValgteFadIndhold, 1, 13);

        Button btnFjern = new Button("Fjern valgt fadindhold");
        btnFjern.setOnAction(event -> fjernFadIndholdAction());
        pane.add(btnFjern, 1, 14);

        pane.add(lblSamletLiter, 1, 15);

        Button btnOpret = new Button("Opret whiskyprodukt");
        Button btnRyd = new Button("Ryd felter");

        btnOpret.setOnAction(event -> opretWhiskyProduktAction());
        btnRyd.setOnAction(event -> rydFelter());

        HBox buttons = new HBox(15, btnOpret, btnRyd);
        pane.add(buttons, 1, 17);


        return pane;
    }

    private void updateFadIndhold() {
        cmbFadIndhold.getItems().clear();
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
        }

        if (antalLiter > fadIndhold.getResterendeLiter()) {
            visFejl("Der er ikke nok resterede liter på fadet");
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

        for (double liter : valgteLiter) {
            samletLiter += liter;
        }

        lblSamletLiter.setText("Samlet antal liter i whiskyprodukt: "
                + String.format("%.1f", samletLiter));
    }

    private void updateFadIndholdInfo() {
        FadIndhold fadIndhold = cmbFadIndhold.getValue();

        txaFadIndholdInfo.setText("");

    }


    private void opretWhiskyProduktAction() {
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
