package com.backend.economundi.database.dao.entity.coin;

public class Currencies {
    private String source;
    private CurrencyEntity USD;
    private CurrencyEntity EUR;
    private CurrencyEntity GBP;
    private CurrencyEntity ARS;
    private CurrencyEntity BTC;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public CurrencyEntity getUSD() {
        return USD;
    }

    public void setUSD(CurrencyEntity USD) {
        this.USD = USD;
    }

    public CurrencyEntity getEUR() {
        return EUR;
    }

    public void setEUR(CurrencyEntity EUR) {
        this.EUR = EUR;
    }

    public CurrencyEntity getGBP() {
        return GBP;
    }

    public void setGBP(CurrencyEntity GBP) {
        this.GBP = GBP;
    }

    public CurrencyEntity getARS() {
        return ARS;
    }

    public void setARS(CurrencyEntity ARS) {
        this.ARS = ARS;
    }

    public CurrencyEntity getBTC() {
        return BTC;
    }

    public void setBTC(CurrencyEntity BTC) {
        this.BTC = BTC;
    }
}
