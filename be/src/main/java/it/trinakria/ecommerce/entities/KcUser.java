package it.trinakria.ecommerce.entities;

import lombok.Data;

@Data
public class KcUser {
    private String userName;
    private String emailId;
    private String password;
    private String firstname;
    private String lastName;
}
