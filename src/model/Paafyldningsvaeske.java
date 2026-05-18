package model;

import java.util.ArrayList;

public interface Paafyldningsvaeske {
    void addPaafyldningsRegistreringTil(PaafyldningsRegistrering paafyldningsRegistrering);

    ArrayList<Destillering> getDestilleringer();

    void reducerResterendeLiter(double antalLiter);

    double getAlkoholProcent();

    ArrayList<PaafyldningsRegistrering> getPaafyldningsRegistreringerTil();

    double getAntalLiter();
}
