package com.backend.economundi.consumer;

import com.backend.economundi.database.dao.entity.ResultNewsEntity;
import java.io.IOException;

public class ApiIpcaConsumer {
    
    private final String url = 
        "https://api.bcb.gov.br/dados/serie/bcdata.sgs.4448/dados?formato=json";
    
    public void getIpca() throws IOException {
        ApiConsumerGeneric generic = new ApiConsumerGeneric();
        ResultNewsEntity result = generic.getData(url, ResultNewsEntity.class);
    }
}
