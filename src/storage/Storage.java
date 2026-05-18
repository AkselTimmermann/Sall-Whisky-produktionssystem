package storage;

import model.*;

import java.util.ArrayList;

public class Storage implements StorageInterface {
    private ArrayList<Destillering> destilleringer = new ArrayList<>();
    private ArrayList<Fad> fade = new ArrayList<>();
    private ArrayList<Lager> lagre = new ArrayList<>();
    private ArrayList<Leverandoer> leverandoerer = new ArrayList<>();
    private ArrayList<MaltBatch> maltBatches = new ArrayList<>();
    private ArrayList<Medarbejder> medarbejderer = new ArrayList<>();
    private ArrayList<Destillat> destillater = new ArrayList<>();
    private ArrayList<FlaskeSamling> flaskeSamlinger = new ArrayList<>();
    private ArrayList<WhiskyProdukt> whiskyProdukter = new ArrayList<>();

    private ArrayList<FadIndhold> fadIndholdListe = new ArrayList<>();
    private ArrayList<PaafyldningsRegistrering> paafyldningsRegistreringer = new ArrayList<>();


    public void addDestillering(Destillering destillering) {
        if (!destilleringer.contains(destillering)) {
            destilleringer.add(destillering);
        }
    }


    public void addFad(Fad fad) {
        if (!fade.contains(fad)) {
            fade.add(fad);
        }
    }

    public void addLager(Lager lager) {
        if (!lagre.contains(lager)) {
            lagre.add(lager);
        }
    }

    public void addLeverandoer(Leverandoer leverandoer) {
        if (!leverandoerer.contains(leverandoer)) {
            leverandoerer.add(leverandoer);
        }
    }
    public void addMaltBatch(MaltBatch maltBatch) {
        if (!maltBatches.contains(maltBatch)) {
            maltBatches.add(maltBatch);
        }
    }
    public void addMedarbejder(Medarbejder medarbejder) {
        if (!medarbejderer.contains(medarbejder)) {
            medarbejderer.add(medarbejder);
        }
    }
    public void addDestillat(Destillat destillat) {
        if (!destillater.contains(destillat)) {
            destillater.add(destillat);
        }
    }

    public void addFadIndhold(FadIndhold fadIndhold) {
        if (!fadIndholdListe.contains(fadIndhold)) {
            fadIndholdListe.add(fadIndhold);
        }
    }

    public void addPaafyldningsRegistrering(PaafyldningsRegistrering paafyldningsRegistrering) {
        if (!paafyldningsRegistreringer.contains(paafyldningsRegistrering)) {
            paafyldningsRegistreringer.add(paafyldningsRegistrering);
        }
    }

    @Override
    public void addFlaskesamling(FlaskeSamling flaskeSamling) {
        if (!flaskeSamlinger.contains(flaskeSamling)) {
            flaskeSamlinger.add(flaskeSamling);
        }
    }

    @Override
    public void addWhiskyProdukt(WhiskyProdukt whiskyProdukt) {
        if(!whiskyProdukter.contains(whiskyProdukt)) {
            whiskyProdukter.add(whiskyProdukt);
        }
    }

    public ArrayList<Destillering> getDestilleringer() {
        return new ArrayList<Destillering>(destilleringer);
    }


    public ArrayList<Fad> getFade() {
        return new ArrayList<Fad>(fade);
    }

    public ArrayList<Lager> getLagre() {
        return new ArrayList<Lager>(lagre);
    }

    @Override
    public ArrayList<Leverandoer> getLeverandoer() {
        return new ArrayList<>(leverandoerer);
    }

    @Override
    public ArrayList<Medarbejder> getMedarbejder() {
        return new ArrayList<>(medarbejderer);
    }

    @Override
    public ArrayList<MaltBatch> getMaltBatch() {
        return new ArrayList<>(maltBatches);
    }

    @Override
    public ArrayList<Destillat> getDestillat() {
        return new ArrayList<>(destillater);
    }

    public ArrayList<FadIndhold> getFadIndholdListe() {
        return new ArrayList<>(fadIndholdListe);
    }

    public ArrayList<PaafyldningsRegistrering> getPaafyldningsRegistreringer() {
        return new ArrayList<>(paafyldningsRegistreringer);
    }

    @Override
    public ArrayList<FlaskeSamling> getFlaskesamling() {
        return new ArrayList<>(flaskeSamlinger);
    }

    @Override
    public ArrayList<WhiskyProdukt> getWhiskyProdukt() {
        return new ArrayList<>(whiskyProdukter);
    }

    @Override
    public ArrayList<Paafyldningsvaeske> getPaafyldningsVaesker() {
        ArrayList<Paafyldningsvaeske> paafyldningsvaesker = new ArrayList<>();
        paafyldningsvaesker.addAll(getDestillat());
        paafyldningsvaesker.addAll(getFadIndholdListe());
        return paafyldningsvaesker;
    }


}
