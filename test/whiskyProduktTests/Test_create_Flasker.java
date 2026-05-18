package whiskyProduktTests;

import model.FlaskeSamling;
import model.WhiskyProdukt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test_create_Flasker {
    private AutoCloseable closeable;
    private WhiskyProdukt whiskyProdukt;
    @Mock private FlaskeSamling flaskeSamling;

    @BeforeEach
    void setup() {
        whiskyProdukt = new WhiskyProdukt("nyWhisky",1,"Smager godt", LocalDate.of(2026,12,24),0);
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExceptionStoerrelse0(){
        //Arrange
        double stoerrelse = 0;
        int antal = 10;

        assertThrows(IllegalArgumentException.class,()->whiskyProdukt.createFlasker(stoerrelse,antal,flaskeSamling));
    }

    @Test
    void testExceptionAntalFlasker0(){
        //Arrange
        double stoerrelse = 10;
        int antal = 0;

        assertThrows(IllegalArgumentException.class,()->whiskyProdukt.createFlasker(stoerrelse,antal,flaskeSamling));
    }

}
