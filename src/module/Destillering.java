package module;

import java.time.LocalDate;

public class Destillering {

    private String newMakeNr;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String maltBatch;
    private String kornsort;
    private double maengdeVaeske;
    private double alkoholProcent;
    private String rygeMateriale;
    private String kommentar;

    // Kommentar
    public Destillering(String newMakeNr, LocalDate startDato, LocalDate slutDato,
                        String maltBatch, String kornsort, double maengdeVaeske,
                        double alkoholProcent, String rygeMateriale, String kommentar) {

        this.newMakeNr = newMakeNr;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.maengdeVaeske = maengdeVaeske;
        this.alkoholProcent = alkoholProcent;
        this.rygeMateriale = rygeMateriale;
        this.kommentar = kommentar;
    }

    public String getNewMakeNr() {
        return newMakeNr;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public String getMaltBatch() {
        return maltBatch;
    }

    public String getKornsort() {
        return kornsort;
    }

    public double getMaengdeVaeske() {
        return maengdeVaeske;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public String getRygeMateriale() {
        return rygeMateriale;
    }

    public String getKommentar() {
        return kommentar;
    }
}
