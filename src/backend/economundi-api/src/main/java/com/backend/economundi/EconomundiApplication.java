package com.backend.economundi;

import com.backend.economundi.consumer.ApiIndexesConsumer;
import com.backend.economundi.consumer.ApiNewsConsumer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
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

    @Scheduled(fixedDelay = 15 * MINUTO)
    public void reportCurrentTime() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH EEEE");
        ApiNewsConsumer api = new ApiNewsConsumer();
        api.refreshNews();
        
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        Integer hour = Integer.parseInt(sdf.format(new Date()).split(" ")[0]);
        String week = sdf.format(new Date()).split(" ")[1];
        
        if ((hour >= 10 && hour <= 18)
                && ((!week.equals("Domingo") && !week.equals("Sunday"))
                && ((!week.equals("Sábado") && !week.equals("Saturday"))))) {
            ApiIndexesConsumer apiIdx = new ApiIndexesConsumer();
            
            System.out.println("Coleta de índices: " + sdf.format(new Date()));
            apiIdx.getCurrencies();
            apiIdx.getStocks();
        } else {
            System.out.println("Fora do horário: " + sdf.format(new Date()));
        }
    }
}
