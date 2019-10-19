package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.stocks.MarketSharesEntity;
import java.util.Map;


public interface IStockDao extends IBaseDao<MarketSharesEntity>{
    public MarketSharesEntity readByName(String name);
    public Map<String, Map<String, Object>> readStocks();
}
