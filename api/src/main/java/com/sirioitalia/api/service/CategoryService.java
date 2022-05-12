package com.sirioitalia.api.service;

import com.sirioitalia.api.model.CategoryEntity;
import com.sirioitalia.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<CategoryEntity> getCategories() {
        return categoryRepository.findAll();
    }

    public void addNewCategory(CategoryEntity category) {
        Optional<CategoryEntity> categoryOptional =  categoryRepository
                .findCategoryByLabel(category.getLabel());
        if(categoryOptional.isPresent()) {
            throw new IllegalStateException("article already exists");
        }
        categoryRepository.save(category);
    }

    public void deleteCategory(Integer categoryId) {
        boolean exists = categoryRepository.existsById(categoryId);
        if (!exists) {
            throw new IllegalStateException("category with id" + categoryId + "does not exists");
        }
        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    public void updateCategory(Integer categoryId, String label) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException(
                        "category with id" + categoryId + "does not exists"));

        if (label != null &&
                label.length() > 0 &&
                !Objects.equals(category.getLabel(), label)) {
            category.setLabel(label);
        }
    }
}
