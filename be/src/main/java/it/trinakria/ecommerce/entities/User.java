package it.trinakria.ecommerce.entities;

import jakarta.persistence.*;

import lombok.Data;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @SequenceGenerator(name="seq",sequenceName="users_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "id")
    public Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    @Column(name = "email")
    public String email;

    @Column(name = "profileImg")
    public String profileImg;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_product",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") })
    private Set<Product> boughtProducts = new HashSet<>();

}
