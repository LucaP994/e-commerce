package it.trinakria.ecommerce.model.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseDto {
    private Long id;
    private Date createAt;
    private Date updateAt;
}
