package gui;

import controller.Controller;
import model.*;

import java.time.LocalDate;

public class InitialData {

    public static void initData(Controller controller) {
        Medarbejder me1 = controller.createMedarbejder("Snævar", 1);
        Medarbejder me2 = controller.createMedarbejder("Casper", 2);

        Leverandoer l1 = controller.createLeverandoer("Dansk Fad Leverandør", "Danmark");
        Leverandoer l2 = controller.createLeverandoer("Madrid Cask Supply", "Spanien");
        Leverandoer l3 = controller.createLeverandoer("French Cask reseller", "Frankrig");

        MaltBatch ma1 = controller.createMaltBatch("Mark 7", "Evergreen", "1");
        MaltBatch ma2 = controller.createMaltBatch("Mark 6", "Stairway", "2");


        Lager ladeLager = controller.createLager("Lade hos Bondemanden", "Sall, Danmark", 400);
        Lager containerLager = controller.createLager("Container bag destilleriet", "Sall, Danmark", 200);


        opretLagerStruktur(controller, ladeLager, 2, 3, 10);
        opretLagerStruktur(controller, containerLager, 3, 3, 8);



        controller.createFad("54", "Egetræ",
                "Ex-bourbon", 32, l2);
        controller.createFad("58", "Egetræ",
                "Ex-cherry", 94, l1);
        controller.createFad("314", "Egetræ",
                "Ex-Revesaltes Ambré",
                230, l3);


        controller.createDestillering("NM77P",
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 1),
                120, 42, "Tørv",
                "Testdestillering", ma1, me2);

        controller.createDestillering("NM76P",
                LocalDate.of(2026, 2, 2),
                LocalDate.of(2026, 2, 2),
                140, 52, "",
                "Testdestillering 2", ma2, me2);


    }

    private static void opretLagerStruktur(Controller controller, Lager lager,
                                           int antalReoler, int antalHylderPrReol,
                                           int antalPladserPrHylde) {
        for (int reolNr = 1; reolNr < antalReoler; reolNr++) {
            Reol reol = controller.createReol(reolNr);

            for (int hyldeNr = 1; hyldeNr < antalHylderPrReol; hyldeNr++) {
                Hylde hylde = controller.createHylde(hyldeNr);

                for (int pladsNr = 1; pladsNr < antalPladserPrHylde; pladsNr++) {
                    LagerPlads plads = controller.createLagerPlads(pladsNr);
                    hylde.addPlads(plads);
                }
                reol.addHylde(hylde);
            }
            lager.addReol(reol);
        }
    }

}
