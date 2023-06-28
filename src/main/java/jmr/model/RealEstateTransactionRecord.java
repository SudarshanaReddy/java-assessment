package jmr.model;

import lombok.Data;

@Data
public class RealEstateTransactionRecord {

    private String street;
    private String city;
    private String zip;
    private String state;
    private String beds;
    private String baths;
    private String sqft;
    private String type;
    private String saleDate;
    private String price;
    private String latitude;
    private String longitude;
}
