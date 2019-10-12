package com.backend.economundi.database.dao.entity.coin;

public class Currencies {
    private String source;
    private USD USD;
    private EUR EUR;
    private GBP GBP;
    private ARS ARS;
    private BTC BTC;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public USD getUSD() {
        return USD;
    }

    public void setUSD(USD USD) {
        this.USD = USD;
    }

    public EUR getEUR() {
        return EUR;
    }

    public void setEUR(EUR EUR) {
        this.EUR = EUR;
    }

    public GBP getGBP() {
        return GBP;
    }

    public void setGBP(GBP GBP) {
        this.GBP = GBP;
    }

    public ARS getARS() {
        return ARS;
    }

    public void setARS(ARS ARS) {
        this.ARS = ARS;
    }

    public BTC getBTC() {
        return BTC;
    }

    public void setBTC(BTC BTC) {
        this.BTC = BTC;
    }
}
