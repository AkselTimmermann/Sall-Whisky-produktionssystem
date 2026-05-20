package whiskyProduktTests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class Test_getDistinctFadId {
    WhiskyProdukt whiskyProdukt;
    Fad fad1;
    FadIndhold fadIndhold1;
    FadIndhold fadIndhold2;
    Leverandoer leverandoer;
    Destillat destillat;
    Destillering destillering;
    Medarbejder medarbejder;
    MaltBatch maltBatch;

    @BeforeEach
    void setup() {
        leverandoer = new Leverandoer("Aksel", "Århus");
        medarbejder = new Medarbejder("Mads", 1);
        fad1 = new Fad("1", "Eg", "sherry", 100, leverandoer);
        fadIndhold1 = new FadIndhold(fad1);
        fadIndhold2 = new FadIndhold(fad1);
        maltBatch = new MaltBatch("Mark", "korn", "1");
        whiskyProdukt = new WhiskyProdukt("Whisky",1,"Ikke relevant", LocalDate.of(2026,6,24),0);
        ArrayList<Destillering> destilleringer = new ArrayList<>();
        destillering = new Destillering("1",LocalDate.of(2016,6,24), LocalDate.of(2016,6,25),1000,40,"Ingen", "ikke relevant", maltBatch, medarbejder);
        destilleringer.add(destillering);
        double[] antalLiter = {500};
        destillat = new Destillat("1", destilleringer, antalLiter);
        fadIndhold1.opretPaafyldningsRegistrering(10, LocalDate.of(2020,5,24), destillat, medarbejder);
        fadIndhold1.opretModningsRegistrering(40,LocalDate.of(2026,6,24),20,"irrelevant", "irrelevant");
        fadIndhold2.opretPaafyldningsRegistrering(20,LocalDate.of(2016,6,24),destillat,medarbejder);
        fadIndhold2.opretModningsRegistrering(40,LocalDate.of(2026,6,24),20,"irrelevant", "irrelevant");

    }

    @Test
    void TC1_getDistinctFade_2Produktregistreringer_sammeFad() {

        //Arrange
        whiskyProdukt.createProduktRegistrering(1, fadIndhold1);
        whiskyProdukt.createProduktRegistrering(1, fadIndhold2);
        //Act
        Set<String> fade = whiskyProdukt.getDistinctFadId();

        //Assert
        assertEquals(1, fade.size());
        assertTrue(fade.contains(fad1.getFadId()));

    }
    @Test
    void TC2_getDistinctFade_2Produktregistreringer_2fade() {
        //arrange
        Fad fad2 = new Fad("2", "eg", "bourbon",100, leverandoer);
        fadIndhold2 = new FadIndhold(fad2);
        fadIndhold2.opretPaafyldningsRegistrering(20,LocalDate.of(2016,6,24),destillat,medarbejder);
        fadIndhold2.opretModningsRegistrering(40,LocalDate.of(2026,6,24),20,"irrelevant", "irrelevant");

        whiskyProdukt.createProduktRegistrering(1, fadIndhold1);
        whiskyProdukt.createProduktRegistrering(1,fadIndhold2);

        //Act
        Set<String> fade = whiskyProdukt.getDistinctFadId();

        assertEquals(2, fade.size());
        assertTrue(fade.contains(fad1.getFadId()));
        assertTrue(fade.contains(fad2.getFadId()));
    }
}
