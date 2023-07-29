package org.example.pos.service.impl;

import org.example.pos.db.FactoryConfig;
import org.example.pos.dto.CustomerDto;
import org.example.pos.entity.Customer;
import org.example.pos.repo.CustomerRepo;
import org.example.pos.repo.RepoFactory;
import org.example.pos.service.CustomerService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    CustomerRepo customerRepo = RepoFactory.getInstance().getRepo(RepoFactory.RepoType.CUSTOMER);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public CustomerDto save(CustomerDto entity) {
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Customer customer = customerRepo.save(modelMapper.map(entity, Customer.class), session);
            transaction.commit();
            return modelMapper.map(customer, CustomerDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }


    @Override
    public boolean delete(Integer id) {
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Customer byIdCustomer = customerRepo.findById(id, session);
            if (byIdCustomer != null) {
                customerRepo.delete(byIdCustomer, session);
                transaction.commit();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();

        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(CustomerDto entity) {
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            customerRepo.update(modelMapper.map(entity, Customer.class), session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public CustomerDto findById(Integer integer) {
        Session session = FactoryConfig.getInstance().getSession();

        try {
            Customer customer = customerRepo.findById(integer, session);
            if (customer != null) {
                return modelMapper.map(customer, CustomerDto.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CustomerDto> getAll() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        Session session = FactoryConfig.getInstance().getSession();
        try {
            List<Customer> all = customerRepo.getAll(session);
            return all.stream().map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }
}
