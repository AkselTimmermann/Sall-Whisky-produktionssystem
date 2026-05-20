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
import static org.mockito.Mockito.*;

public class Test_beregnAlkoholProcent {
    private WhiskyProdukt whiskyProdukt;
    private AutoCloseable closeable;



    @BeforeEach
    void setup() {
        whiskyProdukt = spy(new WhiskyProdukt("nyWhisky",1,"Smager godt", LocalDate.of(2026,12,24),0));
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBeregnAlkoholProcent_MereAlkoholEndVaeske_Exception(){
        //Arrange
        Mockito.when(whiskyProdukt.samletAntalLiter()).thenReturn( 20.0);
        Mockito.when(whiskyProdukt.samletAlkoholMaengde()).thenReturn(20.1);

        //Act and Assert
        assertThrows(RuntimeException.class,()->whiskyProdukt.beregnAlkoholProcent());
    }

    @Test
    void testBeregnAlkoholProcent_IngenVaeske() {
        //Arrange

        Mockito.when(whiskyProdukt.samletAntalLiter()).thenReturn((double) 0);
        Mockito.when(whiskyProdukt.samletAlkoholMaengde()).thenReturn(0.0);

        double alkoholProcent = whiskyProdukt.beregnAlkoholProcent();

        assertEquals(0,alkoholProcent);
    }

    @Test
    void testBeregnAlkoholProcent_Normal() {
        //Arrange

        Mockito.when(whiskyProdukt.samletAntalLiter()).thenReturn((double) 20);
        Mockito.when(whiskyProdukt.samletAlkoholMaengde()).thenReturn(5.0);

        //Act
        double alkoholProcent = whiskyProdukt.beregnAlkoholProcent();
        //Assert
        assertEquals(0.25,alkoholProcent);
    }
}
