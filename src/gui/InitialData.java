package gui;

import controller.Controller;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

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



        Fad fad1 = controller.createFad("54", "Egetræ",
                "Ex-bourbon", 200, l2);
        Fad fad2 = controller.createFad("58", "Egetræ",
                "Ex-cherry", 94, l1);
        Fad fad3= controller.createFad("314", "Egetræ",
                "Ex-Revesaltes Ambré",
                230, l3);


        Destillering destillering1 = controller.createDestillering("NM77P",
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 1),
                80, 42, "Tørv",
                "Testdestillering", ma1, me2);

        Destillering destillering2 = controller.createDestillering("NM76P",
                LocalDate.of(2026, 2, 2),
                LocalDate.of(2026, 2, 2),
                80, 52, "",
                "Testdestillering 2", ma2, me2);

        ArrayList<Destillering>destilleringer = new ArrayList<>();
        destilleringer.add(destillering1);

        double[] antalLiter = {50};

        Destillat destillat1 =  controller.createDestillat("1", destilleringer, antalLiter);

        FadIndhold fadIndhold1 = controller.createFadindhold(fad1);

        ModningsRegistrering modningsRegistrering1 = controller.createModningsRegistrering(42, LocalDate.of(2025,6,24),45,"Ikke relevant","Ikke relevant", fadIndhold1);
        PaafyldningsRegistrering paafyldningsRegistrering1 = controller.createPaafyldningsRegistrering(42, LocalDate.of(2016,6,24),destillat1,fad1,me1);

        ArrayList<FadIndhold> fadindholerne = new ArrayList<>();
        fadindholerne.add(fadIndhold1);

        ArrayList<Double> antalLiterIWhisky = new ArrayList<Double>();
        antalLiterIWhisky.add(20.0);

        WhiskyProdukt whiskyProdukt1 = controller.createWhiskyProdukt("Fryd", 1, "Ikke relevant",LocalDate.of(2026,5,18),0,fadindholerne,antalLiterIWhisky);
        ProduktRegistrering produktRegistrering1 = controller.createProduktRegistrering(20,fadIndhold1,whiskyProdukt1);

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
