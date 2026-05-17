package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.FlaskeSamling;
import model.WhiskyProdukt;

public class RegistrerFlaskningPane extends BorderPane {

    private final Controller controller;
    private final StartVindue startVindue;

    public RegistrerFlaskningPane(Controller controller, StartVindue startVindue) {
        this.controller = controller;
        this.startVindue = startVindue;
        initContent();
    }

    Label whiskyLbl = new Label("Vælg whisky produkt");
    Label flaskeStoerrelseLbl = new Label("Flaske størrelse");
    Label oensketAntalLbl = new Label("Ønsket antal flasker");
    Label maksAntalLbl = new Label("Maks antal flasker:");
    Label tiloversLbl = new Label("Whisky tilovers:");

    ComboBox<WhiskyProdukt> whiskyProduktComboBox = new ComboBox<>();
    TextField flaskeStoerrelseTxf = new TextField();
    TextField oesnketAntalTxf = new TextField();
    Button registrerBtn = new Button("Registrer");
    Button rydBtn = new Button("Ryd felter");
    Button vaelgFlaskeStoerrelseBtn = new Button("Vælg");
    Button vaelgOesnetStoerrelseBtn = new Button("Vælg");


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

        flaskeStoerrelseTxf.setPrefWidth(350);
        whiskyProduktComboBox.setPrefWidth(350);
        oesnketAntalTxf.setPrefWidth(350);

        VBox vbox1 = new VBox(2,whiskyLbl, whiskyProduktComboBox);
        pane.add(vbox1,0,1);

        VBox vBox2 = new VBox(2,flaskeStoerrelseLbl, flaskeStoerrelseTxf,vaelgFlaskeStoerrelseBtn);
        pane.add(vBox2,0,2);



        pane.add(maksAntalLbl,0,3);

        VBox vBox3 = new VBox(2,oensketAntalLbl,oesnketAntalTxf,vaelgOesnetStoerrelseBtn);
        pane.add(vBox3,0,4);


        pane.add(tiloversLbl,0,5);

        HBox hBox = new HBox(30, registrerBtn, rydBtn);
        pane.add(hBox,0,6);

        vaelgFlaskeStoerrelseBtn.setOnAction(actionEvent -> beregnMaksAntalFlaskerAction());
        vaelgOesnetStoerrelseBtn.setOnAction(actionEvent -> beregnWhiskyTiloversAction());
        registrerBtn.setOnAction(actionEvent -> registrerAftapningAction());
        rydBtn.setOnAction(actionEvent -> rydFelterAction());
        return pane;
    }

    private void beregnMaksAntalFlaskerAction() {
        String stoerrelseText = flaskeStoerrelseTxf.getText().trim();
        WhiskyProdukt whiskyProdukt = whiskyProduktComboBox.getSelectionModel().getSelectedItem();
        if (stoerrelseText.isEmpty()) {
            visFejl("Indtast venligts størrelse på flaske");
        }
        if (whiskyProdukt == null) {
            visFejl("Vælg et whiskyprodukt");
        }
        Double stoerrelse = Double.parseDouble(stoerrelseText);
        if (stoerrelse <= 0) {
            visFejl("Størrelse skal være større end 0");
        }
        double maks = whiskyProdukt.maksAntalFlasker(stoerrelse);
        maksAntalLbl.setText("Maks antal flakser: " + maks);
    }

    private void beregnWhiskyTiloversAction() {
        String antalFlaskerText = oesnketAntalTxf.getText().trim();
        WhiskyProdukt whiskyProdukt = whiskyProduktComboBox.getSelectionModel().getSelectedItem();
        String stoerrelseText = flaskeStoerrelseTxf.getText().trim();
        double stoerrelse = Double.parseDouble(stoerrelseText);

        if (antalFlaskerText.isEmpty()) {
            visFejl("Indtast ønsket antal flasker");
        }
        if (whiskyProdukt == null) {
            visFejl("Vælg et Whiskyprodukt");
        }
        int antalFlasker = Integer.parseInt(antalFlaskerText);
        if (antalFlasker <= 0 ) {
            visFejl("antal flasker skal være større end 0");
        }
        double tilovers = whiskyProdukt.beregnWhiskyTilovers(stoerrelse, antalFlasker);

        if (antalFlasker > whiskyProdukt.maksAntalFlasker(stoerrelse)) {
            visFejl("Ønsket antal flasker overstiger antal flasker tilgængelige");
        }
        tiloversLbl.setText("Whisky tilovers: " + tilovers);
    }

    private void registrerAftapningAction() {
        try {
            WhiskyProdukt whiskyProdukt = whiskyProduktComboBox.getSelectionModel().getSelectedItem();
            double stoerrelse = Double.parseDouble(flaskeStoerrelseTxf.getText().trim());
            int antalFLasker = Integer.parseInt(oesnketAntalTxf.getText().trim());
            FlaskeSamling flaskeSamling = controller.createFlaskeSamling();

            whiskyProdukt.createFlasker(stoerrelse, antalFLasker, flaskeSamling);
            boolean ok = visInfo("Registrering oprettet \n vil du registrere placering med det samme?");
            if (ok) {
                startVindue.showRegistrerLagerPlacering();
            }
            rydFelterAction();


        } catch (Exception e) {
            visFejl(e.getMessage());
        }

    }
    private void rydFelterAction() {
        whiskyProduktComboBox.getSelectionModel().clearSelection();
        flaskeStoerrelseTxf.clear();
        maksAntalLbl.setText("Maks antal flasker:");
        oesnketAntalTxf.clear();
        tiloversLbl.setText("Whisky tilovers:");
    }

    private void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText("Vælg størrelse");
        alert.setContentText(besked);
        alert.showAndWait();
    }

    private boolean visInfo(String besked) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Registrering Oprettet");
        alert.setHeaderText(null);
        alert.setContentText(besked);

        ButtonType svar = alert.showAndWait().orElse(ButtonType.CANCEL);

        return svar == ButtonType.OK;
    }

}
