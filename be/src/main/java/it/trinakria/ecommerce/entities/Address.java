package it.trinakria.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {

    @Id
    @SequenceGenerator(name="address_seq",sequenceName="address_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="address_seq")
    @Column(name = "id")
    public Long id;

    @Column(name = "region")
    public String region;

    @Column(name = "town")
    public String town;

    @Column(name = "street")
    public String street;

    @Column(name = "c_number")
    public String c_number;

    @Column(name = "zipCode")
    public String zipCode;

    @Column(name = "info")
    public String info;

}
