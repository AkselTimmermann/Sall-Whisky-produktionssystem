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


    public double getAntalLiter() {
        return antalLiter;
    }
}
