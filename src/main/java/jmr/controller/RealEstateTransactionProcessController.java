package jmr.controller;

import com.opencsv.exceptions.CsvValidationException;
import jmr.model.Response;
import jmr.model.UpdateRecordRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import jmr.service.RealEstateTransactionProcessService;

import java.io.IOException;

@RestController
@RequestMapping(path = "/v1/process-csv")
public class RealEstateTransactionProcessController {

    private final RealEstateTransactionProcessService realEstateTransactionProcessService;

    RealEstateTransactionProcessController(final RealEstateTransactionProcessService realEstateTransactionProcessService) {
        this.realEstateTransactionProcessService = realEstateTransactionProcessService;
    }

    /**
     * This endpoint reads csv file and returns the contents
     * @return
     * @throws IOException
     * @throws CsvValidationException
     */
    @GetMapping(path = "/getList", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response readCSV() throws Exception {

        Response response = null;
        try {
            return this.realEstateTransactionProcessService.readCSV();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    /**
     * This endpoint is to update the csv file specific fields.
     * @param updateRecordRequest
     * @return
     * @throws Exception
     */
    @PostMapping(path = "/updateList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateList(@RequestBody UpdateRecordRequest updateRecordRequest) throws Exception {
        try {
            this.realEstateTransactionProcessService.updateRecordsRequest(updateRecordRequest);
            return ResponseEntity.ok("List updated Successfully");
        } catch (Exception ex) {
            throw new Exception(ex);
        }

    }
}
