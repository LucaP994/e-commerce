package it.trinakria.ecommerce.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class KcUser {
    private String userName;
    private String emailId;
    private String password;
    private String firstname;
    private String lastName;
    private List<String> roles = new ArrayList<>();
}
