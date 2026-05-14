package gui;

import controller.Controller;
import model.Leverandoer;
import model.MaltBatch;
import model.Medarbejder;

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


        controller.createLager("Lade hos Bondemanden", "Sall, Danmark", 400);
        controller.createLager("Container bag destilleriet", "Sall, Danmark", 200);


        controller.createFad("54", "Egetræ",
                "Lille ex-bourbon fad af spansk egetræ.", 32, l2);
        controller.createFad("58", "Egetræ",
                "Mellem dansk egetræsfad", 94, l1);
        controller.createFad("314", "Egetræ",
                "Stort fransk Revesaltes Ambré fad. Tidligere brugt til lagring af hedvin.",
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
}
