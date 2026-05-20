package whiskyProduktTests;

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

public class Test_samletAntalLiter {
    private AutoCloseable closeable;
    private WhiskyProdukt whiskyProdukt;
    @Mock private ProduktRegistrering mockProduktRegistrering1;
    @Mock private ProduktRegistrering mockProduktRegistrering2;
    @Mock private ProduktRegistrering mockProduktRegistrering3;
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
    void testSamletAntalLiter_IngenProduktRegistreringer() {
        //Arrange
        Mockito.when(mockProduktRegistrering1.getAntalLiter()).thenReturn(1.0);
        Mockito.when(mockProduktRegistrering2.getAntalLiter()).thenReturn(2.0);
        Mockito.when(mockProduktRegistrering3.getAntalLiter()).thenReturn(3.0);

        //Act
        double samletAntalLiter = whiskyProdukt.samletAntalLiter();

        //Assert
        Assertions.assertEquals(0, samletAntalLiter);
    }

    @Test
    void testSamletAntalLiter_EnsLiter() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Mockito.when(mockProduktRegistrering1.getAntalLiter()).thenReturn(10.5);
        Mockito.when(mockProduktRegistrering2.getAntalLiter()).thenReturn(10.5);
        Mockito.when(mockProduktRegistrering3.getAntalLiter()).thenReturn(10.5);

        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt,mockedProduktRegistreringer);

        //Act
        double samletAntalLiter = whiskyProdukt.samletAntalLiter();

        //Assert
        Assertions.assertEquals(31.5, samletAntalLiter);
    }

    @Test
    void testSamletAntalLiter_ForskelligLiter() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Mockito.when(mockProduktRegistrering1.getAntalLiter()).thenReturn(100.5);
        Mockito.when(mockProduktRegistrering2.getAntalLiter()).thenReturn(77.4);
        Mockito.when(mockProduktRegistrering3.getAntalLiter()).thenReturn(980.0);

        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt,mockedProduktRegistreringer);

        //Act
        double samletAntalLiter = whiskyProdukt.samletAntalLiter();

        //Assert
        Assertions.assertEquals(1157.9, samletAntalLiter);
    }
}
