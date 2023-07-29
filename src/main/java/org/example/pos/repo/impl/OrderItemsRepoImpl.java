package org.example.pos.repo.impl;

import org.example.pos.entity.OrderItem;
import org.example.pos.repo.OrderItemsRepo;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public class OrderItemsRepoImpl implements OrderItemsRepo {

    @Override
    public OrderItem save(OrderItem orderItem, Session session) throws Exception {
        Serializable save = session.save(orderItem);
        orderItem.setId((int)save);
        return orderItem;
    }

    @Override
    public void delete(OrderItem entity, Session session) throws Exception {

    }

    @Override
    public void update(OrderItem entity, Session session) throws Exception {

    }

    @Override
    public OrderItem findById(Integer integer, Session session) throws Exception {
        return null;
    }

    @Override
    public List<OrderItem> getAll(Session session) throws Exception {
        return null;
    }
    public void addAll(List<OrderItem> items, Session session) throws Exception {
        for (OrderItem ob :items) {
            this.save(ob,session);
        }
    }
}
