package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.FadIndhold;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class RegistrerModningPane extends BorderPane {

    private final Controller controller;

    public RegistrerModningPane(Controller controller) {
        this.controller = controller;
        initContent();
        updateFadInholdListe();
    }
    Label lblFade = new Label("Fade");
    Label lblAlkoholProcent = new Label("Alkoholprocent:");
    Label lbldato = new Label("Dato:");
    Label lblantalLiter = new Label("Antal liter");
    Label lblTitel = new Label("Titel");
    Label lblNote = new Label("Note");

    private TextField tfAlk = new TextField();
    private DatePicker dpDato = new DatePicker();
    private TextField tfAntalLiter = new TextField();
    private Button btnRegistrer = new Button("Registrer");
    private Button btnRyd = new Button("Ryd felter");
    private TextField tfNote = new TextField();
    private TextField tfTitel = new TextField();
    private ListView<FadIndhold> fadIndholdListView = new ListView<>();
    private void initContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(30, 60, 30, 60));

        VBox header = createHeader();
        GridPane format = createFormat();

        root.getChildren().addAll(header, format);
        this.setCenter(root);
    }

    private VBox createHeader() {
        Label title = new Label("Registrer ...");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subtitle = new Label("Registrer ny modning etc.");
        subtitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subtitle);
    }

    private GridPane createFormat() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30));
        pane.setVgap(15);
        pane.setHgap(15);
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        Label lblLagerInfo = new Label("Modningsoplysninger");
        lblLagerInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblLagerInfo, 0, 0, 2, 1);

        dpDato.setPrefWidth(350);
        tfAlk.setPrefWidth(350);
        tfAntalLiter.setPrefWidth(350);
        tfTitel.setPrefWidth(350);
        tfNote.setPrefWidth(350);
        fadIndholdListView.setPrefWidth(350);
        fadIndholdListView.setPrefHeight(350);

        //Grid 1
        VBox vBoxLw = new VBox(lblFade, fadIndholdListView);
        pane.add(vBoxLw,0,1,1,6);

        //Grid 1
        VBox vbox1 = new VBox(lblTitel,tfTitel);
        pane.add(vbox1,1,1);

        //Grid 2
        VBox vbox2 = new VBox(lblAlkoholProcent, tfAlk);
        pane.add(vbox2,1,2);

        //Grid 3
        VBox vbox3 = new VBox(lblantalLiter, tfAntalLiter);
        pane.add(vbox3,1,3);

        //Grid 4
        VBox vbox4 = new VBox(lbldato, dpDato);
        pane.add(vbox4, 1,4);

        //Grid 5
        VBox vbox5 = new VBox(lblNote, tfNote);
        pane.add(vbox5,1,5);

        //Grid 6
        HBox hbox1 = new HBox(50,btnRegistrer, btnRyd);
        pane.add(hbox1,1,6);



        btnRegistrer.setOnAction(actionEvent -> registrerModningAction());
        btnRyd.setOnAction(actionEvent -> rydFelterAction());

        return pane;
    }

    private void registrerModningAction() {
        try {
            LocalDate dato = dpDato.getValue();
            String alkoholText = tfAlk.getText().trim();
            String antalLiterText = tfAntalLiter.getText().trim();
            String note = tfNote.getText().trim();
            String titel = tfTitel.getText().trim();
            FadIndhold fadIndhold = fadIndholdListView.getSelectionModel().getSelectedItem();

            if (dato == null) {
                visFejl("Vælg dato");
                return;
            }
            if (alkoholText.isEmpty()) {
                visFejl("Indtast alkoholprocent");
                return;
            }
            if (antalLiterText.isEmpty()) {
                visFejl("Indtast antal liter");
                return;
            }
            if (note.isEmpty()) {
                visInfo("Indtast note");
                return;
            }
            if (titel.isEmpty()) {
                visInfo("Indtast titel");
                return;
            }
            if (fadIndhold == null) {
                visFejl("Vælg et fad");
                return;
            }
            double alkohol = Double.parseDouble(alkoholText);
            double antalLiter = Double.parseDouble(antalLiterText);
            if (alkohol <= 0) {
                visFejl("Alkoholprocent skal være større end 0");
                return;
            }
            if (antalLiter <= 0) {
                visFejl("Antal liter skal være større end 0");
                return;
            }

            controller.createModningsRegistrering(alkohol, dato, antalLiter, note, titel, fadIndhold);
            visInfo("Registrering oprettet");

            rydFelterAction();



        } catch (Exception e) {
            visInfo(e.getMessage());
        }
    }

    private void rydFelterAction() {
        dpDato.setValue(null);
        tfAlk.clear();
        tfAntalLiter.clear();
    }

    private void updateFadInholdListe() {
        ArrayList<FadIndhold> fadIndholdsliste = controller.getFadeSorteretEfterModningsDato();

        fadIndholdListView.getItems().setAll(fadIndholdsliste);
    }
    private void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText("Registrering kunne ikke oprettes");
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
