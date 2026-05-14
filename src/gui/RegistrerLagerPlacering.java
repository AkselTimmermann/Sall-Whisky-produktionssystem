package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Lager;
import model.LagerObjekt;
import model.LagerPlads;

public class RegistrerLagerPlacering extends BorderPane {

    private final Controller controller;

    public RegistrerLagerPlacering(Controller controller) {
        this.controller = controller;
        initContent();
    }

    Label objekterLbl = new Label("Vælg objekt");
    Label fadEllerSamlingLbl = new Label();
    Label lagreLbl = new Label("Lagre");
    Label pladserLbl = new Label("Pladser");

    ListView<LagerObjekt> ledigeObjekterLv = new ListView<>();
    ComboBox<Lager> lagerCb = new ComboBox<>();
    ListView<LagerPlads> pladserLv = new ListView<>();
    ComboBox<String> objektCb = new ComboBox<>();

    Button registrerPladsBtn = new Button("Registrer plads");
    Button rydBtn = new Button("Ryd felter");
    private void initContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(30, 60, 30, 60));

        VBox header = createHeader();
        GridPane format = createFormat();

        root.getChildren().addAll(header, format);
        this.setCenter(root);
    }
    private VBox createHeader() {
        Label title = new Label("Registrer lagerplacering");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subtitle = new Label("Registrer Lagerplacering for et fad eller flasker");
        subtitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subtitle);
    }
    private GridPane createFormat() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30));
        pane.setVgap(15);
        pane.setHgap(15);
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        Label lblLagerInfo = new Label("Registreringsformular");
        lblLagerInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblLagerInfo, 0, 0, 2, 1);

        pladserLv.setPrefWidth(350);
        ledigeObjekterLv.setPrefWidth(350);

        VBox vboxObjekt = new VBox(objekterLbl, objektCb);
        pane.add(vboxObjekt,0,1);

        VBox vboxFad = new VBox(fadEllerSamlingLbl, ledigeObjekterLv);
        pane.add(vboxFad,0,2);

        VBox vboxLager = new VBox(lagreLbl, lagerCb);
        pane.add(vboxLager,1,1);

        VBox vboxPlads = new VBox(pladserLbl, pladserLv);
        pane.add(vboxPlads,1,2);

        HBox hboxBtn = new HBox(30,registrerPladsBtn, rydBtn);
        pane.add(hboxBtn,0,3);

        hboxBtn.setAlignment(Pos.CENTER_RIGHT);

        objektCb.getItems().addAll("Fad", "Flaskesamling");
        objektCb.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    if (newValue == null) {
                        ledigeObjekterLv.getItems().clear();
                        fadEllerSamlingLbl.setText(null);
                        return;
                    }
                    if (newValue.equals("Fad")) {
                        ledigeObjekterLv.getItems().setAll(controller.getFadeUdenPlacering());
                        fadEllerSamlingLbl.setText("Fade");
                    } else if (newValue.equals("Flaskesamling")) {
                        ledigeObjekterLv.getItems().setAll(controller.getFlaskeSamlingUdenPlacering());
                        fadEllerSamlingLbl.setText("Flaskesamlinger");
                    }
                }
        );

        lagerCb.getItems().addAll(controller.getLagre());
        lagerCb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        pladserLv.getItems().setAll(controller.getLagerPladser(newValue));
                    }
                }
        );

        registrerPladsBtn.setOnAction(actionEvent -> registrerPladsAction());
        rydBtn.setOnAction(actionEvent -> rydFelterAction());

        return pane;
    }

    private void registrerPladsAction() {
        try {
            LagerObjekt objekt = ledigeObjekterLv.getSelectionModel().getSelectedItem();
            LagerPlads plads = pladserLv.getSelectionModel().getSelectedItem();

            if (objekt == null) {
                visFejl("Vælg et lagerObjekt");
            }
            if (plads == null) {
                visFejl("Vælg en plads");
            }
            if (plads.erOptaget()) {
                boolean fortsæt = bekraeftOptagetPlads();

                if (!fortsæt) {
                    return;
                }
            }
            controller.placerObjekt(objekt, plads);
            visInfo("Placering registreret");
            rydFelterAction();
        } catch (Exception e) {
            visFejl(e.getMessage());
        }

    }

    private void rydFelterAction() {
        ledigeObjekterLv.getItems().clear();
        pladserLv.getItems().clear();
        objektCb.getSelectionModel().clearSelection();
        lagerCb.getSelectionModel().clearSelection();
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
        alert.setTitle("Registrering oprettet");
        alert.setHeaderText(null);
        alert.setContentText(besked);
        alert.showAndWait();
    }
    private boolean bekraeftOptagetPlads() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Plads optaget");
        alert.setHeaderText("Pladsen er allerede optaget");
        alert.setContentText("Vil du stadig placere objektet på denne plads?");

        ButtonType svar = alert.showAndWait().orElse(ButtonType.CANCEL);

        return svar == ButtonType.OK;
    }
}
