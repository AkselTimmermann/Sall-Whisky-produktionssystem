package fadIndhold;

import model.Fad;
import model.FadIndhold;
import model.PaafyldningsRegistrering;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test_IsLagretMinimum3Aar {
    private AutoCloseable closeable;
    private FadIndhold fadIndhold;
    @Mock private Fad fad;
    @Mock private PaafyldningsRegistrering paafyldningsRegistrering;
    private ArrayList<PaafyldningsRegistrering> mockedPaafyldningsRegistreringer = new ArrayList<>();

    @BeforeEach
    void setup(){
        closeable = MockitoAnnotations.openMocks(this);
        fadIndhold = new FadIndhold(fad);
        mockedPaafyldningsRegistreringer.add(paafyldningsRegistrering);
    }

    @Test
    void test_isLagretMinimum3Aar_ligeUnder3Aar() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Field field = fadIndhold.getClass().getDeclaredField("paafyldningsRegistreringer");
        field.setAccessible(true);
        field.set(fadIndhold,mockedPaafyldningsRegistreringer);

        Mockito.when(paafyldningsRegistrering.getDato()).thenReturn(LocalDate.of(2023,4,15));
        LocalDate testDato = LocalDate.of(2026,4,14);

        //Act
        boolean isLagretMinimum3Aar = fadIndhold.isLagretMinimum3Aar(testDato);

        //Assert
        assertFalse(isLagretMinimum3Aar);
    }
    @Test
    void test_isLagretMinimum3Aar_Praecis() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Field field = fadIndhold.getClass().getDeclaredField("paafyldningsRegistreringer");
        field.setAccessible(true);
        field.set(fadIndhold,mockedPaafyldningsRegistreringer);

        Mockito.when(paafyldningsRegistrering.getDato()).thenReturn(LocalDate.of(2023,4,15));
        LocalDate testDato = LocalDate.of(2026,4,15);

        //Act
        boolean isLagretMinimum3Aar = fadIndhold.isLagretMinimum3Aar(testDato);

        //Assert
        assertTrue(isLagretMinimum3Aar);
    }

    @Test
    void test_isLagretMinimum3Aar_LigeOver() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Field field = fadIndhold.getClass().getDeclaredField("paafyldningsRegistreringer");
        field.setAccessible(true);
        field.set(fadIndhold,mockedPaafyldningsRegistreringer);

        Mockito.when(paafyldningsRegistrering.getDato()).thenReturn(LocalDate.of(2023,4,15));
        LocalDate testDato = LocalDate.of(2026,4,16);

        //Act
        boolean isLagretMinimum3Aar = fadIndhold.isLagretMinimum3Aar(testDato);

        //Assert
        assertTrue(isLagretMinimum3Aar);
    }

    @Test
    void test_isLagretMinimum3Aar_FoerLagringBegynder() throws NoSuchFieldException, IllegalAccessException {
        //Arrange
        Field field = fadIndhold.getClass().getDeclaredField("paafyldningsRegistreringer");
        field.setAccessible(true);
        field.set(fadIndhold,mockedPaafyldningsRegistreringer);

        Mockito.when(paafyldningsRegistrering.getDato()).thenReturn(LocalDate.of(2023,4,15));
        LocalDate testDato = LocalDate.of(2023,4,14);

        //Act
        boolean isLagretMinimum3Aar = fadIndhold.isLagretMinimum3Aar(testDato);

        //Assert
        assertFalse(isLagretMinimum3Aar);
    }
}
