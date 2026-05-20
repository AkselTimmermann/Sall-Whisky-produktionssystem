package whiskyProduktTests;

import model.FadIndhold;
import model.ProduktRegistrering;
import model.WhiskyProdukt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Test_createProduktRegistrering {
    private AutoCloseable closeable;
    private WhiskyProdukt whiskyProdukt;
    @Mock private FadIndhold fadIndhold;

    @BeforeEach
    void setup() {
        whiskyProdukt = new WhiskyProdukt("nyWhisky", 1, "Smager godt", LocalDate.of(2026, 12, 24), 0);
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduktRegistrering_FadindholdNull_Exception() {
        //Arrange
        double antalLiter = 100;
        Mockito.when(fadIndhold.getResterendeLiter()).thenReturn(100.0);
        Mockito.when(fadIndhold.isLagretMinimum3Aar(whiskyProdukt.getDato())).thenReturn(true);

        //Act & Assert
        assertThrows(IllegalArgumentException.class,()->whiskyProdukt.createProduktRegistrering(antalLiter,null));
    }

    @Test
    void testCreateProduktRegistrering_antalLiterMindreEnd0_Exception() {
        //Arrange
        double antalLiter = -0.5;
        Mockito.when(fadIndhold.getResterendeLiter()).thenReturn(100.0);
        Mockito.when(fadIndhold.isLagretMinimum3Aar(whiskyProdukt.getDato())).thenReturn(true);

        //Act & Assert
        assertThrows(IllegalArgumentException.class,()->whiskyProdukt.createProduktRegistrering(antalLiter,fadIndhold));
    }

    @Test
    void testCreateProduktRegistrering_ikkeNokLiterIFadindhold_Exception() {
        //Arrange
        double antalLiter = 100.5;
        Mockito.when(fadIndhold.getResterendeLiter()).thenReturn(100.0);
        Mockito.when(fadIndhold.isLagretMinimum3Aar(whiskyProdukt.getDato())).thenReturn(true);

        //Act & Assert
        assertThrows(IllegalArgumentException.class,()->whiskyProdukt.createProduktRegistrering(antalLiter,fadIndhold));
    }

    @Test
    void testCreateProduktRegistrering_ErLagretForKortTid_Exception() {
        //Arrange
        double antalLiter = 100;
        Mockito.when(fadIndhold.getResterendeLiter()).thenReturn(100.0);
        Mockito.when(fadIndhold.isLagretMinimum3Aar(whiskyProdukt.getDato())).thenReturn(false);

        //Act & Assert
        assertThrows(IllegalArgumentException.class,()->whiskyProdukt.createProduktRegistrering(antalLiter,fadIndhold));
    }

    @Test
    void testCreateProduktRegistrering_NormalErTilføjetTomListe() {
        //Arrange
        double antalLiter = 100;
        Mockito.when(fadIndhold.getResterendeLiter()).thenReturn(100.0);
        Mockito.when(fadIndhold.isLagretMinimum3Aar(whiskyProdukt.getDato())).thenReturn(true);

        //Act
        ProduktRegistrering produktRegistrering = whiskyProdukt.createProduktRegistrering(antalLiter,fadIndhold);

        //Assert
        assertSame(produktRegistrering,whiskyProdukt.getProduktRegistreringer().getLast());
    }

    @Test
    void testCreateProduktRegistrering_NormalErTilføjetListe() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        double antalLiter = 100;
        Mockito.when(fadIndhold.getResterendeLiter()).thenReturn(100.0);
        Mockito.when(fadIndhold.isLagretMinimum3Aar(whiskyProdukt.getDato())).thenReturn(true);
        ArrayList<ProduktRegistrering> mockedProduktRegistreringer = new ArrayList<>();
        mockedProduktRegistreringer.add(Mockito.mock(ProduktRegistrering.class));
        mockedProduktRegistreringer.add(Mockito.mock(ProduktRegistrering.class));

        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt,mockedProduktRegistreringer);

        //Act
        ProduktRegistrering produktRegistrering = whiskyProdukt.createProduktRegistrering(antalLiter,fadIndhold);

        //Assert
        assertSame(produktRegistrering,whiskyProdukt.getProduktRegistreringer().getLast());
    }


}
