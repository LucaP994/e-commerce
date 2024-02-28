package it.trinakria.ecommerce.model.dto;

import it.trinakria.ecommerce.model.entities.Address;
import it.trinakria.ecommerce.model.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto extends BaseDto{

    private Role role;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String profileImg;
    private String dateOfBirthStr;
    private List<Long> boughtProductsReference;
    private Address address;

}
