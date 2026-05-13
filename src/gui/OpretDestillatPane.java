package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Destillat;
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
        updateDestilleringer();
    }

    // initContent Opretter en stor VBox og indsætter heri én VBox (Overskrifter) og en GridPane (indholdet)
    private void initContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(30, 60, 30, 60));

        VBox header = createHeader();
        GridPane format = createFormat();


        root.getChildren().addAll(header, format);
        this.setCenter(root);
    }

    // Metode der opretter header-VBox (overskrifter)
    private VBox createHeader() {
        Label title = new Label("Opret destillat");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subtitle = new Label("Sammensæt et destillat ud fra " +
                "én eller flere destilleringer");
        subtitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subtitle);
    }

    // Metode der opretter Gridpane (alt resterende indhold)
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

        lblSamletLiter = new Label("Samlet antal liter: 0");
        lblGennemsnitAlk = new Label("Gennemsnitlig alkoholprocent: 0.00%");

        Label lblInfo = new Label("Destillatoplysninger");
        lblInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblInfo, 0, 0, 2, 1);

        pane.add(new Label("Destillat nr.:"), 0, 1);
        pane.add(txfDestillatNr, 1, 1);

        Label lblDestillering = new Label("Tilføj destillering");
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

    // Metode der kaldes hver gang indholdet i de viste destilleringer skal opdateres
    private void updateDestilleringer() {
        cmbDestillering.getItems().clear();
        cmbDestillering.getItems().addAll(controller.getDestilleringer());
    }





    // setOnAction for "Opret destillat"-knap
    private void opretDestillatAction() {
        String destillatNr = txfDestillatNr.getText().trim();

        if (destillatNr.isEmpty()) {
            visFejl("Destillat nr. skal udfyldes.");
            return;
        }
        if (valgteDestilleringer.isEmpty()) {
            visFejl("Destillatet skal bestå af mindst én destillering.");
            return;
        }

        int[] literArray = new int[valgteLiter.size()];
        for (int i = 0; i < valgteLiter.size(); i++) {
            literArray[i] = valgteLiter.get(i);
        }

        try {
            Destillat destillat = controller.createDestillat(
                    destillatNr,
                    new ArrayList<>(valgteDestilleringer),
                    literArray);


            visInfo("Destillat oprettet: " + destillat.getDestillatNr());

            rydFelter();
            updateDestilleringer();

        } catch (Exception e) {
            visFejl(e.getMessage());
        }
    }

    // setOnAction for "Fjern destillering"-knap
    private void fjernValgtDestilleringAction() {
        int index = lvwValgteDestilleringer.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            visFejl("Vælg en destillering der skal fjernes");
            return;
        }
        valgteDestilleringer.remove(index);
        valgteLiter.remove(index);

        updateValgteListe();
    }

    // setOnAction for "Tilføj destillering"-knap
    private void tilfoejDestilleringAction() {
        Destillering destillering = cmbDestillering.getValue();

        if (destillering == null) {
            visFejl("Vælg en destillering for at tilføje den.");
            return;
        }

        if (valgteDestilleringer.contains(destillering)) {
            visFejl("Denne destillering er allerede tilføjet.");
            return;
        }

        int antalLiter;
        try {
            antalLiter = Integer.parseInt(txfAntalLiter.getText().trim());
        } catch (NumberFormatException e) {
            visFejl("Antal liter skal være et heltal.");
            return;
        }

        if (antalLiter <= 0) {
            visFejl("Antal liter skal være større end 0.");
            return;
        }

        if (antalLiter > destillering.getAntalLiter()) {
            visFejl("Der er ikke nok resterende liter tilbage på den valgte destillering.");
            return;
        }


        valgteDestilleringer.add(destillering);
        valgteLiter.add(antalLiter);

        updateValgteListe();

        cmbDestillering.getSelectionModel().clearSelection();
        txfAntalLiter.clear();

    }

    // Metode der kaldes hver gang valgte destilleringer skal opdateres (fjernes eller tilføjes)
    private void updateValgteListe() {
        lvwValgteDestilleringer.getItems().clear();

        for (int i = 0; i < valgteDestilleringer.size(); i++) {
            Destillering d = valgteDestilleringer.get(i);
            int liter = valgteLiter.get(i);

            String tekst = d.getNewMakeNr() +
                    " (" + liter + " liter" + ", " + d.getAlkoholProcent() + "%)";

            lvwValgteDestilleringer.getItems().add(tekst);
        }
        updateOpsummering();
    }

    // Metode der kaldes hver gang valgte destilleringer skal opdateres, og opsummeringen dermed ændres
    private void updateOpsummering() {
        double samletLiter = 0;
        double samletAlkohol = 0;

        for (int i = 0; i < valgteLiter.size(); i++) {
            double liter = valgteLiter.get(i);
            double alkoholProcent = valgteDestilleringer.get(i).getAlkoholProcent();

            samletLiter += liter;
            samletAlkohol += liter * alkoholProcent;
        }

        double gennemsnitligAlkohol = 0;
        if (samletLiter > 0) {
            gennemsnitligAlkohol = samletAlkohol / samletLiter;
        }
        lblSamletLiter.setText("Samlet antal liter: " + samletLiter);
        lblGennemsnitAlk.setText("Gennemsnitlig alkoholprocent: " +
                String.format("%.2f",gennemsnitligAlkohol) + "%");
    }

    // setOnAction for "Ryd felter"-knap
    private void rydFelter() {
        txfDestillatNr.clear();
        cmbDestillering.getSelectionModel().clearSelection();
        txfAntalLiter.clear();

        valgteDestilleringer.clear();
        valgteLiter.clear();

        updateValgteListe();
    }

    // Metode der kan kaldes til fejlhåndtering
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
        alert.setTitle("Oprettet");
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }

}
