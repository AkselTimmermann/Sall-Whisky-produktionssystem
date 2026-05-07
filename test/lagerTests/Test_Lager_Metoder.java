package lagerTests;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test_Lager_Metoder {
    private Lager l1;
    private Reol r1;
    private Hylde h1;
    private Hylde h2;
    private LagerPlads lp1;
    private LagerPlads lp2;
    private LagerPlads lp3;
    private LagerPlads lp4;

    private Fad fad1;
    private Fad fad2;


    @BeforeEach
    void setup() {
        l1 = new Lager("Laden", "Ladevej1", 100);
        r1 = new Reol(1);
        h1 = new Hylde(1);
        h2 = new Hylde(2);
        lp1 = new LagerPlads(1);
        lp2 = new LagerPlads(2);
        lp3 = new LagerPlads(3);
        lp4 = new LagerPlads(4);

        l1.addReol(r1);
        r1.addHylde(h1);
        r1.addHylde(h2);
        h1.addPlads(lp1);
        h1.addPlads(lp2);
        h2.addPlads(lp3);
        h2.addPlads(lp4);

        fad1 = new Fad("eg1", "Eg", "2 år gammelt", 50);
        fad2 = new Fad("Eg2", "eg", "2 år gammelt", 50);


    }

    //--------------------------
    //Test af findPlads metoden
    //--------------------------

    @Test
    void findPladsTC1_gyldigplads_returnererPlads() {
        //Arrange
        int reolNr = 1;
        int hyldeNr = 1;
        int pladsNr = 1;

        //Act
        LagerPlads plads = l1.findPlads(1, 1, 1);

        assertNotNull(plads);
        assertEquals(1, plads.getPladsNr());
    }
    @Test
    void findPladsTC2_ReolFindesIkke_CastException() {
        assertThrows(IllegalStateException.class, () -> l1.findPlads(99,1,1));
    }
    @Test
    void findPladsTC3_HyldeFindesIkke_CastException() {
        assertThrows(IllegalStateException.class, () -> l1.findPlads(1,99,1));
    }
    @Test
    void findPladsTC4_PladsFindesIkke_CastException() {
        assertThrows(IllegalStateException.class, () -> l1.findPlads(1,1,99));
    }
    @Test
    void findPladsTC5_UnderGraense_CastException() {
        assertThrows(IllegalStateException.class, () -> l1.findPlads(1,1,0));
    }
    @Test
    void findPladsTC6_OverGraense_CastException() {
        assertThrows(IllegalStateException.class, () -> l1.findPlads(1,1,5));
    }

    //----------------------------
    //Test af placerObjekt metoden
    //----------------------------

    @Test
    void placerObjektTC1_GyldigPlads_objektPlaceres() {
        //Arrange er i BeforeEach - fad oprettes

        //Act
        l1.placerObjekt(fad1,1,1,1);

        //Assert
        LagerPlads plads = l1.findPlads(1,1,1);
        assertEquals(fad1, plads.getIndhold());
    }
    @Test
    void placerObjektTC2_UgyldigPlads_PladsOptaget() {
        //Arrange er i BeforeEach - 2 fad er oprettet

        //Act
        l1.placerObjekt(fad1,1,1,1);

        //Assert
        assertThrows(IllegalStateException.class, () -> l1.placerObjekt(fad2,1,1,1));
    }

    @Test
    void placerObjektTC3_PlaceringFindesIkke_CastException() {
        //Arrange er i BeforeEach - fad oprettet

        //Act - Assert
        assertThrows(IllegalStateException.class, () -> l1.placerObjekt(fad1,1,1,99));
    }

    //---------------------------
    //Test af fjernObjekt metoden
    //---------------------------

    @Test
    void fjernObjektTC1_ObjektFindes_ObjektFjernes() {
        //Arrange
        l1.placerObjekt(fad1,1,1,1);
        //Act
        l1.fjernObjekt(fad1);
        //Assert
        assertNull(l1.findPlads(1,1,1).getIndhold());
    }
    @Test
    void fjernObjektTC2_ObjektFindesIkke_CastException() {
        assertThrows(IllegalStateException.class, () -> l1.fjernObjekt(fad1));
    }

    //-----------------------------
    //Test af findPlacering metoden
    //----------------------------
    @Test
    void findPlaceringTC1_ObjektFindes_Placeringprintes() {
        //Arrange
        l1.placerObjekt(fad1,1,1,1);

        //Act
        String placering = l1.findPlacering(fad1);

        //Assert
        assertEquals("Reol 1, Hylde 1, Plads 1", placering);
    }
    @Test
    void findPlaceringTC2_ObjektetFindesIkke_PlaceringIkkeFundet() {
        //Arrange er i BeforeEach

        //Act
        String placering = l1.findPlacering(fad1);

        //Assert
        assertEquals("Placering ikke fundet", placering);
    }

}
