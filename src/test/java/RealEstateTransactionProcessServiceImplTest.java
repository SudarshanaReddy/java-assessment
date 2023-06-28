import com.opencsv.exceptions.CsvValidationException;
import jmr.model.Response;
import jmr.service.RealEstateTransactionProcessServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class RealEstateTransactionProcessServiceImplTest {

    @Spy
    @InjectMocks
    private RealEstateTransactionProcessServiceImpl realEstateTransactionProcessService;

    @Mock
    private ResourceLoader resourceLoader;

    @Test
    public void whenResourceNullonUpdate() throws IOException, CsvValidationException {
        Mockito.when(this.resourceLoader.getResource(ArgumentMatchers.anyString())).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> {
            this.realEstateTransactionProcessService.updateRecordsRequest(ArgumentMatchers.any());
        });
    }
}
