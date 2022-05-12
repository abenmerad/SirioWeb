package com.sirioitalia.api.service;

import com.sirioitalia.api.exception.ResourceException;
import com.sirioitalia.api.model.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long categoryId) throws ResourceException;

    List<Category> getCategories();

    Category updateCategory(Long categoryId, Category categoryDetails) throws ResourceException;

    Category createCategory(Category category) throws ResourceException;

    void deleteCategory(Long categoryId) throws ResourceException;
}
