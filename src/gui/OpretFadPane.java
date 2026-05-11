package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OpretFadPane extends BorderPane {

    private final Controller controller;

    public OpretFadPane(Controller controller) {
        this.controller = controller;
        initContent();
    }

    Label fadId = new Label("FadId:");
    Label traaType = new Label("Træ Type:");
    Label stoerrelse = new Label("Størrelse:");
    Label beskrivelse = new Label("Beskrivelse");
    TextField fadIdTxf = new TextField();
    TextField traaTypeTxf = new TextField();
    TextField stoerrelseTxf = new TextField();
    TextArea beskriveseTa = new TextArea();
    Button btnOpret = new Button("Opret fad");
    Button btnRyd = new Button("Ryd felter");

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

        pane.add(fadId, 0, 1);
        pane.add(fadIdTxf, 1, 1);

        pane.add(traaType, 0, 2);
        pane.add(traaTypeTxf, 1, 2);

        pane.add(stoerrelse, 0, 3);
        pane.add(stoerrelseTxf, 1, 3);

        pane.add(beskrivelse, 0, 4);
        pane.add(beskriveseTa, 1, 4);

        HBox buttons = new HBox(btnOpret, btnRyd);
        pane.add(buttons, 1, 5);

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

        } catch (NumberFormatException e) {
            visFejl(e.getMessage());
        } catch (Exception e) {
            visFejl(e.getMessage());
        }
    }

    private void rydFelterAction() {

    }

    private void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText("Fad kunne ikke oprettes");
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
