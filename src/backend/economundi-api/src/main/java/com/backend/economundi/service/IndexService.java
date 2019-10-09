package com.backend.economundi.service;

import com.backend.economundi.consumer.ApiConsumerGeneric;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IndexService {
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd");
    private final Date DATE = new Date();
    private final String DATE_NEW_CHECK = "02";
    
    public void checkNewIndex () {
        if (DATE_FORMAT.format(DATE).equals(DATE_NEW_CHECK)) {
            @SuppressWarnings("unused")
			ApiConsumerGeneric generic = new ApiConsumerGeneric();
        }
    }
}
