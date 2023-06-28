package jmr.model;

import lombok.Data;

import java.util.List;

@Data
public class Response {

    private List<RealEstateTransactionRecord> data;
}
