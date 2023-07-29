package org.example.pos.repo.impl;

import org.example.pos.entity.Order;
import org.example.pos.repo.OrderRepo;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public class OrderRepoImpl implements OrderRepo {
    @Override
    public Order save(Order order, Session session) throws Exception {
        Serializable save = session.save(order);
        order.setId((int) save);
        return order;
    }

    @Override
    public void delete(Order entity, Session session) throws Exception {

    }

    @Override
    public void update(Order entity, Session session) throws Exception {

    }

    @Override
    public Order findById(Integer integer, Session session) throws Exception {
        return null;
    }

    @Override
    public List<Order> getAll(Session session) throws Exception {
        return null;
    }
}
