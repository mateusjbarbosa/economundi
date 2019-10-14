package com.backend.economundi.database.dao.entity.stocks;

public class Stocks {
    private StockEntity IBOVESPA;
    private StockEntity NASDAQ;
    private StockEntity CAC;
    private StockEntity NIKKEI;

    public StockEntity getIBOVESPA() {
        return IBOVESPA;
    }

    public void setIBOVESPA(StockEntity IBOVESPA) {
        this.IBOVESPA = IBOVESPA;
    }

    public StockEntity getNASDAQ() {
        return NASDAQ;
    }

    public void setNASDAQ(StockEntity NASDAQ) {
        this.NASDAQ = NASDAQ;
    }

    public StockEntity getCAC() {
        return CAC;
    }

    public void setCAC(StockEntity CAC) {
        this.CAC = CAC;
    }

    public StockEntity getNIKKEI() {
        return NIKKEI;
    }

    public void setNIKKEI(StockEntity NIKKEI) {
        this.NIKKEI = NIKKEI;
    }
    
    
}
