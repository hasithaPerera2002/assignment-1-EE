package org.example.pos.service.impl;

import org.example.pos.dto.OrderItemDto;
import org.example.pos.entity.Order;
import org.example.pos.repo.CustomerRepo;
import org.example.pos.repo.ItemRepo;
import org.example.pos.repo.OrderRepo;
import org.example.pos.repo.RepoFactory;
import org.example.pos.service.OrderService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderRepo orderRepo=RepoFactory.getInstance().getRepo(RepoFactory.RepoType.ORDER);
    private ItemRepo itemRepo=RepoFactory.getInstance().getRepo(RepoFactory.RepoType.ITEM);
    private CustomerRepo customerRepo=RepoFactory.getInstance().getRepo(RepoFactory.RepoType.CUSTOMER);
    ModelMapper modelMapper=new ModelMapper();
    @Override
    public OrderItemDto save(OrderItemDto orderItemDto) {
        Order map = modelMapper.map(orderItemDto, Order.class);
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean update(OrderItemDto entity) {
        return false;
    }

    @Override
    public OrderItemDto findById(Integer integer) {
        return null;
    }

    @Override
    public List<OrderItemDto> getAll() {
        return null;
    }
}
