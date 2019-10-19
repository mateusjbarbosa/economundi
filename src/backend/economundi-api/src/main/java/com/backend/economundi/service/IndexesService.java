package com.backend.economundi.service;

import com.backend.economundi.database.dao.ICurrencyDao;
import com.backend.economundi.database.dao.ICurrencyQuoteDao;
import com.backend.economundi.database.dao.IMarketSharesDao;
import com.backend.economundi.database.dao.IMarketSharesQuoteDao;
import com.backend.economundi.database.dao.IStocksDao;
import com.backend.economundi.database.dao.IStocksQuoteDao;
import com.backend.economundi.database.dao.entity.StockEntity;
import com.backend.economundi.database.dao.entity.coin.Currencies;
import com.backend.economundi.database.dao.entity.coin.CurrencyEntity;
import com.backend.economundi.database.dao.entity.stocks.MarketSharesEntity;
import com.backend.economundi.database.dao.entity.stocks.MarketShares;
import com.backend.economundi.database.dao.impl.CurrencyDao;
import com.backend.economundi.database.dao.impl.CurrencyQuoteDao;
import com.backend.economundi.database.dao.impl.MarketSharesDao;
import com.backend.economundi.database.dao.impl.MarketSharesQuoteDao;
import com.backend.economundi.database.dao.impl.StocksDao;
import com.backend.economundi.database.dao.impl.StocksQuoteDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexesService {

    private final ICurrencyDao currrecyDao = new CurrencyDao();
    private final ICurrencyQuoteDao currencyQuoteDao = new CurrencyQuoteDao();
    private final IMarketSharesDao marketSharesDao = new MarketSharesDao();
    private final IMarketSharesQuoteDao marketSharesQuoteDao = new MarketSharesQuoteDao();
    private final IStocksDao stockDao = new StocksDao();
    private final IStocksQuoteDao stockQuoteDao = new StocksQuoteDao();
    

    /**
     * Realiza a persistência das cotações das moedas.
     *
     * @param currencies Cotações das moedas.
     */
    public void createCurrencies(Currencies currencies) {
        CurrencyEntity currency = new CurrencyEntity();

        checkExistenceCurrencies(currencies);

        // Peso Argentino.
        currency.setBuy(currencies.getARS().getBuy());
        currency.setSell(currencies.getARS().getSell());
        currency.setVariation(currencies.getARS().getVariation());
        currency.setId(currencies.getARS().getId());
        currencyQuoteDao.create(currency);

        // Bitcoin.
        currency.setBuy(currencies.getBTC().getBuy());
        currency.setSell(currencies.getBTC().getSell());
        currency.setVariation(currencies.getBTC().getVariation());
        currency.setId(currencies.getBTC().getId());
        currencyQuoteDao.create(currency);

        // Euro.
        currency.setBuy(currencies.getEUR().getBuy());
        currency.setSell(currencies.getEUR().getSell());
        currency.setVariation(currencies.getEUR().getVariation());
        currency.setId(currencies.getEUR().getId());
        currencyQuoteDao.create(currency);

        // Libra Esterlina.
        currency.setBuy(currencies.getGBP().getBuy());
        currency.setSell(currencies.getGBP().getSell());
        currency.setVariation(currencies.getGBP().getVariation());
        currency.setId(currencies.getGBP().getId());
        currencyQuoteDao.create(currency);

        // Dólar.
        currency.setBuy(currencies.getUSD().getBuy());
        currency.setSell(currencies.getUSD().getSell());
        currency.setVariation(currencies.getUSD().getVariation());
        currency.setId(currencies.getUSD().getId());
        currencyQuoteDao.create(currency);
    }

    /**
     * Cria ou atualiza os índices das bolsas.
     *
     * @param marketShares Estrutura que contém os valores a serem inseridos.
     */
    public void createMarketShares(MarketShares marketShares) {
        MarketSharesEntity marketSharesE = new MarketSharesEntity();

        checkExistenceMarketShares(marketShares);

        // França.
        marketSharesE.setVariation(marketShares.getCAC().getVariation());
        marketSharesE.setPoints(marketShares.getCAC().getPoints());
        marketSharesE.setId(marketShares.getCAC().getId());
        marketSharesQuoteDao.create(marketSharesE);

        // Brasil.
        marketSharesE.setVariation(marketShares.getIBOVESPA().getVariation());
        marketSharesE.setPoints(marketShares.getIBOVESPA().getPoints());
        marketSharesE.setId(marketShares.getIBOVESPA().getId());
        marketSharesQuoteDao.create(marketSharesE);

        // EUA.
        marketSharesE.setVariation(marketShares.getNASDAQ().getVariation());
        marketSharesE.setPoints(marketShares.getNASDAQ().getPoints());
        marketSharesE.setId(marketShares.getNASDAQ().getId());
        marketSharesQuoteDao.create(marketSharesE);

        // EUA.
        marketSharesE.setVariation(marketShares.getNIKKEI().getVariation());
        marketSharesE.setPoints(marketShares.getNIKKEI().getPoints());
        marketSharesE.setId(marketShares.getNIKKEI().getId());
        marketSharesQuoteDao.create(marketSharesE);
    }
    
    public void createStock(StockEntity stock) {
        stockQuoteDao.create(stock);
    }

    /**
     * Coleta os índices econômicos: câmbio, bolsa e ações.
     *
     * @return Map com os valores de cada índice.
     */
    public Map<String, Object> getIndexes() {
        Map<String, Object> indexes = new HashMap<>();

        indexes.put("currencies", currencyQuoteDao.readQuote());
        indexes.put("market_shares", marketSharesQuoteDao.readQuote());
        indexes.put("stocks", stockQuoteDao.readQuote());

        return indexes;
    }
    
    /**
     * Coleta todas as ações que estão cadastradas.
     * @return Lista com as ações.
     */
    public List<StockEntity> getStocks() {
        List<StockEntity> symbolList = stockDao.readAll();
        
        return symbolList;
    }

    /**
     * Verifica a existênci da moeda no banco de dado, criando caso não exista.
     *
     * @param currencies Cotações das moedas.
     */
    private void checkExistenceCurrencies(Currencies currencies) {

        // Peso argentino.
        String name = currencies.getARS().getName();
        CurrencyEntity currency = currrecyDao.readByName(name);

        if (currency != null) {
            currencies.getARS().setId(currency.getId());
        } else {
            currency = new CurrencyEntity();

            currency.setName(name);
            currrecyDao.create(currency);
            currencies.getARS().setId(currency.getId());
        }

        if (currencies.getARS().getSell() == null) {
            currencies.getARS().setSell(0F);
        }

        // Bitcoin.
        name = currencies.getBTC().getName();
        currency = currrecyDao.readByName(name);

        if (currency != null) {
            currencies.getBTC().setId(currency.getId());
        } else {
            currency = new CurrencyEntity();

            currency.setName(name);
            currrecyDao.create(currency);
            currencies.getBTC().setId(currency.getId());
        }

        if (currencies.getBTC().getSell() == null) {
            currencies.getBTC().setSell(0F);
        }

        // Euro.
        name = currencies.getEUR().getName();
        currency = currrecyDao.readByName(name);

        if (currency != null) {
            currencies.getEUR().setId(currency.getId());
        } else {
            currency = new CurrencyEntity();

            currency.setName(name);
            currrecyDao.create(currency);
            currencies.getEUR().setId(currency.getId());
        }

        if (currencies.getEUR().getSell() == null) {
            currencies.getEUR().setSell(0F);
        }

        // Libra Esterlina.
        name = currencies.getGBP().getName();
        currency = currrecyDao.readByName(name);

        if (currency != null) {
            currencies.getGBP().setId(currency.getId());
        } else {
            currency = new CurrencyEntity();

            currency.setName(name);
            currrecyDao.create(currency);
            currencies.getGBP().setId(currency.getId());
        }

        if (currencies.getGBP().getSell() == null) {
            currencies.getGBP().setSell(0F);
        }

        // Dólar.
        name = currencies.getUSD().getName();
        currency = currrecyDao.readByName(name);

        if (currency != null) {
            currencies.getUSD().setId(currency.getId());
        } else {
            currency = new CurrencyEntity();

            currency.setName(name);
            currrecyDao.create(currency);
            currencies.getUSD().setId(currency.getId());
        }

        if (currencies.getUSD().getSell() == null) {
            currencies.getUSD().setSell(0F);
        }
    }

    void checkExistenceMarketShares(MarketShares marketShares) {
        String name = marketShares.getCAC().getName();
        MarketSharesEntity marketSharesE = marketSharesDao.readByName(name);

        // Paris.
        if (marketSharesE != null) {
            marketShares.getCAC().setId(marketSharesE.getId());
        } else {
            marketSharesE = new MarketSharesEntity();

            marketSharesE.setName(marketShares.getCAC().getName());
            marketSharesE.setLocation(marketShares.getCAC().getLocation());
            marketSharesDao.create(marketSharesE);
            marketShares.getCAC().setId(marketSharesE.getId());
        }

        if (marketShares.getCAC().getPoints() == null) {
            marketShares.getCAC().setPoints(0D);
        }

        // Brasil.
        name = marketShares.getIBOVESPA().getName();
        marketSharesE = marketSharesDao.readByName(name);

        if (marketSharesE != null) {
            marketShares.getIBOVESPA().setId(marketSharesE.getId());
        } else {
            marketSharesE = new MarketSharesEntity();

            marketSharesE.setName(marketShares.getIBOVESPA().getName());
            marketSharesE.setLocation(marketShares.getIBOVESPA().getLocation());
            marketSharesDao.create(marketSharesE);
            marketShares.getIBOVESPA().setId(marketSharesE.getId());
        }

        if (marketShares.getIBOVESPA().getPoints() == null) {
            marketShares.getIBOVESPA().setPoints(0D);
        }

        // EUA.
        name = marketShares.getNASDAQ().getName();
        marketSharesE = marketSharesDao.readByName(name);

        if (marketSharesE != null) {
            marketShares.getNASDAQ().setId(marketSharesE.getId());
        } else {
            marketSharesE = new MarketSharesEntity();

            marketSharesE.setName(marketShares.getNASDAQ().getName());
            marketSharesE.setLocation(marketShares.getNASDAQ().getLocation());
            marketSharesDao.create(marketSharesE);
            marketShares.getNASDAQ().setId(marketSharesE.getId());
        }

        if (marketShares.getNASDAQ().getPoints() == null) {
            marketShares.getNASDAQ().setPoints(0D);
        }

        // Japão.
        name = marketShares.getNIKKEI().getName();
        marketSharesE = marketSharesDao.readByName(name);

        if (marketSharesE != null) {
            marketShares.getNIKKEI().setId(marketSharesE.getId());
        } else {
            marketSharesE = new MarketSharesEntity();

            marketSharesE.setName(marketShares.getNIKKEI().getName());
            marketSharesE.setLocation(marketShares.getNIKKEI().getLocation());
            marketSharesDao.create(marketSharesE);
            marketShares.getNIKKEI().setId(marketSharesE.getId());
        }

        if (marketShares.getNIKKEI().getPoints() == null) {
            marketShares.getNIKKEI().setPoints(0D);
        }
    }

}
