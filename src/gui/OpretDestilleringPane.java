package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.MaltBatch;
import model.Medarbejder;

public class OpretDestilleringPane extends BorderPane {

    private final Controller controller;

    public OpretDestilleringPane(Controller controller) {
        this.controller = controller;
        initContent();
    }

    //Labels
    Label lblNewMakeNr = new Label("New Make Nr:");
    Label lblRygeMateriale = new Label("Rygemateriale:");
    Label lblKommentar = new Label("Kommentar:");
    Label lblAntalLiter = new Label("Antal liter:");
    Label lblAlkoholProcent = new Label("Alkohol procent:");
    Label lblMaltBatch = new Label("Malt batch:");
    Label lblMedarbejder = new Label("Medarbejder");
    Label lblStartDato = new Label("Start dato:");
    Label lblSlutDato = new Label("Slut dato");

    private TextField txfNewMakeNr = new TextField();
    private TextField txfRygemateriale = new TextField();

    private TextArea txtAreaKommentar = new TextArea();

    private TextField txfAntalLiter = new TextField();
    private TextField txfAlkoholProcent = new TextField();

    private ComboBox<MaltBatch> maltBatchCb = new ComboBox<>();
    private ComboBox<Medarbejder> medarbejderCb = new ComboBox<>();

    private DatePicker dpStartDato = new DatePicker();
    private DatePicker dpSlutDato = new DatePicker();

    Button btnOpret = new Button("Opret destillering");
    Button btnRyd = new Button("Ryd felter");


    private void initContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(30, 60, 30, 60));

        VBox header = createHeader();
        GridPane format = createFormat();

        root.getChildren().addAll(header, format);
        this.setCenter(root);
    }

    private VBox createHeader() {

        Label title = new Label("Opret Destillering");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subTitle = new Label("Opret ny Destillering");
        subTitle.setStyle("-fx-font-size: 16px;");

        return new VBox(5, title, subTitle);
    }

    private GridPane createFormat() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(30));
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        Label lblLagerInfo = new Label("Destilleringsoplysninger");
        lblLagerInfo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        pane.add(lblLagerInfo, 0, 0, 2, 1);

        txfNewMakeNr.setPrefWidth(350);
        txfRygemateriale.setPrefWidth(350);
        txtAreaKommentar.setPrefWidth(350);
        txfAntalLiter.setPrefWidth(350);
        txfAlkoholProcent.setPrefWidth(350);
        dpStartDato.setPrefWidth(350);
        dpSlutDato.setPrefWidth(350);
        maltBatchCb.setPrefWidth(350);
        medarbejderCb.setPrefWidth(350);

        //grid 1
        VBox vbox1 = new VBox(lblNewMakeNr, txfNewMakeNr);
        VBox vBox2 = new VBox(lblRygeMateriale, txfRygemateriale);
        HBox hbox1 = new HBox(50,vbox1, vBox2);
        pane.add(hbox1,0,1);

        //grid 2
        VBox vBox3 = new VBox(lblAntalLiter, txfAntalLiter);
        VBox vBox4 = new VBox(lblAlkoholProcent,txfAlkoholProcent);
        HBox hbox2 = new HBox(50, vBox3, vBox4);
        pane.add(hbox2,0,2);

        //grid 3
        VBox vBox5 = new VBox(lblMaltBatch, maltBatchCb);
        VBox vBox6 = new VBox(lblMedarbejder, medarbejderCb);
        HBox hbox3 = new HBox(50, vBox5, vBox6);
        pane.add(hbox3,0,3);

        //grid 4
        VBox vBox7 = new VBox(lblStartDato, dpStartDato);
        VBox vBox8 = new VBox(lblSlutDato, dpSlutDato);
        HBox hBox4 = new HBox(50, vBox7, vBox8);
        pane.add(hBox4,0,4);

        //grid 5
        VBox vBox9 = new VBox(lblKommentar, txtAreaKommentar);
        pane.add(vBox9,0,5);

        //grid 6
        HBox hBox5 = new HBox(50,btnOpret, btnRyd);
        pane.add(hBox5,0,6);

        btnOpret.setOnAction(actionEvent -> opretDestilleringAction());
        btnRyd.setOnAction(actionEvent -> rydFelterAction());

        medarbejderCb.getItems().addAll(controller.getMedarbejdere());
        maltBatchCb.getItems().add(controller.getMaltBatches());

        return pane;
    }

    private void opretDestilleringAction() {

    }

    private void rydFelterAction() {

    }
    private void visFejl(String besked) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText("Destillering kunne ikke oprettes");
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
