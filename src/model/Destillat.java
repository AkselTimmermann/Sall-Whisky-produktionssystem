package model;

import java.util.ArrayList;

public class Destillat {
    private int antalLiter;
    private double alkoholProcent;
    private String destillatNr;
    private ArrayList<PaafyldningsRegistrering> paafyldningsRegistreringer = new ArrayList<>();
    private ArrayList<Destillering> destilleringer = new ArrayList<>();

    public Destillat(double alkoholProcent, String destillatNr, ArrayList<Destillering> destilleringer, int[] antalLiterAfHverDestillat) {
        if (destilleringer.size()!=antalLiterAfHverDestillat.length){
            throw new IllegalArgumentException("Hver brugt destillering skal have angivet en mængde");
        }
        this.alkoholProcent = alkoholProcent;
        this.destillatNr = destillatNr;
        setDestilleringer(destilleringer);
        for (int i = 0; i < destilleringer.size(); i++) {
            destilleringer.get(i).reducerResterendeLiter(antalLiterAfHverDestillat[i]);
            updateAntalLiterAndAlkoholprocent(antalLiterAfHverDestillat[i],destilleringer.get(i).getAlkoholProcent());
        }
    }

    private void setDestilleringer(ArrayList<Destillering> destilleringer) {
        destilleringer.forEach(destillering -> addDestillering(destillering));
    }

    public void addDestillering(Destillering destillering){
        if (!destilleringer.contains(destillering)){
            destilleringer.add(destillering);
            destillering.addDestillat(this);
        }
    }



    private void updateAntalLiterAndAlkoholprocent(int maengde, double alkoholProcent){
        this.alkoholProcent = (this.antalLiter*this.alkoholProcent+maengde*alkoholProcent)/(this.antalLiter+maengde);
        this.antalLiter+=maengde;
    }
}
