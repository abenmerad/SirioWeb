package com.sirioitalia.api.implementation;

import com.sirioitalia.api.exception.ResourceException;
import com.sirioitalia.api.model.Image;
import com.sirioitalia.api.model.Item;
import com.sirioitalia.api.repository.ImageRepository;
import com.sirioitalia.api.repository.ItemRepository;
import com.sirioitalia.api.service.ItemService;
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
    private ImageRepository imageRepository;

    public ItemServiceImpl() {
        super();
    }

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ImageRepository imageRepository) {
        super();
        this.itemRepository = itemRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Item> getItems() {
        return (List<Item>) itemRepository.findAll();
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
            Item createdItem = itemRepository.save(item);

            if (item.getImages() != null ) {
                for (Image image:
                     item.getImages()) {

                    image.setItem(item);
                }

                imageRepository.saveAll(item.getImages());
            }

            return createdItem;
        } catch (Exception e) {
            throw new ResourceException(e.getMessage(), e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
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