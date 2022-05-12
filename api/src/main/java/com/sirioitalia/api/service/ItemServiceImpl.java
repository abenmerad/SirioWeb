package com.sirioitalia.api.service;

import com.sirioitalia.api.exception.ResourceException;
import com.sirioitalia.api.model.Item;
import com.sirioitalia.api.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Item getItemById(Long id) throws ResourceException {
        Item itemFound = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceException("404", "Item Not Found"));

        return itemFound;
    }

    @Override
    public Item createItem(Item item) throws ResourceException {
        try {
            return itemRepository.save(item);
        } catch (Exception e) {
            throw new ResourceException("CreateItemException", "An error has occurred: " + HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @Transactional
    public Item updateItem(Long itemId, Item item) throws ResourceException {
        try {

            Item updatedItem = itemRepository.findById(itemId)
                    .orElseThrow(() -> new ResourceException("404", "Item Not Found"));


            updatedItem.setLabel(item.getLabel() == null
                    ? updatedItem.getLabel()
                    : item.getLabel());

            updatedItem.setDescription(item.getDescription() == null
                    ? updatedItem.getDescription()
                    : item.getDescription());

            updatedItem.setPrice(Double.valueOf(item.getPrice()).equals(0.0)
                    ? updatedItem.getPrice()
                    : item.getPrice());

            updatedItem.setLength(Double.valueOf(item.getLength()).equals(0.0)
                    ? updatedItem.getPrice()
                    : item.getPrice());

            updatedItem.setWidth(Double.valueOf(item.getWidth()).equals(0.0)
                    ? updatedItem.getWidth()
                    : item.getWidth());

            updatedItem.setHeight(Double.valueOf(item.getHeight()).equals(0.0)
                    ? updatedItem.getHeight()
                    : item.getHeight());

            updatedItem.setWeight(Double.valueOf(item.getWeight()).equals(0.0)
                    ? updatedItem.getWeight()
                    : item.getWeight());

            updatedItem.setCategory(item.getCategory() == null
                    ? updatedItem.getCategory()
                    : item.getCategory());

            return itemRepository.save(updatedItem);

        } catch (Exception e) {
            throw new ResourceException(e.getMessage(), e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteItem(Long itemId) throws ResourceException {
        Item itemToDelete = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceException("404", "Item Not Found", HttpStatus.NOT_FOUND));

        itemRepository.delete(itemToDelete);
    }
}