package org.example.pos.service;

import org.example.pos.dto.SuperDto;

import java.io.Serializable;
import java.util.List;

public interface CRUDService <T extends SuperDto, ID extends Serializable> extends SuperService{
    public T save(T entity);
    public boolean delete(ID id) ;
    public boolean update(T entity);
    public T findById(ID id);
    public List<T> getAll();

}
