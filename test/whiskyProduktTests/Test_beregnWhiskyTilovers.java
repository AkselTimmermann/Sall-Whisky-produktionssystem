package whiskyProduktTests;

import model.Flaske;
import model.WhiskyProdukt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class Test_beregnWhiskyTilovers {
    private WhiskyProdukt whiskyProdukt;
    private AutoCloseable closeable;



    @BeforeEach
    void setup() {
        whiskyProdukt = spy(new WhiskyProdukt("nyWhisky",1,"Smager godt", LocalDate.of(2026,12,24),0));
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBeregnWhiskyTilovers_StoerrelseMindreEndNul_exception() {
        //Arrange
        double stoerrelse = 0;
        int antalFlasker =10;
        Mockito.when(whiskyProdukt.samletAntalLiter()).thenReturn(10.0);
        Mockito.when(whiskyProdukt.antalLiterIFlasker()).thenReturn(5.0);

        //Act and assert

        assertThrows(RuntimeException.class,()->whiskyProdukt.beregnWhiskyTilovers(stoerrelse,antalFlasker));
    }

    @Test
    void testBeregnWhiskyTilovers_FlaskerFaerreEndNul_Exception() {
        //Arrange
        double stoerrelse = 0.5;
        int antalFlasker =0;
        Mockito.when(whiskyProdukt.samletAntalLiter()).thenReturn(10.0);
        Mockito.when(whiskyProdukt.antalLiterIFlasker()).thenReturn(5.0);

        //Act and assert

        assertThrows(RuntimeException.class,()->whiskyProdukt.beregnWhiskyTilovers(stoerrelse,antalFlasker));
    }

    @Test
    void testBeregnWhiskyTilovers_LidtNegativ() {
        //Arrange
        double stoerrelse = 0.5;
        int antalFlasker = 10;
        Mockito.when(whiskyProdukt.samletAntalLiter()).thenReturn(10.0);
        Mockito.when(whiskyProdukt.antalLiterIFlasker()).thenReturn(5.1);

        //Act
        double whiskyTilovers = whiskyProdukt.beregnWhiskyTilovers(stoerrelse,antalFlasker);

        //Assert
        //Pga. afrundinger i Java bliver vi nødt til at indføre en fejlmargin
        assertEquals(-0.1,whiskyTilovers,0.00000005);
    }

    @Test
    void testBeregnWhiskyTilovers_LidtPositiv() {
        //Arrange
        double stoerrelse = 0.5;
        int antalFlasker = 10;
        Mockito.when(whiskyProdukt.samletAntalLiter()).thenReturn(10.0);
        Mockito.when(whiskyProdukt.antalLiterIFlasker()).thenReturn(4.9);

        //Act
        double whiskyTilovers = whiskyProdukt.beregnWhiskyTilovers(stoerrelse,antalFlasker);

        //Pga. afrundinger i Java bliver vi nødt til at indføre en fejlmargin
        //Assert
        assertEquals(0.1,whiskyTilovers,0.000000005);
    }
}
