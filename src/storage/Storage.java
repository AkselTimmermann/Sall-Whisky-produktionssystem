package storage;

import model.Destillering;
import model.Fad;
import model.Lager;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<Destillering> destilleringer;
    private static ArrayList<Fad> fade;
    private static ArrayList<Lager> lagre;


    public static void addDestillering(Destillering destillering) {
        if (!destilleringer.contains(destillering)) {
            destilleringer.add(destillering);
        }
    }

    public static void addFad(Fad fad) {
        if (!fade.contains(fad)) {
            fade.add(fad);
        }
    }

    public static void addLager(Lager lager) {
        if (!lagre.contains(lager)) {
            lagre.add(lager);
        }
    }

    public static ArrayList<Destillering> getDestilleringer() {
        return new ArrayList<Destillering>(destilleringer);
    }

    public static ArrayList<Fad> getFade() {
        return new ArrayList<Fad>(fade);
    }

    public static ArrayList<Lager> getLagre() {
        return new ArrayList<Lager>(lagre);
    }
}
