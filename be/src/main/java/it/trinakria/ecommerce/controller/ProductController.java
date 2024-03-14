package it.trinakria.ecommerce.controller;
import it.trinakria.ecommerce.config.AppConfiguration;
import it.trinakria.ecommerce.model.dto.ProductDto;
import it.trinakria.ecommerce.model.entities.Category;
import it.trinakria.ecommerce.model.entities.Product;
import it.trinakria.ecommerce.repository.ProductRepo;
import it.trinakria.ecommerce.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = AppConfiguration.API_BASE + "/products", produces = {"application/json"})
public class ProductController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductServiceImpl productService;
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<Product> all(){
        return productService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Product getProduct(@PathVariable Long id){
        return productService.getById(id);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    List<Product> getByCategory(@PathVariable Long id){
        return productService.getByCategory(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    Product newProduct(@RequestBody ProductDto product){
        return productService.create(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    Product updateProduct(@RequestBody ProductDto newProduct, @PathVariable Long id){
        return productService.update(newProduct, id);

    }
    @RequestMapping(value = "/{id}/category/{categoryId}", method = RequestMethod.PUT)
    Product setCategory(@PathVariable Long id,@PathVariable Long categoryId ){
        return productService.setCategory(id,categoryId);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }
}
