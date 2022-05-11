package com.sirioitalia.api.service;

import com.sirioitalia.api.exception.ResourceNotFoundException;
import com.sirioitalia.api.model.Item;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ItemService {
    Optional<Item> getItemById(Long id) throws ResourceNotFoundException;

    List<Item> getItems();

    Item updateItem(Item item) throws ResourceNotFoundException;

    Item createItem(Item item);
}
