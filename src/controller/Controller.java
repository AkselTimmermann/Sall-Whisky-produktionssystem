package controller;

import model.*;
import storage.Storage;
import storage.StorageInterface;

import java.time.LocalDate;

public class Controller {
    private StorageInterface storage;

    public Controller(StorageInterface storage){
    this.storage = storage;
    }

    public Lager createLager(String navn, String lokation, int stoerrelse){
        Lager lager = new Lager(navn, lokation, stoerrelse);
        storage.addLager(lager);
        return lager;
    }

    public Reol createReol(int reolNr){
        Reol reol = new Reol(reolNr);
        return reol;
    }

    public Hylde createHylde(int hyldeNr){
        Hylde hylde = new Hylde(hyldeNr);
        return hylde;
    }

    public LagerPlads createLagerPlads(int pladsNr){
        LagerPlads lagerPlads = new LagerPlads(pladsNr);
        return lagerPlads;
    }

    public Fad createFad(String fadId, String traeType, String beskrivelse, int stoerrelse){
        Fad fad = new Fad(fadId, traeType, beskrivelse, stoerrelse);
        storage.addFad(fad);
        return fad;
    }


    public PaafyldningsRegistrering createPaafyldningsRegistrering(double antalLiter, LocalDate dato, Destillering destillering, Fad fad, Medarbejder medarbejder) {
        PaafyldningsRegistrering paafyldningsRegistrering = fad.opretPaafyldningsRegistrering(antalLiter, dato, destillering, medarbejder);
        destillering.reducerResterendeLiter(antalLiter);
        return paafyldningsRegistrering;
    }

    public ModningsRegistrering createModningsRegistrering(double alkoholProcent, LocalDate dato, double antalLiter, String note, String titel, Fad fad) {
        ModningsRegistrering modningsRegistrering = fad.opretModningsRegistrering(alkoholProcent, dato, antalLiter, note, titel);
        return modningsRegistrering;
    }

    public Destillering createDestillering(String newMakeNr, LocalDate startDato, LocalDate slutDato, double maengdeVaeske, double alkoholProcent, String rygeMateriale, String kommentar, MaltBatch maltBatch, Medarbejder medarbejder){
        Destillering destillering = new Destillering(newMakeNr, startDato, slutDato, maengdeVaeske, alkoholProcent, rygeMateriale, kommentar, maltBatch, medarbejder);
        storage.addDestillering(destillering);
        return destillering;
    }


}
