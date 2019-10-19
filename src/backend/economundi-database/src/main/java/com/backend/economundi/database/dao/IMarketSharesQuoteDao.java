package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.stocks.MarketSharesEntity;
import java.util.Map;

public interface IMarketSharesQuoteDao extends IBaseDao<MarketSharesEntity>{
    public Map<String, Map<String, Object>> readQuote();
}
