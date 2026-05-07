package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Hylde;
import model.Lager;
import model.LagerPlads;
import model.Reol;

import static java.lang.Integer.parseInt;

public class OpretLagerPane extends BorderPane {

    private final Controller controller;

    private TextField txfNavn;
    private TextField txfLokation;
    private TextField txfStoerrelse;

    private TextField txfAntalReoler;
    private TextField txfAntalHylder;
    private TextField txfPladserPrHylde;

    public OpretLagerPane(Controller controller) {
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

        Label title = new Label("Opret lager");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subTitle = new Label("Opret et nyt lager");
        subTitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subTitle);
    }

    private GridPane createFormat() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30));
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        txfNavn = new TextField();
        txfLokation = new TextField();
        txfStoerrelse = new TextField();

        txfAntalReoler = new TextField();
        txfAntalHylder = new TextField();
        txfPladserPrHylde = new TextField();

        txfNavn.setPrefWidth(350);
        txfLokation.setPrefWidth(350);
        txfStoerrelse.setPrefWidth(350);

        int row = 0;

        Label lblLagerInfo = new Label("Lageroplysninger");
        lblLagerInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblLagerInfo, 0, 0, 2, 1);

        pane.add(new Label("Navn:"), 0, 1);
        pane.add(txfNavn, 1, 1);

        pane.add(new Label("Lokation"), 0, 2);
        pane.add(txfLokation, 1, 2);

        pane.add(new Label("Størrelse (m3)"), 0, 3);
        pane.add(txfStoerrelse, 1, 3);

        Label lblStruktur = new Label("Lagerstruktur");
        lblStruktur.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblStruktur, 0, 4, 2, 1);

        pane.add(new Label("Antal reoler:"), 0, 5);
        pane.add(txfAntalReoler, 1, 5);

        pane.add(new Label("Antal hylder pr. reol:"), 0, 6);
        pane.add(txfAntalHylder, 1, 6);

        pane.add(new Label("Antal paldser pr. hylde:"), 0, 7);
        pane.add(txfPladserPrHylde, 1, 7);

        Button btnOpret = new Button("Opret lager");
        Button btnRyd = new Button("Ryd felter");

        btnOpret.setOnAction(event -> opretLagerAction());

        btnRyd.setOnAction(event -> rydFelter());

        HBox buttons = new HBox(15, btnOpret, btnRyd);
        pane.add(buttons, 1, 8);


        return pane;

    }

    private void opretLagerAction() {
        try {
            String navn = txfNavn.getText().trim();
            String lokation = txfLokation.getText().trim();

            if (navn.isEmpty()) {
                visFejl("Navn skal udfyldes.");
                return;
            }
            if (lokation.isEmpty()) {
                visFejl("Lokation skal udfyldes.");
                return;
            }

            int stoerrelse = Integer.parseInt(txfStoerrelse.getText());
            int antalReoler = Integer.parseInt(txfAntalReoler.getText());
            int antalHylderPrReol = Integer.parseInt(txfAntalHylder.getText());
            int antalPladserPrHylde = Integer.parseInt(txfPladserPrHylde.getText());

            Lager lager = controller.createLager(navn, lokation, stoerrelse);

            for (int reolNr = 1; reolNr <= antalReoler; reolNr++) {
                Reol reol = new Reol(reolNr);

                for (int hyldeNr = 1; hyldeNr <= antalHylderPrReol; hyldeNr++) {
                    Hylde hylde = new Hylde(hyldeNr);

                    for (int pladsNr = 1; pladsNr <= antalPladserPrHylde; pladsNr++) {
                        LagerPlads lagerPlads = new LagerPlads(pladsNr);

                        hylde.addPlads(lagerPlads);
                    }

                    reol.addHylde(hylde);
                }

                lager.addReol(reol);

            }

            visInfo("Lageret blev oprettet med " + antalReoler + " reoler, " +
                    (antalReoler * antalHylderPrReol) + " hylder og " +
                    (antalReoler * antalHylderPrReol * antalPladserPrHylde) + " lagerpladser");

            rydFelter();

            } catch (NumberFormatException e) {
            visFejl(e.getMessage());
        } catch (Exception e) {
            visFejl(e.getMessage());
        }
    }


    private void rydFelter() {
        txfNavn.clear();
        txfLokation.clear();
        txfStoerrelse.clear();

        txfAntalReoler.clear();
        txfAntalHylder.clear();
        txfPladserPrHylde.clear();
    }


    private void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText("Lager kunne ikke oprettes");
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
