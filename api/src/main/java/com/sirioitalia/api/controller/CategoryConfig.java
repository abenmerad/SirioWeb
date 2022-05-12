package com.sirioitalia.api.controller;

import com.sirioitalia.api.model.CategoryEntity;
import com.sirioitalia.api.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategoryConfig {

//    @Bean
//    CommandLineRunner commandLineRunner(
//            CategoryRepository repository) {
//        return args -> {
//            CategoryEntity table = new CategoryEntity(
//                    "Table"
//            );
//            CategoryEntity bureau = new CategoryEntity(
//                    "Bureau"
//            );
//
//            repository.saveAll(
//                    List.of(table, bureau)
//            );
//        };
//    }
}
