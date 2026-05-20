package whiskyProduktTests;

import model.WhiskyProdukt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class Test_Constructor {
    private WhiskyProdukt whiskyProdukt;
    private WhiskyProdukt whiskyProduktAnden;

    @Test
    void testConstructor_negativFortynding_Exception(){
        //Arrange
        double fortynding = -1;

        //Act & Assert

        Assertions.assertThrows(IllegalArgumentException.class,()->whiskyProdukt = new WhiskyProdukt("nyWhisky", 1, "Smager godt", LocalDate.of(2026, 12, 24), fortynding));
    }

    @Test
    void testConstructor_whiskyProduktOprettet(){
        //Arrange
        double fortynding = 0;

        //Act
        whiskyProdukt = new WhiskyProdukt("nyWhisky", 1, "Smager godt", LocalDate.of(2026, 12, 24), fortynding);
        whiskyProduktAnden = new WhiskyProdukt("nyWhisky", 1, "Smager godt", LocalDate.of(2026, 12, 24), fortynding);
        //Assert
        Assertions.assertInstanceOf(WhiskyProdukt.class, whiskyProdukt);
        Assertions.assertNotEquals(whiskyProdukt, whiskyProduktAnden);
    }
}
