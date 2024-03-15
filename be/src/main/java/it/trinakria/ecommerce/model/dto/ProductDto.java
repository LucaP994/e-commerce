package it.trinakria.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ProductDto extends BaseDto{

    private String name;
    private String description;
    private String imgUrls;
    private Double price;
    private Double rating;
    private Long ratingCount;
    private List<Long> categories = new ArrayList<>();

}
