package com.sirioitalia.api.implementation;

import com.sirioitalia.api.exception.ResourceException;
import com.sirioitalia.api.model.Category;
import com.sirioitalia.api.repository.CategoryRepository;
import com.sirioitalia.api.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl () {
        super();
    }

    @Autowired
    public CategoryServiceImpl (CategoryRepository categoryRepository) {
        super();
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long categoryId) throws ResourceException {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceException("404", "Category not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Category> getCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category updateCategory(Long categoryId, Category categoryDetails) throws ResourceException {
        Category categoryToUpdate = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceException("404", "Category not found", HttpStatus.NOT_FOUND));

        categoryToUpdate.setLabel(categoryDetails.getLabel());
        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    @Transactional
    public Category createCategory(Category category) throws ResourceException {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) throws ResourceException {
        Category categoryToDelete = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceException("404", "Category not found", HttpStatus.NOT_FOUND));

        categoryRepository.delete(categoryToDelete);
    }
}
