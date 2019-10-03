package com.backend.economundi.database.dao;

import java.util.List;

import com.backend.economundi.database.dao.entity.NewsBlackListEntity;

public interface INewsBlackList extends IBaseDao<NewsBlackListEntity> {
	public List<NewsBlackListEntity> readAll();
}
