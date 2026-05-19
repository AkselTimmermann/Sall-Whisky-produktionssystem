package whiskyProduktTests;

import model.WhiskyProdukt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;

public class Test_maksAntalFlasker {
    private WhiskyProdukt whiskyProdukt;
    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        whiskyProdukt = spy(new WhiskyProdukt("nyWhisky",1,"Smager godt", LocalDate.of(2026,12,24),0));
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMaksAntalFlasker_stoerrelseMindreEnd0_Exception() {
        //Arrange
        double stoerrelse = 0;
        Mockito.when(whiskyProdukt.samletAntalLiter()).thenReturn(10.0);
        Mockito.when(whiskyProdukt.antalLiterIFlasker()).thenReturn(10.0);

        //Act and assert

        assertThrows(IllegalArgumentException.class,()->whiskyProdukt.maksAntalFlasker(stoerrelse));
    }

    @Test
    void testMaksAntalFlasker_RundNedTilHeleFlasker() {
        //Arrange
        double stoerrelse = 0.99;
        Mockito.when(whiskyProdukt.samletAntalLiter()).thenReturn(20.0);
        Mockito.when(whiskyProdukt.antalLiterIFlasker()).thenReturn(5.0);

        //Act
        int maksAntalFlasker = whiskyProdukt.maksAntalFlasker(stoerrelse);
        //Assert

        assertEquals(15,maksAntalFlasker);
    }
}
