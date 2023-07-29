package org.example.pos.service.impl;

import org.example.pos.db.FactoryConfig;
import org.example.pos.dto.ItemDto;
import org.example.pos.entity.Item;
import org.example.pos.repo.ItemRepo;
import org.example.pos.repo.RepoFactory;
import org.example.pos.service.ItemService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    ModelMapper modelMapper = new ModelMapper();
    ItemRepo itemRepo = RepoFactory.getInstance().getRepo(RepoFactory.RepoType.ITEM);

    @Override
    public ItemDto save(ItemDto itemDto) {
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Item item = itemRepo.save(modelMapper.map(itemDto, Item.class), session);

            transaction.commit();
            return modelMapper.map(item, ItemDto.class);
        } catch (Exception e) {
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Integer id) {
        Session session = FactoryConfig.getInstance().getSession();
        ItemDto byId = findById(id);
        Transaction transaction= session.beginTransaction();
        if (byId != null) {
            try {
                itemRepo.delete(modelMapper.map(byId, Item.class), session);
                transaction.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }finally {
                session.close();
            }
        }
        return false;
    }

    @Override
    public boolean update(ItemDto entity) {
        Session session = FactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Item map = modelMapper.map(entity, Item.class);
            System.out.println(map);
            map.setItems(null);
            itemRepo.update(map, session);
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
    public ItemDto findById(Integer id) {

        try (Session session = FactoryConfig.getInstance().getSession()) {
            Item item = itemRepo.findById(id, session);
            if (item != null) {
                return modelMapper.map(item, ItemDto.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<ItemDto> getAll() {
        List<ItemDto> itemDtos = new ArrayList<>();
        Session session = FactoryConfig.getInstance().getSession();
        try  {
            List<Item> all = itemRepo.getAll(session);
            for (Item item : all) {
                itemDtos.add(modelMapper.map(item, ItemDto.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return itemDtos;
    }
}
