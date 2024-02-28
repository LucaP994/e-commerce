package it.trinakria.ecommerce.model.entities;

import it.trinakria.ecommerce.model.dto.Role;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


import java.util.*;
@Getter
@Setter
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"}, name = "unique_user_username")
        })
public class User extends BaseEntity{
    @Id
    @SequenceGenerator(name="users_seq",sequenceName="users_seq",initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="users_seq")
    @Column(name = "id")
    public Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "username", unique = true)
    public String username;
    @Column(name = "firstname")
    public String firstname;
    @Column(name = "lastname")
    public String lastname;

    public String password;

    @Column(name = "email", unique = true)
    public String email;

    @Column(name = "profileImg")
    public String profileImg;

    @Column(name = "dateOfBirth")
    public Date dateOfBirth;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_product",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") })
    private List<Product> boughtProducts = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public Address address;

}
