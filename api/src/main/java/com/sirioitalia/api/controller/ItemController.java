package com.sirioitalia.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.sirioitalia.api.exception.ResourceNotFoundException;
import com.sirioitalia.api.model.Item;
import com.sirioitalia.api.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") final Long itemId) throws ResourceNotFoundException {
        Optional<Item> item = itemService.getItemById(itemId);

        return new ResponseEntity<>(item.get(), HttpStatus.FOUND);
    }

    @PostMapping("/items/")
    @Transactional
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) throws ResourceNotFoundException{
        Item createdItem = itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping("/items")
    public ResponseEntity<Item> updateItem(@Valid @RequestBody Item item)  {
        return null;
    }
}
