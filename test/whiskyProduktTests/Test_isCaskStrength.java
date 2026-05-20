package whiskyProduktTests;

import model.WhiskyProdukt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class Test_isCaskStrength {
    private WhiskyProdukt whiskyProdukt;

    @Test
    void testIsCaskStrength_True0Fortynding(){
        //Arrange
        whiskyProdukt = new WhiskyProdukt("nyWhisky", 1, "Smager godt", LocalDate.of(2026, 12, 24), 0);

        //Act
        boolean isCaskStrength = whiskyProdukt.isCaskStrength();

        //Assert
        Assertions.assertTrue(isCaskStrength);
    }

    @Test
    void testIsCaskStrength_False01Fortynding(){
        //Arrange
        whiskyProdukt = new WhiskyProdukt("nyWhisky", 1, "Smager godt", LocalDate.of(2026, 12, 24), 0.1);

        //Act
        boolean isCaskStrength = whiskyProdukt.isCaskStrength();

        //Assert
        Assertions.assertFalse(isCaskStrength);
    }


}
