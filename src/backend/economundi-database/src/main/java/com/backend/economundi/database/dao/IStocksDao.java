package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.StockEntity;
import java.util.List;

public interface IStocksDao extends IBaseDao<StockEntity>{
    List<StockEntity> readAll();
}
