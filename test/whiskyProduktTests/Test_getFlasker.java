package whiskyProduktTests;

import model.Flaske;
import model.ProduktRegistrering;
import model.WhiskyProdukt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;

public class Test_getFlasker {
    private WhiskyProdukt whiskyProdukt;
    ArrayList<Flaske> mockedFlasker = new ArrayList<>();
    @Mock private Flaske mockFlaske1;
    @Mock private Flaske mockFlaske2;
    @Mock private Flaske mockFlaske3;

    @Test
    void testGetProduktRegistreringer_IkkeSammeObjekt() throws IllegalAccessException, NoSuchFieldException {
        //Vi vil gerne have metoden returnerer en ny ArrayList, da vi ellers kan redigere objekterne gennem
        //den og for eksempel fjerne eller tilføje flere til listen. Det vil vi gerne have man kun kan gøre gennem dedikerede metoder.

        //Arrange
        whiskyProdukt = new WhiskyProdukt("nyWhisky", 1, "Smager godt", LocalDate.of(2026, 12, 24), 0);

        Field field = whiskyProdukt.getClass().getDeclaredField("flasker");
        field.setAccessible(true);
        field.set(whiskyProdukt, mockedFlasker);

        //Act
        ArrayList<Flaske> flaskerNy1= whiskyProdukt.getFlasker();
        ArrayList<Flaske> flaskerNy2= whiskyProdukt.getFlasker();

        //Assert
        Assertions.assertNotSame(mockedFlasker,flaskerNy1);
        Assertions.assertNotSame(flaskerNy1,flaskerNy2);
    }

    @Test
    void testGetProduktRegistreringer_SammeIndhold() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        whiskyProdukt = new WhiskyProdukt("nyWhisky", 1, "Smager godt", LocalDate.of(2026, 12, 24), 0);
        mockedFlasker.add(mockFlaske1);
        mockedFlasker.add(mockFlaske2);
        mockedFlasker.add(mockFlaske3);

        Field field = whiskyProdukt.getClass().getDeclaredField("flasker");
        field.setAccessible(true);
        field.set(whiskyProdukt, mockedFlasker);

        //Act
        ArrayList<Flaske> flaskerNy1= whiskyProdukt.getFlasker();

        //Assert
        Assertions.assertSame(mockedFlasker.get(0),flaskerNy1.get(0));
        Assertions.assertSame(mockedFlasker.get(1),flaskerNy1.get(1));
        Assertions.assertSame(mockedFlasker.get(2),flaskerNy1.get(2));
    }
}
