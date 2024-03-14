package it.trinakria.ecommerce.services;

import it.trinakria.ecommerce.model.dto.ProductDto;
import it.trinakria.ecommerce.model.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product getById(Long id);
    List<Product> getByCategory(Long id);
    Product create(ProductDto product);
    Product update(ProductDto product, Long id);
    Product setCategory(Long categoryId,Long productId);
    void delete(Long id);

}
