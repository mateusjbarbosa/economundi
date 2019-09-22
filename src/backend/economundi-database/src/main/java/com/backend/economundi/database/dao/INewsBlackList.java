package com.backend.economundi.database.dao;

import java.util.List;

import com.backend.economundi.database.dao.entity.NewsBlackList;

public interface INewsBlackList extends IBaseDao<NewsBlackList> {
	public List<NewsBlackList> readAll();
}
