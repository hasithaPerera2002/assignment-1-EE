package org.example.pos.repo;

import org.example.pos.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface CRUDRepo<T extends SuperEntity, ID extends Serializable> {
    public T save(T entity, Session session)throws Exception;
    public void delete(T entity, Session session ) throws Exception;
    public void update(T entity, Session session) throws Exception;
    public T findById(ID id, Session session) throws Exception;
    public List<T> getAll(Session session) throws Exception;
}
