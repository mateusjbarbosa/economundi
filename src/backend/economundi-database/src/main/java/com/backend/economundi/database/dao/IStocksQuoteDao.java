package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.StockEntity;
import java.util.Map;

public interface IStocksQuoteDao extends IBaseDao<StockEntity>{
    public Map<String, Map<String, Object>> readQuote();
}
