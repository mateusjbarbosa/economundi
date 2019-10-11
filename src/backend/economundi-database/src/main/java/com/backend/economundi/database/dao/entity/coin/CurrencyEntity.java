package com.backend.economundi.database.dao.entity.coin;

public abstract class CurrencyEntity {
    private String name;
    private Float buy;
    private Float sell;
    private Float vatiation;

    public Float getBuy() {
        return buy;
    }

    public void setBuy(Float buy) {
        this.buy = buy;
    }

    public Float getSell() {
        return sell;
    }

    public void setSell(Float sell) {
        this.sell = sell;
    }

    public Float getVatiation() {
        return vatiation;
    }

    public void setVatiation(Float vatiation) {
        this.vatiation = vatiation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
