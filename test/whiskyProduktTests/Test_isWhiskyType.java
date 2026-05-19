package whiskyProduktTests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Test_isWhiskyType {
    private AutoCloseable closeable;
    private WhiskyProdukt whiskyProdukt;
    @Mock private ProduktRegistrering mockProduktRegistrering1;
    @Mock private ProduktRegistrering mockProduktRegistrering2;
    @Mock private ProduktRegistrering mockProduktRegistrering3;
    @Mock private FadIndhold mockFadindhold1;
    @Mock private FadIndhold mockFadindhold2;
    @Mock private FadIndhold mockFadindhold3;
    @Mock private Fad mockFad1;
    @Mock private Fad mockFad2;
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
    void testIsWhiskyType_TomListe_exception(){
        //Act and Assert
        assertThrows(RuntimeException.class,()->whiskyProdukt.isWhiskyType());
    }

    @Test
    void testIsWhiskyType_SingleMaltOgSingleCaskForskelligFadindhold() throws NoSuchFieldException, IllegalAccessException {
        //Skal returnere SingleCask, fordi en SingleCask er altid også en SingleMalt, mens en SingleMalt er ikke nødvendigvis en SingleCask
        //Arrange
        Mockito.when(mockProduktRegistrering1.getFadIndhold()).thenReturn(mockFadindhold1);
        Mockito.when(mockProduktRegistrering2.getFadIndhold()).thenReturn(mockFadindhold2);
        Mockito.when(mockProduktRegistrering3.getFadIndhold()).thenReturn(mockFadindhold3);
        Mockito.when(mockFadindhold1.getFad()).thenReturn(mockFad1);
        Mockito.when(mockFadindhold2.getFad()).thenReturn(mockFad1);
        Mockito.when(mockFadindhold3.getFad()).thenReturn(mockFad1);

        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt,mockedProduktRegistreringer);

        //Act
        WhiskyType whiskyType = whiskyProdukt.isWhiskyType();

        //Assert
        assertSame(WhiskyType.SINGLECASK,whiskyType);

    }

    @Test
    void testIsWhiskyType_SingleMaltOgSingleCaskSammeFadindhold() throws NoSuchFieldException, IllegalAccessException {
        //Skal returnere SingleCask, fordi en SingleCask er altid også en SingleMalt, mens en SingleMalt er ikke nødvendigvis en SingleCask
        //Arrange
        Mockito.when(mockProduktRegistrering1.getFadIndhold()).thenReturn(mockFadindhold1);
        Mockito.when(mockProduktRegistrering2.getFadIndhold()).thenReturn(mockFadindhold1);
        Mockito.when(mockProduktRegistrering3.getFadIndhold()).thenReturn(mockFadindhold1);
        Mockito.when(mockFadindhold1.getFad()).thenReturn(mockFad1);


        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt,mockedProduktRegistreringer);

        //Act
        WhiskyType whiskyType = whiskyProdukt.isWhiskyType();

        //Assert
        assertSame(WhiskyType.SINGLECASK,whiskyType);

    }
    @Test
    void testIsWhiskyType_KunSingleMalt1() throws NoSuchFieldException, IllegalAccessException {
        //Test hvor første fad i listen på 3 er anderledes end de andre
        //Arrange
        Mockito.when(mockProduktRegistrering1.getFadIndhold()).thenReturn(mockFadindhold1);
        Mockito.when(mockProduktRegistrering2.getFadIndhold()).thenReturn(mockFadindhold2);
        Mockito.when(mockProduktRegistrering3.getFadIndhold()).thenReturn(mockFadindhold3);
        Mockito.when(mockFadindhold1.getFad()).thenReturn(mockFad2);
        Mockito.when(mockFadindhold2.getFad()).thenReturn(mockFad1);
        Mockito.when(mockFadindhold3.getFad()).thenReturn(mockFad1);

        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt,mockedProduktRegistreringer);

        //Act
        WhiskyType whiskyType = whiskyProdukt.isWhiskyType();

        //Assert
        assertSame(WhiskyType.SINGLEMALT,whiskyType);
    }

    @Test
    void testIsWhiskyType_KunSingleMalt2() throws NoSuchFieldException, IllegalAccessException {
        //Test hvor midterste fad i listen på 3 er anderledes end de andre
        //Arrange
        Mockito.when(mockProduktRegistrering1.getFadIndhold()).thenReturn(mockFadindhold1);
        Mockito.when(mockProduktRegistrering2.getFadIndhold()).thenReturn(mockFadindhold2);
        Mockito.when(mockProduktRegistrering3.getFadIndhold()).thenReturn(mockFadindhold3);
        Mockito.when(mockFadindhold1.getFad()).thenReturn(mockFad1);
        Mockito.when(mockFadindhold2.getFad()).thenReturn(mockFad2);
        Mockito.when(mockFadindhold3.getFad()).thenReturn(mockFad1);

        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt,mockedProduktRegistreringer);

        //Act
        WhiskyType whiskyType = whiskyProdukt.isWhiskyType();

        //Assert
        assertSame(WhiskyType.SINGLEMALT,whiskyType);
    }

    @Test
    void testIsWhiskyType_KunSingleMalt3() throws NoSuchFieldException, IllegalAccessException {
        //Test hvor sidste fad i listen på 3 er anderledes end de andre
        //Arrange
        Mockito.when(mockProduktRegistrering1.getFadIndhold()).thenReturn(mockFadindhold1);
        Mockito.when(mockProduktRegistrering2.getFadIndhold()).thenReturn(mockFadindhold2);
        Mockito.when(mockProduktRegistrering3.getFadIndhold()).thenReturn(mockFadindhold3);
        Mockito.when(mockFadindhold1.getFad()).thenReturn(mockFad1);
        Mockito.when(mockFadindhold2.getFad()).thenReturn(mockFad1);
        Mockito.when(mockFadindhold3.getFad()).thenReturn(mockFad2);

        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt,mockedProduktRegistreringer);

        //Act
        WhiskyType whiskyType = whiskyProdukt.isWhiskyType();

        //Assert
        assertSame(WhiskyType.SINGLEMALT,whiskyType);
    }
}
