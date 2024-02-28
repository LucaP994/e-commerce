package it.trinakria.ecommerce.repository;

import it.trinakria.ecommerce.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
