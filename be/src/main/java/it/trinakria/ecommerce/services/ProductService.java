package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.model.dto.ProductDto;
import it.trinakria.ecommerce.model.entities.Product;

import java.io.File;
import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product getById(Long id);
    List<Product> getByCategory(Long id);
    Product create(ProductDto product);
    Product update(ProductDto product, Long id);
    Product setCategory(Long productId,Long categoryId);
    Product removeCategory(Long productId,Long categoryId);
    Product setRating(Long id,Double rating);
    List<String> getImages(Long id);
    void delete(Long id);

}
