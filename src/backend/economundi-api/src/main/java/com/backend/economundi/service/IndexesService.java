package com.backend.economundi.service;

import com.backend.economundi.database.dao.ICurrencyDao;
import com.backend.economundi.database.dao.IQuoteDao;
import com.backend.economundi.database.dao.IStockDao;
import com.backend.economundi.database.dao.entity.coin.Currencies;
import com.backend.economundi.database.dao.entity.coin.CurrencyEntity;
import com.backend.economundi.database.dao.entity.stocks.MarketSharesEntity;
import com.backend.economundi.database.dao.entity.stocks.MarketShares;
import com.backend.economundi.database.dao.impl.CurrencyDao;
import com.backend.economundi.database.dao.impl.QuoteDao;
import com.backend.economundi.database.dao.impl.MarketSharesDao;
import java.util.HashMap;
import java.util.Map;

public class IndexesService {

    private final ICurrencyDao currrecyDao = new CurrencyDao();
    private final IQuoteDao quoteDao = new QuoteDao();
    private final IStockDao stockDao = new MarketSharesDao();

    /**
     * Realiza a persistência das cotações das moedas.
     *
     * @param currencies Cotações das moedas.
     */
    public void createQuote(Currencies currencies) {
        CurrencyEntity currency = new CurrencyEntity();

        checkExistenceCurrencies(currencies);

        // Peso Argentino.
        currency.setBuy(currencies.getARS().getBuy());
        currency.setSell(currencies.getARS().getSell());
        currency.setVariation(currencies.getARS().getVariation());
        currency.setId(currencies.getARS().getId());
        quoteDao.create(currency);

        // Bitcoin.
        currency.setBuy(currencies.getBTC().getBuy());
        currency.setSell(currencies.getBTC().getSell());
        currency.setVariation(currencies.getBTC().getVariation());
        currency.setId(currencies.getBTC().getId());
        quoteDao.create(currency);

        // Euro.
        currency.setBuy(currencies.getEUR().getBuy());
        currency.setSell(currencies.getEUR().getSell());
        currency.setVariation(currencies.getEUR().getVariation());
        currency.setId(currencies.getEUR().getId());
        quoteDao.create(currency);

        // Libra Esterlina.
        currency.setBuy(currencies.getGBP().getBuy());
        currency.setSell(currencies.getGBP().getSell());
        currency.setVariation(currencies.getGBP().getVariation());
        currency.setId(currencies.getGBP().getId());
        quoteDao.create(currency);

        // Dólar.
        currency.setBuy(currencies.getUSD().getBuy());
        currency.setSell(currencies.getUSD().getSell());
        currency.setVariation(currencies.getUSD().getVariation());
        currency.setId(currencies.getUSD().getId());
        quoteDao.create(currency);
    }

    /**
     * Cria ou atualiza os índices das bolsas.
     *
     * @param stocks Estrutura que contém os valores a serem inseridos.
     */
    public void createStocks(MarketShares stocks) {
        String name = stocks.getIBOVESPA().getName();
        MarketSharesEntity stock = stockDao.readByName(name);

        // IBOVESPA.
        if (stocks.getIBOVESPA().getPoints() == null) {
            stocks.getIBOVESPA().setPoints(0D);
        }

        if (stock == null) {
            stockDao.create(stocks.getIBOVESPA());
        } else {
            stocks.getIBOVESPA().setId(stock.getId());
            stockDao.update(stocks.getIBOVESPA());
        }

        // NASDAQ.
        if (stocks.getNASDAQ().getPoints() == null) {
            stocks.getNASDAQ().setPoints(0D);
        }

        name = stocks.getNASDAQ().getName();
        stock = stockDao.readByName(name);

        if (stock == null) {
            stockDao.create(stocks.getNASDAQ());
        } else {
            stocks.getNASDAQ().setId(stock.getId());
            stockDao.update(stocks.getNASDAQ());
        }

        // CAC.
        if (stocks.getCAC().getPoints() == null) {
            stocks.getCAC().setPoints(0D);
        }

        name = stocks.getCAC().getName();
        stock = stockDao.readByName(name);

        if (stock == null) {
            stockDao.create(stocks.getCAC());
        } else {
            stocks.getCAC().setId(stock.getId());
            stockDao.update(stocks.getCAC());
        }

        // NIKKEI.
        if (stocks.getNIKKEI().getPoints() == null) {
            stocks.getNIKKEI().setPoints(0D);
        }

        name = stocks.getNIKKEI().getName();
        stock = stockDao.readByName(name);

        if (stock == null) {
            stockDao.create(stocks.getNIKKEI());
        } else {
            stocks.getNIKKEI().setId(stock.getId());
            stockDao.update(stocks.getNIKKEI());
        }
    }

    /**
     * Coleta os índices econômicos: câmbio, bolsa e ações.
     *
     * @return Map com os valores de cada índice.
     */
    public Map<String, Object> getIndexes() {
        Map<String, Object> indexes = new HashMap<>();

        indexes.put("currencies", quoteDao.readQuote());
        indexes.put("market_shares", stockDao.readStocks());

        return indexes;
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
}
