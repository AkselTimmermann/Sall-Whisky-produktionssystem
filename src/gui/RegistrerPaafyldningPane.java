package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class RegistrerPaafyldningPane extends BorderPane {

    private final Controller controller;

    private ComboBox<Paafyldningsvaeske> cmbDestillat;
    private ComboBox<Medarbejder> cmbMedarbejder;
    private ComboBox<Fad> cmbFad;

    private TextField txfAntalLiter;
    private DatePicker dpDato;

    private TextArea txaDestillatInfo;
    private TextArea txaFadInfo;

    private ListView<String> lvwValgteDestillater;
    private Label lblSamletLiter;

    private final ArrayList<Paafyldningsvaeske> valgteDestillater = new ArrayList<>();
    private final ArrayList<Double> valgteLiter = new ArrayList<>();


    public RegistrerPaafyldningPane(Controller controller) {
        this.controller = controller;
        initContent();
        updateComboBoxes(); //Kaldes hver gang siden åbnes, så comboboxes er opdateret
    }


    // Her placeres indhold på siden, ved at kalde metoder der opretter indhold
    private void initContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(30, 60, 30, 60));

        VBox header = createHeader();
        GridPane format = createFormat();

        root.getChildren().addAll(header, format);
        this.setCenter(root);
    }

    // Metode der indsætter titel og subtitel på siden
    private VBox createHeader() {
        Label title = new Label("Registrer påfyldning");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subtitle = new Label("Vælg et fad for at tilføje destillater.");
        subtitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subtitle);
    }

    // Metode der opretter indhold på siden
    private GridPane createFormat() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30));
        pane.setVgap(15);
        pane.setHgap(15);
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        // Opretter indhold
        cmbFad = new ComboBox<>();
        cmbFad.setPrefWidth(350);

        cmbMedarbejder = new ComboBox<>();
        cmbMedarbejder.setPrefWidth(350);

        cmbDestillat = new ComboBox<>();
        cmbDestillat.setPrefWidth(350);

        dpDato = new DatePicker();
        dpDato.setPrefWidth(350);
        dpDato.setValue(LocalDate.now());

        txfAntalLiter = new TextField();
        txfAntalLiter.setPrefWidth(350);
        txfAntalLiter.setPromptText("Antal liter fra valgt destillat");

        txaFadInfo = new TextArea("Vælg et fad for at se information");
        txaFadInfo.setEditable(false);
        txaFadInfo.setWrapText(true);
        txaFadInfo.setPrefRowCount(5);
        txaFadInfo.setPrefWidth(500);

        txaDestillatInfo = new TextArea("Vælg et destillat for at se information.");
        txaDestillatInfo.setEditable(false);
        txaDestillatInfo.setWrapText(true);
        txaDestillatInfo.setPrefRowCount(5);
        txaDestillatInfo.setPrefWidth(500);

        lvwValgteDestillater = new ListView<>();
        lvwValgteDestillater.setPrefSize(500, 100);

        lblSamletLiter = new Label("Samlet påfyldning: 0.00 liter");


        cmbDestillat.setOnAction(event -> updateDestillatInfo());
        cmbFad.setOnAction(event -> updateFadInfo());


        // Indsætter indhold
        Label lblInfo = new Label("Påfyldningsoplysninger");
        lblInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblInfo, 0, 0, 2, 1);

        pane.add(new Label("Fad:"), 0, 1);
        pane.add(cmbFad, 1, 1);

        pane.add(new Label("Fad info"), 0, 2);
        pane.add(txaFadInfo, 1, 2);

        pane.add(new Label("Medarbejder:"), 0, 3);
        pane.add(cmbMedarbejder, 1 ,3);

        pane.add(new Label("Dato:"), 0, 4);
        pane.add(dpDato, 1, 4);

        Label lblTilfoejDestillat = new Label("Tilføj destillat");
        lblTilfoejDestillat.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblTilfoejDestillat, 0, 6, 2 ,1);

        pane.add(new Label("Destillat:"), 0, 7);
        pane.add(cmbDestillat, 1, 7);

        pane.add(new Label("Destillat info:"), 0, 8);
        pane.add(txaDestillatInfo, 1, 8);

        pane.add(new Label("Antal liter:"), 0, 9);
        pane.add(txfAntalLiter, 1, 9);

        Button btnTilfoej = new Button("Tilføj destillat");
        btnTilfoej.setOnAction(event -> tilfoejDestillatAction());
        pane.add(btnTilfoej, 1, 10);


        Label lblValgte = new Label("Valgte destillater");
        lblValgte.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblValgte, 0, 12, 2, 1);

        pane.add(lvwValgteDestillater, 1, 13);

        Button btnFjern = new Button("Fjern valgt destillat");
        btnFjern.setOnAction(event -> fjernValgtDestillatAction());
        pane.add(btnFjern, 1, 14);

        pane.add(lblSamletLiter, 1, 15);


        Button btnRegistrer = new Button("Registrer påfyldning");
        Button btnRyd = new Button("Ryd felter");

        btnRegistrer.setOnAction(event -> registrerPaafyldningAction());
        btnRyd.setOnAction(event -> rydFelter());

        HBox buttons = new HBox(15, btnRegistrer, btnRyd);
        pane.add(buttons, 1, 17);

        return pane;
    }



    // Metode der opdaterer comboboxes hver gang siden åbnes
    private void updateComboBoxes() {
        cmbDestillat.getItems().clear();
        cmbFad.getItems().clear();
        cmbMedarbejder.getItems().clear();

        cmbDestillat.getItems().addAll(controller.getPaafyldningsvaesker());
        cmbFad.getItems().addAll(controller.getFade());
        cmbMedarbejder.getItems().addAll(controller.getMedarbejdere());
    }


    // Metode der fjerner et destillat fra listen over valgte destillater
    private void fjernValgtDestillatAction() {
        int index = lvwValgteDestillater.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            visFejl("Vælg et destillat der skal fjernes.");
            return;
        }

        valgteDestillater.remove(index);
        valgteLiter.remove(index);

        updateValgteListe();
    }

    // Metode der tilføjer et destillat til listen over valgte destillater
    private void tilfoejDestillatAction() {
        Paafyldningsvaeske destillat = cmbDestillat.getValue();

        if (destillat == null) {
            visFejl("Vælg et destillat.");
            return;
        }

        if (valgteDestillater.contains(destillat)) {
            visFejl("Dette destillat er allerede tilføjet.");
            return;
        }

        double antalLiter;

        try {
            antalLiter = Double.parseDouble(txfAntalLiter.getText().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            visFejl("Antal liter skal være et tal.");
            return;
        }
        if (antalLiter <= 0) {
            visFejl("Antal liter skal være større end 0.");
            return;
        }

        if (antalLiter > destillat.getAntalLiter()) {
            visFejl("Antal liter overstiger mængden i det valgte destillat.");
            return;
        }

        valgteDestillater.add(destillat);
        valgteLiter.add(antalLiter);

        updateValgteListe();

        cmbDestillat.getSelectionModel().clearSelection();
        txfAntalLiter.clear();
        updateDestillatInfo();


    }

    // Metode der registrerer en påfyldning
    private void registrerPaafyldningAction() {
        Fad fad = cmbFad.getValue();
        Medarbejder medarbejder = cmbMedarbejder.getValue();
        LocalDate dato = dpDato.getValue();


        if (fad == null) {
            visFejl("Vælg et fad.");
            return;
        }

        if (medarbejder == null) {
            visFejl("Vælg en medarbejder.");
            return;
        }

        if (dato == null) {
            visFejl("Vælg en dato.");
            return;
        }

        if (valgteDestillater.isEmpty()) {
            visFejl("Tilføj mindst ét destillat.");
            return;
        }

        double samletValgteLiter = beregnSamletValgteLiter();

        double ledigKapacitet = fad.getLedigKapacitet();

        if (samletValgteLiter > ledigKapacitet) {
            visFejl("Der er ikke plads nok på fadet. Ledig kapacitet: " +
                    String.format("%.2f", ledigKapacitet) + " liter.");
            return;
        }


        try {
            for (int i = 0; i < valgteDestillater.size(); i++) {
                Paafyldningsvaeske destillat = valgteDestillater.get(i);
                double liter = valgteLiter.get(i);

                controller.createPaafyldningsRegistrering(liter, dato, destillat, fad, medarbejder);
            }

            visInfo("Påfyldning registreret");
            rydFelter();
            updateComboBoxes();

        } catch (Exception e) {
            visFejl(e.getMessage());
        }

    }

    private double beregnSamletValgteLiter() {
        double samletLiter = 0;

        for (double liter :valgteLiter) {
            samletLiter += liter;
        }
        return samletLiter;
    }

    // Metode der opdaterer listen over valgte destillater
    private void updateValgteListe() {
        lvwValgteDestillater.getItems().clear();

        for (int i = 0; i < valgteDestillater.size(); i++) {
            Paafyldningsvaeske destillat = valgteDestillater.get(i);
            double liter = valgteLiter.get(i);

//            String tekst = destillat.getDestillatNr()
//                    + " (" + liter + " liter, "
//                    + String.format("%.2f", destillat.getAlkoholProcent()) + "%)";
//
//            lvwValgteDestillater.getItems().add(tekst);
        }

        updateOpsummering();
        updateFadInfo();
    }

    // Metode der kaldes hver gang listen af valgte destillater opdateres
    private void updateOpsummering() {
        double samletLiter = 0;

        for (double liter : valgteLiter) {
            samletLiter += liter;
        }
        lblSamletLiter.setText("Samlet påfyldning: " + String.format("%.2f", samletLiter) + " liter");
    }


    private void updateDestillatInfo() {
        Paafyldningsvaeske destillat = cmbDestillat.getValue();

        if (destillat == null) {
            txaDestillatInfo.setText("Vælg et destillat for at se information");
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Består af følgende destilleringer:\n");

//        for (Destillering destillering : destillat.getDestilleringer()) {
//            sb.append("- ").append(destillering.getNewMakeNr())
//                    .append(" | ").append(String.format("%.2f", destillering.getAlkoholProcent())).append("%");
//            sb.append("\n");
//        }
//        txaDestillatInfo.setText(sb.toString());

    }

    private void updateFadInfo() {
        Fad fad = cmbFad.getValue();

        if (fad == null) {
            txaFadInfo.setText("Vælg et fad for at se information.");
            return;
        }

        txaFadInfo.setText("Beskrivelse: " + fad.getBeskrivelse() + "\n" +
                        "Leverandør: " + fad.getLeverandoer() + "\n" +
                        "Ledig kapacitet: " + String.format("%.2f", fad.getLedigKapacitet()));
    }


    private void rydFelter() {
        cmbFad.getSelectionModel().clearSelection();
        cmbMedarbejder.getSelectionModel().clearSelection();
        cmbDestillat.getSelectionModel().clearSelection();

        dpDato.setValue(LocalDate.now());
        txfAntalLiter.clear();

        valgteDestillater.clear();
        valgteLiter.clear();

        updateValgteListe();
        updateDestillatInfo();
        updateFadInfo();
    }

    private  void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }

    // Metode der kaldes når et destillat oprettes
    private void visInfo(String besked) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registreret");
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }
}
