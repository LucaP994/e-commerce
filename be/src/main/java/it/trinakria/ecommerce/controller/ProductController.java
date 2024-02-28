package it.trinakria.ecommerce.controller;
import it.trinakria.ecommerce.config.AppConfiguration;
import it.trinakria.ecommerce.entities.Category;
import it.trinakria.ecommerce.entities.Product;
import it.trinakria.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = AppConfiguration.API_BASE + "/products", produces = {"application/json"})
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    List<Product> all(){
        List<Product> products = productRepo.findAll();
        for(Product it: products){
            Set<Category> categories = it.getCategories();
            for(Category cat: categories){
                System.out.println(cat.getName());
            }
        }
        return productRepo.findAll();
    }

    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable Long id){
        return productRepo.findById(id).get();
    }

    @GetMapping("/products/category/{id}")
    List<Product> getByCategory(@PathVariable Long id){
        return productRepo.findItemsByCategoriesId(id);
    }

    @PostMapping("/product")
    Product newProduct(@RequestBody Product product){
        return productRepo.save(product);
    }

    @PutMapping("/product/{id}")
    Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        return productRepo.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setCategories(newProduct.getCategories());
                    product.setPrice(newProduct.getPrice());
                    product.setImgUrls(newProduct.getImgUrls());
                    product.setRatingCount(newProduct.getRatingCount());
                    return productRepo.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepo.save(newProduct);
                });
    }
    @PutMapping("/product/category/{id}")
    Product setCategory(@RequestBody Category category, @PathVariable Long id){
        return productRepo.findById(id)
                .map(product -> {
                    Set<Category> categories = product.getCategories();
                    categories.add(category);
                    product.setCategories(categories);
                    return productRepo.save(product);
                }).get();
    }
    @DeleteMapping("/product/{id}")
    void deleteProduct(@PathVariable Long id){
        productRepo.deleteById(id);
    }
}
