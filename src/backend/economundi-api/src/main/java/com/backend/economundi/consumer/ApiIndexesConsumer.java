package com.backend.economundi.consumer;

import com.backend.economundi.database.dao.entity.StockEntity;
import com.backend.economundi.database.dao.entity.coin.Currencies;
import com.backend.economundi.database.dao.entity.stocks.MarketShares;
import com.backend.economundi.service.IndexesService;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
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
            service.createCurrencies(currencies);
        } catch (JSONException ex) {
            Logger.getLogger(ApiIndexesConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getMarketShares() throws IOException {
        URLConnection connection = new URL("https://api.hgbrasil.com/finance?array_limit=1&fields=only_results,stocks&key=d590478c").openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();
        MarketShares marketShares;
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

        try {
            JSONObject jsonObj = new JSONObject(reader.readLine());
            Gson g = new Gson();

            marketShares = g.fromJson(jsonObj.get("stocks").toString(), MarketShares.class);
            service.createMarketShares(marketShares);
        } catch (JSONException ex) {
            Logger.getLogger(ApiIndexesConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getStocks() throws IOException {
        List<StockEntity> stocksList = service.getStocks();

        for (StockEntity stocks : stocksList) {
            URLConnection connection = new URL("https://api.hgbrasil.com/finance/stock_price?key=d590478c&symbol=" + stocks.getSymbol()).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            StockEntity stock;
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

            try {
                JSONObject jsonObj = new JSONObject(reader.readLine());
                Gson g = new Gson();
                String stockDto = jsonObj.get("results").toString();
                
                jsonObj = new JSONObject(stockDto);

                stock = g.fromJson(jsonObj.get(stocks.getSymbol()).toString(), StockEntity.class);
                stock.setId(stocks.getId());
                service.createStock(stock);
            } catch (JSONException ex) {
                Logger.getLogger(ApiIndexesConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
