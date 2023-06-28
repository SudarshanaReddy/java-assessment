package jmr.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import jmr.model.RealEstateTransactionRecord;
import jmr.model.Response;
import jmr.model.UpdateRecord;
import jmr.model.UpdateRecordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RealEstateTransactionProcessServiceImpl implements RealEstateTransactionProcessService {

    @Autowired
    ResourceLoader resourceLoader;

    /**
     * This method is used to read the CSV file and returns the results.
     * @return
     * @throws IOException
     * @throws CsvValidationException
     */
    @Override
    public Response readCSV() throws IOException, CsvValidationException {

        final Resource resource = this.resourceLoader.getResource("classpath:realestatetransactions.csv");
        final Response response = new Response();
        if(resource!=null) {
            final CSVReader csvReader = new CSVReader(new FileReader(resource.getFile()));
            String[] values = null;
            List<RealEstateTransactionRecord> realEstateTransactionRecords = new ArrayList<>();

            while ((values = csvReader.readNext()) != null) {
                RealEstateTransactionRecord realEstateTransactionRecord = new RealEstateTransactionRecord();
                realEstateTransactionRecord.setStreet(values[0]);
                realEstateTransactionRecord.setCity(values[1]);
                realEstateTransactionRecord.setZip(values[2]);
                realEstateTransactionRecord.setState(values[3]);
                realEstateTransactionRecord.setBeds(values[4]);
                realEstateTransactionRecord.setBaths(values[5]);
                realEstateTransactionRecord.setSqft(values[6]);
                realEstateTransactionRecord.setType(values[7]);
                realEstateTransactionRecord.setSaleDate(values[8]);
                realEstateTransactionRecord.setPrice(values[9]);
                realEstateTransactionRecord.setLatitude(values[10]);
                realEstateTransactionRecord.setLongitude(values[11]);
                realEstateTransactionRecords.add(realEstateTransactionRecord);
            }
            response.setData(realEstateTransactionRecords);
        }
        return response;
    }


    /**
     * This method is to update the csv file specific cells/fields.
     * @param updateRecordRequest
     * @throws IOException
     * @throws CsvException
     */
    @Override
    public void updateRecordsRequest(UpdateRecordRequest updateRecordRequest) throws IOException, CsvException {
        final Resource resource = this.resourceLoader.getResource("classpath:realestatetransactions.csv");
        final CSVReader csvReader = new CSVReader(new FileReader(resource.getFile()));
        List<String[]> csvBody = csvReader.readAll();
        CSVWriter writer = null;
        csvReader.close();

        /**
         * Loop - Below will update the CSV file based on the row id and column id.
         */
        for(UpdateRecord request : updateRecordRequest.getUpdateRecordList()) {
            // get CSV row column  and replace with by using row and column
            System.out.println("csv attr:"+csvBody.get(request.getRowId())[request.getColumnId()]);
            csvBody.get(request.getRowId())[request.getColumnId()] = request.getValue();
            // Write to CSV file which is open
            writer = new CSVWriter(new FileWriter(resource.getFile()));
            writer.writeAll(csvBody);
        }
        writer.flush();
        writer.close();
    }
}
