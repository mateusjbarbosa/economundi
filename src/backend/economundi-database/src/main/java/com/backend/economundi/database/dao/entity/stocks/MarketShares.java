package com.backend.economundi.database.dao.entity.stocks;

public class MarketShares {
    private MarketSharesEntity IBOVESPA;
    private MarketSharesEntity NASDAQ;
    private MarketSharesEntity CAC;
    private MarketSharesEntity NIKKEI;

    public MarketSharesEntity getIBOVESPA() {
        return IBOVESPA;
    }

    public void setIBOVESPA(MarketSharesEntity IBOVESPA) {
        this.IBOVESPA = IBOVESPA;
    }

    public MarketSharesEntity getNASDAQ() {
        return NASDAQ;
    }

    public void setNASDAQ(MarketSharesEntity NASDAQ) {
        this.NASDAQ = NASDAQ;
    }

    public MarketSharesEntity getCAC() {
        return CAC;
    }

    public void setCAC(MarketSharesEntity CAC) {
        this.CAC = CAC;
    }

    public MarketSharesEntity getNIKKEI() {
        return NIKKEI;
    }

    public void setNIKKEI(MarketSharesEntity NIKKEI) {
        this.NIKKEI = NIKKEI;
    }
    
    
}
