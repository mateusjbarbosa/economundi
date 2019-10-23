package com.backend.economundi.database.dao.entity;

public class StockEntity {
    private Long id;
    private String symbol;
    private String name;
    private Double market_cap;
    private Double price;
    private Double change_percent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(Double market_cap) {
        this.market_cap = market_cap;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getChangePercent() {
        return change_percent;
    }

    public void setChange_percent(Double change_percent) {
        this.change_percent = change_percent;
    }
}
