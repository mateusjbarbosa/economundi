package com.backend.economundi.database.dao;

public interface IBaseDao<E> {
    public void create(E entity );
    
    public E read(Long id);
    
    public void update(E entity);
    
    public void delete(E entity);
}
