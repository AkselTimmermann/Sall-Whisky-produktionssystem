package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.*;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ForsidePane extends BorderPane {

    private final Controller controller;

    private ListView<Fad> lvwFade;
    private ListView<FadIndhold> lvwFadIndhold;
    private TextArea txaDetaljer;

    private Label lblAntalFade;
    private Label lblAntalFadIndhold;
    private ListView<WhiskyProdukt> whiskyProduktListView = new ListView<>();
    private TextArea whiskyTextArea = new TextArea();
    private Label whiskyLbl = new Label("Whisky produkter");
    private Label whiskyInfoLbl = new Label("Whisky produkt info");

    public ForsidePane(Controller controller) {
        this.controller = controller;
        initContent();
        updateLists();
    }

    private void initContent() {
        VBox root = new VBox(25);
        root.setPadding(new Insets(30, 60, 30, 60));

        VBox header = createHeader();
        GridPane content = createContent();

        Button btnOpdater = new Button("Opdater lister");
        btnOpdater.setOnAction(event -> updateLists());

        Button btnSorterFad = new Button("Sorter efter seneste modning");
        btnSorterFad.setOnAction(event -> sorterFad());

        HBox buttons = new HBox(15, btnOpdater, btnSorterFad);

        root.getChildren().addAll(header, content, buttons);

        this.setCenter(root);

    }


    private VBox createHeader() {
        Label title = new Label("Forside og historik");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subTitle = new Label("Overblik over fade og produkter");
        subTitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subTitle);
    }

    private GridPane createContent() {
        GridPane pane = new GridPane();
        pane.setHgap(25);
        pane.setVgap(15);

        Label lblFadeTitel = new Label("Fade");
        lblFadeTitel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label lblFadIndholdTitel = new Label("Fadindhold");
        lblFadIndholdTitel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label lblDetaljerTitel = new Label("Detaljer");
        lblDetaljerTitel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        lvwFade = new ListView<>();
        lvwFade.setPrefSize(360, 120);

        lvwFadIndhold = new ListView<>();
        lvwFadIndhold.setPrefSize(420, 320);

        txaDetaljer = new TextArea("Vælg et fad eller fadindhold for at se detaljer");
        txaDetaljer.setEditable(false);
        txaDetaljer.setWrapText(true);
        txaDetaljer.setPrefSize(500, 320);

        lblAntalFade = new Label();
        lblAntalFadIndhold = new Label();

        lvwFade.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, selectedFad) -> {
                    if (selectedFad != null) {
                        lvwFadIndhold.getSelectionModel().clearSelection();
                        visFadDetaljer(selectedFad);
                    }
                }
        );

        lvwFadIndhold.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, selectedFadIndhold) -> {
                    if (selectedFadIndhold != null) {
                        lvwFade.getSelectionModel().clearSelection();
                        visFadIndholdDetaljer(selectedFadIndhold);
                    }
                }
        );

        whiskyProduktListView.getSelectionModel().selectedItemProperty().addListener((
                observableValue, oldWhiskyProdukt, newWhiskyProdukt) -> {
            if (newWhiskyProdukt != null) {
                whiskyProduktInfo(newWhiskyProdukt);
            }
        });

        pane.add(lblFadeTitel, 0, 0);
        pane.add(lblFadIndholdTitel, 1, 0);
        pane.add(lblDetaljerTitel, 2, 0);

        pane.add(lvwFade, 0, 1);
        pane.add(lvwFadIndhold, 1, 1);
        pane.add(txaDetaljer, 2, 1);

        pane.add(lblAntalFade, 0, 2);
        pane.add(lblAntalFadIndhold, 1, 2);

        pane.add(whiskyLbl,0,3);
        pane.add(whiskyProduktListView,0,4);
        pane.add(whiskyInfoLbl,1,3);
        pane.add(whiskyTextArea,1,4);


        return pane;
    }

    private void visFadDetaljer(Fad fad) {
        StringBuilder sb = new StringBuilder();

        sb.append("Fad\n");
        sb.append("--------------------\n");
        sb.append("Fad ID: ").append(fad.getFadId()).append("\n");
        sb.append("Trætype: ").append(fad.getTraaType()).append("\n");
        sb.append("Størrelse: ").append(fad.getStoerrelse()).append(" liter\n");
        sb.append("Status: ").append(fad.getStatus()).append("\n");
        sb.append("Beskrivelse: ").append(fad.getBeskrivelse()).append("\n");
        sb.append("Leverandør: ").append(fad.getLeverandoer()).append("\n");

        if (fad.getLagerPlads() != null) {
            sb.append("Lagerplacering : ").append(fad.getLagerPlads()).append("\n");
        } else {
            sb.append("Lagerplacering: Ikke placeret\n");
        }

        sb.append("\nFadindhold på fadet: ").append(fad.getAktivtFadIndhold()).append("\n");

        txaDetaljer.setText(sb.toString());
    }

    private void visFadIndholdDetaljer(FadIndhold fadIndhold) {
        StringBuilder sb = new StringBuilder();

        sb.append("Fadindhold\n");
        sb.append("--------------------\n");
        sb.append("Fad: ").append(fadIndhold.getFad()).append("\n");
        sb.append("Samlet påfyldning: ").append(String.format("%.1f", fadIndhold.getSamletPaafyldning())).append(" liter\n");

        sb.append("Resterende liter: ").append(String.format("%.1f", fadIndhold.getResterendeLiter())).append(" liter\n");

        sb.append("\nPåfyldninger\n");
        sb.append("--------------------\n");

        for (PaafyldningsRegistrering registrering : fadIndhold.getPaafyldningsRegistreringer()) {
            sb.append("- ")
                    .append(registrering.getDato())
                    .append(" | ")
                    .append(String.format("%.1f", registrering.getAntalLiter()))
                    .append(" liter");


            sb.append(" | Destillat: ").append(registrering.getDestillat());
            sb.append(" | Medarbejder: ").append(registrering.getMedarbejder());

            sb.append("\n");
        }

        sb.append("\nModningsregistreringer\n");
        sb.append("--------------------\n");

        ArrayList<ModningsRegistrering> modningsRegistreringer;

        try {
            modningsRegistreringer = fadIndhold.getModningsRegistreringer();
        } catch (Exception e) {
            modningsRegistreringer = new ArrayList<>();
        }

        if (modningsRegistreringer.isEmpty()) {
            sb.append("Ingen modningsregistreringer.\n");
        } else {
            for (ModningsRegistrering registrering : modningsRegistreringer) {
                sb.append("- ");

                if (registrering.getDato() != null) {
                    sb.append(registrering.getDato()).append(" | ");
                }

                sb.append(String.format("%.1f", registrering.getAntalLiter()))
                        .append(" liter | ")
                        .append(String.format("%.2f", registrering.getAlkoholProcent()))
                        .append("%\n");
            }
        }
        txaDetaljer.setText(sb.toString());
    }


    private void sorterFad() {
    }


    private void updateLists() {
        lvwFade.getItems().clear();
        lvwFadIndhold.getItems().clear();
        whiskyProduktListView.getItems().clear();

        lvwFade.getItems().addAll(controller.getFade());
        lvwFadIndhold.getItems().addAll(controller.getFadIndhold());
        whiskyProduktListView.getItems().addAll(controller.getWhiskyprodukter());

        lblAntalFade.setText("Antal fade: " + controller.getFade().size());
        lblAntalFadIndhold.setText("Antal fadindhold: " + controller.getFadIndhold().size());

        txaDetaljer.setText("Vælg et fad eller fadindhold for at se detaljer.");
    }

    private void whiskyProduktInfo(WhiskyProdukt whiskyProdukt) {
        whiskyTextArea.setText(controller.visHistorik(whiskyProdukt));
    }
}
