package com.sirioitalia.api.service;

import com.sirioitalia.api.exception.ResourceException;
import com.sirioitalia.api.model.Item;

import java.util.List;

public interface ItemService {
    Item getItemById(Long id) throws ResourceException;

    List<Item> getItems();

    Item updateItem(Long id, Item item) throws ResourceException;

    Item createItem(Item item) throws ResourceException;

    void deleteItem(Long itemId) throws ResourceException;
}
