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


        MaltBatch ma1 = controller.createMaltBatch("Mark 7", "Evergreen", "1");
        MaltBatch ma2 = controller.createMaltBatch("Mark 6", "Stairway", "2");

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
