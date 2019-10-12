package com.backend.economundi.database.dao;

import com.backend.economundi.database.dao.entity.coin.CurrencyGeneric;


public interface ICurrencyDao extends IBaseDao<CurrencyGeneric>{
    public CurrencyGeneric readByName(String name);
}
