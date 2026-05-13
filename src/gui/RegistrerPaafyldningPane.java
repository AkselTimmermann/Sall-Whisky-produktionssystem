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
import model.Fad;
import model.Medarbejder;

import java.time.LocalDate;

public class RegistrerPaafyldningPane extends BorderPane {

    private final Controller controller;

    private ComboBox<Destillat> cmbDestillat;
    private ComboBox<Medarbejder> cmbMedarbejder;
    private ComboBox<Fad> cmbFad;

    private TextField txfAntalLiter;
    private DatePicker dpDato;

    private TextArea txaDestillatInfo;
    private TextArea txaFadInfo;


    public RegistrerPaafyldningPane(Controller controller) {
        this.controller = controller;
        initContent();
        updateComboBoxes();
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
        Label title = new Label("Registrer påfyldning");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subtitle = new Label("Registrer at et destillat hældes på et fad.");
        subtitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subtitle);
    }

    private GridPane createFormat() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30));
        pane.setVgap(15);
        pane.setHgap(15);
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        // Opretter indhold
        cmbDestillat = new ComboBox<>();
        cmbDestillat.setPrefWidth(350);

        cmbFad = new ComboBox<>();
        cmbFad.setPrefWidth(350);

        cmbMedarbejder = new ComboBox<>();
        cmbMedarbejder.setPrefWidth(350);

        txfAntalLiter = new TextField();
        txfAntalLiter.setPrefWidth(350);
        txfAntalLiter.setPromptText("Antal liter der hældes på fadet");

        dpDato = new DatePicker();
        dpDato.setPrefWidth(350);
        dpDato.setValue(LocalDate.now());

        txaDestillatInfo = new TextArea("Vælg et destillat for at se information.");
        txaDestillatInfo.setEditable(false);
        txaDestillatInfo.setWrapText(true);
        txaDestillatInfo.setPrefRowCount(5);
        txaDestillatInfo.setPrefWidth(500);

        txaFadInfo = new TextArea("Vælg et fad for at se information");
        txaFadInfo.setEditable(false);
        txaFadInfo.setWrapText(true);
        txaFadInfo.setPrefRowCount(4);
        txaFadInfo.setPrefWidth(500);


        cmbDestillat.setOnAction(event -> updateDestillatInfo());
        cmbFad.setOnAction(event -> updateFadInfo());


        // Indsætter indhold
        Label lblInfo = new Label("Påfyldningsoplysninger");
        lblInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblInfo, 0, 0, 2, 1);

        pane.add(new Label("Destillat:"), 0, 1);
        pane.add(cmbDestillat, 1, 1);

        pane.add(new Label("Destillat info"), 0, 2);
        pane.add(txaDestillatInfo, 1, 2);

        pane.add(new Label("Fad"), 0, 3);
        pane.add(cmbFad, 1, 3);

        pane.add(new Label("Fad info:"), 0, 4);
        pane.add(txaFadInfo, 1, 4);

        pane.add(new Label("Medarbejder:"), 0, 5);
        pane.add(cmbMedarbejder, 1, 5);

        pane.add(new Label("Dato:"), 0, 6);
        pane.add(dpDato, 1, 6);

        pane.add(new Label("Antal liter:"), 0, 7);
        pane.add(txfAntalLiter, 1, 7);


        Button btnRegistrer = new Button("Registrer påfyldning");
        Button btnRyd = new Button("Ryd felter");

        btnRegistrer.setOnAction(event -> registrerPaafyldningAction());
        btnRyd.setOnAction(event -> rydFelter());

        HBox buttons = new HBox(15, btnRegistrer, btnRyd);
        pane.add(buttons, 1, 8);

        return pane;
    }

    // Metode der opdaterer comboboxes hver gang siden åbnes
    private void updateComboBoxes() {
        cmbDestillat.getItems().clear();
        cmbFad.getItems().clear();
        cmbMedarbejder.getItems().clear();

        cmbDestillat.getItems().addAll(controller.getDestillater());
        cmbFad.getItems().addAll(controller.getFade());
        cmbMedarbejder.getItems().addAll(controller.getMedarbejdere());
    }



    private void registrerPaafyldningAction() {
    }


    private void updateDestillatInfo() {
        Destillat destillat = cmbDestillat.getValue();


        txaDestillatInfo.setText("Består af følgende destilleringer:");


    }

    private void updateFadInfo() {
        Fad fad = cmbFad.getValue();


    }


    private void rydFelter() {
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
