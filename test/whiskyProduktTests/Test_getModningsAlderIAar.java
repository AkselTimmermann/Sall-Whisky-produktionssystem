package whiskyProduktTests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.util.ArrayList;

public class Test_getModningsAlderIAar {
    FadIndhold fadIndhold1;
    WhiskyProdukt whiskyProdukt1;
    Fad fad1;
    Leverandoer leverandoer1;
    Destillat destillat1;
    Medarbejder medarbejder1;
    Destillering destillering1;
    MaltBatch maltBatch1;
    Fad fad2;
    FadIndhold fadIndhold2;
    Destillat destillat2;
    Destillering destillering2;

    @BeforeEach
    void setUp() {
        medarbejder1 = new Medarbejder("Mads", 1);
        maltBatch1 = new MaltBatch("Mark", "byg", "1");

        leverandoer1 = new Leverandoer("Mark", "Århus");
        fad1 = new Fad("12", "eg", "egetræ", 100, leverandoer1);
        fadIndhold1 = new FadIndhold(fad1);

        whiskyProdukt1 = new WhiskyProdukt(
                "Whisky",
                1,
                "ikke relevant",
                LocalDate.of(2026,6,24),
                0
        );

        destillering1 = new Destillering(
                "1",
                LocalDate.of(2016,6,24),
                LocalDate.of(2016,6,25),
                100,
                40,
                "Ingen",
                "Ikke relevant",
                maltBatch1,
                medarbejder1
        );

        ArrayList<Destillering> destilleringer = new ArrayList<>();
        destilleringer.add(destillering1);

        double[] antalLiter = {20.0};

        destillat1 = new Destillat("1", destilleringer, antalLiter);
    }
    @Test
    void TC1_getModningsAlderIAar_1FadIndhold() {
        // Arrange
        fadIndhold1.opretModningsRegistrering(40, LocalDate.of(2026,6,24),20, "ikke relevant", "ikke relevant");
        fadIndhold1.opretPaafyldningsRegistrering(20,LocalDate.of(2016,6,24), destillat1, medarbejder1);

        whiskyProdukt1.createProduktRegistrering(1, fadIndhold1);

        // Act
        int alder = whiskyProdukt1.getModningsAlderIAar();

        // Assert
        assertEquals(10, alder);
    }

    @Test
    void TC2_getModningsAlderIAar_2Fadindhold() {

        // Fadindhold 1 - 10 år gammelt
        fadIndhold1.opretPaafyldningsRegistrering(
                20,
                LocalDate.of(2016,6,24),
                destillat1,
                medarbejder1
        );

        fadIndhold1.opretModningsRegistrering(
                40,
                LocalDate.of(2026,6,24),
                20,
                "ikke relevant",
                "ikke relevant"
        );

        whiskyProdukt1.createProduktRegistrering(1, fadIndhold1);



        // Fadindhold 2 - 3 år gammelt
        ArrayList<Destillering> destilleringer2 = new ArrayList<>();
        destillering2 = new Destillering(
                "2",
                LocalDate.of(2016,6,24),
                LocalDate.of(2016,6,25),
                100,
                40,
                "Ingen",
                "Ikke relevant",
                maltBatch1,
                medarbejder1
        );
        destilleringer2.add(destillering2);

        double[] antalLiter2 = {20.0};

        destillat2 = new Destillat("2", destilleringer2, antalLiter2);

        Leverandoer leverandoer2 = new Leverandoer("Hans", "Aalborg");
        Fad fad2 = new Fad("13", "sherry", "egetræ", 100, leverandoer2);

        fadIndhold2 = new FadIndhold(fad2);

        fadIndhold2.opretPaafyldningsRegistrering(
                20,
                LocalDate.of(2022,6,24),
                destillat2,
                medarbejder1
        );

        fadIndhold2.opretModningsRegistrering(
                40,
                LocalDate.of(2026,7,25),
                20,
                "ikke relevant",
                "ikke relevant"
        );

        whiskyProdukt1.createProduktRegistrering(1, fadIndhold2);

        // Act
        int alder = whiskyProdukt1.getModningsAlderIAar();

        // Assert
        assertEquals(4, alder);
    }

}