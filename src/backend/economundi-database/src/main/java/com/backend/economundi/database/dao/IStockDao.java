package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.stocks.StockEntity;


public interface IStockDao extends IBaseDao<StockEntity>{
    public StockEntity readByName(String name);
}
