package it.trinakria.ecommerce.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Column(name = "createAt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updateAt", nullable = true)
    private Date updateAt;


    @PrePersist
    @PreUpdate
    protected void beforeSave() {
        if (createAt == null) {
            setCreateAt(new Date());
        } else {
            setUpdateAt(new Date());
        }
    }
}
