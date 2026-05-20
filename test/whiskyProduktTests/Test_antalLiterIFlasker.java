package whiskyProduktTests;

import model.Flaske;
import model.ProduktRegistrering;
import model.WhiskyProdukt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

public class Test_antalLiterIFlasker {
    private WhiskyProdukt whiskyProdukt;
    @Mock private Flaske mockFlaske005;
    @Mock private Flaske mockFlaske1000;
    @Mock private Flaske mockFlaske0751;
    @Mock private Flaske mockFlaske0752;
    @Mock private Flaske mockFlaske0753;

    private AutoCloseable closeable;



    @BeforeEach
    void setup() {
        whiskyProdukt = new WhiskyProdukt("nyWhisky",1,"Smager godt", LocalDate.of(2026,12,24),0);
        closeable = MockitoAnnotations.openMocks(this);


    }

    @Test
    void testAntalLiterIFlaskerAlmindeligTest() throws IllegalAccessException, NoSuchFieldException {
        //Arrange
        ArrayList<Flaske> flasker = new ArrayList<>(Arrays.asList(mockFlaske005,mockFlaske1000,mockFlaske0751,mockFlaske0752,mockFlaske0753));
        Field field = whiskyProdukt.getClass().getDeclaredField("flasker");
        field.setAccessible(true);
        field.set(whiskyProdukt,flasker);

        doReturn(((double) 0.05)).when(mockFlaske005).getStoerrelse();
        doReturn(((double) 1000)).when(mockFlaske1000).getStoerrelse();
        doReturn(((double) 0.75)).when(mockFlaske0751).getStoerrelse();
        doReturn(((double) 0.75)).when(mockFlaske0752).getStoerrelse();
        doReturn(((double) 0.75)).when(mockFlaske0753).getStoerrelse();

        //Act
        double antalLiterIFlasker = whiskyProdukt.antalLiterIFlasker();

        //Assert
        assertEquals(((double) 1002.3),antalLiterIFlasker);
    }

    @Test
    void testAntalFlasker_IngenFlasker(){
        //Arrange
        //Vi tester en resultatet ved en tom liste af flasker, så der skal ikke tilknyttes nogen

        //Act
        double antalLiterIFlasker = whiskyProdukt.antalLiterIFlasker();

        //Assert
        assertEquals(0,antalLiterIFlasker);
    }


}
