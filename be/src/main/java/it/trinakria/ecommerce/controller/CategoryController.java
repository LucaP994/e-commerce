package it.trinakria.ecommerce.controller;

import it.trinakria.ecommerce.config.AppConfiguration;
import it.trinakria.ecommerce.model.entities.Category;
import it.trinakria.ecommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = AppConfiguration.API_BASE + "/categories", produces = {"application/json"})
public class CategoryController {
    @Autowired
    private CategoryRepo categoryRepo;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<Category> all(){
        return categoryRepo.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Category getItem(@PathVariable Long id){
        return categoryRepo.findById(id).get();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    Category newItem(@RequestBody Category cateory){
        return categoryRepo.save(cateory);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deleteItem(@PathVariable Long id){
        categoryRepo.deleteById(id);
    }
}
