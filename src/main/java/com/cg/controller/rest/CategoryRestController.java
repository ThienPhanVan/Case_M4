package com.cg.controller.rest;

import com.cg.model.Category;
import com.cg.model.dto.CategoryDTO;
import com.cg.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<Category> categoryDTOS = categoryService.findAll();

        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        Category category = categoryOptional.get();
        CategoryDTO categoryDTO = category.toCategoryDTO();

        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@RequestBody Category category) {
        Category newCategory = categoryService.save(category);

        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> doUpdate(@RequestBody CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryService.findById(categoryDTO.toCategory().getId());
        Category categoryUpdate = categoryService.save(categoryOptional.get());
        return new ResponseEntity<>(categoryUpdate, HttpStatus.OK);
    }

}
