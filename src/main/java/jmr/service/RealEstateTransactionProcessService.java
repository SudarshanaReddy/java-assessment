package jmr.service;

import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import jmr.model.RealEstateTransactionRecord;
import jmr.model.Response;
import jmr.model.UpdateRecordRequest;

import java.io.IOException;
import java.util.List;

public interface RealEstateTransactionProcessService {

    Response readCSV() throws IOException, CsvValidationException;

    void updateRecordsRequest(UpdateRecordRequest updateRecordRequest) throws IOException, CsvException;
}
