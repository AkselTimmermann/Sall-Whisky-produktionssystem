package controller;

import model.*;
import storage.StorageInterface;

import java.time.LocalDate;
import java.util.ArrayList;

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

    public Fad createFad(String fadId, String traeType, String beskrivelse, int stoerrelse, Leverandoer leverandoer){
        Fad fad = new Fad(fadId, traeType, beskrivelse, stoerrelse, leverandoer);
        storage.addFad(fad);
        return fad;
    }


    public PaafyldningsRegistrering createPaafyldningsRegistrering(double antalLiter, LocalDate dato, Destillat destillat, Fad fad, Medarbejder medarbejder) {
        PaafyldningsRegistrering paafyldningsRegistrering = fad.opretPaafyldningsRegistrering(antalLiter, dato, destillat, medarbejder);
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

    public Leverandoer createLeverandoer(String navn, String lokation) {
        Leverandoer leverandoer = new Leverandoer(navn, lokation);
        storage.addLeverandoer(leverandoer);
        return leverandoer;
    }
    public MaltBatch createMaltBatch(String kornMark, String bygSort, String batchNr) {
        MaltBatch maltBatch = new MaltBatch(kornMark, bygSort, batchNr);
        storage.addMaltBatch(maltBatch);
        return maltBatch;
    }
    public Medarbejder createMedarbejder(String navn, int medArbejderNr) {
        Medarbejder medarbejder = new Medarbejder(navn, medArbejderNr);
        storage.addMedarbejder(medarbejder);
        return medarbejder;
    }
    public Destillat createDestillat(String destillatNr, ArrayList<Destillering> destilleringer, int[] antalLiterAfHverDestillering) {
        Destillat destillat = new Destillat(destillatNr, destilleringer, antalLiterAfHverDestillering);
        storage.addDestillat(destillat);
        return destillat;
    }

    public ArrayList<Leverandoer> getLeverandoer() {
        return storage.getLeverandoer();
    }

    public ArrayList<Destillering> getDestilleringer() {
        return storage.getDestilleringer();
    }

    public ArrayList<Fad> getFade() {
        return storage.getFade();
    }

    public ArrayList<Lager> getLagre() {
        return storage.getLagre();
    }

    public ArrayList<Destillat> getDestillater() {
        return storage.getDestillat();
    }

    public ArrayList<Medarbejder> getMedarbejdere() {
        return storage.getMedarbejder();
    }

    public ArrayList<MaltBatch> getMaltBatches() {
        return storage.getMaltBatch();
    }
}
