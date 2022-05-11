package com.sirioitalia.api.service;

import com.sirioitalia.api.exception.ResourceNotFoundException;
import com.sirioitalia.api.model.Item;
import com.sirioitalia.api.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    private ItemRepository itemRepository;

    public ItemServiceImpl() {
        super();
    }

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        super();
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> getItemById(Long id) throws ResourceNotFoundException {
        Optional<Item> itemFound = itemRepository.findById(id);
        if (itemFound.isPresent()) {
            throw new ResourceNotFoundException("Item not found");
        }

        return itemFound;
    }

    @Override
    public Item createItem(Item item) {
        Item addedItem = itemRepository.save(item);
        return addedItem;
    }

    @Override
    @Transactional(readOnly = false)
    public Item updateItem(Item item) throws ResourceNotFoundException {
        return null;
    }
}