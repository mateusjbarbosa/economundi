package com.backend.economundi;

import com.backend.economundi.consumer.ApiIndexesConsumer;
import com.backend.economundi.consumer.ApiNewsConsumer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
@EnableScheduling
public class EconomundiApplication {

    private final long SEGUNDO = 1000;
    private final long MINUTO = SEGUNDO * 60;
    private final long HORA = MINUTO * 60;
    @SuppressWarnings("unused")
    private final long DIA = 24 * HORA;

    public static void main(String[] args) {
        SpringApplication.run(EconomundiApplication.class, args);

    }

    @Scheduled(fixedDelay = HORA)
    public void reportCurrentTime() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh a");
        ApiNewsConsumer api = new ApiNewsConsumer();

        api.refreshNews();

        Integer hour = Integer.parseInt(sdf.format(new Date()).split(" ")[0]);
        String initials = sdf.format(new Date()).split(" ")[1];

        if ((initials.equals("AM") && hour >= 10)
                || (initials.equals("PM") && hour <= 6)) {
            ApiIndexesConsumer apiIdx = new ApiIndexesConsumer();
            apiIdx.getCurrencies();
        }
    }
}
