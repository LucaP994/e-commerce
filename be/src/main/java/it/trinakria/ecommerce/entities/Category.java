package it.trinakria.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @SequenceGenerator(name="categories_seq",sequenceName="categories_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="categories_seq")
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

}
