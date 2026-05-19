package whiskyProduktTests;

import model.FadIndhold;
import model.ModningsRegistrering;
import model.ProduktRegistrering;
import model.WhiskyProdukt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;

public class Test_samletAlkoholMaengde {
    private AutoCloseable closeable;
    private WhiskyProdukt whiskyProdukt;
    @Mock private ProduktRegistrering mockProduktRegistrering1;
    @Mock private ProduktRegistrering mockProduktRegistrering2;
    @Mock private ProduktRegistrering mockProduktRegistrering3;
    @Mock private FadIndhold mockFadindhold1;
    @Mock private FadIndhold mockFadindhold2;
    @Mock private FadIndhold mockFadindhold3;
    ArrayList<ProduktRegistrering> mockedProduktRegistreringer = new ArrayList<>();

    @BeforeEach
    void setup(){
        closeable = MockitoAnnotations.openMocks(this);
        whiskyProdukt = new WhiskyProdukt("nyWhisky", 1, "Smager godt", LocalDate.of(2026, 12, 24), 0);
        mockedProduktRegistreringer.add(mockProduktRegistrering1);
        mockedProduktRegistreringer.add(mockProduktRegistrering2);
        mockedProduktRegistreringer.add(mockProduktRegistrering3);
    }

    @Test
    void testSamletAlkoholMaengde_lilleMaengde() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Mockito.when(mockProduktRegistrering1.getAntalLiter()).thenReturn(1.0);
        Mockito.when(mockProduktRegistrering2.getAntalLiter()).thenReturn(2.0);
        Mockito.when(mockProduktRegistrering3.getAntalLiter()).thenReturn(3.0);

        Mockito.when(mockProduktRegistrering1.getFadIndhold()).thenReturn(mockFadindhold1);
        Mockito.when(mockProduktRegistrering2.getFadIndhold()).thenReturn(mockFadindhold2);
        Mockito.when(mockProduktRegistrering3.getFadIndhold()).thenReturn(mockFadindhold3);
        Mockito.when(mockFadindhold1.getSidstRegistreredeAlkoholProcent()).thenReturn(40.0);
        Mockito.when(mockFadindhold2.getSidstRegistreredeAlkoholProcent()).thenReturn(50.0);
        Mockito.when(mockFadindhold3.getSidstRegistreredeAlkoholProcent()).thenReturn(60.0);

        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt,mockedProduktRegistreringer);

        //Act
        double samletAlkoholMaegde = whiskyProdukt.samletAlkoholMaengde();

        //Assert
        Assertions.assertEquals(3.2,samletAlkoholMaegde);
    }

    @Test
    void testSamletAlkoholMaengde_IngenProduktregistreringer() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Mockito.when(mockProduktRegistrering1.getAntalLiter()).thenReturn(1.0);
        Mockito.when(mockProduktRegistrering2.getAntalLiter()).thenReturn(2.0);
        Mockito.when(mockProduktRegistrering3.getAntalLiter()).thenReturn(3.0);

        Mockito.when(mockProduktRegistrering1.getFadIndhold()).thenReturn(mockFadindhold1);
        Mockito.when(mockProduktRegistrering2.getFadIndhold()).thenReturn(mockFadindhold2);
        Mockito.when(mockProduktRegistrering3.getFadIndhold()).thenReturn(mockFadindhold3);
        Mockito.when(mockFadindhold1.getSidstRegistreredeAlkoholProcent()).thenReturn(40.0);
        Mockito.when(mockFadindhold2.getSidstRegistreredeAlkoholProcent()).thenReturn(50.0);
        Mockito.when(mockFadindhold3.getSidstRegistreredeAlkoholProcent()).thenReturn(60.0);

        //Act
        double samletAlkoholMaegde = whiskyProdukt.samletAlkoholMaengde();

        //Assert
        Assertions.assertEquals(0,samletAlkoholMaegde);
    }

    @Test
    void testSamletAlkoholMaengde_100pAlkohol() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Mockito.when(mockProduktRegistrering1.getAntalLiter()).thenReturn(10.0);
        Mockito.when(mockProduktRegistrering2.getAntalLiter()).thenReturn(15.0);
        Mockito.when(mockProduktRegistrering3.getAntalLiter()).thenReturn(25.0);

        Mockito.when(mockProduktRegistrering1.getFadIndhold()).thenReturn(mockFadindhold1);
        Mockito.when(mockProduktRegistrering2.getFadIndhold()).thenReturn(mockFadindhold2);
        Mockito.when(mockProduktRegistrering3.getFadIndhold()).thenReturn(mockFadindhold3);
        Mockito.when(mockFadindhold1.getSidstRegistreredeAlkoholProcent()).thenReturn(100.0);
        Mockito.when(mockFadindhold2.getSidstRegistreredeAlkoholProcent()).thenReturn(100.0);
        Mockito.when(mockFadindhold3.getSidstRegistreredeAlkoholProcent()).thenReturn(100.0);

        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt,mockedProduktRegistreringer);

        //Act
        double samletAlkoholMaegde = whiskyProdukt.samletAlkoholMaengde();

        //Assert
        Assertions.assertEquals(50,samletAlkoholMaegde);
    }
}
