package controller;

import model.*;
import storage.Storage;
import storage.StorageInterface;

import java.time.LocalDate;

public class Controller {
    private StorageInterface storage;

    Controller(StorageInterface storage){
    this.storage = storage;
    }

    public Lager createLager(String navn, String lokation, int kapacitet){
        return null;
    }

    public Reol createReol(int reolNr){
        return null;
    }

    public Hylde createHylde(int hyldeNr){
        return null;
    }

    public LagerPlads createLagerPlads(int pladsNr){
        return null;
    }

    public Fad createFad(String fadId, String traeType, String beskrivelse, int stoerrelse, FadStatus status){
        return null;
    }

    //Mangler Modningsregistrering og paafyldningsregistrering

    public Destillering createDestillering(String newMakeNr, LocalDate startDato, LocalDate slutDato, String maltBatch, String kornSort, double maengdeVaeske, double alkoholProcent, String rygeMateriale, String kommentar){
        return null;
    }


}
