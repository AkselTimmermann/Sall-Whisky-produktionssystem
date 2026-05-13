package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class WhiskyProdukt {
    private int produktNr;
    private String beskrivelse;
    private LocalDate dato;
    private double fotynding;
    private ArrayList<Flaske> flasker = new ArrayList<>();
    private ArrayList<ProduktRegistrering> produktRegistreringer = new ArrayList<>();

    public WhiskyProdukt(int produktNr, String beskrivelse, LocalDate dato, double fotynding, ArrayList<ProduktRegistrering> produktRegistreringer) {
        this.produktNr = produktNr;
        this.beskrivelse = beskrivelse;
        this.dato = dato;
        this.fotynding = fotynding;
        this.produktRegistreringer = produktRegistreringer;
    }

    public boolean isCaskStrength(){
        return false;
    }

    public WhiskyType isWhiskyType(){
        return null;
    }

    public int maksAntalFlasker(double stoerrelse){
        return 0;
    }

    public double beregnAlkoholProcent(){
        return 0;
    }

    public double samletAntalLiter(){
        return 0;
    }

    public ArrayList<ProduktRegistrering> getProduktRegistreringer() {
        return new ArrayList<>(produktRegistreringer);
    }


}
