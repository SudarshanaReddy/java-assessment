package jmr.model;

import lombok.Data;

@Data
public class UpdateRecord {

    private int rowId;
    private int columnId;
    private String value;
}
