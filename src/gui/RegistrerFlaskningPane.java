package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.WhiskyProdukt;

public class RegistrerFlaskningPane extends BorderPane {

    private final Controller controller;

    public RegistrerFlaskningPane(Controller controller) {
        this.controller = controller;
        initContent();
    }

    Label whiskyLbl = new Label("Whisky produkter");
    Label flaskeStoerrelseLbl = new Label("Flaske størrelse");
    Label oensketAntalLbl = new Label("Antal flasker");
    Label maksAntalLbl = new Label("Maks antal flasker:");
    Label tiloversLbl = new Label("Whisky tilovers");

    ComboBox<WhiskyProdukt> whiskyProduktComboBox = new ComboBox<>();
    TextField flaskeStoerrelseTxf = new TextField();
    TextField oesnketAntalTxf = new TextField();
    Button registrerBtn = new Button("Registrer");
    Button rydBtn = new Button("Ryd felter");
    Button vaelgFlaskeStoerrelseBtn = new Button("Vælg");


    private void initContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(30, 60, 30, 60));

        VBox header = createHeader();
        GridPane format = createFormat();

        root.getChildren().addAll(header, format);
        this.setCenter(root);
    }

    private VBox createHeader() {
        Label title = new Label("Registrer flaskning");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subtitle = new Label("Registrer påfyldning af et whiskyprodukt på flasker.");
        subtitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subtitle);
    }

    private GridPane createFormat() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30));
        pane.setVgap(15);
        pane.setHgap(15);
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        Label lblLagerInfo = new Label("Aftapningsoplysninger");
        lblLagerInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblLagerInfo, 0, 0, 2, 1);

        VBox vbox1 = new VBox(whiskyLbl, whiskyProduktComboBox);
        pane.add(vbox1,0,1);

        VBox vBox2 = new VBox(flaskeStoerrelseLbl, flaskeStoerrelseTxf);
        pane.add(vBox2,0,2);

        pane.add(vaelgFlaskeStoerrelseBtn,0,3);

        pane.add(maksAntalLbl,0,4);

        VBox vBox3 = new VBox(oensketAntalLbl,oesnketAntalTxf);
        pane.add(vBox3,0,5);

        pane.add(tiloversLbl,0,6);

        HBox hBox = new HBox(30, registrerBtn, rydBtn);
        pane.add(hBox,0,7);

        vaelgFlaskeStoerrelseBtn.setOnAction(actionEvent -> beregnMaksAntalFlaskerAction());

        return pane;
    }

    private void registrerAftapningAction() {

    }
    private void rydFelterAction() {

    }

    private void beregnMaksAntalFlaskerAction() {
        String stoerrelseText = flaskeStoerrelseTxf.getText().trim();
        if (stoerrelseText.isEmpty()) {
            visFejl("Indtast venligts størrelse på flaske");
        }
        Double stoerrelse = Double.parseDouble(stoerrelseText);
        if (stoerrelse <= 0) {
            visFejl("Størrelse skal være større end 0");
        }
    }

    private void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText("Vælg størrelse");
        alert.setContentText(besked);
        alert.showAndWait();
    }

}
