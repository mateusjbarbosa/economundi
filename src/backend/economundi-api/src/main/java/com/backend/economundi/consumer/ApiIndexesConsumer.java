package com.backend.economundi.consumer;

import com.backend.economundi.database.dao.entity.coin.Currencies;
import com.backend.economundi.database.dao.entity.stocks.Stocks;
import com.backend.economundi.service.IndexesService;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiIndexesConsumer {

    private final IndexesService service = new IndexesService();

    /**
     * Realiza o consumo das cotações das moedas.
     *
     * @throws IOException
     */
    public void getCurrencies() throws IOException {
        URLConnection connection = new URL("https://api.hgbrasil.com/finance?array_limit=1&fields=only_results,currencies&key=d590478c").openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();
        Currencies currencies;
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
        try {
            JSONObject jsonObj = new JSONObject(reader.readLine());
            Gson g = new Gson();

            currencies = g.fromJson(jsonObj.get("currencies").toString(), Currencies.class);
            service.createQuote(currencies);
        } catch (JSONException ex) {
            Logger.getLogger(ApiIndexesConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getStocks() throws IOException {
        URLConnection connection = new URL("https://api.hgbrasil.com/finance?array_limit=1&fields=only_results,stocks&key=d590478c").openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();
        Stocks stocks;
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

        try {
            JSONObject jsonObj = new JSONObject(reader.readLine());
            Gson g = new Gson();
            
            stocks = g.fromJson(jsonObj.get("stocks").toString(), Stocks.class);
            service.createStocks(stocks);
        } catch (JSONException ex) {
            Logger.getLogger(ApiIndexesConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
