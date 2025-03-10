package it.trinakria.ecommerce.repository;

import it.trinakria.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findItemsByCategoriesId(Long categoryId);
}
