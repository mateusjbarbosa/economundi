package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.coin.CurrencyGeneric;
import java.util.Map;

public interface IQuoteDao extends IBaseDao<CurrencyGeneric>{
    public Map<String, Map<String, Object>> readQuote();
}
