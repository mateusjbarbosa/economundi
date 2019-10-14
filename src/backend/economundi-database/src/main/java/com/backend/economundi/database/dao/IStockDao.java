package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.stocks.StockEntity;
import java.util.Map;


public interface IStockDao extends IBaseDao<StockEntity>{
    public StockEntity readByName(String name);
    public Map<String, Map<String, Object>> readStocks();
}
