package storage;

import model.Destillering;
import model.Fad;
import model.Lager;

import java.util.ArrayList;

public class Storage implements StorageInterface {
    private ArrayList<Destillering> destilleringer;
    private ArrayList<Fad> fade;
    private ArrayList<Lager> lagre;


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

    public ArrayList<Destillering> getDestilleringer() {
        return new ArrayList<Destillering>(destilleringer);
    }

    public ArrayList<Fad> getFade() {
        return new ArrayList<Fad>(fade);
    }

    public ArrayList<Lager> getLagre() {
        return new ArrayList<Lager>(lagre);
    }
}
