package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class StartVindue extends BorderPane {

    private final Controller controller;

    private Button btnForside;
    private Button btnOpretLager;
    private Button btnOpretFad;
    private Button btnOpretDestillering;


    public StartVindue(Controller controller) {
        this.controller = controller;
        initContent();
    }

    private void initContent() {
        this.setLeft(createSideMenu());

        // Startside når progammet åbnes
        showForside();
    }

    private VBox createSideMenu() {
        VBox sideMenu = new VBox();
        sideMenu.setPrefWidth(200);
        sideMenu.setPadding(new Insets(0));
        sideMenu.setStyle(
                "-fx-border-color: black;" +
                "-fx-border-width: 0 1 0 0;");

        Label title = new Label("Sall Whisky \nDistillery");
        title.setMaxWidth(Double.MAX_VALUE);
        title.setPadding(new Insets(16));
        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 0 0 1 0;");

        btnForside = createButtonMenu("Forside");
        btnOpretLager = createButtonMenu("Opret lager");
        btnOpretFad = createButtonMenu("Opret fad");
        btnOpretDestillering = createButtonMenu("Opret destillering");

        btnForside.setOnAction(event -> showForside());
        btnOpretLager.setOnAction(event -> showOpretLager());
        btnOpretFad.setOnAction(event -> showOpretFad());
        btnOpretDestillering.setOnAction(event -> showOpretDestillering());

        VBox buttons = new VBox(12);
        buttons.setPadding(new Insets(35, 25, 0, 25));
        buttons.getChildren().addAll(
                btnForside,
                btnOpretLager,
                btnOpretFad,
                btnOpretDestillering);

        sideMenu.getChildren().addAll(title, buttons);

        return sideMenu;
    }

    // Metode der bruges til at oprette og placere menu-knapper
    private Button createButtonMenu(String text) {
        Button button = new Button(text);
        button.setPrefWidth(230);
        button.setPrefHeight(48);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setStyle(menuButtonStyle(false));
        return button;
    }

    private void showForside() {
        markSelected(btnForside);
        this.setCenter(new ForsidePane(controller));
    }

    private void showOpretLager() {
        markSelected(btnOpretLager);
        this.setCenter(new OpretLagerPane(controller));
    }

    private void showOpretFad() {
        markSelected(btnOpretFad);
        this.setCenter(new OpretFadPane(controller));
    }

    private void showOpretDestillering() {
        markSelected(btnOpretDestillering);
        this.setCenter(new OpretDestilleringPane(controller));
    }

    private void markSelected(Button selectedButton) {
        btnForside.setStyle(menuButtonStyle(false));
        btnOpretLager.setStyle(menuButtonStyle(false));
        btnOpretFad.setStyle(menuButtonStyle(false));
        btnOpretDestillering.setStyle(menuButtonStyle(false));

        selectedButton.setStyle(menuButtonStyle(true));
    }

    // Metode der markerer valgt knap
    private String menuButtonStyle(boolean selected) {
        if (selected) {
            return "-fx-font-size: 17px;" +
                    "-fx-background-color: #8c8c8c;" +
                    "-fx-text-fill: black;" +
                    "-fx-alignment: center-left;" +
                    "-fx-padding: 0 0 0 14;";
        }
        return "-fx-font-size: 17px;" +
                "-fx-background-color: transparent;" +
                "-fx-text-fill: black;" +
                "-fx-border-color: transparent;" +
                "-fx-alignment: center-left;" +
                "-fx-padding: 0 0 0 14;";
    }

}


