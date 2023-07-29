package org.example.pos.service;

import org.example.pos.service.impl.CustomerServiceImpl;
import org.example.pos.service.impl.ItemServiceImpl;
import org.example.pos.service.impl.OrderServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    public enum ServiceType {
        ITEM, CUSTOMER,ORDER
    }


   public static ServiceFactory getInstance() {
       return serviceFactory==null?serviceFactory=new ServiceFactory():serviceFactory;
   }
   public <T extends SuperService>T getService(ServiceType type) {
        switch (type) {
            case ITEM:
                return (T) new ItemServiceImpl();
            case CUSTOMER:
                return (T) new CustomerServiceImpl();
            case ORDER:
                return (T) new OrderServiceImpl();
        }
        return null;
   }

}
