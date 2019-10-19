package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.stocks.MarketSharesEntity;
import java.util.List;
import java.util.Map;


public interface IMarketSharesDao extends IBaseDao<MarketSharesEntity>{
    public MarketSharesEntity readByName(String name);
    
    public List<MarketSharesEntity> readAll();
}
