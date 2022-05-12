package com.sirioitalia.api.repository;

import com.sirioitalia.api.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository
        extends JpaRepository<CategoryEntity, Integer> {

    @Query("SELECT c FROM CategoryEntity c where c.label = ?1")
    Optional<CategoryEntity> findCategoryByLabel(String label);
}
