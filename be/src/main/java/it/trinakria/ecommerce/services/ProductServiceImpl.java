package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.model.dto.CategoryDto;
import it.trinakria.ecommerce.model.dto.ProductDto;
import it.trinakria.ecommerce.model.entities.Category;
import it.trinakria.ecommerce.model.entities.Product;
import it.trinakria.ecommerce.model.entities.User;
import it.trinakria.ecommerce.repository.CategoryRepo;
import it.trinakria.ecommerce.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Product> getAll() {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public Product getById(Long id) {
        Product product = productRepo.findById(id).orElseGet(() -> {
            log.error("No product found for id {}", id);
            return new Product();
        });
        return product;
    }

    @Override
    public List<Product> getByCategory(Long id) {
        return productRepo.findItemsByCategoriesId(id);
    }

    @Override
    public Product create(ProductDto newProduct) {
        List<Category> categories = new ArrayList<>();
        for (Long c : newProduct.getCategories()) {
            Category newCat = categoryRepo.findById(c).get();
            categories.add(newCat);
        }
        Product product = new Product();
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setCategories(categories);
        product.setPrice(newProduct.getPrice());
        product.setImgUrls(newProduct.getImgUrls());
        product.setRatingCount(newProduct.getRatingCount());
        return productRepo.save(product);
    }

    @Override
    public Product update(ProductDto newProduct, Long id) {
        Product product = productRepo.findById(id).get();
        List<Category> categories = new ArrayList<>();
        for (Category c : product.getCategories()) {
            Category newCat = new Category();
            newCat.setName(c.getName());
            categories.add(newCat);
        }
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setCategories(categories);
        product.setPrice(newProduct.getPrice());
        product.setImgUrls(newProduct.getImgUrls());
        product.setRating(newProduct.getRating());
        product.setRatingCount(newProduct.getRatingCount());
        product.setUpdateAt(new Date());
        return productRepo.save(product);
    }

    @Override
    public Product setCategory(Long productId,Long categoryId) {
        Category newCat = categoryRepo.findById(categoryId).get();
        return productRepo.findById(productId)
                .map(product -> {
                    if(!product.getCategories().contains(newCat)) {
                        List<Category> categories = product.getCategories();
                        categories.add(newCat);
                        product.setCategories(categories);
                        product.setUpdateAt(new Date());
                    }
                    return productRepo.save(product);
                }).get();
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }
}
