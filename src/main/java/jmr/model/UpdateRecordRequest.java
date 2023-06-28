package jmr.model;

import lombok.Data;

import java.util.List;

@Data
public class UpdateRecordRequest {

    private List<UpdateRecord> updateRecordList;
}
