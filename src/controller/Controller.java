package controller;

import model.*;
import storage.StorageInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Controller {
    private StorageInterface storage;

    public Controller(StorageInterface storage){
    this.storage = storage;
    }

    public void placerObjekt(LagerObjekt objekt, LagerPlads plads) {
        if (objekt == null) {
            throw new IllegalArgumentException("Vælg et lagerobjekt");
        }
        if (plads == null) {
            throw new IllegalArgumentException("Vælg en lagerplads");
        }
        plads.placerIndhold(objekt);
    }

    //Returnere en sorteret liste med ældste registrering øverst
    public ArrayList<FadIndhold> getFadeSorteretEfterModningsDato() {
        ArrayList<FadIndhold> liste = storage.getFadIndholdListe();

        liste.sort(Comparator.comparing(fadIndhold -> fadIndhold.getModningsRegistreringer().getLast().getDato()));

        return liste;
    }

    public ArrayList<Fad> getFadeUdenPlacering() {
        ArrayList<Fad> fadeUdenPlacering = new ArrayList<>();
        for (Fad fad : storage.getFade()) {
            if (fad.getLagerPlads() == null) {
                fadeUdenPlacering.add(fad);
            }
        }
        return fadeUdenPlacering;
    }

    public ArrayList<FlaskeSamling> getFlaskeSamlingUdenPlacering() {
        ArrayList<FlaskeSamling> flaskeSamlingUdenPlacering = new ArrayList<>();
        for (FlaskeSamling flaskeSamling : storage.getFlaskesamling()) {
            if (flaskeSamling.getLagerPlads() == null) {
                flaskeSamlingUdenPlacering.add(flaskeSamling);
            }
        }
        return flaskeSamlingUdenPlacering;
    }

    public ArrayList<LagerPlads> getLagerPladser(Lager lager) {
        return lager.getLagretsPladser();
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

    public WhiskyProdukt createWhiskyProdukt(
            String navn,
            int produktNr,
            String beskrivelse,
            LocalDate dato,
            double fortynding,
            ArrayList<FadIndhold> fadIndholdListe,
            ArrayList<Double> literListe) {

        if (fadIndholdListe.size() != literListe.size()) {
            throw new IllegalArgumentException("Hvert fadindhold skal have en mængde.");
        }

        WhiskyProdukt whiskyProdukt = new WhiskyProdukt(
                navn,
                produktNr,
                beskrivelse,
                dato,
                fortynding);

        for (int i = 0; i < fadIndholdListe.size(); i++) {
            whiskyProdukt.createProduktRegistrering(
                    literListe.get(i),
                    fadIndholdListe.get(i));
        }

        storage.addWhiskyProdukt(whiskyProdukt);
        return whiskyProdukt;
    }


    public FlaskeSamling createFlaskeSamling() {
        int samlingsNr = storage.getFlaskesamling().size() + 1;
        FlaskeSamling flaskeSamling = new FlaskeSamling(samlingsNr);
        storage.addFlaskesamling(flaskeSamling);
        return flaskeSamling;
    }

    public PaafyldningsRegistrering createPaafyldningsRegistrering(double antalLiter, LocalDate dato, Destillat destillat, Fad fad, Medarbejder medarbejder) {
        FadIndhold fadIndhold = fad.getAktivtFadIndhold();

        if (fadIndhold == null) {
            fadIndhold = new FadIndhold(fad);
            storage.addFadIndhold(fadIndhold);
        }

        PaafyldningsRegistrering paafyldningsRegistrering = fadIndhold.opretPaafyldningsRegistrering(antalLiter, dato, destillat, medarbejder);

        fad.setStatus(FadStatus.AKTIV);
        storage.addPaafyldningsRegistrering(paafyldningsRegistrering);
        return paafyldningsRegistrering;
    }

    public ModningsRegistrering createModningsRegistrering(double alkoholProcent, LocalDate dato, double antalLiter, String note, String titel, FadIndhold fadIndhold) {
        ModningsRegistrering modningsRegistrering = fadIndhold.opretModningsRegistrering(alkoholProcent, dato, antalLiter, note, titel);
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
    public Destillat createDestillat(String destillatNr, ArrayList<Destillering> destilleringer, double[] antalLiterAfHverDestillering) {
        Destillat destillat = new Destillat(destillatNr, destilleringer, antalLiterAfHverDestillering);
        storage.addDestillat(destillat);
        return destillat;
    }

    public ArrayList<Flaske> registrerFlaskning(WhiskyProdukt whiskyProdukt, double stoerrelse, int antal) {
        ArrayList<Flaske> oprettedeFlasker = new ArrayList<>();
        int resterendeFlasker = antal;

        while (resterendeFlasker > 0) {
            FlaskeSamling flaskeSamling = createFlaskeSamling();

            int antalTilDenneSamling = Math.min(100, resterendeFlasker);

            oprettedeFlasker.addAll(whiskyProdukt.createFlasker(stoerrelse, antalTilDenneSamling, flaskeSamling));

            resterendeFlasker -= antalTilDenneSamling;
        }
        return oprettedeFlasker;
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

    public ArrayList<FadIndhold> getFadIndhold() {
        return storage.getFadIndholdListe();
    }

    public FadIndhold getAktivtFadIndhold(Fad fad) {
        return fad.getAktivtFadIndhold();
    }
}
