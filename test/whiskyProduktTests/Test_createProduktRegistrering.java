package whiskyProduktTests;

import model.FadIndhold;
import model.WhiskyProdukt;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public class Test_createProduktRegistrering {
    private AutoCloseable closeable;
    private WhiskyProdukt whiskyProdukt;
    @Mock private FadIndhold fadIndhold;

    @BeforeEach
    void setup() {
        whiskyProdukt = new WhiskyProdukt("nyWhisky",1,"Smager godt", LocalDate.of(2026,12,24),0);
        closeable = MockitoAnnotations.openMocks(this);
    }

    


}
