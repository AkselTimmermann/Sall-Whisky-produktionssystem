package whiskyProduktTests;

import model.ProduktRegistrering;
import model.WhiskyProdukt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;

public class Test_getProduktRegistreringer {
    private WhiskyProdukt whiskyProdukt;
    ArrayList<ProduktRegistrering> mockedProduktRegistreringer = new ArrayList<>();
    @Mock private ProduktRegistrering mockProduktRegistrering1;
    @Mock private ProduktRegistrering mockProduktRegistrering2;
    @Mock private ProduktRegistrering mockProduktRegistrering3;

    @Test
    void testGetProduktRegistreringer_IkkeSammeObjekt() throws IllegalAccessException, NoSuchFieldException {
        //Vi vil gerne have metoden returnerer en ny ArrayList, da vi ellers kan redigere objekterne gennem
        //den og for eksempel fjerne eller tilføje flere til listen. Det vil vi gerne have man kun kan gøre gennem dedikerede metoder.

        //Arrange
        whiskyProdukt = new WhiskyProdukt("nyWhisky", 1, "Smager godt", LocalDate.of(2026, 12, 24), 0);

        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt, mockedProduktRegistreringer);

        //Act
        ArrayList<ProduktRegistrering> produktRegistreringerNy1= whiskyProdukt.getProduktRegistreringer();
        ArrayList<ProduktRegistrering> produktRegistreringerNy2= whiskyProdukt.getProduktRegistreringer();

        //Assert
        Assertions.assertNotSame(mockedProduktRegistreringer,produktRegistreringerNy1);
        Assertions.assertNotSame(produktRegistreringerNy1,produktRegistreringerNy2);
    }

    @Test
    void testGetProduktRegistreringer_SammeIndhold() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        whiskyProdukt = new WhiskyProdukt("nyWhisky", 1, "Smager godt", LocalDate.of(2026, 12, 24), 0);
        mockedProduktRegistreringer.add(mockProduktRegistrering1);
        mockedProduktRegistreringer.add(mockProduktRegistrering2);
        mockedProduktRegistreringer.add(mockProduktRegistrering3);

        Field field = whiskyProdukt.getClass().getDeclaredField("produktRegistreringer");
        field.setAccessible(true);
        field.set(whiskyProdukt, mockedProduktRegistreringer);

        //Act
        ArrayList<ProduktRegistrering> produktRegistreringerNy1= whiskyProdukt.getProduktRegistreringer();

        //Assert
        Assertions.assertSame(mockedProduktRegistreringer.get(0),produktRegistreringerNy1.get(0));
        Assertions.assertSame(mockedProduktRegistreringer.get(1),produktRegistreringerNy1.get(1));
        Assertions.assertSame(mockedProduktRegistreringer.get(2),produktRegistreringerNy1.get(2));
    }
}
