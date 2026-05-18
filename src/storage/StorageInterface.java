package storage;

import model.*;

import java.util.ArrayList;

public interface StorageInterface {
    public void addDestillering(Destillering destillering);

    public void addFad(Fad fad);

    public void addLager(Lager lager);

    public void addLeverandoer(Leverandoer leverandoer);

    public void addMedarbejder (Medarbejder medarbejder);

    public void addMaltBatch(MaltBatch maltBatch);

    public void addDestillat(Destillat destillat);

    public void addFadIndhold(FadIndhold fadIndhold);

    public void addPaafyldningsRegistrering(PaafyldningsRegistrering paafyldningsRegistrering);

    public void addFlaskesamling(FlaskeSamling flaskeSamling);

    public void addWhiskyProdukt(WhiskyProdukt whiskyProdukt);

    public ArrayList<Destillering> getDestilleringer();

    public ArrayList<Fad> getFade();

    public ArrayList<Lager> getLagre();

    public ArrayList<Leverandoer> getLeverandoer();

    public ArrayList<Medarbejder> getMedarbejder();

    public ArrayList<MaltBatch> getMaltBatch();

    public ArrayList<Destillat> getDestillat();

    public ArrayList<FadIndhold> getFadIndholdListe();

    public ArrayList<PaafyldningsRegistrering> getPaafyldningsRegistreringer();

    public ArrayList<FlaskeSamling> getFlaskesamling();

    public ArrayList<WhiskyProdukt> getWhiskyProdukt();

    public ArrayList<Paafyldningsvaeske> getPaafyldningsVaesker();
}
