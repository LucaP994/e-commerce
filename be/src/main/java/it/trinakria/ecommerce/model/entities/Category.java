package it.trinakria.ecommerce.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category extends BaseEntity{
    @Id
    @SequenceGenerator(name="categories_seq",sequenceName="categories_seq",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="categories_seq")
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    public Category(String name){
        this.name = name;
    }
    public Category(){}
}
