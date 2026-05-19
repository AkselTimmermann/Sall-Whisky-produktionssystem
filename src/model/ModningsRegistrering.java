package model;

import java.time.LocalDate;

public class ModningsRegistrering {
    private double alkoholProcent;
    private LocalDate dato;
    private double antalLiter;
    private String note;
    private String titel;

    public ModningsRegistrering(double alkoholProcent, LocalDate dato, double antalLiter, String note, String titel) {
        if (alkoholProcent<0){
            throw new IllegalArgumentException("Du kan ikke have negativ alkoholprocent");
        }
        if (alkoholProcent>100){
            throw new IllegalArgumentException("Du kan ikke have en alkoholprocent på mere end 100%");
        }
        if (antalLiter<0){
            throw new IllegalArgumentException("Der skal være mere mindst 0 liter tilbage");
        }
        this.alkoholProcent = alkoholProcent;
        this.dato = dato;
        this.antalLiter = antalLiter;
        this.note = note;
        this.titel = titel;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public void reducerLiter(double liter) {
        if (liter<=0){
            if (antalLiter <= 0) {
                throw new IllegalArgumentException("liter skal være større end 0");
            }
        }
        if (antalLiter < liter){
            throw new IllegalArgumentException("Der er kun " + antalLiter + " tilbage, så der kan ikke reduceres med " + liter);
        }
        antalLiter -= liter;
    }

    public LocalDate getDato() {
        return dato;
    }

    public double getAntalLiter() {
        return antalLiter;
    }

    public void addAntalLiter(double antalLiter) {
        this.antalLiter += antalLiter;
    }
}
