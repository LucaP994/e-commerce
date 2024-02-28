package it.trinakria.ecommerce.controller;

import it.trinakria.ecommerce.model.entities.Category;
import it.trinakria.ecommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/categories")
    List<Category> all(){
        return categoryRepo.findAll();
    }

    @GetMapping("/category/{id}")
    Category getItem(@PathVariable Long id){
        return categoryRepo.findById(id).get();
    }

    @PostMapping("/category")
    Category newItem(@RequestBody Category cateory){
        return categoryRepo.save(cateory);
    }

    @PutMapping("/category/{id}")
    Category updateItem(@RequestBody Category newCategory, @PathVariable Long id){
        return categoryRepo.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    return categoryRepo.save(category);
                })
                .orElseGet(() -> {
                    newCategory.setId(id);
                    return categoryRepo.save(newCategory);
                });
    }

    @DeleteMapping("/category/{id}")
    void deleteItem(@PathVariable Long id){
        categoryRepo.deleteById(id);
    }
}
