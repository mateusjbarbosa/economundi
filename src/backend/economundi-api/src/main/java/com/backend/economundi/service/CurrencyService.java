package com.backend.economundi.service;

import com.backend.economundi.database.dao.ICurrencyDao;
import com.backend.economundi.database.dao.IQuoteDao;
import com.backend.economundi.database.dao.entity.coin.Currencies;
import com.backend.economundi.database.dao.entity.coin.CurrencyGeneric;
import com.backend.economundi.database.dao.impl.CurrencyDao;
import com.backend.economundi.database.dao.impl.QuoteDao;

public class CurrencyService {

    private final ICurrencyDao currrecyDao = new CurrencyDao();
    private final IQuoteDao quoteDao = new QuoteDao();

    /**
     * Realiza a persistência das cotações das moedas.
     *
     * @param currencies Cotações das moedas.
     */
    public void createQuote(Currencies currencies) {
        CurrencyGeneric currency = new CurrencyGeneric();

        checkExistence(currencies);

        currency.setBuy(currencies.getARS().getBuy());
        currency.setSell(currencies.getARS().getSell());
        currency.setVariation(currencies.getARS().getVariation());
        currency.setId(currencies.getARS().getId());
        quoteDao.create(currency);

    }

    /**
     * Verifica a existênci da moeda no banco de dado, criando caso não exista.
     *
     * @param currencies Cotações das moedas.
     */
    private void checkExistence(Currencies currencies) {

        // Peso argentino.
        String name = currencies.getARS().getName();
        CurrencyGeneric currency = currrecyDao.readByName(name);

        if (currency != null) {
            currencies.getARS().setId(currency.getId());
        } else {
            currency = new CurrencyGeneric();

            currency.setName(name);
            currrecyDao.create(currency);
            currencies.getARS().setId(currency.getId());
        }

        if (currency.getSell() == null) {
            currencies.getARS().setSell(0F);
        }

        // Bitcoin.
        name = currencies.getBTC().getName();
        currency = currrecyDao.readByName(name);

        if (currency != null) {
            currencies.getBTC().setId(currency.getId());
        } else {
            currency = new CurrencyGeneric();

            currency.setName(name);
            currrecyDao.create(currency);
            currencies.getBTC().setId(currency.getId());
        }

        if (currency.getSell() == null) {
            currencies.getBTC().setSell(0F);
        }

        // Euro.
        name = currencies.getEUR().getName();
        currency = currrecyDao.readByName(name);

        if (currency != null) {
            currencies.getEUR().setId(currency.getId());
        } else {
            currency = new CurrencyGeneric();

            currency.setName(name);
            currrecyDao.create(currency);
            currencies.getEUR().setId(currency.getId());
        }

        if (currency.getSell() == null) {
            currencies.getEUR().setSell(0F);
        }

        // Libra Esterlina.
        name = currencies.getGBP().getName();
        currency = currrecyDao.readByName(name);

        if (currency != null) {
            currencies.getGBP().setId(currency.getId());
        } else {
            currency = new CurrencyGeneric();

            currency.setName(name);
            currrecyDao.create(currency);
            currencies.getGBP().setId(currency.getId());
        }

        if (currency.getSell() == null) {
            currencies.getGBP().setSell(0F);
        }

        // Dólar.
        name = currencies.getUSD().getName();
        currency = currrecyDao.readByName(name);

        if (currency != null) {
            currencies.getUSD().setId(currency.getId());
        } else {
            currency = new CurrencyGeneric();

            currency.setName(name);
            currrecyDao.create(currency);
            currencies.getUSD().setId(currency.getId());
        }

        if (currency.getSell() == null) {
            currencies.getUSD().setSell(0F);
        }
    }
}
