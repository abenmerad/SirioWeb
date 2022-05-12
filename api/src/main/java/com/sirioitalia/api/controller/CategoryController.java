package com.sirioitalia.api.controller;

import com.sirioitalia.api.model.CategoryEntity;
import com.sirioitalia.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<CategoryEntity> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping
    public void registerNewCategory(@RequestBody CategoryEntity category) {
        categoryService.addNewCategory(category);
    }

    @DeleteMapping(path = "{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Integer categoryId){
        categoryService.deleteCategory(categoryId);
    }

    @PutMapping(path = "{categoryId}")
    public void updateCategory(
            @PathVariable("categoryId") Integer categoryId,
            @RequestParam(required = false) String label) {
        categoryService.updateCategory(categoryId, label);
    }
}
