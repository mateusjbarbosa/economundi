package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.coin.CurrencyEntity;
import java.util.Map;

public interface ICurrencyQuoteDao extends IBaseDao<CurrencyEntity>{
    public Map<String, Map<String, Object>> readQuote();
}
