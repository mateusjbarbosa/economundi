package com.backend.economundi.database.dao;

import java.util.Map;



public interface IWordAccessDao {
    
    public void create (Long id);
    
    public Map<Long, String> getMostSearched ();
}
