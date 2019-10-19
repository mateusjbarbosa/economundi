package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.coin.CurrencyEntity;
import java.util.List;

public interface ICurrencyDao extends IBaseDao<CurrencyEntity>{
    public CurrencyEntity readByName(String name);
    
    public List<CurrencyEntity> readAll();
}
