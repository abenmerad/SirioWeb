package com.sirioitalia.api.service;

import com.sirioitalia.api.exception.ResourceException;
import com.sirioitalia.api.exception.ResourceNotFoundException;
import com.sirioitalia.api.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item getItemById(Long id) throws ResourceNotFoundException;

    List<Item> getItems();

    Item updateItem(Long id, Item item) throws ResourceException;

    Item createItem(Item item) throws ResourceException;

    void deleteItem(Long itemId) throws ResourceException;
}
