package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Fad;
import model.Leverandoer;

import java.util.Locale;

public class OpretFadPane extends BorderPane {

    private final Controller controller;
    private final StartVindue startVindue;

    public OpretFadPane(Controller controller, StartVindue startVindue) {
        this.controller = controller;
        this.startVindue = startVindue;
        initContent();
    }

    Label fadIdLbl = new Label("FadId:");
    Label traaTypeLbl = new Label("Træ Type:");
    Label stoerrelseLbl = new Label("Størrelse:");
    Label beskrivelseLbl = new Label("Beskrivelse:");
    Label leverandoerLbl = new Label("Leverandør:");
    private TextField fadIdTxf = new TextField();
    private TextField traaTypeTxf = new TextField();
    private TextField stoerrelseTxf = new TextField();
    private TextArea beskriveseTa = new TextArea();
    private Button btnOpret = new Button("Opret fad");
    private Button btnRyd = new Button("Ryd felter");
    private ComboBox<Leverandoer> leverandoerCb = new ComboBox<>();

    private void initContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(30, 60, 30, 60));

        VBox header = createHeader();
        GridPane format = createFormat();

        root.getChildren().addAll(header, format);
        this.setCenter(root);


    }

    private GridPane createFormat() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30));
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        Label lblLagerInfo = new Label("Fadoplysninger");
        lblLagerInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblLagerInfo, 0, 0, 2, 1);

        fadIdTxf.setPrefWidth(350);
        stoerrelseTxf.setPrefWidth(350);
        traaTypeTxf.setPrefWidth(350);
        leverandoerCb.setPrefWidth(350);

        pane.add(fadIdLbl, 0, 1);
        pane.add(fadIdTxf, 1, 1);

        pane.add(traaTypeLbl, 0, 2);
        pane.add(traaTypeTxf, 1, 2);

        pane.add(stoerrelseLbl, 0, 3);
        pane.add(stoerrelseTxf, 1, 3);

        pane.add(leverandoerLbl, 0,4);
        pane.add(leverandoerCb,1,4);

        pane.add(beskrivelseLbl, 0, 5);
        pane.add(beskriveseTa, 1, 5);

        HBox buttons = new HBox(btnOpret, btnRyd);
        pane.add(buttons, 1, 6);

        buttons.setSpacing(10);

        leverandoerCb.getItems().addAll(controller.getLeverandoer());

        btnOpret.setOnAction(actionEvent -> opretFadAction());
        btnRyd.setOnAction(actionEvent -> rydFelterAction());

        return pane;
    }

    private VBox createHeader() {

        Label title = new Label("Opret Fad");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subTitle = new Label("Opret et nyt fad");
        subTitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subTitle);
    }

    private void opretFadAction() {
        try {
            String fadId = fadIdTxf.getText().trim();
            String traaType = traaTypeTxf.getText().trim();
            String stoerrelseTxt = stoerrelseTxf.getText().trim();
            Leverandoer leverandoer = leverandoerCb.getSelectionModel().getSelectedItem();
            String beskrivelse = beskriveseTa.getText();

            if (fadId.isEmpty()) {
                visFejl("Fad id skal udfyldes");
                return;
            }
            if (traaType.isEmpty()) {
                visFejl("Træ type skal udfyldes");
                return;
            }
            if (stoerrelseTxt.isEmpty()) {
                visFejl("Størrelse skal udfyldes");
                return;
            }
            if(leverandoer == null) {
                visFejl("Leverandør skal vælges");
            }
            if (beskrivelse.isEmpty()) {
                visFejl("Beskrivelse skal udfyldes");
                return;
            }
            int stoerrelse = Integer.parseInt(stoerrelseTxt);
            if (stoerrelse <= 0) {
                visFejl("Størrelse skal være større end 0");
                return;
            }

            controller.createFad(fadId, traaType, beskrivelse, stoerrelse, leverandoer);
            boolean ok = visInfo("Fad blev oprettet med fadId: " + fadId + ", træ type: " + traaType + ", størrelse:" + stoerrelse + ", Leverandør:" + leverandoer +
            "\n" + "Vil du registrere lagerplacering?");

            if (ok) {
                startVindue.showRegistrerLagerPlacering();
            }
            rydFelterAction();

        } catch (Exception e) {
            visFejl(e.getMessage());
        }
    }

    private void rydFelterAction() {
        fadIdTxf.clear();
        traaTypeTxf.clear();
        stoerrelseTxf.clear();
        leverandoerCb.getSelectionModel().clearSelection();
        beskriveseTa.clear();
    }

    private void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText("Fad kunne ikke oprettes");
        alert.setContentText(besked);
        alert.showAndWait();
    }

    private boolean visInfo(String besked) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fad Oprettet");
        alert.setHeaderText(null);
        alert.setContentText(besked);

        ButtonType svar = alert.showAndWait().orElse(ButtonType.CANCEL);

        return svar == ButtonType.OK;
    }
}
