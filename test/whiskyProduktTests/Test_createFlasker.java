package whiskyProduktTests;

import model.Flaske;
import model.FlaskeSamling;
import model.WhiskyProdukt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Test_createFlasker {
    private AutoCloseable closeable;
    private WhiskyProdukt whiskyProdukt;
    @Mock private FlaskeSamling flaskeSamling;

    @BeforeEach
    void setup() {
        whiskyProdukt = new WhiskyProdukt("nyWhisky",1,"Smager godt", LocalDate.of(2026,12,24),0);
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFlasker_Stoerrelse0_Exception(){
        //Arrange
        double stoerrelse = 0;
        int antal = 10;

        //Act & Assert
        assertThrows(IllegalArgumentException.class,()->whiskyProdukt.createFlasker(stoerrelse,antal,flaskeSamling));
    }

    @Test
    void testCreateFlasker_AntalFlasker0_Exception(){
        //Arrange
        double stoerrelse = 10;
        int antal = 0;

        //Act & Assert
        assertThrows(IllegalArgumentException.class,()->whiskyProdukt.createFlasker(stoerrelse,antal,flaskeSamling));
    }
@Test
    void testCreateFlasker_Opret1LilleFlaske(){
        //Arrange
        double stoerrelse = 0.1;
        int antal = 1;
        int antalFlaskerTidligereOprettet = whiskyProdukt.getFlasker().size();

        //Act
        ArrayList<Flaske> flaskerOprettet = whiskyProdukt.createFlasker(stoerrelse,antal,flaskeSamling);
        ArrayList<Flaske> flaskerPaaWhiskyprodukt = new ArrayList<>();
        for (int i = antalFlaskerTidligereOprettet; i < antalFlaskerTidligereOprettet+antal; i++) {
            flaskerPaaWhiskyprodukt.add(whiskyProdukt.getFlasker().get(i));
        }
        //Assert
        for (int i = 0; i < 1; i++) {
            assertSame(flaskerPaaWhiskyprodukt.get(i), flaskerOprettet.get(i));
        }
    }

    @Test
    void testOpretFlasker_100StoreFlasker(){
        //Arrange
        double stoerrelse = 0.75;
        int antal = 100;
        int antalFlaskerTidligereOprettet = whiskyProdukt.getFlasker().size();

        //Act
        ArrayList<Flaske> flaskerOprettet = whiskyProdukt.createFlasker(stoerrelse,antal,flaskeSamling);
        ArrayList<Flaske> flaskerPaaWhiskyprodukt = new ArrayList<>();
        for (int i = antalFlaskerTidligereOprettet; i < antalFlaskerTidligereOprettet+antal; i++) {
            flaskerPaaWhiskyprodukt.add(whiskyProdukt.getFlasker().get(i));
        }
        //Assert
        for (int i = 0; i < flaskerOprettet.size(); i++) {
            assertSame(flaskerPaaWhiskyprodukt.get(i), flaskerOprettet.get(i));
        }
    }

    @Test
    void testOpretFlaskerNaarDerAlleredeErOprettetFlasker(){
        //Arrange
        double stoerrelse = 0.75;
        int antal = 100;
        whiskyProdukt.createFlasker(stoerrelse,antal,flaskeSamling);
        int antalFlaskerTidligereOprettet = whiskyProdukt.getFlasker().size();

        //Act

        ArrayList<Flaske> flaskerOprettet = whiskyProdukt.createFlasker(stoerrelse,antal,flaskeSamling);
        ArrayList<Flaske> flaskerPaaWhiskyprodukt = new ArrayList<>();
        for (int i = antalFlaskerTidligereOprettet; i < antalFlaskerTidligereOprettet+antal; i++) {
            flaskerPaaWhiskyprodukt.add(whiskyProdukt.getFlasker().get(i));
        }
        //Assert
        for (int i = 0; i < flaskerOprettet.size(); i++) {
            assertSame(flaskerPaaWhiskyprodukt.get(i), flaskerOprettet.get(i));
        }
    }

}
