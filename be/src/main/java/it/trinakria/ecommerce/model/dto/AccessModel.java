package it.trinakria.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessModel {
    private String client_id;
    private String username;
    private String password;
    private String grant_type;
}
