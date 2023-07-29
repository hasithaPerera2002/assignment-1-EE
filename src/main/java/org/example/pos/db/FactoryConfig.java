package org.example.pos.db;

import org.example.pos.entity.Customer;
import org.example.pos.entity.Item;
import org.example.pos.entity.Order;
import org.example.pos.entity.OrderItem;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
public class FactoryConfig {
    private static FactoryConfig instance ;
    private final SessionFactory sessionFactory;
    private FactoryConfig() {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Item.class).addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Order.class).addAnnotatedClass(OrderItem.class);
        sessionFactory=configuration.buildSessionFactory();
    }
    public static FactoryConfig getInstance(){
        if (instance == null) instance = new FactoryConfig();
        return instance;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
