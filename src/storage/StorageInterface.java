package storage;

import model.Destillering;
import model.Fad;
import model.Lager;

import java.util.ArrayList;

public interface StorageInterface {
    public void addDestillering(Destillering destillering);

    public void addFad(Fad fad);

    public void addLager(Lager lager);

    public ArrayList<Destillering> getDestilleringer();

    public ArrayList<Fad> getFade();

    public ArrayList<Lager> getLagre();
}
